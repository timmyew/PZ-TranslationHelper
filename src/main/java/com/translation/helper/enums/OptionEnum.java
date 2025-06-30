package com.translation.helper.enums;

public enum OptionEnum {
    OLD_FILE("old-file-path"),
    NEW_FILE("new-file-path"),
    SORT("sort"),
    MERGED_OUTPUT_FILE("output-file-path"),
    META_DATA("meta-data");

    private final String option;

    OptionEnum(String option) {
        this.option = option;
    }

    public String toString() {
        return this.option;
    }
}
