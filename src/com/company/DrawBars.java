package com.company;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;

// inherit JPanel
public class DrawBars extends JPanel {

    // Initialize number of bars, heights, random seed, swingworker, pointers for coloring
    public int[] barHeight = new int[Parameters.NUM_BARS_1];
    public int numBars;
    private int barWidth;
    private int height;
    private SwingWorker<Void, Void> shuffler, sorter;
    private final int seed = 8679;
    private boolean drawInts, twoPointers;
    private int p1, p2;

    // Initialize counts for display
    public static int bubbleCounter = 0;
    public static int insertionCounter = 0;
    public static int selectionCounter = 0;
    public static int mergeCounter = 0;
    public static int quickCounter = 0;
    public static int heapCounter = 0;
    public static int radixCounter = 0;


    public DrawBars() {
        // constructor
        super();
        setPreferredSize(new Dimension(Parameters.WIDTH_1, Parameters.HEIGHT_1));
        setBackground(Color.DARK_GRAY);
        this.numBars = Parameters.NUM_BARS_1;
        barWidth = Parameters.BAR_WIDTH_1;
        height = Parameters.HEIGHT_1;
        initBarHeight(this.numBars);
    }

    public DrawBars(int numPanels) {
        // constructor, needs to know number of image panels (1, 2, or 4)
        // initializes bar characteristics
        super();
        setBackground(Color.DARK_GRAY);

        if (numPanels == 1) {
            setPreferredSize(new Dimension(Parameters.WIDTH_1, Parameters.HEIGHT_1));
            this.numBars = Parameters.NUM_BARS_1;
            barWidth = Parameters.BAR_WIDTH_1;
            height = Parameters.HEIGHT_1;
        } else if (numPanels == 2) {
            setPreferredSize(new Dimension(Parameters.WIDTH_2, Parameters.HEIGHT_2));
            this.numBars = Parameters.NUM_BARS_2;
            barWidth = Parameters.BAR_WIDTH_2;
            height = Parameters.HEIGHT_2;
            initBarHeight(numBars);
        } else if (numPanels == 4) {
            setPreferredSize(new Dimension(Parameters.WIDTH_4, Parameters.HEIGHT_4));
            this.numBars = Parameters.NUM_BARS_4;
            barWidth = Parameters.BAR_WIDTH_4;
            height = Parameters.HEIGHT_4;
            initBarHeight(numBars);
        } else {
            System.err.println("error: invalid numPanels.");
        }
        initBarHeight(numBars);

    }

    // bar heights from min to max
    public void initBarHeight(int numBars) {
        for (int i = 0; i < numBars-1; i++) {
            barHeight[i] = i + 1;
        }
    }

