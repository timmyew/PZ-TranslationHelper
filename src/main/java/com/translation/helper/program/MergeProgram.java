package com.translation.helper.program;

import com.translation.helper.constant.HelpConstants;
import com.translation.helper.enums.OptionEnum;
import com.translation.helper.model.dto.MergeModel;
import com.translation.helper.model.dto.MergeOptions;
import com.translation.helper.program.base.AbstractProgram;
import com.translation.helper.enums.CommandEnum;
import com.translation.helper.service.MergeService;
import com.translation.helper.util.CommandLineInterfaceUtils;
import com.translation.helper.util.OptionUtils;
import java.util.List;

public class MergeProgram extends AbstractProgram {
    private final String LINE_SEPARATOR = System.lineSeparator();
    private final String MERGED_SUCCESSFUL = "Files successfully merged.";
    private final String META_DATA_NEW_ATTRIBUTES = LINE_SEPARATOR.concat("New attributes that added to the merged file: ");
    private final String META_DATA_MERGED_MISSING_ATTRIBUTES = LINE_SEPARATOR.concat("Attributes of the new file that missing in the merged file:");
    private final String META_DATA_NEW_MISSING_ATTRIBUTES = LINE_SEPARATOR.concat("Attributes of the merged file that missing in the new file:");
    private final String TWO_SPACES = "  ";

    private final MergeService mergeService = new MergeService();

    public MergeProgram() {
        super(CommandEnum.MERGE);
    }

    @Override
    public void execute(List<String> args) {
        MergeModel mergeModel = mergeService.merge(
                MergeOptions.builder()
                        .newFilePath(getRequiredOptionValue(args, OptionEnum.NEW_FILE))
                        .oldFilePath(getRequiredOptionValue(args, OptionEnum.OLD_FILE))
                        .mergedOutputFilePath(getRequiredOptionValue(args, OptionEnum.MERGED_OUTPUT_FILE))
                        .doMetaData(OptionUtils.isOptionExistingFile(args, OptionEnum.META_DATA))
                        .doSort(OptionUtils.isOptionExistingFile(args, OptionEnum.SORT))
                        .build()
        );

        System.out.println(MERGED_SUCCESSFUL);

        if (OptionUtils.isOptionExistingFile(args, OptionEnum.META_DATA)) {
            System.out.println(META_DATA_NEW_ATTRIBUTES.concat(
                    CommandLineInterfaceUtils.getListAsString(mergeModel.getNewAttributesList(), TWO_SPACES)));
            System.out.println(META_DATA_MERGED_MISSING_ATTRIBUTES.concat(
                    CommandLineInterfaceUtils.getListAsString(mergeModel.getMergeFileMissingAttributes(), TWO_SPACES)));
            System.out.println(META_DATA_NEW_MISSING_ATTRIBUTES.concat(
                    CommandLineInterfaceUtils.getListAsString(mergeModel.getNewFileMissingAttributes(), TWO_SPACES)));
        }
    }

    @Override
    public String getHelp() {
        return HelpConstants.MERGE_DESCRIPTION;
    }

    private String getRequiredOptionValue(List<String> args, OptionEnum option) {
        OptionUtils.checkRequiredOption(args, option);
        return OptionUtils.tryGetOptionValue(
                OptionUtils.getOptionString(args, option)
        );
    }
}
