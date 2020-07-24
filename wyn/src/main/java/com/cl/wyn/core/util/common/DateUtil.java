package com.cl.wyn.core.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期工具类
 *
 * @author cl 2017年2月28日
 */
public class DateUtil {

    public static final String DEFAULT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_DATE = "yyyy-MM-dd";

    public static final String BD_DATE_FORMAT = "yyyy/MM/dd";
    public static final String BD_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm";

    public static final String SHORT_DATE = "MM-dd";

    public static final String DATE_FMT_MONTH_END = "yyyy-MM";
    public static final String CAR_LICENSE_DATE_FORMAT = "yyyy/MM";

    public static final long MILLS_OF_DAY = 3600L * 24L * 1000L;

    public static final long MILLS_OF_HOUR = 3600L * 1000L;

    public static final long MILLS_OF_MINUTER = 60L * 1000L;

    public static final long MILLS_OF_SECOND = 1000L;

    public static final int ONE_YEAR = 365; //一年的天数
    public static final int ONE_MOUNTH = 365; //一月的天数


    public static Date parse(String input, String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        try {
            return sdf.parse(input);
        } catch (ParseException e) {

        }
        return null;
    }


    public static Date parse(String input) {
        return parse(input, DEFAULT_DATE_TIME);
    }


    public static Date parseMonthEnd(String input) {
        return parse(input, DATE_FMT_MONTH_END);
    }


