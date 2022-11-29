package javaTool.leTu.lib;

import java.util.ArrayList;

import javaTool.leTu.testObj;
import javaTool.leTu.testObj1;
import javaTool.leTu.lib.MyLib.ColorTerminal;
import javaTool.leTu.lib.MyLib.MyFile;
import javaTool.leTu.lib.MyLib.MyList;
import javaTool.leTu.lib.MyLib.Random;
import javaTool.leTu.lib.MyLib.ColorTerminal.COLOR;
import javaTool.leTu.lib.MyLib.Random.CASE;

public class TestLib {

    public static int getColumnCount(Class<?> a) {
        return a.getClass().getDeclaredFields().length;
    }

    public static void main(String[] args) {

        // ColorTerminal.println(Random.randString(20, CASE.UPPERCASE),
        // COLOR.BLACK_BOLD);
        // ColorTerminal.println(Random.randString(20, CASE.LOWERCASE),
        // COLOR.BLUE_BOLD);
        // ColorTerminal.println(Random.randString(20, CASE.INSENSITIVE_WITH_NUMBER),
        // COLOR.CYAN_BOLD);
        // ColorTerminal.println(Random.randString(20, CASE.INSENSITIVE),
        // COLOR.GREEN_BOLD);
        // ColorTerminal.println(Random.randString(20, CASE.UPPERCASE_WITH_NUMBER),
        // COLOR.MAGENTA_BOLD);
        // ColorTerminal.println(Random.randString(20, CASE.LOWERCASE_WITH_NUMBER),
        // COLOR.RED_BOLD);
        // ColorTerminal.println(Random.randString(20, CASE.INSENSITIVE_WITH_SPECIAL),
        // COLOR.YELLOW_BOLD);
        // ColorTerminal.println(Random.randString(20, CASE.UPPERCASE_WITH_SPECIAL),
        // COLOR.BLACK_UNDERLINED);
        // ColorTerminal.println(Random.randString(20, CASE.LOWERCASE_WITH_SPECIAL),
        // COLOR.BLUE_UNDERLINED);
        // ColorTerminal.println(Random.randString(20, CASE.INSENSITIVE_ALL),
        // COLOR.CYAN_UNDERLINED);
        // ColorTerminal.println(Random.randString(20, CASE.UPPERCASE_ALL),
        // COLOR.GREEN_UNDERLINED);
        // ColorTerminal.println(Random.randString(20, CASE.LOWERCASE_ALL),
        // COLOR.MAGENTA_UNDERLINED);
        // ColorTerminal.format("%-10s|\n", "test", COLOR.BLUE_BOLD_BRIGHT);

        // MyUtils.betterUtil(true);
        // System.out.println(MyUtils.getString("getString"));
        // System.out.println(MyUtils.getStringRegex("getStringRegex", "\\w"));
        // System.out.println(MyUtils.getStringContain("getStringContain",
        // "0123456789"));

        // Menu mn = new Menu(
        // "Add new CD ",
        // "Search by ",
        // "Display all",
        // "Update / Dele aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
        // "Save to file ",
        // "Print fr");
        // mn.show("test");

        // Menu menu = new Menu(
        // "Add new CD",
        // "Search by title",
        // "Display all CD",
        // "Update / Delete wwwwwwwwwwwwwwwwwww",
        // "Save to file",
        // "Print from file");
        // int choice = menu.show("table");
        // System.out.println(choice);
        // System.out.println(menu.toString());
        // ColorTerminal.formatln("%-10s|%-10s|%-10s",
        // "hello", 1, true, COLOR.BLUE_BOLD_BRIGHT);
        // System.out.format("%-10s|%-10s|%-10s|%-10s", "hello", "1", "2");
        ArrayList<testObj> a = new ArrayList<>();
        ArrayList<testObj1> b = new ArrayList<>();
        ArrayList<testObj1> c = new ArrayList<>();
        int maxEntity = 11;
        int maxA = 3;
        int minA = 3;

        // int maxB = Random.randInt(0, 10000);
        // int maxB = 11;
        // int maxA = Random.randInt(0, 10);
        for (int i = 0; i < maxEntity; i++)
            a.add(new testObj(
                    Random.randString(Random.randInt(minA, maxA), CASE.INSENSITIVE_ALL),
                    Random.randString(Random.randInt(minA, maxA), CASE.INSENSITIVE_ALL),
                    Random.randString(Random.randInt(minA, maxA), CASE.INSENSITIVE_ALL),
                    Random.randString(Random.randInt(minA, maxA), CASE.INSENSITIVE_ALL)));

        // for (int i = 0; i < maxEntity; i++)
        // b.add(new testObj1(
        // Random.randInt(0, maxB),
        // Random.randInt(0, maxB),
        // Random.randInt(0, maxB),
        // Random.randInt(0, maxB),
        // Random.randInt(0, maxB)));

        // MyFile<Product> list = new MyFile<>("Product.dat");

       MyList.printTable("a", a, "b", 1);
        // MyList.printTable("b", a, "colB", 1);
        // MyList.printTable("ta", b);
        // MyList.printTable("super long long table name abcde abcd", b);

        // ColorTerminal.format(" %s %s", "a", "b", COLOR.RED_BOLD_BRIGHT,
        // COLOR.YELLOW_BOLD_BRIGHT);
        // testObj1 x = new testObj1(1, 2, 3, 4, 5);
        // testObj y = new testObj("alo", "2dsa", "3", "4");
        // MyList.printList(a);
        // MyList.getInfo(y);
        // list.printTable("123456789", a);
        // list.printTable("12345678", a);

    }
}