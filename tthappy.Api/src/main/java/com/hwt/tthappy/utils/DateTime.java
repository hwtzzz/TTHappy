package com.hwt.tthappy.utils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author hu
 * @since 2023-9-07
 */

public class DateTime {
    private Date dateTime = null;

    //region 实例化一个datetime对象
    //无参数 默认使用当前系统时间
    public DateTime() {
        this.dateTime = new Date();
    }

    //传入一个日期时间
    public DateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * 传入标准日期时间字符串： yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd
     *
     * @param dateTimeString 传入标准日期时间字符串
     */
    public DateTime(String dateTimeString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateTimeString.contains(":") ? "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd");
            this.dateTime = sdf.parse(dateTimeString);
        } catch (Exception e) {
            throw new UserFriendlyException("日期时间“" + dateTimeString + "”转换失败");
        }
    }

    /**
     * 传入自定义格式的datetime字符串
     *
     * @param dateTimeString 日期时间字符串
     * @param formatPattern  自定义yyyyMMdd格式
     */
    public DateTime(String dateTimeString, String formatPattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
            this.dateTime = sdf.parse(dateTimeString);
        } catch (Exception e) {
            throw new UserFriendlyException("日期时间“" + dateTimeString + "”转换失败");
        }
    }
    //endregion

    //region 其他方法
    //清楚时间，只保留日期部分
    public DateTime clearTime() {
        Calendar c = Calendar.getInstance();
        c.setTime(this.dateTime);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        this.dateTime = c.getTime();
        return this;
    }

    //无值抛出异常
    void checkNullValue() {
        if (this.dateTime == null) {
            throw new NullPointerException("DateTime为Null");
        }
    }
    //endregion

    //region 返回值合计

    //返回经过一系列处理后的日期时间
    public Date getDateTime() {
        checkNullValue();
        return this.dateTime;
    }

    //返回datetime字符串
    public String toDateTimeString() {
        checkNullValue();
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.dateTime);
    }

    /**
     * 返回date字符串，格式：yyyy-MM-dd
     */
    public String toDateString() {
        checkNullValue();
        return new SimpleDateFormat("yyyy-MM-dd").format(this.dateTime);
    }

    /**
     * 返回自定义格式化字符串,遵循yyyyMMdd...格式
     */
    public String toFormatString(String formatPattern) {
        checkNullValue();
        return new SimpleDateFormat(formatPattern).format(this.dateTime);
    }
    //endregion

    //region 在datetime上增加年月日时分秒

    //addType是Calendar.MONTH这样的变量值
    void addNumber(int addType, int number) {
        checkNullValue();

        Calendar c = Calendar.getInstance();
        c.setTime(this.dateTime);
        c.add(addType, number);
        this.dateTime = c.getTime();
    }

    public DateTime addYear(int number) {
        addNumber(Calendar.YEAR, number);
        return this;
    }

    public DateTime addMonth(int number) {
        addNumber(Calendar.MONTH, number);
        return this;
    }

    public DateTime addDays(int number) {
        addNumber(Calendar.DATE, number);
        return this;
    }

    public DateTime addHour(int number) {
        addNumber(Calendar.HOUR, number);
        return this;
    }

    public DateTime addMinute(int number) {
        addNumber(Calendar.MINUTE, number);
        return this;
    }

    public DateTime addSecond(int number) {
        addNumber(Calendar.SECOND, number);
        return this;
    }

    public DateTime addMilliSecond(int number) {
        addNumber(Calendar.MILLISECOND, number);
        return this;
    }

    /**
     * 之前时间跟现在时间的间隔天数
     *
     * @param time
     * @return
     */
    public int timeInterval(String time) {
        DateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        int days = 0;
        try {
            Date startDate = simpleFormat.parse(time);
            Date endDate = simpleFormat.parse(simpleFormat.format(new Date()));
            long startTime = startDate.getTime();
            long endTime = endDate.getTime();
            days = (int) ((endTime - startTime) / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }


    //endregion


}
