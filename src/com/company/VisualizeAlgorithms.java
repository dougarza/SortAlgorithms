package com.company;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.FlowLayout;
import java.util.HashMap;

public class VisualizeAlgorithms extends JFrame {

    public static JPanel container = new JPanel(new FlowLayout());
    public static LayoutManager card = new CardLayout();
    public static JPanel subcontainer = new JPanel(card);
    public static ImagePanel imagePanel0 = new ImagePanel();
    public static ImagePanel imagePanel1 = new ImagePanel();
    public static ImagePanel imagePanel2 = new ImagePanel();
    public static ImagePanel imagePanel4 = new ImagePanel();
    public static JButton shuffleButton;
    public static JButton runButton;
    public static ButtonPanel buttonPanel;
    public static JButton skipButton;
    public static JButton resetButton;
    public static int methodCounter = 0;
    public static HashMap<Integer, String> methodMap = new HashMap<>();


    public VisualizeAlgorithms() {
        super("Sorting Algorithms Visualized");

        container.setBackground(new Color(50, 50, 50));

        buttonPanel = new ButtonPanel();

        buttonPanel.setBackground(Color.DARK_GRAY);

        imagePanel0.setImagePanel(0);
        imagePanel1.setImagePanel(1);
        imagePanel2.setImagePanel(2);
        imagePanel4.setImagePanel(4);

        subcontainer.add("zero", imagePanel0);
        subcontainer.add("one", imagePanel1);
        subcontainer.add("two", imagePanel2);
        subcontainer.add("four", imagePanel4);

        container.add(buttonPanel);
        container.add(subcontainer);

        // button instances
        shuffleButton = buttonPanel.shuffleButton;
        runButton = buttonPanel.runButton;
        skipButton = buttonPanel.skipButton;
        resetButton = buttonPanel.resetButton;
        JRadioButton singleAlgo = buttonPanel.singleAlgo;
        JRadioButton compareTwo = buttonPanel.compareTwo;
        JRadioButton compareFour = buttonPanel.compareFour;

        shuffleButton.setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
        runButton.setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
        skipButton.setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
        resetButton.setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
        shuffleButton.setEnabled(false);
        runButton.setEnabled(false);
        skipButton.setEnabled(false);
        resetButton.setEnabled(false);

        // call action event handler
        ActionListener actionListener = new ActionEventHandler(subcontainer, imagePanel1, imagePanel2, imagePanel4, runButton, shuffleButton, singleAlgo, compareTwo, compareFour, resetButton, skipButton);

        // action listeners to buttons
        singleAlgo.addActionListener(actionListener);
        compareTwo.addActionListener(actionListener);
        compareFour.addActionListener(actionListener);
        shuffleButton.addActionListener(actionListener);
        runButton.addActionListener(actionListener);
        skipButton.addActionListener(actionListener);
        resetButton.addActionListener(actionListener);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setContentPane(container);

        validate();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // run main
    public static void main(String[] args) {
        new VisualizeAlgorithms();
    }
}
