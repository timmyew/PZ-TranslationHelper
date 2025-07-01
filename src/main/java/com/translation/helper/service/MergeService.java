package com.translation.helper.service;

import com.translation.helper.algorithm.comparator.AlphapetComparator;
import com.translation.helper.model.dto.MergeModel;
import com.translation.helper.model.dto.MergeOptions;
import com.translation.helper.util.FileUtils;
import com.translation.helper.validator.MergeOptionsValidator;

import java.util.ArrayList;

//Todo: Do better time complexity with hashmaps
public class MergeService {
    private final MergeOptionsValidator validator = MergeOptionsValidator.getInstance();

    public MergeService() {
    }

    public MergeModel merge(MergeOptions options) {
        validator.validate(options);

        ArrayList<String> oldFile = FileUtils.readFile(options.getOldFilePath());
        ArrayList<String> newFile = FileUtils.readFile(options.getNewFilePath());
        ArrayList<String> mergedFile = mergeLists(oldFile, newFile);

        if (options.isDoSort())
            sortAttributesFaster(mergedFile);

        if (!options.getMergedOutputFilePath().isBlank())
            FileUtils.writeFile(mergedFile, options.getMergedOutputFilePath());

        return MergeModel.builder()
                .oldFile(oldFile)
                .newFile(newFile)
                .mergedFile(mergedFile)
                .mergeFileMissingAttributes(options.isDoMetaData() ? getNotIncludeAttributArray(mergedFile, newFile) : null)
                .newFileMissingAttributes(options.isDoMetaData() ? getNotIncludeAttributArray(newFile, mergedFile) : null)
                .newAttributesList(options.isDoMetaData() ? getNewAttributes(oldFile, newFile) : null)
                .build();
    }

    //O(n*m) Time complexity
    private ArrayList<String> mergeLists(ArrayList<String> oldFile, ArrayList<String> newFile) {
        ArrayList<String> mergedFile = new ArrayList<>(oldFile.size() + newFile.size());
        mergedFile.addAll(oldFile);

        //Lua Array starting new file
        int newPosBegin = getStartPos(newFile);
        int newPosEnd = getEndPos(newFile);

        //Lua Array starting old file
        int oldPosBegin = getStartPos(oldFile);
        int oldPosEnd = getEndPos(oldFile);

        String newAttribute = "";
        String oldAttribute = "";

        for (int i = newPosBegin; i <= newPosEnd; i++) {
            newAttribute = getAttribute(newFile.get(i));
            for (int j = oldPosBegin; j <= oldPosEnd; j++) {
                oldAttribute = getAttribute(oldFile.get(j));

                if (newAttribute.equals(oldAttribute)) {
                    break;
                }
                //Attribute is not existing
                else if (j == oldPosEnd) {
                    mergedFile.add(mergedFile.size() - 1, newFile.get(i));
                }
            }
        }

        mergedFile.replaceAll(String::trim);
        return mergedFile;
    }

    //O(n*m) Time complexity
    private ArrayList<String> getNewAttributes(ArrayList<String> oldFile, ArrayList<String> newFile) {
        ArrayList<String> newAttributes = new ArrayList<>();

        //Lua Array starting new file
        int newPosBegin = getStartPos(newFile);
        int newPosEnd = getEndPos(newFile);

        //Lua Array starting old file
        int oldPosBegin = getStartPos(oldFile);
        int oldPosEnd = getEndPos(oldFile);

        String newAttribute = "";
        String oldAttribute = "";

        for (int i = newPosBegin; i < newPosEnd; i++) {
            newAttribute = getAttribute(newFile.get(i));
            for (int j = oldPosBegin; j < oldPosEnd; j++) {
                oldAttribute = getAttribute(oldFile.get(j));

                if (newAttribute.equals(oldAttribute)) {
                    break;
                }
                if (!newAttribute.isBlank() &&
                        !oldAttribute.isBlank() &&
                        j == oldPosEnd - 1) {
                    newAttributes.add(newAttribute);
                }
            }
        }

        return newAttributes;
    }

    //O(n²) + O(n log n) + O(m * n) Time complexity
    private void sortAttributesFaster(ArrayList<String> attributes) {
        ArrayList<Integer> lineBreakIndexList = new ArrayList<>();

        //Remove whitespaces for sorting and save it in emptyAndSpacesMap
        for (int i = attributes.size() - 1; i > 0; i--) {
            if (attributes.get(i).trim().isBlank()) {
                lineBreakIndexList.add(i);
                attributes.remove(i);
            }
        }

        attributes.subList(getStartPos(attributes), getEndPos(attributes) + 1).sort(new AlphapetComparator());

        for (int i = lineBreakIndexList.size() - 1; i >= 0; i--) {
            attributes.add(lineBreakIndexList.get(i), "");
        }
    }

    //O(n*m)
    private int getStartPos(ArrayList<String> file) {
        int startPos = 0;
        for (int i = 0; i < file.size(); i++) {
            if (file.get(i).contains("{")) {
                startPos = i + 1;
                break;
            }
        }
        return startPos;
    }

    //O(n*m)
    private int getEndPos(ArrayList<String> file) {
        int endPos = 0;
        for (int i = file.size() - 1; i > 0; i--) {
            if (file.get(i).contains("}")) {
                endPos = i - 1;
                break;
            }
        }
        return endPos;
    }

    private String getAttribute(String line) {
        String result = line;

        try {
            if (!line.isBlank()) {
                result = line.substring(0, line.indexOf("=") - 1);
            }
        } catch (Exception e) {
            System.out.println(line);
        }

        return result.trim();
    }

    //O(n*m)
    private ArrayList<String> getNotIncludeAttributArray(ArrayList<String> validateArray, ArrayList<String> compareArray) {
        ArrayList<String> validateArrayNotIncludeAttribute = new ArrayList<>(Math.max(validateArray.size(), compareArray.size()));
        boolean isNotFound = false;
        String validateAttribute = "";
        String compareAttribute = "";

        int comparePosBegin = getStartPos(compareArray);
        int comparePosEnd = getEndPos(compareArray);

        int validateArrayPosBegin = getStartPos(validateArray);
        int validateArrayPosEnd = getEndPos(validateArray);

        for (int i = comparePosBegin; i < comparePosEnd; i++) {
            compareAttribute = getAttribute(compareArray.get(i));
            isNotFound = false;
            for (int j = validateArrayPosBegin; j < validateArrayPosEnd; j++) {
                validateAttribute = getAttribute(validateArray.get(j));

                if (validateAttribute.equals(compareAttribute) || compareAttribute.isBlank()) {
                    break;
                } else if (j == validateArrayPosEnd - 1) {
                    isNotFound = true;
                    break;
                }
            }
            if (isNotFound) {
                validateArrayNotIncludeAttribute.add(compareAttribute);
            }
        }
        return validateArrayNotIncludeAttribute;
    }
}
