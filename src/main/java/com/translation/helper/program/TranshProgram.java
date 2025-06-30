package com.translation.helper.program;

import com.translation.helper.enums.CommandEnum;
import com.translation.helper.program.base.AbstractProgram;

public class TranshProgram extends AbstractProgram {

    public TranshProgram() {
        super(CommandEnum.TRANSH);
    }

    @Override
    public String getHelp() {
        return "";
    }
}
