package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

// imagepanel inherits JPanel
public class ImagePanel extends JPanel {

    private int panelWidth;
    private int panelHeight;
    DrawBars[] drawBars;
    JLayeredPane[] layeredPane;
    ButtonSelector[] buttonSelector;
    int numPanels;

    public ImagePanel() {
        super();
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(Parameters.WIDTH * 7 / 8, Parameters.HEIGHT));
    }

    // setting image panel depends on number of panels (1, 2, or 4)
    public ImagePanel setImagePanel(int numPanels) {

        if (numPanels == 0) {
            this.setLayout(new GridLayout(1, 1));
            panelWidth = Parameters.WIDTH_1;
            panelHeight = Parameters.HEIGHT_1;
            return this;
        } else if (numPanels == 1) {
            this.setLayout(new GridLayout(1, 1));
            panelWidth = Parameters.WIDTH_1;
            panelHeight = Parameters.HEIGHT_1;
        } else if (numPanels == 2) {
            this.setLayout(new GridLayout(2, 1));
            panelWidth = Parameters.WIDTH_2;
            panelHeight = Parameters.HEIGHT_2;
        } else if (numPanels == 4) {
            this.setLayout(new GridLayout(2, 2));
            panelWidth = Parameters.WIDTH_4;
            panelHeight = Parameters.HEIGHT_4;
        }

        drawBars = new DrawBars[numPanels];
        buttonSelector = new ButtonSelector[numPanels];
        layeredPane = new JLayeredPane[numPanels];
        JPanel[] subPanel = new JPanel[numPanels];

        // add bars to image
        for (int i = 0; i < numPanels; i++) {
            drawBars[i] = new DrawBars(numPanels);
            buttonSelector[i] = new ButtonSelector(i);
            layeredPane[i] = new JLayeredPane();

            drawBars[i].setBounds(0, 0, panelWidth, panelHeight);

            if (numPanels == 1) {
                buttonSelector[i].setBounds(350, 230, 310, 112);
            } else if (numPanels == 2) {
                buttonSelector[i].setBounds(350, 100, 310, 112);
            } else if (numPanels == 4) {
                buttonSelector[i].setBounds(110, 90, 310, 112);
            }

            layeredPane[i].add(drawBars[i], 0, 0);
            layeredPane[i].add(buttonSelector[i], 1, 0);

            subPanel[i] = new JPanel(new BorderLayout());
            subPanel[i].setBackground(new Color(50, 50, 50));
            subPanel[i].add(layeredPane[i]);

            this.add(subPanel[i]);
        }

        return this;
    }

    // getters
    public ButtonSelector getButtonSelector(int i) {
        return buttonSelector[i];
    }

    public JLayeredPane getLayeredPane(int i) {
        return layeredPane[i];
    }

//    public JLayeredPane getLayeredPaneNumber() {
//        return
//    }

    public int getLayeredPaneArrayLength() {
        return layeredPane.length;
    }

    public DrawBars[] getDrawBarsArray() {
        return drawBars;
    }

    public DrawBars getDrawBars(int drawBarsNum) {
        return drawBars[drawBarsNum];
    }

    public int getNumPanels() {
        return numPanels;
    }

}

class ButtonSelector extends JPanel{

    JButton bubbleSort;
    JButton insertionSort;
    JButton selectionSort;
    JButton mergeSort;
    JButton quickSort;
    JButton heapSort;
    JButton radixSort;

    public int numButtonSelector;

