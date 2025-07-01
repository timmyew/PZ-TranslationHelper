package com.translation.helper.gui;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractForm {
    private boolean isBuild = false;
    protected final JFrame frame;
    protected final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    protected AbstractForm(String title, boolean isMainForm) {
        frame = new JFrame(title);
        frame.setSize(screenSize.width / 5, screenSize.height / 5);

        if (isMainForm)
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        else
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        center();
    }

    protected abstract void buildForm();

    public void show() {
        if (!isBuild){
            buildForm();
            isBuild = true;
        }

        center();
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }

    private void center(){
        frame.setLocation((screenSize.width / 2) - (frame.getSize().width / 2), (screenSize.height / 2) - (frame.getSize().height / 2));
    }
}
