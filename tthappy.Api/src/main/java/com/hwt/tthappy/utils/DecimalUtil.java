package com.hwt.tthappy.utils;
import java.math.BigDecimal;
import java.util.regex.Pattern;


/**
 * BigDecimal加减乘除运算；<br/>
 * 结果默认保留2位小数，可指定scale设置保留位数；<br/>
 * 末尾去0；
 */
public class DecimalUtil {
    private static final int SCALE = 2;

    /**
     * 统一n位小数
     *
     * @param v
     * @param scale
     * @return
     */
    public static BigDecimal uniValue(BigDecimal v, int scale) {
        return new BigDecimal(v.setScale(scale, BigDecimal.ROUND_HALF_UP)
                .stripTrailingZeros().toPlainString());
    }

    //region 加减乘除运算

    /**
     * 加法<br/>四舍五入保留2位小数位数
     */
    public static BigDecimal add(BigDecimal d1, BigDecimal d2) {
        return add(d1, d2, SCALE);
    }

    /**
     * 加法<br/>四舍五入保留指定位数
     */
    public static BigDecimal add(BigDecimal d1, BigDecimal d2, int scale) {
        return uniValue(d1.add(d2), scale);
    }


    /**
     * 减法：d1 - d2<br/>
     * 四舍五入保留2位小数位数
     */
    public static BigDecimal subtract(BigDecimal d1, BigDecimal d2) {
        return subtract(d1, d2, SCALE);
    }

    /**
     * 减法：d1 - d2<br/>
     * 四舍五入保留指定位数
     */
    public static BigDecimal subtract(BigDecimal d1, BigDecimal d2, int scale) {
        return uniValue(d1.subtract(d2), scale);
    }


    /**
     * 乘法<br/>四舍五入保留2位小数
     */
    public static BigDecimal multiply(BigDecimal d1, BigDecimal d2) {
        return multiply(d1, d2, SCALE);
    }

    /**
     * 乘法<br/>四舍五入保留指定位数
     */
    public static BigDecimal multiply(BigDecimal d1, BigDecimal d2, int scale) {
        return uniValue(d1.multiply(d2), scale);
    }


    /**
     * 除法：d1 ÷ d2<br/>
     * 四舍五入保留2位小数位数
     */
    public static BigDecimal divide(BigDecimal d1, BigDecimal d2) {
        return divide(d1, d2, SCALE);
    }

    /**
     * 除法：d1 ÷ d2<br/>
     * 四舍五入保留指定位数
     */
    public static BigDecimal divide(BigDecimal d1, BigDecimal d2, int scale) {
        return uniValue(d1.divide(d2, 10, BigDecimal.ROUND_HALF_UP), scale);
    }
    //endregion

    /**
     * 转为BigDecimal
     *
     * @param number 传入有效数字即可，任意数据类型
     */
    public static BigDecimal toBigDecimal(Object number) {
        String v = number.toString();
        boolean isNum = Pattern.matches("^(0|([1-9]\\d*))(\\.\\d+)?$", v);
        UserFriendlyException.throwException(!isNum, "调用toBigDecimal时传入的值“" + v + "”不是有效数字");
        return new BigDecimal(number.toString());
    }

    /**
     * 大于
     */
    public static boolean gt(BigDecimal sourceNumber, Object targetNumber) {
        return sourceNumber.compareTo(toBigDecimal(targetNumber)) > 0;
    }

    /**
     * 大于等于
     */
    public static boolean gte(BigDecimal sourceNumber, Object targetNumber) {
        return sourceNumber.compareTo(toBigDecimal(targetNumber)) >= 0;
    }

    /**
     * 大于等于
     */
    public static boolean lt(BigDecimal sourceNumber, Object targetNumber) {
        return sourceNumber.compareTo(toBigDecimal(targetNumber)) < 0;
    }

    /**
     * 小于等于
     */
    public static boolean lte(BigDecimal sourceNumber, Object targetNumber) {
        return sourceNumber.compareTo(toBigDecimal(targetNumber)) <= 0;
    }

    /**
     * 等于
     */
    public static boolean equals(BigDecimal sourceNumber, Object targetNumber) {
        return sourceNumber.equals(toBigDecimal(targetNumber));
    }
}