    public static String format(Date date, String fmt) {
        try {
            if (date == null) {
                return "";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(fmt);
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }


    public static String formatToMonthEnd(Date date) {
        try {
            return format(date, DATE_FMT_MONTH_END);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 取一天中最大的时间的字符串描述
     *
     * @param date
     * @return
     */
    public static String ceilingToStr(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        return sm.format(date) + " 23:59:59";
    }


    /**
     * 取一天中最小的时间字符串描述
     *
     * @param date
     * @return
     */
    public static String floorToStr(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        return sm.format(date) + " 00:00:00";
    }

    /**
     * 取一天中最小的时间
     * @param date
     * @return
     */
    public static Date floorToDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 计算天
     *
     * @param date
     * @param changeValue 正数为增加，负数为减去
     * @return
     */
    public static Date changeDay(Date date, int changeValue) {
        if (date == null || changeValue == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, changeValue);
        return c.getTime();
    }


    /**
     * 判断目标时间 targetDate 距当前时间是否未在 days 天之内, 以天为单位
     *
     * @param targetDate
     * @param days
     * @return
     */
    public static boolean isInDaysFromNow(Date targetDate, int days) {
        long currentDateMills = new Date().getTime();
        long durationDay = (currentDateMills - targetDate.getTime()) / MILLS_OF_DAY;
        return durationDay <= days;
    }


//    /**
//     * 获取当天往前后往后几天的几点整的时间
//     *
//     * @param addDate
//     * @param hour
//     * @return
//     */
//    public static Date getAfterDate(int addDate, int hour) {
//        Date currentDate = new Date();
//        Date date = DateUtils.addDays(currentDate, addDate);
//        date = DateUtils.setHours(date, hour);
//        date = DateUtils.setMinutes(date, 0);
//        date = DateUtils.setSeconds(date, 0);
//        date = DateUtils.setMilliseconds(date, 0);
//        return date;
//    }


//    /**
//     * 获取当天往前后往后几年的0点时间
//     *
//     * @param
//     * @return
//     */
//    public static Date getAfterYear(int addYear) {
//        Date currentDate = new Date();
//        Date date = DateUtils.addYears(currentDate, addYear);
//        date = DateUtils.setHours(date, 0);
//        date = DateUtils.setMinutes(date, 0);
//        date = DateUtils.setSeconds(date, 0);
//        date = DateUtils.setMilliseconds(date, 0);
//        return date;
//    }


//    /**
//     * 获取某天往前后往后几天的几点整的时间
//     *
//     * @param currentDate
//     * @param addDate
//     * @param hour
//     * @return
//     */
//    public static Date getAfterDate(Date currentDate, int addDate, int hour) {
//        Date date = DateUtils.addDays(currentDate, addDate);
//        date = DateUtils.setHours(date, hour);
//        date = DateUtils.setMinutes(date, 0);
//        date = DateUtils.setSeconds(date, 0);
//        date = DateUtils.setMilliseconds(date, 0);
//        return date;
//    }


    /**
     * 模糊计算两个日期相差天数
     *
     * @param fDate
     * @param oDate
     * @return
     */
    public static int dateSubtract(Date fDate, Date oDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        int startDay = aCalendar.get(Calendar.DAY_OF_YEAR);
        int startYear = aCalendar.get(Calendar.YEAR);
        aCalendar.setTime(oDate);
        int endDay = aCalendar.get(Calendar.DAY_OF_YEAR);
        int endyear = aCalendar.get(Calendar.YEAR);

        return (endyear - startYear) * ONE_YEAR + (endDay - startDay);

    }

    public static Date addDay(Date date , int day){
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        aCalendar.add(Calendar.DAY_OF_YEAR, day);
        return aCalendar.getTime();
    }
    public static int getWeek(Date date) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        int result = aCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        return result == 0 ? 7 : result;
    }

    /**
     * 计算两个日期相差的月份
     *
     * @param begin
     * @param end
     * @return
     */
    public static int MonthSubtrac(Date begin, Date end) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(begin);
        int startMonth = aCalendar.get(Calendar.MONTH);
        int startYear = aCalendar.get(Calendar.YEAR);
        aCalendar.setTime(end);
        int endMonth = aCalendar.get(Calendar.MONTH);
        int endYear = aCalendar.get(Calendar.YEAR);
        return (endYear - startYear) * 12 + (endMonth - startMonth);
    }


    /**
     * 判断目标时间戳 距当前时间是否超过 seconds 秒
     *
     * @param timestamp 这里的单位为毫秒,不是 Unix 时间戳
     * @param seconds
     * @return
     */
    public static boolean isExceedCurrentBySeconds(long timestamp, int seconds) {
        long currentDateMills = new Date().getTime();
        return ((currentDateMills - timestamp) > (seconds * MILLS_OF_SECOND));
    }


    public static int getDay(Date date) {
        SimpleDateFormat formatDate = new SimpleDateFormat("MM");
        String format = formatDate.format(date);
        return Integer.valueOf(format);
    }

    /**
     * 相差多少分钟
     * @param date1
     * @param date2
     * @return
     */
    public static long subtractGetMinute(Date date1, Date date2){
        return (date1.getTime() - date2.getTime())/1000/60;
    }

    /**
     * 相差几天几时几分几秒
     * @param date1
     * @param date2
     * @return
     */
    public static String subtractGetDifference(Date date1, Date date2){
        StringBuilder sb = new StringBuilder();
        long difference = date1.getTime() - date2.getTime();
        if(difference < 0) {
            sb.append("-");
            difference = date2.getTime() - date1.getTime();
        }
        if(difference >= MILLS_OF_DAY) {
            sb.append(difference / MILLS_OF_DAY + "天");
            difference =  difference % MILLS_OF_DAY;
        }

        if(difference >= MILLS_OF_HOUR) {
            sb.append(difference / MILLS_OF_HOUR + "时");
            difference =  difference % MILLS_OF_HOUR;
        }
        if(difference >= MILLS_OF_MINUTER) {
            sb.append(difference / MILLS_OF_MINUTER + "分");
            difference =  difference % MILLS_OF_MINUTER;
        }
        if(difference >= MILLS_OF_SECOND) {
            sb.append(difference / MILLS_OF_SECOND + "妙");
            difference =  difference % MILLS_OF_SECOND;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //System.out.println(getDay(new Date()));

        //System.out.println(subtractGetMinute(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()-60000)));


//        System.out.println(subtractGetDifference(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()-4000l*24*1000)));
        System.out.println(addDay(new Date(), 60));
        System.out.println(getWeek(new Date()));
        System.out.println(floorToDate(new Date()));
    }
}
