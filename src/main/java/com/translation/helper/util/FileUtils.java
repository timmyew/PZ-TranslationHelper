package com.translation.helper.util;

import com.translation.helper.constant.ExceptionConstants;
import com.translation.helper.exception.FileIOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private FileUtils() {}

    public static ArrayList<String> readFile(String filePath) {
        ArrayList<String> fileRows = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            fileRows = new ArrayList<>(reader.lines().toList());
        }
        catch (Exception exception) {
            throw new FileIOException(String.format(ExceptionConstants.FILE_IO_EXCEPTION, filePath));
        }

        return fileRows;
    }
}
