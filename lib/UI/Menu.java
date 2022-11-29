package javaTool.lib.UI;

import java.util.ArrayList;

import javaTool.lib.util.MyUtils;


/**
 * {@code Menu()} dùng để tạo nhanh bảng menu với format đẹp
 * Là {@code Controct} dùng để nhận lần lượt các chuỗi
 * <blockquote>
 *
 * <pre>{@code
 * Menu mn = new Menu("a", "b", "c");
 * }</pre>
 *
 * </blockquote>
 * <p>
 * {@code show()} dùng để in ra menu và trả về lựa option được chọn từ menu
 * <p>
 * có thể thay đổi tên bảng menu thông qua {@code menuName}
 * <p>
 * default: {@code menuName} = MENU
 * \
 * <blockquote>
 *
 * <pre>{@code
 * // output
 * ====MENU====
 * 1. option a
 * 2. option b
 * 3. option c
 * 4. Exit
 * ============
 * Enter choice:
 * ------------
 * }</pre>
 *
 * </blockquote>
 */
public class Menu extends ArrayList<String> {
    private static int optMaxLength;

    /**
     * Là {@code Controct} dùng để nhận lần lượt các chuỗi
     * <blockquote>
     *
     * <pre>{@code
     * Menu mn = new Menu("a", "b", "c");
     * }</pre>
     *
     * </blockquote>
     *
     * @param args
     */
    public Menu(String... args) {
        optMaxLength = args[0].length();
        for (String each : args) {
            each = each.trim();
            optMaxLength = Math.max(each.length(), optMaxLength);
            this.add(each);
        }
    }

    /**
     * {@code show()} dùng để in ra menu và trả về lựa option được chọn từ menu
     * <p>
     * có thể thay đổi tên bảng menu thông qua {@code menuName}
     * <p>
     * default: {@code menuName} = MENU
     *
     * <blockquote>
     *
     * <pre>{@code
     * // output
     * ====MENU====
     * 1. option a
     * 2. option b
     * 3. option c
     * 4. Exit
     * ============
     * Enter choice:
     * ------------
     * }</pre>
     *
     * </blockquote>
     *
     * @param menuName
     * @return int
     */
    public int show() {
        return show("MENU");
    }

    /**
     * {@code show()} dùng để in ra menu và trả về lựa option được chọn từ menu
     * <p>
     * có thể thay đổi tên bảng menu thông qua {@code menuName}
     * <p>
     * default: {@code menuName} = MENU
     *
     * <blockquote>
     *
     * <pre>{@code
     * // output
     * ====MENU====
     * 1. option a
     * 2. option b
     * 3. option c
     * 4. Exit
     * ============
     * Enter choice: 
     * ------------
     * }</pre>
     *
     * </blockquote>
     *
     * @param menuName
     * @return int
     */
    public int show(String menuName) {
        if (this.isEmpty()) { // menu không có phần tử
            System.out.println("[]");
            return 0;
        }

        int numOfOpt = String.valueOf(this.size()).length();

        menuName = menuName.isEmpty() ? "menu" : menuName; // tên menu trống

        int strLength = optMaxLength + numOfOpt + 2;
        strLength = (strLength % 2 != 0) ? strLength - 1 : strLength; // chiều dài tối đa của menu không cân

        int lineLength = menuName.length();

        int nameLength = menuName.length();
        int minLength = ("Enter choice: " + this.size()).length();
        nameLength = (nameLength < minLength) ? minLength : nameLength; // chiều dài tối đa < chiều dài tối thiểu
        nameLength = (nameLength % 2 == 0) ? nameLength - 1 : nameLength; // tên menu không cân

        if (menuName.length() > optMaxLength) // tên menu dài hơn chiều dài tối đa
            System.out.print("==" + menuName.toUpperCase() + "==");
        else {
            int start = (nameLength % 2 == 0) ? 1 : 0;
            strLength = (nameLength % 2 == 0) ? strLength : strLength++;
            for (int i = start; i < strLength; i++) {
                if (i == (strLength / 2) - (nameLength / 2)) {
                    System.out.print(menuName.toUpperCase());
                    i += nameLength;
                }
                lineLength++;
                System.out.print("=");
            }
        }
        System.out.println();

        int count = 1;
        for (String each : this) // in ra index + option
            System.out.format("%-" + numOfOpt + "s. %-10s", count++, each + "\n");
        System.out.format("%-" + numOfOpt + "s. %-5s", 0, "Exit\n");

        String lowerString = "";
        for (int i = 0; i < ((menuName.length() > optMaxLength) ? menuName.length() + 4
                : lineLength); i++)
            lowerString += "-";

        System.out.println(lowerString);
        int choice = MyUtils.getInt("Enter choice", 0, this.size());
        System.out.println(lowerString.replaceAll("-", "="));

        return choice;
    }
}
