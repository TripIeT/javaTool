package javaTool.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Scanner;

import javaTool.lib.MyLib.ColorTerminal.COLOR;

public class MyLib {

    private static final Scanner inputFrom = new Scanner(System.in);

    /**
     * {@code StringDefault} chứa các {@code String} chuẩn sử dụng nhiều cho
     * {@code RegEx} và {@code Contain}
     */
    public static class StrDefault {
        public static final String ALPHABET_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
        public static final String ALPHABET_UPPERCASE = ALPHABET_LOWERCASE.toUpperCase();
        public static final String ALPHABET_ALLCASE = ALPHABET_UPPERCASE + ALPHABET_LOWERCASE;
        public static final String SPECIAL_CHAR = "[$&+,:;=?@#|'<>.-^*()%!]";
        public static final String NUMBER = "0123456789";
        public static final String ALL = ALPHABET_ALLCASE + SPECIAL_CHAR + NUMBER;

        public static final String INT_REGEX = "[+-]?(\\d+)";
        public static final String FLOAT_REGEX = "[+-]?([0-9]*([.]|,))?[0-9]+";
        public static final String DOUBLE_REGEX = FLOAT_REGEX;
        public static final String BOOL_REGEX = "(?i)(Y|N|T|F|1|0|true|false|yes|no|right|wrong)";
        public static final String YES_REGEX = "(?i)(Y|T|1|true|yes)";
        public static final String NO_REGEX = "(?i)(N|F|0|false|no)";
    }

    /**
     * {@code Random} các Data Type và Object
     *
     * <p>
     * Ví dụ để random 1 số bất kì theo kiểu {@code int} với miền giá trị từ
     * [ {@code Integer.MIN_VALUE}, {@code Integer.MAX_VALUE} ] user có thể:
     * <blockquote>
     *
     * <pre>{@code
     * Random.RandInt();
     * }</pre>
     *
     * </blockquote>
     * <p>
     * Với trường hợp random số {@code int} trong khoảng user có thể truyền vào
     * {@code RandInt()} với 2 giá trị {@code MIN} và {@code MAX} bất kì
     * <blockquote>
     *
     * <pre>{@code
     * Random.RandInt(MIN, MAX);
     * }</pre>
     *
     * </blockquote>
     * <p>
     * Với trường hợp {@code MIN > MAX} hàm {@code RandInt()} sẽ tự động swap 2 giá
     * trị
     * {@code MIN} và {@code MAX} để đảm bảo kết quả trả về đúng
     * <p>
     * Với kiểu {@code char} khi gọi đến {@code randChar()} user cần truyền vào
     * {@code CASE} cần thiết
     * <p>
     * Ở function() {@code randChar()} có 3 {@code CASE}:
     * <p>
     * {@code INSENSITIVE}: Không quan tâm đến
     * <p>
     * {@code UPPERCASE}: viêt hoa toàn bộ
     * <p>
     * {@code LOWERCASE}: viêt thường toàn bộ
     * <p>
     * Vẫn còn rất nhiều {@code CASE} khác nhưng {@code RandChar()} chỉ
     * nhận duy nhất 3 {@code CASE} này. Trường hợp truyền nhưng {@code CASE} khác
     * vào output sẽ là {@code null}
     * <blockquote>
     *
     * <pre>{@code
     * Random.RandChar(CASE.UPPERCASE);
     * }</pre>
     *
     * </blockquote>
     * <p>
     * Để random {@code String} cần dùng {@code RandString()}
     */
    public static class Random extends StrDefault {
        /**
         * {@code CASE} chứa các Case trong {@code String}
         * <p>
         * Bao gồm
         *
         * <pre>{@code
         * |INSENSITIVE
         * UPPERCASE
         * LOWERCASE
         *
         * INSENSITIVE_WITH_NUMBER
         * UPPERCASE_WITH_NUMBER
         * LOWERCASE_WITH_NUMBER
         *
         * INSENSITIVE_WITH_SPECIAL
         * UPPERCASE_WITH_SPECIAL
         * LOWERCASE_WITH_SPECIAL
         *
         * INSENSITIVE_ALL
         * UPPERCASE_ALL
         * LOWERCASE_ALL
         * }</pre>
         *
         * </blockquote>
         */
        enum CASE {
            // SENSITIVE(0),
            INSENSITIVE(ALPHABET_ALLCASE),
            UPPERCASE(ALPHABET_UPPERCASE),
            LOWERCASE(ALPHABET_LOWERCASE),

            INSENSITIVE_WITH_NUMBER(ALPHABET_ALLCASE + NUMBER),
            UPPERCASE_WITH_NUMBER(ALPHABET_UPPERCASE + NUMBER),
            LOWERCASE_WITH_NUMBER(ALPHABET_LOWERCASE + NUMBER),

