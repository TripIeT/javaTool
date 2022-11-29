package javaTool.leTu.lib.validate;

import java.util.ArrayList;

import javaTool.leTu.lib.util.MyUtils;

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
public class Check {
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