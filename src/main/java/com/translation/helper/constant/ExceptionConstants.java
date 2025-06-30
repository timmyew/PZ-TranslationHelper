package com.translation.helper.constant;

public class ExceptionConstants {
    private ExceptionConstants(){}
    public static final String PROGRAM_REGISTER_FAILED = "Failed to register program '%s' because it is already registered!";
    public static final String INVALID_PARAMETER = "Failed to parse parameter '%s' in command '%s'";
    public static final String EXECUTE_NOT_IMPLEMENTED = "Failed to execute command '%s' because it is not implemented!";
    public static final String CIRCULAR_REGISTERED_EXCEPTION = "Circular program registered exception at '%s'.";
    public static final String OPTION_IS_MISSING_EXCEPTION = "Option [%s] is missing.";
    public static final String OPTION_VALUE_IS_MISSING_EXCEPTION = "Can't parse option \"%s\", because the text value is missing.";
    public static final String FILE_IO_EXCEPTION = "Failed to read file with path: '%s'";
    public static final String MERGE_OPTIONS_NEW_FILE_INVALID_PATH = "Exception by validate Merge-Options: New file path can't be empty.";
    public static final String MERGE_OPTIONS_OLD_FILE_INVALID_PATH = "Exception by validate Merge-Options: Old file path can't be empty.";
}