            INSENSITIVE_WITH_SPECIAL(ALPHABET_ALLCASE + SPECIAL_CHAR),
            UPPERCASE_WITH_SPECIAL(ALPHABET_UPPERCASE + SPECIAL_CHAR),
            LOWERCASE_WITH_SPECIAL(ALPHABET_LOWERCASE + SPECIAL_CHAR),

            INSENSITIVE_ALL(ALL),
            UPPERCASE_ALL(ALPHABET_UPPERCASE + NUMBER + SPECIAL_CHAR),
            LOWERCASE_ALL(ALPHABET_LOWERCASE + NUMBER + SPECIAL_CHAR),
            ;

            private final String contain;

            CASE(String contain) {
                this.contain = contain;
            }

            public String getContain() {
                return contain;
            }

            @Override
            public String toString() {
                return contain;
            }
        }

        /**
         * Dùng để random số {@code double} trong khoảng xác định, user cần truyền vào
         * {@code RandDouble()} 2 giá trị {@code min} và {@code max} bất kì
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandDouble(min, max);
         * }</pre>
         *
         * </blockquote>
         * <p>
         * Hoặc có thể dùng như:
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandDouble();
         * }</pre>
         *
         * </blockquote>
         * <p>
         * để random với miền giá trị từ
         * <p>
         * [{@code Double.MIN_VALUE}, {@code Double.MAX_VALUE}]
         * <p>
         * Với trường hợp
         * {@code min > max} hàm {@code RandInt()} sẽ tự động swap 2 giá
         * trị {@code min} và {@code max} để đảm bảo kết quả trả về đúng
         *
         * @param min
         * @param max
         * @return double
         */
        public static double randDouble(double min, double max) {
            // DecimalFormat df = new DecimalFormat("+#,##0.00;-#");
            // DecimalFormat df = new DecimalFormat("#,##");

            if (min > max)
                min = MyUtils.swap(max, max = min);

            // return Double.valueOf(df.format((Double) (Math.random() * (max - min + 1) +
            // min)));
            return Double.valueOf((Double) (Math.random() * (max - min + 1) + min));
        }

        /**
         * Dùng để random số {@code double} trong khoảng xác định, user cần truyền vào
         * {@code RandDouble()} 2 giá trị {@code min} và {@code max} bất kì
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandDouble(min, max);
         * }</pre>
         *
         * </blockquote>
         * <p>
         * Hoặc có thể dùng như:
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandDouble();
         * }</pre>
         *
         * </blockquote>
         * <p>
         * để random với miền giá trị từ
         * <p>
         * [{@code Double.MIN_VALUE}, {@code Double.MAX_VALUE}]
         * <p>
         * Với trường hợp
         * {@code min > max} hàm {@code RandInt()} sẽ tự động swap 2 giá
         * trị {@code min} và {@code max} để đảm bảo kết quả trả về đúng
         *
         * @param min
         * @param max
         * @return double
         */
        public static double randDouble() {
            return randDouble(Double.MIN_VALUE, Double.MAX_VALUE);
        }

        /**
         * Dùng để random số {@code float} trong khoảng xác định, user cần truyền
         * vào
         * {@code RandFloat()} 2 giá trị {@code min} và {@code max} bất kì
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandFloat(min, max);
         * }</pre>
         *
         * </blockquote>
         * <p>
         * Hoặc có thể dùng như:
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandFloat();
         * }</pre>
         *
         * </blockquote>
         * <p>
         * để random với miền giá trị từ
         * <p>
         * [ {@code Float.MIN_VALUE}, {@code Float.MAX_VALUE} ]
         * <p>
         * Với trường hợp {@code min > max} hàm {@code RandFloat()} sẽ tự động swap 2
         * giá trị {@code min} và {@code max} để đảm bảo kết quả trả về đúng
         *
         * @param min
         * @param max
         * @return float
         */
        public static float randFloat(float min, float max) {
            return (float) randDouble(min, max);
        }

        /**
         * Dùng để random số {@code float} trong khoảng xác định, user cần truyền
         * vào
         * {@code RandFloat()} 2 giá trị {@code min} và {@code max} bất kì
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandFloat(min, max);
         * }</pre>
         *
         * </blockquote>
         * <p>
         * Hoặc có thể dùng như:
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandFloat();
         * }</pre>
         *
         * </blockquote>
         * <p>
         * để random với miền giá trị từ
         * <p>
         * [ {@code Float.MIN_VALUE}, {@code Float.MAX_VALUE} ]
         * <p>
         * Với trường hợp {@code min > max} hàm {@code RandFloat()} sẽ tự động swap 2
         * giá trị {@code min} và {@code max} để đảm bảo kết quả trả về đúng
         *
         * @param min
         * @param max
         * @return float
         */
        public static float randFloat() {
            return (float) randDouble(Float.MIN_VALUE, Float.MAX_VALUE);
        }