    // paints the bar components by drawing rectangles
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < numBars - 1; i++) {
            g.setColor(Color.CYAN);
            g.fillRect(barWidth * i, height - barHeight[i], barWidth, barHeight[i]);
            g.setColor(Color.BLACK);
            g.drawRect(barWidth * i, height - barHeight[i], barWidth, barHeight[i]);
        }
        // pointers color bars appropriately
          if (drawInts) {
              g.setColor(Color.RED);
              g.fillRect(barWidth * p1, height - barHeight[p1], barWidth, barHeight[p1]);
              if (twoPointers) {
                  g.setColor(Color.BLUE);
                  g.fillRect(barWidth * p2, height - barHeight[p2], barWidth, barHeight[p2]);
              }
          }
    }

    // fisher yates shuffle to scramble the array
    public void fisherYatesShuffle(int lengthArray, int[] barHeightArray) {
        shuffler = new SwingWorker() {
            @Override
            public Void doInBackground() throws InterruptedException {
                repaint();
                for (int i = lengthArray - 1; i >= 1; i--) {
                    Random rand = new Random(seed);
                    int j = rand.nextInt(i + 1);         // pick a random index
                    swap(barHeightArray, i, j);             // swap array indices

                    // pause for animation
                    Thread.sleep(1);
                    repaint();
                }
                return null;

            }

            // executed after animation is complete
            @Override
            public void done() {
                super.done();
                VisualizeAlgorithms.buttonPanel.getRunButton().setBorder(BorderFactory.createBevelBorder(1));
                VisualizeAlgorithms.buttonPanel.getRunButton().setBackground(ButtonPanel.MEDIUM_GRAY);
                VisualizeAlgorithms.buttonPanel.getRunButton().setForeground(Color.WHITE);
                VisualizeAlgorithms.buttonPanel.getRunButton().setEnabled(true);

                VisualizeAlgorithms.buttonPanel.getShuffleButton().setBorder(BorderFactory.createLineBorder(ButtonPanel.MEDIUM_GRAY));
                VisualizeAlgorithms.buttonPanel.getShuffleButton().setBackground(ButtonPanel.DARK_MEDIUM_GRAY);
                VisualizeAlgorithms.buttonPanel.getShuffleButton().setForeground(ButtonPanel.TRANSLUCENT_WHITE);
                VisualizeAlgorithms.buttonPanel.getShuffleButton().setEnabled(false);

                drawInts = true;
            }
        };
        shuffler.execute();
    }

    // method for swapping array indices
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // bubble sort method
    public void bubbleSort(int lengthArray, int[]  barHeightArray) {
        sorter = new SwingWorker() {
            @Override
            public Void doInBackground() throws InterruptedException {
                drawInts = true;
                for (int i = 0; i < lengthArray - 1; i++) {
                    for (int j = 0; j < lengthArray - i - 1; j++) {
                        if (barHeightArray[j] > barHeightArray[j + 1]) {
                            // swap arr[j+1] and arr[j]
                            p1 = j;
                            Thread.sleep(Parameters.THREAD_TIME);
                            repaint();
                            swap(barHeightArray, j, j+1);
                            bubbleCounter++;
                            ActionEventHandler.bubbleNum.setText("Array swaps: " + bubbleCounter);
                        }
                    }
                }
                drawInts = false;
                Thread.sleep(Parameters.THREAD_TIME);
                repaint();
                return null;
            }

        };
        sorter.execute();
    }

    // Main function to call recursive sortMiddle() and merge()
    public void mergeSort(int lengthArray, int[] barHeightArray) {
        sorter = new SwingWorker() {
            @Override
            public Void doInBackground() throws InterruptedException {
                drawInts = true;
                sortMiddle(barHeightArray, 0, lengthArray-1);
                drawInts = false;
                Thread.sleep(Parameters.THREAD_TIME);
                repaint();
                return null;
            }

            private void sortMiddle(int[] arr, int left, int right) throws InterruptedException {
                if (left < right) {
                    // midpoint between left and right
                    int mid = left + (right - left) / 2;

                    // Split first half and second half to be sorted separately
                    sortMiddle(arr, left, mid);
                    sortMiddle(arr, mid + 1, right);

                    // Call merge on the sorted halves
                    merge(arr, left, mid, right);
                }
            }

            private void merge(int[] arr, int left, int mid, int right) throws InterruptedException {
                // find sizes of two subarrays to be merged
                int n1 = mid - left + 1;
                int n2 = right - mid;

                // create temp arrays
                int[] L = new int[n1];
                int[] R = new int[n2];

                // build temp arrays with data
                for (int i = 0; i < n1; ++i)
                    L[i] = arr[left + i];
                for (int j = 0; j < n2; ++j)
                    R[j] = arr[mid + 1 + j];

                // merge temp arrays
                // Initial indexes of first and second subarrays
                int i = 0, j = 0;

                // Initial index of merged subarray array
                int k = left;
                while (i < n1 && j < n2) {
                    if (L[i] <= R[j]) {
                        arr[k] = L[i];
                        i++;
                    }
                    else {
                        arr[k] = R[j];
                        j++;
                    }
                    p1 = k;
                    mergeCounter++;
                    ActionEventHandler.mergeNum.setText("Array swaps: " + mergeCounter);
                    Thread.sleep(Parameters.THREAD_TIME);
                    repaint();
                    k++;
                }

                // remaining elements of L
                while (i < n1) {
                    arr[k] = L[i];
                    mergeCounter++;
                    ActionEventHandler.mergeNum.setText("Array swaps: " + mergeCounter);
                    Thread.sleep(Parameters.THREAD_TIME);
                    repaint();
                    p1 = k;
                    i++;
                    k++;
                }

                // remaining elements of R
                while (j < n2) {
                    arr[k] = R[j];
                    mergeCounter++;
                    ActionEventHandler.mergeNum.setText("Array swaps: " + mergeCounter);
                    Thread.sleep(Parameters.THREAD_TIME);
                    repaint();
                    p1 = k;
                    j++;
                    k++;
                }
            }

        };
        sorter.execute();
    }

    // insertion sort method
    public void insertionSort(int lengthArray, int[] barHeightArray) {
        sorter = new SwingWorker() {
            public Void doInBackground() throws InterruptedException {
                twoPointers = true;
                drawInts = true;
                for (int i = 1; i < lengthArray; ++i) {
                    int key = barHeightArray[i];
                    int j = i - 1;

                    while (j >= 0 && barHeightArray[j] > key) {
                        barHeightArray[j + 1] = barHeightArray[j];
                        p2 = j + 1;
                        insertionCounter++;
                        ActionEventHandler.insertionNum.setText("Array swaps: " + insertionCounter);
                        Thread.sleep(Parameters.THREAD_TIME);
                        repaint();

                        j = j - 1;
                    }
                    barHeightArray[j + 1] = key;
                    p2 = j + 1;
                    p1 = i+1;
                    insertionCounter++;
                    ActionEventHandler.insertionNum.setText("Array swaps: " + insertionCounter);
                    Thread.sleep(Parameters.THREAD_TIME);
                    repaint();
                }
                drawInts = false;
                twoPointers = false;
                Thread.sleep(Parameters.THREAD_TIME);
                repaint();
                return null;
            }
        };
        sorter.execute();
    }

    public void quickSort(int lengthArray, int[] barHeightArray) {
        sorter = new SwingWorker () {
            @Override
            public Void doInBackground() throws InterruptedException {
                twoPointers = true;
                drawInts = true;
                getPivot(barHeightArray, 0, lengthArray-1);
                drawInts = false;
                twoPointers = false;
                Thread.sleep(Parameters.THREAD_TIME);
                repaint();
                return null;
            }

            private void getPivot(int[] arr, int low, int high) throws InterruptedException{
                if (low < high) {
                    // pivot_index is partitioning index, arr[pivot_index] is now at correct place in sorted array
                    int pivot_index = partition(arr, low, high);

                    getPivot(arr, low, pivot_index - 1);  // Before pivot_index
                    getPivot(arr, pivot_index + 1, high); // After pivot_index
                }
            }

            private int partition(int[]arr, int low, int high) throws InterruptedException{
                // pivot - Element at right most position
                int pivot = arr[high];
                int i = (low - 1);  // Index of smaller element
                for (int j = low; j <= high-1; j++)
                {
                    // If current element is smaller than the pivot, swap the element with pivot
                    if (arr[j] < pivot)
                    {
                        i++;    // increment index of smaller element
                        swap(arr, i, j);
                        p1 = j;
                        p2 = i;
                        quickCounter++;
                        ActionEventHandler.quickNum.setText("Array swaps: " + quickCounter);
                        Thread.sleep(Parameters.THREAD_TIME);
                        repaint();
                    }
                }
                swap(arr, i + 1, high);
                quickCounter++;
                ActionEventHandler.quickNum.setText("Array swaps: " + quickCounter);
                Thread.sleep(Parameters.THREAD_TIME);
                repaint();
                return (i + 1);
            }
        };
        sorter.execute();
    }

    public void radixSort(int lengthArray, int[] barHeightArray) {
        sorter = new SwingWorker() {
            @Override
            public Void doInBackground() throws InterruptedException {
                drawInts = true;
                radixSortCall(barHeightArray, lengthArray);
                drawInts = false;
                twoPointers = false;
                Thread.sleep(Parameters.THREAD_TIME);
                repaint();
                return null;
            }

            int getMax(int[] arr, int n) {
                int mx = arr[0];
                for (int i = 1; i < n; i++)
                    if (arr[i] > mx)
                        mx = arr[i];
                return mx;
            }

            // A function to do counting sort of arr[] according to
            // the digit represented by exp.
            private void counterSort(int[] arr, int n, int exp) throws InterruptedException {
                int[] output = new int[n]; // output array
                int i;
                int[] count = new int[10];
                Arrays.fill(count, 0);

                // Store count of occurrences in count[]
                for (i = 0; i < n; i++) {
                    count[(arr[i] / exp) % 10]++;
                    p1 = i;
                    Thread.sleep(Parameters.THREAD_TIME);
                    repaint();
                }

                // Change count[i] so that count[i] now contains
                // actual position of this digit in output[]
                for (i = 1; i < 10; i++) {
                    count[i] += count[i - 1];
                }

                // Build the output array
                for (i = n - 1; i >= 0; i--) {
                    output[count[(arr[i] / exp) % 10] - 1] = arr[i];
                    count[(arr[i] / exp) % 10]--;
                }

                // Copy the output array to arr[], so that arr[] now
                // contains sorted numbers according to current digit
                for (i = 0; i < n; i++) {
                    arr[i] = output[i];
                    p1 = i;
                    radixCounter++;
                    ActionEventHandler.radixNum.setText("Array swaps: " + radixCounter);
                    Thread.sleep(Parameters.THREAD_TIME);
                    repaint();
                }
            }

            // The main function to that sorts arr[] of size n using
            // Radix Sort
            private void radixSortCall(int[] arr, int n) throws InterruptedException {
                // Find the maximum number to know number of digits
                int m = getMax(arr, n);

                // Do counting sort for every digit. Note that
                // instead of passing digit number, exp is passed.
                // exp is 10^i where i is current digit number
                for (int exp = 1; m / exp > 0; exp *= 10)

                    counterSort(arr, n, exp);
            }
        };
        sorter.execute();
    }

    public void selectionSort(int lengthArray, int[] barHeightArray) {
        sorter = new SwingWorker() {
            @Override
            public Void doInBackground() throws InterruptedException {
                twoPointers = true;
                drawInts = true;

                // One by one move boundary of unsorted subarray
                for (int i = 0; i < lengthArray -1; i++) {
                    // Find the minimum element in unsorted array
                    int min_idx = i;
                    for (int j = i+1; j < lengthArray; j++)
                        if (barHeightArray[j] < barHeightArray[min_idx]) {
                            min_idx = j;
                            p2 = j;
                            p1 = min_idx;
                            selectionCounter++;
                            ActionEventHandler.selectionNum.setText("Array swaps: " + selectionCounter);
                            Thread.sleep(Parameters.THREAD_TIME);
                            repaint();
                        }

                    // Swap the found minimum element with the first
                    // element
                    int temp = barHeightArray[min_idx];
                    barHeightArray[min_idx] = barHeightArray[i];
                    barHeightArray[i] = temp;
                    p2 = i;
                    p1 = min_idx;
                    selectionCounter++;
                    ActionEventHandler.selectionNum.setText("Array swaps: " + selectionCounter);
                    Thread.sleep(Parameters.THREAD_TIME);
                    repaint();
                }
                drawInts = false;
                twoPointers = false;
                Thread.sleep(Parameters.THREAD_TIME);
                repaint();
                return null;
            }
        };
        sorter.execute();
    }

    public void heapSort(int lengthArray, int[] barHeightArray) {
        sorter = new SwingWorker() {
            @Override
            public Void doInBackground() throws InterruptedException {
                drawInts = true;
                twoPointers = true;
                heapSortCall(barHeightArray, lengthArray);
                drawInts = false;
                twoPointers = false;
                Thread.sleep(Parameters.THREAD_TIME);
                repaint();
                return null;
            }

            private void heapSortCall(int[] arr, int arrLength) throws InterruptedException {

                // Build heap (rearrange array)
                for (int i = arrLength / 2 - 1; i >= 0; i--) {
                    heapify(arr, arrLength, i);
                }

                // One by one extract an element from heap
                for (int i = arrLength - 1; i > 0; i--) {
                    // Move current root to end
                    swap(arr, i, 0);
                    Thread.sleep(Parameters.THREAD_TIME);
                    heapCounter++;
                    ActionEventHandler.heapNum.setText("Array swaps: " + heapCounter);
                    p2 = 0;
                    p1 = i;
                    repaint();

                    // call max heapify on the reduced heap

                    heapify(arr, i, 0);
                }
            }

            // To heapify a subtree rooted with node i which is
            // an index in arr[]. n is size of heap
            private void heapify(int[] arr, int n, int i) throws InterruptedException {
                int largest = i; // Initialize largest as root
                int l = 2 * i + 1; // left = 2*i + 1
                int r = 2 * i + 2; // right = 2*i + 2

                // If left child is larger than root
                if (l < n && arr[l] > arr[largest])
                    largest = l;

                // If right child is larger than largest so far
                if (r < n && arr[r] > arr[largest])
                    largest = r;

                // If largest is not root
                if (largest != i) {
                    int swap = arr[i];
                    arr[i] = arr[largest];
                    arr[largest] = swap;
                    heapCounter++;
                    ActionEventHandler.heapNum.setText("Array swaps: " + heapCounter);

                    // Recursively heapify the affected sub-tree
                    Thread.sleep(Parameters.THREAD_TIME);
                    p2 = largest;
                    p1 = i;
                    repaint();
                    heapify(arr, n, largest);
                }
            }

        };
        sorter.execute();
    }

}
