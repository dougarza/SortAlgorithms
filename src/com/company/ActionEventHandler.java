package com.company;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

// Class that handles the animations
public class ActionEventHandler implements ActionListener {
    // declare instances of various panels and buttons
    JPanel subcontainer;
    ImagePanel imagePanel1;
    ImagePanel imagePanel2;
    ImagePanel imagePanel4;
    JButton runButton;
    JButton shuffleButton;
    JRadioButton radio1;
    JRadioButton radio2;
    JRadioButton radio3;
    DrawBars[] drawBars;
    public static ImagePanel currentImagePanel;
    public static JButton currentJButton;
    public static JLayeredPane imageLayeredPane;
    JButton resetButton;
    JButton skipButton;

    ButtonSelector currentButtonSelector;

    JButton bubbleSort;
    JButton insertionSort;
    JButton selectionSort;
    JButton mergeSort;
    JButton quickSort;
    JButton heapSort;
    JButton radixSort;

    // Font for JLabels
    private static final Font labelFont = new Font("Helvetica Neue", Font.BOLD, 14);

    int numImages;

    public static JLabel bubbleNum;
    public static JLabel insertionNum;
    public static JLabel selectionNum;
    public static JLabel mergeNum;
    public static JLabel quickNum;
    public static JLabel heapNum;
    public static JLabel radixNum;

    public static JLabel bubbleLabel;
    public static JLabel insertionLabel;
    public static JLabel selectionLabel;
    public static JLabel mergeLabel;
    public static JLabel quickLabel;
    public static JLabel heapLabel;
    public static JLabel radixLabel;

    public ActionEventHandler(JPanel subcontainer, ImagePanel imagePanel1, ImagePanel imagePanel2, ImagePanel imagePanel4, JButton runButton, JButton shuffleButton, JRadioButton radio1, JRadioButton radio2, JRadioButton radio3, JButton resetButton, JButton skipButton) {
        // constructor to pass in image panels and buttons
        this.subcontainer = subcontainer;
        this.imagePanel1 = imagePanel1;
        this.imagePanel2 = imagePanel2;
        this.imagePanel4 = imagePanel4;
        this.runButton = runButton;
        this.shuffleButton = shuffleButton;
        this.radio1 = radio1;
        this.radio2 = radio2;
        this.radio3 = radio3;
        this.resetButton = resetButton;
        this.skipButton = skipButton;
    }

    public ActionEventHandler(JButton bubbleSort, JButton insertionSort, JButton selectionSort, JButton mergeSort, JButton quickSort, JButton heapSort, JButton radixSort, ButtonSelector currentButtonSelector) {
        // constructor to pass in button selector
        this.bubbleSort = bubbleSort;
        this.insertionSort = insertionSort;
        this.selectionSort = selectionSort;
        this.mergeSort = mergeSort;
        this.quickSort = quickSort;
        this.heapSort = heapSort;
        this.radixSort = radixSort;
        this.currentButtonSelector = currentButtonSelector;

    }

