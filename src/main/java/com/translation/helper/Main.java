package com.translation.helper;

import com.translation.helper.core.ProgramLoader;

public class Main {
    public static void main(String[] args) {
        ProgramLoader programLoader = ProgramLoader.getInstance();
        programLoader.init();
        programLoader.execute(args);
    }
}