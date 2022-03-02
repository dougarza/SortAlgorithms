package com.company;

public class Parameters {
    // Constants used throughout project

    // Frame size
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 600;

    // Panel widths for each setting: 1 = single panel, 2 = two panels, 4 = four panels
    public static final int WIDTH_1 = (WIDTH * 7) / 8;
    public static final int WIDTH_2 = WIDTH_1;
    public static final int WIDTH_4 = WIDTH_2 / 2 - 1;

    // Panel heights for each setting
    public static final int HEIGHT_1 = HEIGHT;
    public static final int HEIGHT_2 = HEIGHT_1 / 2 - 1;
    public static final int HEIGHT_4 = HEIGHT_2;

    // non integer bar width for each setting
    public static double BAR_WIDTH_1_inter = WIDTH_1 * 1.0 / HEIGHT_1;
    public static double BAR_WIDTH_2_inter = WIDTH_2 * 1.0 / HEIGHT_2;
    public static double BAR_WIDTH_4_inter = WIDTH_4 * 1.0 / HEIGHT_4;

    // integer value bar widths
    public static final int BAR_WIDTH_1 = (int) Math.ceil(BAR_WIDTH_1_inter);
    public static final int BAR_WIDTH_2 = (int) Math.ceil(BAR_WIDTH_2_inter);
    public static final int BAR_WIDTH_4 = (int) Math.ceil(BAR_WIDTH_4_inter);

    // non integer number of bars
    public static double NUM_BARS_1_inter = WIDTH_1 * 1.0 / BAR_WIDTH_1;
    public static double NUM_BARS_2_inter = WIDTH_2 * 1.0 / BAR_WIDTH_2;
    public static double NUM_BARS_4_inter = WIDTH_4 * 1.0 / BAR_WIDTH_4;

    // integer value number of bars
    public static final int NUM_BARS_1 = (int) Math.floor(NUM_BARS_1_inter);
    public static final int NUM_BARS_2 = (int) Math.ceil(NUM_BARS_2_inter);
    public static final int NUM_BARS_4 = (int) Math.floor(NUM_BARS_4_inter);

    // 10 ms pause for animating
    public static int THREAD_TIME = 10;
}
