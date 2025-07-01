package com.translation.helper.util;

import com.translation.helper.constant.ExceptionConstants;
import com.translation.helper.exception.FileIOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileUtils {
    private FileUtils() {}

    public static ArrayList<String> readFile(String filePath) {
        ArrayList<String> fileRows = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            fileRows = new ArrayList<>(reader.lines().toList());
        }
        catch (Exception exception) {
            throw new FileIOException(String.format(ExceptionConstants.FILE_IO_READ_EXCEPTION, filePath));
        }

        return fileRows;
    }

    public static void writeFile(ArrayList<String> fileRows, String filePath) {
        StringBuilder buffer = new StringBuilder();
        String lineSeparator = System.lineSeparator();

        fileRows.forEach(row -> {
            buffer.append(lineSeparator).append(row);
        });

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(buffer.toString());
            writer.close();
        }
        catch (Exception exception) {
            throw new FileIOException(String.format(ExceptionConstants.FILE_IO_WRITE_EXCEPTION, filePath));
        }
    }
}
