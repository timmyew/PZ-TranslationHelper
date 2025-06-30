package com.translation.helper.program.base;

import com.translation.helper.constant.ExceptionConstants;
import com.translation.helper.core.ProgramLoader;
import com.translation.helper.enums.CommandEnum;
import com.translation.helper.enums.OptionEnum;
import com.translation.helper.exception.*;
import com.translation.helper.util.OptionUtils;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractProgram implements IProgram {
    private final Map<String, IProgram> programs = new HashMap<>();
    @Getter
    protected int depth = 0;
    protected final CommandEnum command;

    protected AbstractProgram(CommandEnum command) {
        this.command = command;
        ProgramLoader.getInstance().getPrograms().add(this);
    }

    @Override
    public CommandEnum getCommand() {
        return command;
    }

    public void execute(List<String> args){
        if (depth != 0)
            validate(args, depth, command.toString());

        if (programs.isEmpty())
            throw new NoExecuteImplementedException(String.format(ExceptionConstants.EXECUTE_NOT_IMPLEMENTED, command.toString()));

        int nextParamIndex = depth;

        if (programs.containsKey(args.get(nextParamIndex))) {
            programs.get(args.get(nextParamIndex)).execute(args);
        }
        else {
            throw new ProgramRegisterFailedException(String.format(ExceptionConstants.INVALID_PARAMETER, args.get(nextParamIndex),
                    String.join(" ", args)));
        }
    }

    public void registerProgram(AbstractProgram program) throws ProgramRegisterFailedException {
        if (program.getClass() == this.getClass()) {
            throw new CircularRegisterProgramException(String.format(ExceptionConstants.CIRCULAR_REGISTERED_EXCEPTION,
                    this.getClass().getName()));
        }

        program.incrementDepth(depth);

        if (!programs.containsKey(program.getCommand().toString()))
            programs.put(program.getCommand().toString(), program);
        else
            throw new ProgramRegisterFailedException(String.format(ExceptionConstants.PROGRAM_REGISTER_FAILED, program.getCommand()));
    }

    protected static void validate(List<String> args, int depth, String command) {
        if (!(args.size() >= depth + 1 && args.get(depth).equals(command))){
            throw new InvalidCommandParameterException(String.format(ExceptionConstants.INVALID_PARAMETER, command,
                    String.join(" ", args))
            );
        }
    }

    protected void incrementDepth(int parentDepth){
        depth = parentDepth + 1;
    }
}
