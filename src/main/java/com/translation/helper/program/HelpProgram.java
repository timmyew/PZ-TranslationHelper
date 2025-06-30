package com.translation.helper.program;

import com.translation.helper.constant.HelpConstants;
import com.translation.helper.core.ProgramLoader;
import com.translation.helper.program.base.AbstractProgram;
import com.translation.helper.enums.CommandEnum;

import java.util.List;

public class HelpProgram extends AbstractProgram{

    public HelpProgram() {
        super(CommandEnum.HELP);
    }

    @Override
    public void execute(List<String> args) {
        List<AbstractProgram> programs = ProgramLoader.getInstance().getPrograms();
        System.out.println(getHelp());

        for (AbstractProgram program : programs) {
            if (!program.getCommand().equals(CommandEnum.HELP) && !program.getCommand().equals(CommandEnum.TRANSH)) {
                System.out.println(program.getHelp());
            }
        }
    }

    @Override
    public String getHelp() {
        return HelpConstants.HELP_DESCRIPTION;
    }
}
