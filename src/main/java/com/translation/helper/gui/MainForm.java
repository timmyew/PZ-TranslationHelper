package com.translation.helper.gui;

import com.translation.helper.model.dto.VersionModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class MainForm extends AbstractForm {
    private final OptionsForm optionsForm = new OptionsForm();
    private final JEditorPane leftEditorPane = new JEditorPane();
    private final JEditorPane rightEditorPane = new JEditorPane();
    private final JTextField newFileTextField = new JTextField();
    private final JTextField oldFileTextField = new JTextField();

    public MainForm() {
        super(String.format("Translation Helper version %s.%s.%s", VersionModel.major, VersionModel.minor, VersionModel.patch), true);
    }

    @Override
    protected void buildForm() {
        frame.setSize(Math.round(screenSize.getSize().width * 0.60f), Math.round(screenSize.getSize().height * 0.60f));
        frame.setResizable(false);

        buildMenuBar();
        buildMergePanel();
    }

    private void buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        buildFileMenu(menuBar);
        frame.setJMenuBar(menuBar);
    }

    private void buildFileMenu(JMenuBar menuBar) {
        JMenu fileMenu = new JMenu("File");
        JMenuItem info = new JMenuItem("Info");
        JMenuItem options = new JMenuItem("Options");
        JMenuItem exit = new JMenuItem("Exit");

        options.addActionListener(actionEvent -> {
            optionsForm.show();
        });

        exit.addActionListener(actionEvent -> {
            frame.dispose();
        });

        fileMenu.add(info);
        fileMenu.add(options);
        fileMenu.add(exit);

        menuBar.add(fileMenu);
    }

    private void buildMergePanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));

        JPanel editorPanel = new JPanel();
        editorPanel.setLayout(new BoxLayout(editorPanel, BoxLayout.LINE_AXIS));

        JPanel mergePanelLeft = new JPanel();
        mergePanelLeft.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        mergePanelLeft.setBackground(Color.CYAN);
        mergePanelLeft.setLayout(new BoxLayout(mergePanelLeft, BoxLayout.PAGE_AXIS));
        mergePanelLeft.add(leftEditorPane);

        JPanel mergePanelRight = new JPanel();
        mergePanelRight.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        mergePanelRight.setBackground(Color.RED);
        mergePanelRight.setLayout(new BoxLayout(mergePanelRight, BoxLayout.PAGE_AXIS));
        mergePanelRight.add(rightEditorPane);

        editorPanel.add(mergePanelLeft);
        editorPanel.add(mergePanelRight);

        newFileTextField.setSize(100, 24);
        oldFileTextField.setSize(100, 24);

        controlPanel.add(newFileTextField);
        controlPanel.add(oldFileTextField);

        mainPanel.add(controlPanel);
        mainPanel.add(editorPanel);

        frame.add(mainPanel);
    }
}