        /**
         * Dùng để random số {@code int} trong khoảng xác định, user cần truyền vào
         * {@code RandInt()} 2 giá trị {@code min} và {@code max} bất kì
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandInt(min, max);
         * }</pre>
         *
         * </blockquote>
         * <p>
         *
         * Hoặc có thể dùng như:
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandInt();
         * }</pre>
         *
         * </blockquote>
         * <p>
         * để random với miền giá trị từ
         * <p>
         * [ {@code Integer.MIN_VALUE}, {@code Integer.MAX_VALUE} ]
         * <p>
         * Với trường hợp {@code min > max} hàm {@code RandInt()} sẽ tự động swap 2 giá
         * trị {@code min} và {@code max} để đảm bảo kết quả trả về đúng
         *
         * @param min
         * @param max
         * @return int
         */
        public static int randInt(int min, int max) {
            return (int) randDouble(min, max);
        }

        /**
         * Dùng để random số {@code int} trong khoảng xác định, user cần truyền vào
         * {@code RandInt()} 2 giá trị {@code min} và {@code max} bất kì
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandInt(min, max);
         * }</pre>
         *
         * </blockquote>
         * <p>
         *
         * Hoặc có thể dùng như:
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandInt();
         * }</pre>
         *
         * </blockquote>
         * <p>
         * để random với miền giá trị từ
         * <p>
         * [ {@code Integer.MIN_VALUE}, {@code Integer.MAX_VALUE} ]
         * <p>
         * Với trường hợp {@code min > max} hàm {@code RandInt()} sẽ tự động swap 2 giá
         * trị {@code min} và {@code max} để đảm bảo kết quả trả về đúng
         *
         * @param min
         * @param max
         * @return int
         */
        public static int randInt() {
            return (int) randDouble(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        /**
         * Dùng để random {@code char} khi gọi đến {@code randChar()} user cần truyền
         * vào{@code CASE} cần thiết
         *
         * <p>
         * Ở function() {@code randChar()} có 3 {@code CASE}:
         * <p>
         * {@code INSENSITIVE}: Không quan tâm đến
         * <p>
         * {@code UPPERCASE}: viêt hoa toàn bộ
         * <p>
         * {@code LOWERCASE}: viêt thường toàn bộ
         * <p>
         * Vẫn còn rất nhiều {@code CASE} khác nhưng {@code RandChar()} chỉ
         * nhận duy nhất 3 {@code CASE} này. Trường hợp truyền nhưng
         * {@code CASE} khác
         * vào output sẽ là {@code null}
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandChar(CASE.UPPERCASE);
         * }</pre>
         *
         * </blockquote>
         *
         * @param c
         * @return
         */
        public static String randChar(CASE c) {
            switch (c) {
                case INSENSITIVE:
                    return randString(1, CASE.INSENSITIVE);
                case UPPERCASE:
                    return randStringContain(1, ALPHABET_UPPERCASE);
                case LOWERCASE:
                    return randStringContain(1, ALPHABET_LOWERCASE);
                default:
                    // System.out.println("CASE not supported");
                    return null;
            }
        }

        /**
         * {@code randContain()} Dùng để random ra 1 kí tự trong 1 String
         *
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandContain("abcxyz");
         *
         * // Output : y
         * }</pre>
         *
         * </blockquote>
         *
         * @param contain
         * @return
         */
        public static String randContain(String contain) {
            return "" + contain.charAt(randInt(0, contain.length() - 1));
        }

        /**
         * {@code randStringContain()} Dùng dể Random String trong khoảng Contain
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandStringContain(7, "abcxyz");
         *
         * // Output : xbayazy
         * }</pre>
         *
         * </blockquote>
         *
         * @param strLength
         * @param contain
         * @return String
         */
        public static String randStringContain(int strLength, String contain) {
            String output = "";
            for (int i = 0; i < strLength; i++)
                output += randContain(contain);

            return output;
        }

        /**
         * {@code randStringInList()} Dùng để Random 1 String bất kì trong List
         *
         * @param list
         * @return String
         */
        public static String randStringInList(ArrayList<String> list) {
            return String.valueOf(randObjectInList(list));
        }

        /**
         * {@code randString()} Random String bất kì theo CASE
         * <blockquote>
         *
         * <pre>{@code
         * Random.RandString(5, CASE.UPPERCASE);
         *
         * // Output : CADFZ
         * }</pre>
         *
         * </blockquote>
         *
         * @param strLength
         * @param c
         * @return String
         */
        public static String randString(int strLength, CASE c) {
            return randStringContain(strLength, c.getContain());
        }

        /**
         * {@code randObjectInList()} Random Object bất kì trong list
         *
         * @param list
         * @return
         */
        public static Object randObjectInList(ArrayList<?> list) {
            return list.get(randInt(0, list.size() - 1));
        }
    }

    /**
     * {@code Check} dùng để kiểm tra data
     * <p>
     * Tất cả function() trong {@code Check} đều trả về {@code Boolean}
     * <p>
     * {@code isNum()} là function() kiểm tra {@code input} có phải là số hay không
     * <blockquote>
     *
     * <pre>{@code
     * Check.isNum("1234");
     * Check.isNum("abcd");
     * }</pre>
     *
     * </blockquote>
     * <p>
     * {@code isInRanged()} là function() kiểm tra {@code x} có thuộc khoảng giá trị
     * [{@code min}, {@code max}] hay không
     * <p>
     * Trường hợp {@code min > max} hàm {@code RandFloat()} sẽ tự động swap 2
     * giá trị {@code min} và {@code max} để đảm bảo kết quả trả về đúng
     * <blockquote>
     *
     * <pre>{@code
     * int x = 5, min = 0, max = 10;
     * Check.isInRanged(5, 0, 10); // min <= x <= max
     * }</pre>
     *
     * </blockquote>
     * <p>
     * {@code isFormat()} là function() kiểm tra {@code input} trùng khớp với
     * {@code regEx} hay không
     * <p>
     * User có custom {@code ERROR MESSAGE} thông qua {@code errorMSg} hoặc không
     * <blockquote>
     *
     * <pre>{@code
     * Check.isFormat("10", "\\d+", "Not a Number"); // true
     * Check.isFormat("aaa", "\\d+"); // false
     * }</pre>
     *
     * </blockquote>
     * <p>
     * {@code isContain()} là function() kiểm tra {@code pattern} có chứa
     * {@code input} hay không
     * <p>
     * User có custom {@code ERROR MESSAGE} thông qua {@code errorMSg} hoặc không
     * <blockquote>
     *
     * <pre>{@code
     * Check.isContain("456", "0123456789", "Not Contain"); // true
     * Check.isContain("abc", "0123456789"); // false
     * }</pre>
     *
     */
    public static class Check {
        /**
         * {@code isNum()} là function() kiểm tra {@code input} có phải là số hay không
         * <blockquote>
         *
         * <pre>{@code
         * Check.isNum("1234");
         * Check.isNum("abcd");
         * }</pre>
         *
         * </blockquote>
         *
         * @param input
         * @return boolean
         */
        public static boolean isNum(String input) {
            if (input.isEmpty())
                return false;
            else
                try {
                    Double.parseDouble(input);
                } catch (NumberFormatException NaN) {
                    return false;
                }

            return true;
        }

        /**
         * {@code isInRanged()} là function() kiểm tra {@code x} có thuộc khoảng giá trị
         * [{@code min}, {@code max}] hay không
         * <p>
         * Trường hợp {@code min > max} hàm {@code RandFloat()} sẽ tự động swap 2
         * giá trị {@code min} và {@code max} để đảm bảo kết quả trả về đúng
         * <blockquote>
         *
         * <pre>{@code
         * int x = 5, min = 0, max = 10;
         * Check.isInRanged(5, 0, 10); // min <= x <= max
         * }</pre>
         *
         * </blockquote>
         *
         * @param x
         * @param min
         * @param max
         * @return boolean
         */
        public static boolean isInRanged(double x, double min, double max) {
            if (min > max)
                min = MyUtils.swap(max, max = min);

            return (min <= x && x <= max);
        }

        /**
         * {@code isExist()} là function() kiểm tra {@code Object} có tồn tại trọng
         * {@code list} không
         *
         * @param obj
         * @param list
         * @return boolean
         */
        public static boolean isExist(Object obj, ArrayList<?> list) {
            for (Object each : list)
                if (each.equals(obj))
                    return true;
            return false;
        }

        /**
         * {@code isFormat()} là function() kiểm tra {@code input} trùng khớp với
         * {@code regEx} hay không
         * <p>
         * User có custom {@code ERROR MESSAGE} thông qua {@code errorMSg} hoặc không
         * <blockquote>
         *
         * <pre>{@code
         * Check.isFormat("10", "\\d+", "Not a Number");
         * Check.isFormat("10", "\\d+");
         * }</pre>
         *
         * </blockquote>
         *
         * @param input
         * @param regex
         * @param errorMsg
         * @return boolean
         */
        public static boolean isFormat(String input, String regex, String errorMsg) {
            System.out.print((!input.matches(regex) && !errorMsg.isEmpty()) ? errorMsg + "\n" : "");
            return input.matches(regex);
        }

        /**
         * {@code isFormat()} là function() kiểm tra {@code input} trùng khớp với
         * {@code regEx} hay không
         * <p>
         * User có custom {@code ERROR MESSAGE} thông qua {@code errorMSg} hoặc không
         * <blockquote>
         *
         * <pre>{@code
         * Check.isFormat("10", "\\d+", "Not a Number");
         * Check.isFormat("aaa", "\\d+");
         * }</pre>
         *
         * </blockquote>
         *
         * @param input
         * @param regex
         * @param errorMsg
         * @return boolean
         */
        public static boolean isFormat(String input, String regex) {
            return input.matches(regex);
        }

        /**
         * {@code isContain()} là function() kiểm tra {@code pattern} có chứa
         * {@code input} hay không
         * <p>
         * User có custom {@code ERROR MESSAGE} thông qua {@code errorMSg} hoặc không
         * <blockquote>
         *
         * <pre>{@code
         * Check.isContain("456", "0123456789", "Not Contain");
         * Check.isContain("abc", "0123456789");
         * }</pre>
         *
         * </blockquote>
         *
         * @param input
         * @param pattern
         * @param errorMsg
         * @return boolean
         */
        public static boolean isContain(String input, String pattern, String errorMsg) {
            System.out.print((!pattern.contains(input) && !errorMsg.isEmpty()) ? errorMsg + "\n" : "");
            return pattern.contains(input);
        }

        /**
         * {@code isContain()} là function() kiểm tra {@code pattern} có chứa
         * {@code input} hay không
         * <p>
         * User có custom {@code ERROR MESSAGE} thông qua {@code errorMSg} hoặc không
         * <blockquote>
         *
         * <pre>{@code
         * Check.isContain("456", "0123456789", "Not Contain");
         * Check.isContain("abc", "0123456789");
         * }</pre>
         *
         * </blockquote>
         *
         * @param input
         * @param pattern
         * @param errorMsg
         * @return boolean
         */
        public static boolean isContain(String input, String pattern) {
            return input.contains(pattern);
        }
    }

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
    public static class Menu extends ArrayList<String> {
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

    /**
     * 
     */
    public static class MyObj {
        public static ArrayList<String> getAttribute(Object o) {
            ArrayList<String> attributeList = new ArrayList<>();
            Class<?> clazz = o.getClass();

            for (Field field : clazz.getDeclaredFields())
                attributeList.add(field.getName());

            return attributeList;
        }

        public static int getNumOfAtb(Class<?> obj) {
            return obj.getClass().getDeclaredFields().length;
        }

        public static ArrayList<String> getInfo(Object obj) {
            ArrayList<String> list = new ArrayList<>();

            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                Object o;
                try {
                    o = f.get(obj);
                } catch (Exception e) {
                    o = e;
                }

                list.add(String.valueOf(o));
            }

            return list;
        }
    }

    /**
     * {@code MyList} là {@code ArrayList} dùng để đọc file
     */
    public static class MyFile<T> extends ArrayList<T> {
        private String filePath;

        public MyFile() {
        }

        public MyFile(String filePath) {
            this.filePath = filePath;
            this.getFile(filePath);
        }

        public void update(ArrayList<T> list) {
            this.removeAll(this);
            this.addAll(list);
        }

        public void update(String filePath) {
            this.removeAll(this);
            this.getFile(filePath);
        }

        public static void setFile(String filePath, ArrayList<?> dataList) {
            try {
                FileOutputStream fis = new FileOutputStream(filePath);
                ObjectOutputStream oos = new ObjectOutputStream(fis);

                oos.writeInt(dataList.size());
                for (int i = 0; i < dataList.size(); i++)
                    oos.writeObject(dataList.get(i));

                oos.close();

                System.out.println("Saved " + ColorTerminal.setColorString("" + dataList.size(), COLOR.CYAN_BRIGHT)
                        + " item(s) to " + ColorTerminal.setColorString(filePath, COLOR.CYAN_BRIGHT));
            } catch (Exception e) {
                System.out.println("cannot save to " + ColorTerminal.setColorString(filePath, COLOR.CYAN_BRIGHT));
                System.out.println("Due to: " + e);
            }
        }

        public void setFile(String filePath) {
            setFile(filePath, this);
        }

        public void setFile() {
            setFile(filePath, this);
        }

        @SuppressWarnings("unchecked")
        public void getFile(String filePath) {
            try {
                FileInputStream fis = new FileInputStream(filePath);
                ObjectInputStream oos = new ObjectInputStream(fis);
                T obj;
                int length = oos.readInt();
                for (int i = 0; i < length; i++) {
                    obj = (T) oos.readObject();
                    this.add(obj);
                }

                System.out.println("Saved " + ColorTerminal.setColorString("" + this.size(), COLOR.CYAN_BRIGHT)
                        + " item(s) to " + ColorTerminal.setColorString(filePath, COLOR.CYAN_BRIGHT));

                oos.close();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("cannot save to " + ColorTerminal.setColorString(filePath, COLOR.CYAN_BRIGHT));
                System.out.println("Due to: " + e);
            }
        }

        public static ArrayList<String> getLineFromFile(String filePath) {
            ArrayList<String> list = new ArrayList<>();
            try {
                File f = new File(filePath);

                if (!f.exists())
                    return null;

                FileReader fr = new FileReader(filePath);
                BufferedReader br = new BufferedReader(fr);

                String detail;
                while ((detail = br.readLine()) != null)
                    list.add(detail);

                fr.close();
                br.close();
            } catch (IOException e) {
                System.out.println("cannot save to " + ColorTerminal.setColorString(filePath, COLOR.CYAN_BRIGHT));
                System.out.println("Due to: " + e);
            }

            return list;
        }

        public static void setLineToFile(String filePath, ArrayList<?> list) {
            File f = new File(filePath);
            FileWriter fw;
            try {
                fw = new FileWriter(f);
                PrintWriter pw = new PrintWriter(fw);

                list.forEach(each -> pw.println(String.valueOf(each)));

                fw.close();
                pw.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        public static ArrayList<String> getSubData(String filePath, String splitPattern, int... index) {
            ArrayList<String> list = getLineFromFile(filePath);
            ArrayList<String> aList = new ArrayList<>();

            list.forEach(each -> {
                each = each.toLowerCase();
                String fileName[] = each.split(splitPattern);
                for (int i : index)
                    aList.add(fileName[i]);
            });

            return aList;
        }
    }

    /**
     *
     *
     */
    public static class MyList {

        public static ArrayList<?> listToSet(ArrayList<?> list) {
            return list.isEmpty() ? null : new ArrayList<>(Arrays.asList(new HashSet<>(list)));
        }

        public static void printList(ArrayList<?> list) {
            if (list.isEmpty()) {
                System.out.println("[]");
                return;
            }
            list.forEach(each -> System.out.println(MyObj.getInfo(each)));
        }

        public static void printList(Class<?> list[]) {
            printList(new ArrayList<>(Arrays.asList(list)));
        }

        private static int getMaxStrLength(ArrayList<String> list) {
            int max = list.get(0).length();
            for (String each : list)
                max = Math.max(each.length(), max);

            return max;
        }

        private static boolean hasEmptyStr(ArrayList<String> list) {
            for (String each : list)
                if (each.length() == 0)
                    return true;

            return false;
        }

        private static ArrayList<String> getAllData(ArrayList<?> list) {
            ArrayList<String> allData = new ArrayList<>();
            list.forEach(each -> allData.addAll(MyObj.getInfo(each)));

            return allData;
        }

        public static void printTable(ArrayList<?> list, Object... renameAttribute) {
            printTable("Untitled", list, renameAttribute);
        }

        public static void printTable(String tableName, ArrayList<?> list, Object... renameAttribute) {
            if (list.isEmpty()) {
                System.out.println("[]");
                return;
            }

            ArrayList<String> atbList = MyObj.getAttribute(list.get(0));

            tableName = tableName.isEmpty() ? "Untitled" : tableName;

            if (atbList.isEmpty()) {
                atbList.add("null");
                tableName = "empty table";
            }

            if (renameAttribute != null) {
                ArrayList<Integer> renameIdx = new ArrayList<>();
                ArrayList<String> renameCol = new ArrayList<>();

                for (Object each : renameAttribute)
                    if (each instanceof Integer)
                        renameIdx.add((int) each);
                    else
                        renameCol.add(String.valueOf(each));

                int j = 0;
                for (int i : renameIdx)
                    atbList.set(i, renameCol.get(j++));
            }

            int numOfAtb = atbList.size();
            int dataLength = getMaxStrLength(getAllData(list));
            int atbLength = getMaxStrLength(atbList);

            int strLength = Math.max(dataLength, atbLength);
            if (strLength <= 4)
                if (hasEmptyStr(getAllData(list)))
                    strLength = 4;
                else if (numOfAtb % 2 != 0 && strLength % 2 != 0)
                    strLength++;
            strLength++;

            int idxLength = String.valueOf(list.size()).length();

            int tableLength = 0;
            int lineLength = 1;
            int nameLength = tableName.length();
            int max = Math.max(atbLength, dataLength);
            if (nameLength % 2 == 0) {
                System.out.println("name%2=0");
                if (max % 2 != 0) { // checked
                    System.out.println("max%2!=0");
                    if (max >= 4) {
                        System.out.println("max>=4");
                        if (idxLength <= 3) {
                            System.out.println("idx<=3");
                            idxLength = 3;
                        } else {
                            System.out.println("idx>3");
                            strLength = max + 1;
                            idxLength++;
                            tableLength++;
                        }
                    } else {
                        System.out.println("max<4");
                    }
                } else {
                    System.out.println("max%2!=0");
                    if (max >= 4) {
                        System.out.println("max>=4");
                        if (idxLength <= 3) {
                            System.out.println("idx<=3");
                            idxLength++;
                            tableLength = 1;
                        } else {
                            System.out.println("idx>3");
                        }
                    } else {
                        System.out.println("max<4");
                    }
                }
            } else {
                System.out.println("name%2!=0");
                if (max >= 4) {
                    System.out.println("max>=4");
                    if (idxLength <= 3) {
                        System.out.println("idx<=3");
                        if (max % 2 == 0) {
                            System.out.println("max%2=0");
                            if (idxLength < 3) {
                                System.out.println("idx<3");
                                idxLength = 2;
                            } else {
                                idxLength = 3;
                            }
                        } else {
                        }
                    }
                } else {
                    System.out.println("max<4");
                    if (idxLength <= 3) {
                        System.out.println("idx<=3");
                        if (max % 2 == 0) {
                            System.out.println("max%2=0");
                            if (idxLength < 3) {
                                System.out.println("idx=1|2");
                                idxLength = 2;
                                tableLength++;
                            } else {
                                System.out.println("idx>1|2");
                                // idxLength = 3;
                                // tableLength += idxLength + (numOfAtb * strLength) + numOfAtb + 2;

                            }
                        } else {
                            System.out.println("max%2!=0");
                            idxLength++;
                            tableLength = 1;
                        }
                    } else {
                        System.out.println("idx>3");
                        strLength += 2;
                        idxLength++;
                    }
                }
            }

            tableLength += idxLength + (numOfAtb * strLength) + numOfAtb + 2;
            // tableLength = (tableLength % 2 != 0 || idxLength % 2 != 0) ? tableLength - 1
            // : tableLength;

            // Title Bar
            lineLength = tableLength;

            System.out.println("Max        : " + max);
            System.out.println("NameLength : " + nameLength);
            System.out.println("DataLength : " + dataLength);
            System.out.println("AtbLength  : " + atbLength);
            System.out.println("IdxLength  : " + idxLength);
            System.out.println("TableLength: " + tableLength);
            System.out.println("StrLength  : " + strLength);
            System.out.println("LineLength : " + lineLength);

            if (tableName.length() > tableLength) {
                lineLength += 6;
                strLength = 1 + (lineLength - ((idxLength + 1) + numOfAtb)) / numOfAtb;
                System.out.println("long name's StrLength: " + strLength);
                System.out.print("===" + tableName.toUpperCase() + "===");
            } else {
                int start = 0;
                // tableLength += 1;

                for (int i = start; i < tableLength; i++) {
                    if (i == (tableLength / 2) - (nameLength / 2)) {
                        System.out.print(tableName.toUpperCase());
                        i += nameLength;
                    }
                    System.out.print("=");
                }
            }

            // Name Col
            System.out.format("\n|%-" + (idxLength + 1) + "s%s", "No.", "|");
            for (String each : atbList)
                ColorTerminal.format("%-" + strLength + "s%s", each, "|", COLOR.YELLOW_BOLD_BRIGHT);
            // other line
            String line = "";
            for (int i = 0; i < lineLength; i++)
                line += "-";
            System.out.println("\n" + line);

            for (int i = 0; i < list.size(); i++) {
                System.out.print("|");
                ColorTerminal.format("%-" + idxLength + "s.%s", i, "|", COLOR.CYAN_BOLD_BRIGHT);

                String head = "%-";
                for (String each : MyObj.getInfo(list.get(i)))
                    ColorTerminal.format(head + strLength + "s%s", each.isEmpty() ? "null" : each, "|",
                            each.isEmpty() ? COLOR.RED_BOLD_BRIGHT : COLOR.RESET);

                System.out.println();
            }

            System.out.println(line.replaceAll("-", "="));
        }
    }

    public static class MyUtils extends StrDefault {
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

    public static class ColorTerminal {
        public static enum COLOR {
            // Color end string, color reset
            RESET("\033[0m"),

            // Regular Colors. Normal color, no bold, background color etc.
            BLACK("\033[0;30m"), // BLACK
            RED("\033[0;31m"), // RED
            GREEN("\033[0;32m"), // GREEN
            YELLOW("\033[0;33m"), // YELLOW
            BLUE("\033[0;34m"), // BLUE
            MAGENTA("\033[0;35m"), // MAGENTA
            CYAN("\033[0;36m"), // CYAN
            WHITE("\033[0;37m"), // WHITE

            // Bold
            BLACK_BOLD("\033[1;30m"), // BLACK
            RED_BOLD("\033[1;31m"), // RED
            GREEN_BOLD("\033[1;32m"), // GREEN
            YELLOW_BOLD("\033[1;33m"), // YELLOW
            BLUE_BOLD("\033[1;34m"), // BLUE
            MAGENTA_BOLD("\033[1;35m"), // MAGENTA
            CYAN_BOLD("\033[1;36m"), // CYAN
            WHITE_BOLD("\033[1;37m"), // WHITE

            // Underline
            BLACK_UNDERLINED("\033[4;30m"), // BLACK
            RED_UNDERLINED("\033[4;31m"), // RED
            GREEN_UNDERLINED("\033[4;32m"), // GREEN
            YELLOW_UNDERLINED("\033[4;33m"), // YELLOW
            BLUE_UNDERLINED("\033[4;34m"), // BLUE
            MAGENTA_UNDERLINED("\033[4;35m"), // MAGENTA
            CYAN_UNDERLINED("\033[4;36m"), // CYAN
            WHITE_UNDERLINED("\033[4;37m"), // WHITE

            // Background
            BLACK_BACKGROUND("\033[40m"), // BLACK
            RED_BACKGROUND("\033[41m"), // RED
            GREEN_BACKGROUND("\033[42m"), // GREEN
            YELLOW_BACKGROUND("\033[43m"), // YELLOW
            BLUE_BACKGROUND("\033[44m"), // BLUE
            MAGENTA_BACKGROUND("\033[45m"), // MAGENTA
            CYAN_BACKGROUND("\033[46m"), // CYAN
            WHITE_BACKGROUND("\033[47m"), // WHITE

            // High Intensity
            BLACK_BRIGHT("\033[0;90m"), // BLACK
            RED_BRIGHT("\033[0;91m"), // RED
            GREEN_BRIGHT("\033[0;92m"), // GREEN
            YELLOW_BRIGHT("\033[0;93m"), // YELLOW
            BLUE_BRIGHT("\033[0;94m"), // BLUE
            MAGENTA_BRIGHT("\033[0;95m"), // MAGENTA
            CYAN_BRIGHT("\033[0;96m"), // CYAN
            WHITE_BRIGHT("\033[0;97m"), // WHITE

            // Bold High Intensity
            BLACK_BOLD_BRIGHT("\033[1;90m"), // BLACK
            RED_BOLD_BRIGHT("\033[1;91m"), // RED
            GREEN_BOLD_BRIGHT("\033[1;92m"), // GREEN
            YELLOW_BOLD_BRIGHT("\033[1;93m"), // YELLOW
            BLUE_BOLD_BRIGHT("\033[1;94m"), // BLUE
            MAGENTA_BOLD_BRIGHT("\033[1;95m"), // MAGENTA
            CYAN_BOLD_BRIGHT("\033[1;96m"), // CYAN
            WHITE_BOLD_BRIGHT("\033[1;97m"), // WHITE

            // High Intensity backgrounds
            BLACK_BACKGROUND_BRIGHT("\033[0;100m"), // BLACK
            RED_BACKGROUND_BRIGHT("\033[0;101m"), // RED
            GREEN_BACKGROUND_BRIGHT("\033[0;102m"), // GREEN
            YELLOW_BACKGROUND_BRIGHT("\033[0;103m"), // YELLOW
            BLUE_BACKGROUND_BRIGHT("\033[0;104m"), // BLUE
            MAGENTA_BACKGROUND_BRIGHT("\033[0;105m"), // MAGENTA
            CYAN_BACKGROUND_BRIGHT("\033[0;106m"), // CYAN
            WHITE_BACKGROUND_BRIGHT("\033[0;107m"), // WHITE

            ;

            private final String colorCode;

            COLOR(String code) {
                this.colorCode = code;
            }

            public String getCode() {
                return colorCode;
            }

            public ArrayList<COLOR> getColorList() {
                return new ArrayList<COLOR>(EnumSet.allOf(COLOR.class));
            }

            @Override
            public String toString() {
                return colorCode;
            }
        }

        private static ColorTerminal ct = new ColorTerminal();

        ColorTerminal() {
        }

        ColorTerminal(COLOR color) {
            System.out.print(color);
        }

        public static void println(Object msg, COLOR color) {
            System.out.print(color);
            System.out.println(String.valueOf(msg));
            ct.close();
        }

        public static void print(Object msg, COLOR color) {
            System.out.print(color);
            System.out.print(String.valueOf(msg));
            // ct.close();
        }

        public static void format(String format, Object... args) {
            ArrayList<Object> objList = new ArrayList<>();
            ArrayList<COLOR> colorList = new ArrayList<>();
            ArrayList<String> formatList = new ArrayList<>(Arrays.asList(format.split("%")));

            formatList.set(1, formatList.get(0) + "%" + formatList.get(1));
            formatList.remove(0);

            for (int i = 1; i < formatList.size(); i++)
                formatList.set(i, "%" + formatList.get(i));

            for (Object each : args)
                if (each instanceof COLOR)
                    colorList.add((COLOR) each);
                else
                    objList.add(each);

            for (int i = 0; i < objList.size(); i++)
                try {
                    System.out.print(colorList.get(i));
                    System.out.format(formatList.get(i), String.valueOf(objList.get(i)));
                } catch (Exception ex) {
                    for (int j = i; j < formatList.size(); j++)
                        try {
                            System.out.format(formatList.get(j), String.valueOf(objList.get(i)));
                        } catch (Exception e) {
                            System.out.println();
                            System.out.format(formatList.get(j), String.valueOf(objList.get(i))); // throw error
                        }
                    break;
                } finally {
                    ct.close();
                }

            ct.close();
        }

        public static void formatln(String format, Object... args) {
            format(format, args);
            System.out.println();
        }

        public static String setColorString(String msg, COLOR color) {
            return "" + color + msg + COLOR.RESET;
        }

        public void close() {
            System.out.print(COLOR.RESET);
        }
    }
}
