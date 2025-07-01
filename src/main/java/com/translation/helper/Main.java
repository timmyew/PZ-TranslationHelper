package com.translation.helper;

import com.translation.helper.core.ProgramLoader;
import com.translation.helper.gui.MainForm;

public class Main{
    public static void main(String[] args) {
        //GUI Mode
        if (args.length == 0) {
            MainForm mainForm = new MainForm();
            mainForm.show();
        }
        //CLI Mode
        else{
            ProgramLoader programLoader = ProgramLoader.getInstance();
            programLoader.init();
            programLoader.execute(args);
        }
    }
}