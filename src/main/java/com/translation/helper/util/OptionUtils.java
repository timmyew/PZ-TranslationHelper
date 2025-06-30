package com.translation.helper.util;

import com.translation.helper.constant.ExceptionConstants;
import com.translation.helper.enums.OptionEnum;
import com.translation.helper.exception.OptionMissingException;
import com.translation.helper.exception.OptionStringMissingException;

import java.util.List;

public class OptionUtils {
    private OptionUtils() {}

    public static void checkRequiredOption(List<String> args, OptionEnum option){
        if (!isOptionExistingFile(args, option))
            throw new OptionMissingException(String.format(ExceptionConstants.OPTION_IS_MISSING_EXCEPTION, option));
    }

    public static boolean isOptionExistingFile(List<String> args, OptionEnum option){
        int posBegin = 0;
        int posEnd = option.toString().length();
        boolean result = false;

        for (String argument : args){
            if (argument.length() >= posEnd && argument.substring(posBegin, posEnd).equals(option.toString())){
                result = true;
                break;
            }
        }

        return result;
    }

    public static String getOptionString(List<String> args, OptionEnum option){
        int posBegin = 0;
        int posEnd = option.toString().length();
        String optionString = "";

        for (String argument : args){
            if (argument.length() >= posEnd && argument.substring(posBegin, posEnd).equals(option.toString())){
                optionString = argument;
                break;
            }
        }

        return optionString;
    }

    public static String tryGetOptionValue(String optionString){
        int posBegin = 0;;
        int posEnd = optionString.length();

        for (int i = 0; i < optionString.length(); i++) {
            if (optionString.charAt(i) == '=') {
                posBegin = i + 1;
                break;
            }
        }

        if (posBegin == posEnd || posBegin == 0)
            throw new OptionStringMissingException(String.format(ExceptionConstants.OPTION_VALUE_IS_MISSING_EXCEPTION, optionString));

        return optionString.substring(posBegin, posEnd);
    }
}