    // button selector on top of display panel
    public ButtonSelector(int i) {
        super();
        numButtonSelector = i;

        setLayout(new FlowLayout(FlowLayout.CENTER));

        Font buttonPanelFont = new Font("Helvetica Neue", Font.PLAIN, 14);

        setBackground(Color.DARK_GRAY);

        JPanel labelPanel = new JPanel();
        JPanel buttonGridPanel = new JPanel(new GridLayout(3, 3));

        // buttons for sorters
        bubbleSort = new JButton("Bubble Sort");
        insertionSort = new JButton("Insertion Sort");
        selectionSort = new JButton("Selection Sort");
        mergeSort = new JButton("Merge Sort");
        quickSort = new JButton("Quick Sort");
        heapSort = new JButton("Heap Sort");
        radixSort = new JButton("Radix Sort");

        // bevel borders
        bubbleSort.setBorder(BorderFactory.createBevelBorder(0));
        insertionSort.setBorder(BorderFactory.createBevelBorder(0));
        selectionSort.setBorder(BorderFactory.createBevelBorder(0));
        mergeSort.setBorder(BorderFactory.createBevelBorder(0));
        quickSort.setBorder(BorderFactory.createBevelBorder(0));
        heapSort.setBorder(BorderFactory.createBevelBorder(0));
        radixSort.setBorder(BorderFactory.createBevelBorder(0));

        // setting fonts
        bubbleSort.setFont(buttonPanelFont);
        insertionSort.setFont(buttonPanelFont);
        selectionSort.setFont(buttonPanelFont);
        mergeSort.setFont(buttonPanelFont);
        quickSort.setFont(buttonPanelFont);
        heapSort.setFont(buttonPanelFont);
        radixSort.setFont(buttonPanelFont);

        // font color
        bubbleSort.setForeground(Color.WHITE);
        insertionSort.setForeground(Color.WHITE);
        selectionSort.setForeground(Color.WHITE);
        mergeSort.setForeground(Color.WHITE);
        quickSort.setForeground(Color.WHITE);
        heapSort.setForeground(Color.WHITE);
        radixSort.setForeground(Color.WHITE);

        // button color
        bubbleSort.setBackground(ButtonPanel.MEDIUM_GRAY);
        insertionSort.setBackground(ButtonPanel.MEDIUM_GRAY);
        selectionSort.setBackground(ButtonPanel.MEDIUM_GRAY);
        mergeSort.setBackground(ButtonPanel.MEDIUM_GRAY);
        quickSort.setBackground(ButtonPanel.MEDIUM_GRAY);
        heapSort.setBackground(ButtonPanel.MEDIUM_GRAY);
        radixSort.setBackground(ButtonPanel.MEDIUM_GRAY);

        // prompt
        JLabel selectAlgorithm = new JLabel("Select Algorithm");
        Font selectLabelFont = new Font("Helvetica Neue", Font.BOLD, 16);
        selectAlgorithm.setFont(selectLabelFont);
        selectAlgorithm.setForeground(Color.WHITE);

        labelPanel.setBackground(Color.DARK_GRAY);

        // add buttons to grid panel
        labelPanel.add(selectAlgorithm);
        buttonGridPanel.add(bubbleSort);
        buttonGridPanel.add(insertionSort);
        buttonGridPanel.add(selectionSort);
        buttonGridPanel.add(mergeSort);
        buttonGridPanel.add(quickSort);
        buttonGridPanel.add(heapSort);
        buttonGridPanel.add(radixSort);
        buttonGridPanel.setBackground(Color.DARK_GRAY);

        ActionEventHandler actionListener = new ActionEventHandler(bubbleSort, insertionSort, selectionSort, mergeSort, quickSort, heapSort, radixSort, this);

        // add action listeners to buttons
        bubbleSort.addActionListener(actionListener);
        insertionSort.addActionListener(actionListener);
        selectionSort.addActionListener(actionListener);
        mergeSort.addActionListener(actionListener);
        quickSort.addActionListener(actionListener);
        heapSort.addActionListener(actionListener);
        radixSort.addActionListener(actionListener);

        LineBorder border = new LineBorder(new Color(50, 50, 50), 2);

        add(labelPanel);
        add(buttonGridPanel);
        setBorder(border);
    }

    public JButton getJButton(String name) {
        switch (name) {
            case "Bubble Sort":
                return bubbleSort;
            case "Insertion Sort":
                return insertionSort;
            case "Selection Sort":
                return selectionSort;
            case "Merge Sort":
                return mergeSort;
            case "Quick Sort":
                return quickSort;
            case "Heap Sort":
                return heapSort;
            case "Radix Sort":
                return radixSort;
        }
        return null;
    }
}
