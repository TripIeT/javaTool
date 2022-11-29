package javaTool.leTu.lib.util;

import java.util.*;

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
public class Random extends StrDefault {
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