    public void actionPerformed(ActionEvent event) {
        // use card layout to place button selector above image panel
        CardLayout cl;
        switch (event.getActionCommand()) {
            // load each subcontainer based on case 1, 2, or 4
            case "Single":
                cl = (CardLayout)(subcontainer.getLayout());
                cl.show(subcontainer, "one");
                currentImagePanel = imagePanel1;
                numImages = 1;
                break;
            case "Compare Two":
                cl = (CardLayout)(subcontainer.getLayout());
                cl.show(subcontainer, "two");
                currentImagePanel = imagePanel2;
                numImages = 2;
                break;
            case "Compare Four":
                cl = (CardLayout)(subcontainer.getLayout());
                cl.show(subcontainer, "four");
                currentImagePanel = imagePanel4;
                numImages = 4;
                break;
            case "Shuffle":
                // animate using Fisher-Yates Shuffle
                drawBars = currentImagePanel.getDrawBarsArray();
                for (DrawBars drawBar : drawBars) {
                    drawBar.fisherYatesShuffle(drawBar.numBars, drawBar.barHeight);
                }
                break;
            case "Run":
                // Execute sorting
                Parameters.THREAD_TIME = 10;
                drawBars = currentImagePanel.getDrawBarsArray();

                // Set run button as clicked
                VisualizeAlgorithms.buttonPanel.getRunButton().setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
                VisualizeAlgorithms.buttonPanel.getRunButton().setBackground(ButtonPanel.DARK_MEDIUM_GRAY);
                VisualizeAlgorithms.buttonPanel.getRunButton().setForeground(ButtonPanel.TRANSLUCENT_WHITE);
                VisualizeAlgorithms.buttonPanel.getRunButton().setEnabled(false);

                // set skip button as clicked
                VisualizeAlgorithms.buttonPanel.getSkipButton().setBorder(BorderFactory.createBevelBorder(1));
                VisualizeAlgorithms.buttonPanel.getSkipButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                VisualizeAlgorithms.buttonPanel.getSkipButton().setForeground(Color.WHITE);
                VisualizeAlgorithms.buttonPanel.getSkipButton().setEnabled(true);

                // get sorting method stored in method hashmap
                for (int i = 0; i < drawBars.length; i++) {
                    if (VisualizeAlgorithms.methodMap.get(i).equals("bubblesort")) {
                        drawBars[i].bubbleSort(drawBars[i].numBars, drawBars[i].barHeight);
                    } else if (VisualizeAlgorithms.methodMap.get(i).equals("insertionsort")) {
                        drawBars[i].insertionSort(drawBars[i].numBars, drawBars[i].barHeight);
                    } else if (VisualizeAlgorithms.methodMap.get(i).equals("selectionsort")) {
                        drawBars[i].selectionSort(drawBars[i].numBars, drawBars[i].barHeight);
                    } else if (VisualizeAlgorithms.methodMap.get(i).equals("mergesort")) {
                        drawBars[i].mergeSort(drawBars[i].numBars, drawBars[i].barHeight);
                    } else if (VisualizeAlgorithms.methodMap.get(i).equals("quicksort")) {
                        drawBars[i].quickSort(drawBars[i].numBars, drawBars[i].barHeight);
                    } else if (VisualizeAlgorithms.methodMap.get(i).equals("radixsort")) {
                        drawBars[i].radixSort(drawBars[i].numBars, drawBars[i].barHeight);
                    } else if (VisualizeAlgorithms.methodMap.get(i).equals("heapsort")) {
                        drawBars[i].heapSort(drawBars[i].numBars, drawBars[i].barHeight);
                    }
                }

                break;
            case "Bubble Sort":
                // NOTE: should methodize each sorting case to compact code

                // If case is triggered, store sort method in method hashmap
                VisualizeAlgorithms.methodMap.put(currentButtonSelector.numButtonSelector, "bubblesort");
                imageLayeredPane = currentImagePanel.getLayeredPane(currentButtonSelector.numButtonSelector);
                imageLayeredPane.remove(currentImagePanel.getButtonSelector(currentButtonSelector.numButtonSelector));

                // Display sort method
                bubbleLabel = new JLabel("Bubble Sort");
                bubbleLabel.setBounds(0, 0, 180, 20);
                bubbleNum = new JLabel("Array swaps: " + DrawBars.bubbleCounter);
                bubbleNum.setBounds(200, 0, 400, 20);
                bubbleLabel.setFont(ActionEventHandler.labelFont);
                bubbleLabel.setForeground(Color.WHITE);
                bubbleNum.setFont(ActionEventHandler.labelFont);
                bubbleNum.setForeground(Color.WHITE);
                imageLayeredPane.add(bubbleLabel, 2, 0);
                imageLayeredPane.add(bubbleNum, 2, 0);

                currentImagePanel.revalidate();
                currentImagePanel.repaint();

                // Turn on reset button
                if (VisualizeAlgorithms.methodCounter == 0) {
                    for (int i = 0; i<3; i++) {
                        ButtonPanel.getRadioButtonArray()[i].setEnabled(false);
                    }
                    VisualizeAlgorithms.buttonPanel.getResetButton().setBorder(BorderFactory.createBevelBorder(1));
                    VisualizeAlgorithms.buttonPanel.getResetButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                    VisualizeAlgorithms.buttonPanel.getResetButton().setForeground(Color.WHITE);
                    VisualizeAlgorithms.buttonPanel.getResetButton().setEnabled(true);
                }

                VisualizeAlgorithms.methodCounter++;

                // Disable current sorting method button
                for (int i = 0; i < currentImagePanel.getLayeredPaneArrayLength(); i++) {
                    currentJButton = currentImagePanel.getButtonSelector(i).getJButton("Bubble Sort");
                    currentJButton.setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
                    currentJButton.setBackground(ButtonPanel.DARK_MEDIUM_GRAY);
                    currentJButton.setForeground(ButtonPanel.TRANSLUCENT_WHITE);
                    currentJButton.setEnabled(false);
                }

                if (VisualizeAlgorithms.methodCounter == currentImagePanel.getLayeredPaneArrayLength()) {
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setBorder(BorderFactory.createBevelBorder(1));
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setForeground(Color.WHITE);
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setEnabled(true);
                }

                // Repeat steps for rest of sorting cases
                break;
            case "Insertion Sort":
                VisualizeAlgorithms.methodMap.put(currentButtonSelector.numButtonSelector, "insertionsort");
                imageLayeredPane = currentImagePanel.getLayeredPane(currentButtonSelector.numButtonSelector);
                imageLayeredPane.remove(currentImagePanel.getButtonSelector(currentButtonSelector.numButtonSelector));

                insertionLabel = new JLabel("Insertion Sort");
                insertionLabel.setBounds(0, 0, 180, 20);
                insertionNum = new JLabel("Array swaps: " + DrawBars.insertionCounter);
                insertionNum.setBounds(200, 0, 400, 20);
                insertionLabel.setFont(ActionEventHandler.labelFont);
                insertionLabel.setForeground(Color.WHITE);
                insertionNum.setFont(ActionEventHandler.labelFont);
                insertionNum.setForeground(Color.WHITE);
                imageLayeredPane.add(insertionLabel, 2, 0);
                imageLayeredPane.add(insertionNum, 2, 0);
                currentImagePanel.revalidate();
                currentImagePanel.repaint();

                currentImagePanel.revalidate();
                currentImagePanel.repaint();

                if (VisualizeAlgorithms.methodCounter == 0) {
                    for (int i = 0; i<3; i++) {
                        ButtonPanel.getRadioButtonArray()[i].setEnabled(false);
                    }
                    VisualizeAlgorithms.buttonPanel.getResetButton().setBorder(BorderFactory.createBevelBorder(1));
                    VisualizeAlgorithms.buttonPanel.getResetButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                    VisualizeAlgorithms.buttonPanel.getResetButton().setForeground(Color.WHITE);
                    VisualizeAlgorithms.buttonPanel.getResetButton().setEnabled(true);
                }

                VisualizeAlgorithms.methodCounter++;

                for (int i = 0; i < currentImagePanel.getLayeredPaneArrayLength(); i++) {
                    currentJButton = currentImagePanel.getButtonSelector(i).getJButton("Insertion Sort");
                    currentJButton.setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
                    currentJButton.setBackground(ButtonPanel.DARK_MEDIUM_GRAY);
                    currentJButton.setForeground(ButtonPanel.TRANSLUCENT_WHITE);
                    currentJButton.setEnabled(false);
                }

                if (VisualizeAlgorithms.methodCounter == currentImagePanel.getLayeredPaneArrayLength()) {
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setBorder(BorderFactory.createBevelBorder(1));
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setForeground(Color.WHITE);
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setEnabled(true);
                }

                break;
            case "Selection Sort":
                VisualizeAlgorithms.methodMap.put(currentButtonSelector.numButtonSelector, "selectionsort");
                imageLayeredPane = currentImagePanel.getLayeredPane(currentButtonSelector.numButtonSelector);
                imageLayeredPane.remove(currentImagePanel.getButtonSelector(currentButtonSelector.numButtonSelector));

                selectionLabel = new JLabel("Selection Sort");
                selectionLabel.setBounds(0, 0, 180, 20);
                selectionNum = new JLabel("Array swaps: " + DrawBars.selectionCounter);
                selectionNum.setBounds(200, 0, 400, 20);
                selectionLabel.setFont(ActionEventHandler.labelFont);
                selectionLabel.setForeground(Color.WHITE);
                selectionNum.setFont(ActionEventHandler.labelFont);
                selectionNum.setForeground(Color.WHITE);
                imageLayeredPane.add(selectionLabel, 2, 0);
                imageLayeredPane.add(selectionNum, 2, 0);
                currentImagePanel.revalidate();
                currentImagePanel.repaint();

                currentImagePanel.revalidate();
                currentImagePanel.repaint();

                if (VisualizeAlgorithms.methodCounter == 0) {
                    for (int i = 0; i<3; i++) {
                        ButtonPanel.getRadioButtonArray()[i].setEnabled(false);
                    }
                    VisualizeAlgorithms.buttonPanel.getResetButton().setBorder(BorderFactory.createBevelBorder(1));
                    VisualizeAlgorithms.buttonPanel.getResetButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                    VisualizeAlgorithms.buttonPanel.getResetButton().setForeground(Color.WHITE);
                    VisualizeAlgorithms.buttonPanel.getResetButton().setEnabled(true);
                }

                VisualizeAlgorithms.methodCounter++;

                for (int i = 0; i < currentImagePanel.getLayeredPaneArrayLength(); i++) {
                    currentJButton = currentImagePanel.getButtonSelector(i).getJButton("Selection Sort");
                    currentJButton.setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
                    currentJButton.setBackground(ButtonPanel.DARK_MEDIUM_GRAY);
                    currentJButton.setForeground(ButtonPanel.TRANSLUCENT_WHITE);
                    currentJButton.setEnabled(false);
                }

                if (VisualizeAlgorithms.methodCounter == currentImagePanel.getLayeredPaneArrayLength()) {
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setBorder(BorderFactory.createBevelBorder(1));
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setForeground(Color.WHITE);
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setEnabled(true);
                }

                break;
            case "Merge Sort":
                VisualizeAlgorithms.methodMap.put(currentButtonSelector.numButtonSelector, "mergesort");
                imageLayeredPane = currentImagePanel.getLayeredPane(currentButtonSelector.numButtonSelector);
                imageLayeredPane.remove(currentImagePanel.getButtonSelector(currentButtonSelector.numButtonSelector));

                mergeLabel = new JLabel("Merge Sort");
                mergeLabel.setBounds(0, 0, 180, 20);
                mergeNum = new JLabel("Array swaps: " + DrawBars.mergeCounter);
                mergeNum.setBounds(200, 0, 400, 20);
                mergeLabel.setFont(ActionEventHandler.labelFont);
                mergeLabel.setForeground(Color.WHITE);
                mergeNum.setFont(ActionEventHandler.labelFont);
                mergeNum.setForeground(Color.WHITE);
                imageLayeredPane.add(mergeLabel, 2, 0);
                imageLayeredPane.add(mergeNum, 2, 0);
                currentImagePanel.revalidate();
                currentImagePanel.repaint();

                currentImagePanel.revalidate();
                currentImagePanel.repaint();

                if (VisualizeAlgorithms.methodCounter == 0) {
                    for (int i = 0; i<3; i++) {
                        ButtonPanel.getRadioButtonArray()[i].setEnabled(false);
                    }
                    VisualizeAlgorithms.buttonPanel.getResetButton().setBorder(BorderFactory.createBevelBorder(1));
                    VisualizeAlgorithms.buttonPanel.getResetButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                    VisualizeAlgorithms.buttonPanel.getResetButton().setForeground(Color.WHITE);
                    VisualizeAlgorithms.buttonPanel.getResetButton().setEnabled(true);
                }

                VisualizeAlgorithms.methodCounter++;

                for (int i = 0; i < currentImagePanel.getLayeredPaneArrayLength(); i++) {
                    currentJButton = currentImagePanel.getButtonSelector(i).getJButton("Merge Sort");
                    currentJButton.setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
                    currentJButton.setBackground(ButtonPanel.DARK_MEDIUM_GRAY);
                    currentJButton.setForeground(ButtonPanel.TRANSLUCENT_WHITE);
                    currentJButton.setEnabled(false);
                }

                if (VisualizeAlgorithms.methodCounter == currentImagePanel.getLayeredPaneArrayLength()) {
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setBorder(BorderFactory.createBevelBorder(1));
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setForeground(Color.WHITE);
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setEnabled(true);
                }

                break;
            case "Quick Sort":
                VisualizeAlgorithms.methodMap.put(currentButtonSelector.numButtonSelector, "quicksort");
                imageLayeredPane = currentImagePanel.getLayeredPane(currentButtonSelector.numButtonSelector);
                imageLayeredPane.remove(currentImagePanel.getButtonSelector(currentButtonSelector.numButtonSelector));

                quickLabel = new JLabel("Quick Sort");
                quickLabel.setBounds(0, 0, 180, 20);
                quickNum = new JLabel("Array swaps: " + DrawBars.quickCounter);
                quickNum.setBounds(200, 0, 400, 20);
                quickLabel.setFont(ActionEventHandler.labelFont);
                quickLabel.setForeground(Color.WHITE);
                quickNum.setFont(ActionEventHandler.labelFont);
                quickNum.setForeground(Color.WHITE);
                imageLayeredPane.add(quickLabel, 2, 0);
                imageLayeredPane.add(quickNum, 2, 0);
                currentImagePanel.revalidate();
                currentImagePanel.repaint();

                currentImagePanel.revalidate();
                currentImagePanel.repaint();

                if (VisualizeAlgorithms.methodCounter == 0) {
                    for (int i = 0; i<3; i++) {
                        ButtonPanel.getRadioButtonArray()[i].setEnabled(false);
                    }
                    VisualizeAlgorithms.buttonPanel.getResetButton().setBorder(BorderFactory.createBevelBorder(1));
                    VisualizeAlgorithms.buttonPanel.getResetButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                    VisualizeAlgorithms.buttonPanel.getResetButton().setForeground(Color.WHITE);
                    VisualizeAlgorithms.buttonPanel.getResetButton().setEnabled(true);
                }

                VisualizeAlgorithms.methodCounter++;

                for (int i = 0; i < currentImagePanel.getLayeredPaneArrayLength(); i++) {
                    currentJButton = currentImagePanel.getButtonSelector(i).getJButton("Quick Sort");
                    currentJButton.setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
                    currentJButton.setBackground(ButtonPanel.DARK_MEDIUM_GRAY);
                    currentJButton.setForeground(ButtonPanel.TRANSLUCENT_WHITE);
                    currentJButton.setEnabled(false);
                }

                if (VisualizeAlgorithms.methodCounter == currentImagePanel.getLayeredPaneArrayLength()) {
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setBorder(BorderFactory.createBevelBorder(1));
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setForeground(Color.WHITE);
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setEnabled(true);
                }

                break;
            case "Heap Sort":
                VisualizeAlgorithms.methodMap.put(currentButtonSelector.numButtonSelector, "heapsort");
                imageLayeredPane = currentImagePanel.getLayeredPane(currentButtonSelector.numButtonSelector);
                imageLayeredPane.remove(currentImagePanel.getButtonSelector(currentButtonSelector.numButtonSelector));

                heapLabel = new JLabel("Heap Sort");
                heapLabel.setBounds(0, 0, 180, 20);
                heapNum = new JLabel("Array swaps: " + DrawBars.heapCounter);
                heapNum.setBounds(200, 0, 400, 20);
                heapLabel.setFont(ActionEventHandler.labelFont);
                heapLabel.setForeground(Color.WHITE);
                heapNum.setFont(ActionEventHandler.labelFont);
                heapNum.setForeground(Color.WHITE);
                imageLayeredPane.add(heapLabel, 2, 0);
                imageLayeredPane.add(heapNum, 2, 0);
                currentImagePanel.revalidate();
                currentImagePanel.repaint();

                currentImagePanel.revalidate();
                currentImagePanel.repaint();

                if (VisualizeAlgorithms.methodCounter == 0) {
                    for (int i = 0; i<3; i++) {
                        ButtonPanel.getRadioButtonArray()[i].setEnabled(false);
                    }
                    VisualizeAlgorithms.buttonPanel.getResetButton().setBorder(BorderFactory.createBevelBorder(1));
                    VisualizeAlgorithms.buttonPanel.getResetButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                    VisualizeAlgorithms.buttonPanel.getResetButton().setForeground(Color.WHITE);
                    VisualizeAlgorithms.buttonPanel.getResetButton().setEnabled(true);
                }

                VisualizeAlgorithms.methodCounter++;

                for (int i = 0; i < currentImagePanel.getLayeredPaneArrayLength(); i++) {
                    currentJButton = currentImagePanel.getButtonSelector(i).getJButton("Heap Sort");
                    currentJButton.setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
                    currentJButton.setBackground(ButtonPanel.DARK_MEDIUM_GRAY);
                    currentJButton.setForeground(ButtonPanel.TRANSLUCENT_WHITE);
                    currentJButton.setEnabled(false);
                }

                if (VisualizeAlgorithms.methodCounter == currentImagePanel.getLayeredPaneArrayLength()) {
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setBorder(BorderFactory.createBevelBorder(1));
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setForeground(Color.WHITE);
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setEnabled(true);
                }

                break;
            case "Radix Sort":
                VisualizeAlgorithms.methodMap.put(currentButtonSelector.numButtonSelector, "radixsort");
                imageLayeredPane = currentImagePanel.getLayeredPane(currentButtonSelector.numButtonSelector);
                imageLayeredPane.remove(currentImagePanel.getButtonSelector(currentButtonSelector.numButtonSelector));

                radixLabel = new JLabel("Radix Sort");
                radixLabel.setBounds(0, 0, 180, 20);
                radixNum = new JLabel("Array swaps: " + DrawBars.radixCounter);
                radixNum.setBounds(200, 0, 400, 20);
                radixLabel.setFont(ActionEventHandler.labelFont);
                radixLabel.setForeground(Color.WHITE);
                radixNum.setFont(ActionEventHandler.labelFont);
                radixNum.setForeground(Color.WHITE);
                imageLayeredPane.add(radixLabel, 2, 0);
                imageLayeredPane.add(radixNum, 2, 0);
                currentImagePanel.revalidate();
                currentImagePanel.repaint();

                currentImagePanel.revalidate();
                currentImagePanel.repaint();

                if (VisualizeAlgorithms.methodCounter == 0) {
                    for (int i = 0; i<3; i++) {
                        ButtonPanel.getRadioButtonArray()[i].setEnabled(false);
                    }
                    VisualizeAlgorithms.buttonPanel.getResetButton().setBorder(BorderFactory.createBevelBorder(1));
                    VisualizeAlgorithms.buttonPanel.getResetButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                    VisualizeAlgorithms.buttonPanel.getResetButton().setForeground(Color.WHITE);
                    VisualizeAlgorithms.buttonPanel.getResetButton().setEnabled(true);
                }

                VisualizeAlgorithms.methodCounter++;

                for (int i = 0; i < currentImagePanel.getLayeredPaneArrayLength(); i++) {
                    currentJButton = currentImagePanel.getButtonSelector(i).getJButton("Radix Sort");
                    currentJButton.setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
                    currentJButton.setBackground(ButtonPanel.DARK_MEDIUM_GRAY);
                    currentJButton.setForeground(ButtonPanel.TRANSLUCENT_WHITE);
                    currentJButton.setEnabled(false);
                }

                if (VisualizeAlgorithms.methodCounter == currentImagePanel.getLayeredPaneArrayLength()) {
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setBorder(BorderFactory.createBevelBorder(1));
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setForeground(Color.WHITE);
                    VisualizeAlgorithms.buttonPanel.getShuffleButton().setEnabled(true);
                }

                break;

            case "Skip":
                // Skip just finishes the sorting without any thread delay, useful for seeing final results
                // Disable skip button
                Parameters.THREAD_TIME = 0;
                VisualizeAlgorithms.buttonPanel.getSkipButton().setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
                VisualizeAlgorithms.buttonPanel.getSkipButton().setBackground(ButtonPanel.DARK_MEDIUM_GRAY);
                VisualizeAlgorithms.buttonPanel.getSkipButton().setForeground(ButtonPanel.TRANSLUCENT_WHITE);
                VisualizeAlgorithms.buttonPanel.getSkipButton().setEnabled(false);
                break;

            case "Reset":
                // Reset just finishes the sorting without any thread delay, useful for setting new sorters
                Parameters.THREAD_TIME = 0;
                VisualizeAlgorithms.methodCounter = 0;

                DrawBars.bubbleCounter = 0;
                DrawBars.insertionCounter = 0;
                DrawBars.selectionCounter = 0;
                DrawBars.mergeCounter = 0;
                DrawBars.quickCounter = 0;
                DrawBars.heapCounter = 0;
                DrawBars.radixCounter = 0;

                // Reset to default state
                for (int i = 0; i < drawBars.length; i++) {
                    imageLayeredPane = currentImagePanel.getLayeredPane(i);
                    if (VisualizeAlgorithms.methodMap.get(i).equals("bubblesort")) {
                        imageLayeredPane.remove(bubbleLabel);
                        imageLayeredPane.remove(bubbleNum);
                    } else if (VisualizeAlgorithms.methodMap.get(i).equals("insertionsort")) {
                        imageLayeredPane.remove(insertionLabel);
                        imageLayeredPane.remove(insertionNum);
                    } else if (VisualizeAlgorithms.methodMap.get(i).equals("selectionsort")) {
                        imageLayeredPane.remove(selectionLabel);
                        imageLayeredPane.remove(selectionNum);
                    } else if (VisualizeAlgorithms.methodMap.get(i).equals("mergesort")) {
                        imageLayeredPane.remove(mergeLabel);
                        imageLayeredPane.remove(mergeNum);
                    } else if (VisualizeAlgorithms.methodMap.get(i).equals("quicksort")) {
                        imageLayeredPane.remove(quickLabel);
                        imageLayeredPane.remove(quickNum);
                    } else if (VisualizeAlgorithms.methodMap.get(i).equals("radixsort")) {
                        imageLayeredPane.remove(radixLabel);
                        imageLayeredPane.remove(radixNum);
                    } else if (VisualizeAlgorithms.methodMap.get(i).equals("heapsort")) {
                        imageLayeredPane.remove(heapLabel);
                        imageLayeredPane.remove(heapNum);
                    }
                }

                // reset image layered pane
                for (int i = 0; i<numImages; i++) {
                    imageLayeredPane = currentImagePanel.getLayeredPane(i);
                    imageLayeredPane.add(currentImagePanel.getButtonSelector(i), 1, 0);

                }

                // reset method hashmap
                VisualizeAlgorithms.methodMap.clear();


                String[] buttonNames = new String[]{"Bubble Sort", "Insertion Sort", "Selection Sort", "Merge Sort", "Quick Sort", "Heap Sort", "Radix Sort"};

                // reset button panel
                for (int j = 0; j<numImages; j++) {
                    for (int i = 0; i < buttonNames.length; i++) {
                        currentJButton = currentImagePanel.getButtonSelector(j).getJButton(buttonNames[i]);
                        currentJButton.setBorder(BorderFactory.createBevelBorder(0));
                        currentJButton.setBackground(ButtonPanel.MEDIUM_GRAY);
                        currentJButton.setForeground(Color.WHITE);
                        currentJButton.setEnabled(true);
                    }
                }

                // disable reset and skip buttons
                VisualizeAlgorithms.buttonPanel.getResetButton().setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
                VisualizeAlgorithms.buttonPanel.getResetButton().setBackground(ButtonPanel.DARK_MEDIUM_GRAY);
                VisualizeAlgorithms.buttonPanel.getResetButton().setForeground(ButtonPanel.TRANSLUCENT_WHITE);
                VisualizeAlgorithms.buttonPanel.getResetButton().setEnabled(false);

                VisualizeAlgorithms.buttonPanel.getSkipButton().setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
                VisualizeAlgorithms.buttonPanel.getSkipButton().setBackground(ButtonPanel.DARK_MEDIUM_GRAY);
                VisualizeAlgorithms.buttonPanel.getSkipButton().setForeground(ButtonPanel.TRANSLUCENT_WHITE);
                VisualizeAlgorithms.buttonPanel.getSkipButton().setEnabled(false);

                // enable radio buttons
                for (int i = 0; i<3; i++) {
                    ButtonPanel.getRadioButtonArray()[i].setEnabled(true);
                }

                currentImagePanel.revalidate();
                currentImagePanel.repaint();

                break;
            default:
                // error for testing/debugging
                System.err.println("Undefined action case.");
                break;
        }
    }
}
