package com.translation.helper.core;

import com.translation.helper.model.dto.VersionModel;
import com.translation.helper.program.HelpProgram;
import com.translation.helper.program.MergeProgram;
import com.translation.helper.program.TranshProgram;
import com.translation.helper.program.base.AbstractProgram;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ProgramLoader{
    @Getter
    private final List<AbstractProgram> programs = new ArrayList<>();
    private TranshProgram rootProgram;
    private static ProgramLoader instance;

    private ProgramLoader(){
    };

    public static ProgramLoader getInstance(){
        instance = instance == null ? new ProgramLoader() : instance;
        return instance;
    }

    public void init(){
        rootProgram = new TranshProgram();
        rootProgram.registerProgram(new HelpProgram());
        rootProgram.registerProgram(new MergeProgram());
    }

    public void execute(String[] args){
        ArrayList<String> argsList = new ArrayList<>(List.of(args));
        rootProgram.execute(argsList);
    }

}
