package javaTool.lib.util;

import java.util.Scanner;

import javaTool.lib.terminal.ColorTerminal;
import javaTool.lib.terminal.ColorTerminal.COLOR;
import javaTool.lib.validate.Check;

public class MyUtils extends StrDefault {

    static Scanner inputFrom = new Scanner(System.in);
    private static boolean betterTerminal = false;

    public static void betterUtil(boolean on) {
        betterTerminal = on;
    }

    public static String getString(String msg) {
        String input = "";
        do {
            ColorTerminal.format("%-10s: ", msg, betterTerminal ? COLOR.BLUE_BOLD_BRIGHT : COLOR.RESET);
            input = inputFrom.nextLine();

            if (input.isEmpty())
                ColorTerminal.println("empty string", betterTerminal ? COLOR.YELLOW_BOLD_BRIGHT : COLOR.RESET);
            else
                break;

        } while (true);

        return input;
    }

    public static String getStringRegex(String msg, String regex, String errorMsg) {
        String input = "";
        errorMsg = betterTerminal ? ColorTerminal.setColorString(errorMsg, COLOR.YELLOW_BOLD_BRIGHT) : errorMsg;

        do {
            input = getString(msg);
        } while (!Check.isFormat(input, regex, errorMsg));

        return input;
    }

    public static String getStringRegex(String msg, String regex) {
        return getStringRegex(msg, regex, "Wrong Format");
    }

    public static String getStringContain(String msg, String contain, String errorMsg) {
        String input = "";
        errorMsg = betterTerminal ? ColorTerminal.setColorString(errorMsg, COLOR.YELLOW_BOLD_BRIGHT) : errorMsg;

        do {
            input = getString(msg);
        } while (!Check.isContain(input, contain, errorMsg));

        return input;
    }

    public static String getStringContain(String msg, String contain) {
        return getStringContain(msg, contain, "Not Contain in " + contain);
    }

    public static float getFloat(String msg) {
        return (float) getDouble(msg);
    }

    public static float getFloat(String msg, float min, float max) {
        return (float) getDouble(msg, min, max);
    }

    public static double getDouble(String msg) {
        String input = "";
        double output = 0;
        do {
            input = getStringRegex(msg, DOUBLE_REGEX, "Invalid Float").replace(",", ".");

            if (Check.isNum(input))
                if ((output = Double.parseDouble(input)) > Double.MAX_VALUE)
                    System.out.println("Invalid Float");
                else
                    break;

        } while (true);

        return output;
    }

    public static double getDouble(String msg, double min, double max) {
        double input = 0;
        do {
            input = getDouble(msg);

            if (Check.isInRanged(input, min, max))
                break;
            else
                System.out.println("out of range (" + min + " - " + max + ")");

        } while (true);

        return input;
    }

    public static int getInt(String msg) {
        String input = "";
        int output = 0;

        do {
            input = getStringRegex(msg, INT_REGEX, "Invalid Integer");

            if (Check.isNum(input))
                try {
                    output = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException error) {
                    ColorTerminal.println("Invalid Integer",
                            betterTerminal ? COLOR.YELLOW_BOLD_BRIGHT : COLOR.RESET);
                }

        } while (true);

        return output;
    }

    public static int getInt(String msg, int min, int max) {
        int input = 0;
        do {
            input = getInt(msg);

            if (Check.isInRanged(input, min, max))
                break;
            else
                ColorTerminal.println("out of range (" + min + " - " + max + ")",
                        betterTerminal ? COLOR.YELLOW_BOLD_BRIGHT : COLOR.RESET);

        } while (true);

        return input;
    }

    public static boolean getBoolean(String msg) {
        String errorMsg = betterTerminal ? ColorTerminal.setColorString("Invalid Boolean", COLOR.YELLOW_BOLD_BRIGHT)
                : "Invalid Boolean";
        String input = getStringRegex(msg + " (Y/N)", BOOL_REGEX, errorMsg);

        return Check.isFormat(input, YES_REGEX);
    }

    public static String formatName(String name) {
        // - xóa khoảng trắng
        name = name.replaceAll(" +", " ").trim();

        // - xóa số
        name = name.replaceAll("\\d", "");

        // - xóa kí tự đặc biệt
        name = name.replaceAll("[^a-zA-Z\\s]", "");

        String nameSplit[] = name.split("\\s");
        for (String namePart : nameSplit) {
            String FirstLetter = namePart.substring(0, 1).toUpperCase();
            String OtherLetter = namePart.substring(1, namePart.length()).toLowerCase() + " ";
            name += (FirstLetter + OtherLetter);
        }

        name = name.trim();
        return name;
    }

    /**
     * <p>
     * y = swap(x, x=y)
     * <p>
     */
    public static double swap(double x, double y) {
        return x;
    }

    public static float swap(float x, float y) {
        return x;
    }

    public static int swap(int x, int y) {
        return x;
    }

    public static String swap(String x, String y) {
        return x;
    }
}
