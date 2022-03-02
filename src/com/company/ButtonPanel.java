package com.company;

import javax.swing.*;
import java.awt.*;

// inherits JPanel class
public class ButtonPanel extends JPanel {

    public JButton shuffleButton;
    public JButton runButton;
    public JButton skipButton;
    public JButton resetButton;
    public static JRadioButton singleAlgo;
    public static JRadioButton compareTwo;
    public static JRadioButton compareFour;
    public static Color MEDIUM_GRAY;
    public static Color DARK_MEDIUM_GRAY;
    public static Color TRANSLUCENT_WHITE;

    public ButtonPanel() {
        // set flowlayout
        super(new FlowLayout(FlowLayout.LEFT));

        // background, layout, sizing
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(Parameters.WIDTH / 8, Parameters.HEIGHT));
        setLayout(new GridLayout(4, 1, 0, 30));

        // Containers for separate button groups
        JPanel radioContainer = new JPanel(new GridLayout(4, 1));
        JPanel buttonContainer = new JPanel(new GridLayout(3, 1));
        JPanel blankContainer = new JPanel(new FlowLayout());
        JPanel resetContainer = new JPanel(new GridLayout(3, 1));

        // Preset fonts
        Font radioFont = new Font("Helvetica Neue", Font.PLAIN, 17);
        Font buttonFont = new Font("Helvetica Neue", Font.PLAIN, 24);
        Font labelFont = new Font("Helvetica Neue", Font.BOLD, 20);

        // custom colors for buttons and labels
        MEDIUM_GRAY = new Color(0.5f, 0.5f, 0.5f, 1f);
        DARK_MEDIUM_GRAY = new Color(0.3f, 0.3f, 0.3f, 1f);
        TRANSLUCENT_WHITE = new Color(1f, 1f,1f, 0.5f);

        // Clickable buttons
        this.shuffleButton = new JButton("Shuffle");
        this.runButton = new JButton("Run");

        this.shuffleButton.setForeground(TRANSLUCENT_WHITE);
        this.shuffleButton.setFont(buttonFont);
        this.shuffleButton.setBackground(DARK_MEDIUM_GRAY);
        this.runButton.setForeground(TRANSLUCENT_WHITE);
        this.runButton.setFont(buttonFont);
        this.runButton.setBackground(DARK_MEDIUM_GRAY);
        this.runButton.setSize(shuffleButton.getMaximumSize());

        this.skipButton = new JButton("Skip");
        this.resetButton = new JButton("Reset");

        this.skipButton.setForeground(TRANSLUCENT_WHITE);
        this.skipButton.setFont(buttonFont);
        this.skipButton.setBackground(DARK_MEDIUM_GRAY);
        this.skipButton.setSize(shuffleButton.getMaximumSize());
        this.resetButton.setForeground(TRANSLUCENT_WHITE);
        this.resetButton.setFont(buttonFont);
        this.resetButton.setBackground(DARK_MEDIUM_GRAY);
        this.resetButton.setSize(shuffleButton.getMaximumSize());

        // Radio buttons
        singleAlgo = new JRadioButton("Single");
        compareTwo = new JRadioButton("Compare Two");
        compareFour = new JRadioButton("Compare Four");

        singleAlgo.setBackground(Color.DARK_GRAY);
        compareTwo.setBackground(Color.DARK_GRAY);
        compareFour.setBackground(Color.DARK_GRAY);
        singleAlgo.setForeground(Color.WHITE);
        compareTwo.setForeground(Color.WHITE);
        compareFour.setForeground(Color.WHITE);
        singleAlgo.setFont(radioFont);
        compareTwo.setFont(radioFont);
        compareFour.setFont(radioFont);

        // Radio button section
        JLabel radioLabel = new JLabel("Run Method", SwingConstants.CENTER);
        radioLabel.setForeground(Color.WHITE);
        radioLabel.setFont(labelFont);
        radioContainer.setBackground(Color.DARK_GRAY);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(singleAlgo);
        buttonGroup.add(compareTwo);
        buttonGroup.add(compareFour);

        // Clickable button section
        JLabel buttonLabel = new JLabel("Execution", SwingConstants.CENTER);
        buttonLabel.setForeground(Color.WHITE);
        buttonLabel.setFont(labelFont);
        buttonContainer.setBackground(Color.DARK_GRAY);

        blankContainer.setBackground(Color.DARK_GRAY);
        resetContainer.setBackground(Color.DARK_GRAY);

        // Add radio buttons
        radioContainer.add(radioLabel);
        radioContainer.add(singleAlgo);
        radioContainer.add(compareTwo);
        radioContainer.add(compareFour);

        // Add clickable buttons
        buttonContainer.add(buttonLabel);
        buttonContainer.add(this.shuffleButton);
        buttonContainer.add(this.runButton);

        JLabel resetLabel = new JLabel("", SwingConstants.CENTER);
        buttonLabel.setForeground(Color.WHITE);
        buttonLabel.setFont(labelFont);
        buttonContainer.setBackground(Color.DARK_GRAY);

        resetContainer.add(resetLabel);
        resetContainer.add(this.skipButton);
        resetContainer.add(this.resetButton);

        // Add both containers
        add(radioContainer);
        add(buttonContainer);
        add(blankContainer);
        add(resetContainer);

    }

    // getters
    public JButton getShuffleButton() {
        return this.shuffleButton;
    }

    public JButton getRunButton() {
        return this.runButton;
    }

    public JButton getResetButton() {
        return this.resetButton;
    }

    public JButton getSkipButton() {
        return this.skipButton;
    }

    public static JRadioButton[] getRadioButtonArray() {
        return new JRadioButton[] {singleAlgo, compareTwo, compareFour};
    }

}
