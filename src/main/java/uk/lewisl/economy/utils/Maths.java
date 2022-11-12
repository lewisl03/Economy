package uk.lewisl.economy.utils;

import java.text.DecimalFormat;

/**
 * @author lewis
 * @since 12/11/2022
 */
public class Maths {
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static String withSuffix(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f %c",
                count / Math.pow(1000, exp),
                "kMGTPE".charAt(exp-1));
    }

    public static String longComma(long amount){
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);

    }
}
