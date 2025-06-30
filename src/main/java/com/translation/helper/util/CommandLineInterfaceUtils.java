package com.translation.helper.util;

import java.util.List;

public class CommandLineInterfaceUtils {
    private CommandLineInterfaceUtils(){}

    public static String getListAsString(List<String> list){
        return getListAsString(list, "");
    }

    public static String getListAsString(List<String> list, String prefix){
        StringBuilder stringBuilder = new StringBuilder();
        String lineSeparator = System.lineSeparator();

        for(String element : list){
            stringBuilder.append(lineSeparator).append(prefix).append(element);
        }

        return stringBuilder.toString();
    }
}
