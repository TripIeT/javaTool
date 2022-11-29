package javaTool.leTu.lib.util;

/**
 * {@code StringDefault} chứa các {@code String} chuẩn sử dụng nhiều cho
 * {@code RegEx} và {@code Contain}
 */
public class StrDefault {
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
