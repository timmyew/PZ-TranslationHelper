package com.translation.helper.enums;

public enum CommandEnum {
    TRANSH("transh"),
    MERGE("merge"),
    HELP("help");

    private final String command;
    CommandEnum(String command) {
        this.command = command;
    }

    public String toString() {
        return this.command;
    }
}
