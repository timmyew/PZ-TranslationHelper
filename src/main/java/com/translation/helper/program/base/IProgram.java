package com.translation.helper.program.base;

import com.translation.helper.enums.CommandEnum;

import java.util.List;

public interface IProgram {
    void execute(List<String> args);
    CommandEnum getCommand();
    String getHelp();
}
