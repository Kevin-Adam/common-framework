package com.cherry.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;


/**
 * @author zhangpcxy@163.com
 * @create 2018/7/10 10:23
 * @desc 日期工具类
 */
public class DateUtils {

    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    public static String DATE_PATTERN_GMT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public static String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_PATTERN3 = "yyyyMMdd";
    public static String DATE_PATTERN4 = "yyyyMMddHHmmss";
    public static String DATE_PATTERN5 = "yyyy-MM-dd";



    /**
     * 获得当前时间
     *
     * @return
     */
    public static Date getCurrentDateTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        String result = DateUtils.getDateTime(date);
        try {
            return sdf.parse(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取 yyyy-MM-dd
     *
     * @return
     */
    public static String getCurrentSimpleDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN5);
        return sdf.format(getCurrentDateTime());
    }

    public static String getCurrentDateTimeToStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN4);
        return sdf.format(getCurrentDateTime());
    }

    public static String getCurrentDateTimeToStr2() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        return sdf.format(getCurrentDateTime());
    }

    public static Long getWmsupdateDateTime() {
        Calendar cl = Calendar.getInstance();
        return cl.getTimeInMillis();
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                java.text.DateFormat df = new SimpleDateFormat(format);
                result = df.format(date);
            }
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 格式化日期 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDateByFormat(date, DATE_PATTERN5);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param format
     * @return 2018-05-10 17:19:45
     */
    public static String formatDateByFormat(Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 将日期字符串解析为Date对象
     *
     * @param dateStr
     * @return (Thu May 10 17 : 13 : 52 CST 2018)
     */
    public static Date parseStringToDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            date = new Date();
        }
        return date;
    }

    /**
     * 将日期字符串解析为Date对象
     *
     * @param dateStr
     * @return
     */
    public static Date parseStringToDate2(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN5);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            date = new Date();
        }
        return date;
    }

    /**
     * 将日期字符串解析为Date对象 注意：字符串必须是 2018-01-01 格式
     *
     * @param date
     * @param format
     * @return Mon Jan 01 00:00:00 CST 2018
     */
    public static Date getDateByFormat(String date, String format) {
        Date result = null;
        if (date != null && !date.trim().equals("")) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.parse(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取当前星期几
     *
     * @return
     */
    public static DayOfWeek getDayOfWeek() {
        return LocalDate.now().getDayOfWeek();
    }

    /**
     * Description: 1：周一，2：周二，3：周三，4：周四，5：周五，6：周六，7：周日
     *
     * @param date
     * @return
     * @Version1.0 2016年7月28日 下午3:34:38 by 张宪斌（zhangxianbin@dangdang.com）创建
     */
    public static int getDayOfWeek(java.util.Date date) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(date);
        int day = c.get(java.util.Calendar.DAY_OF_WEEK);
        return day > 1 ? day - 1 : 7;
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param miliSeconds 精确到毫秒秒的字符串
     * @return
     */
    public static String timeStamp2Date(String miliSeconds, String format) {
        if (miliSeconds == null || miliSeconds.isEmpty() || miliSeconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(miliSeconds)));
    }


    /**
     * 给日期时间类型增加小时
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addDateMinut(Date date, int hour) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);

        if (date == null){
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24小时制
        date = cal.getTime();
        cal = null;
        return date;

    }

    /**
     * 给日期数据加上时间
     *
     * @param date
     * @param timeStr
     * @return
     */
    public static Date getDateTime(Date date, String timeStr) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN5);
        SimpleDateFormat formatNew = new SimpleDateFormat(DATE_PATTERN);
        if (date == null){
            return null;
        }
        String dateStr = format.format(date) + " " + timeStr;
        try {
            date = formatNew.parse(dateStr);
        } catch (ParseException e) {
            logger.info("日期格式转换失败  date：{}" + dateStr);
            e.printStackTrace();
        }
        return date;

    }


    //test
    public static void main(String[] args) throws Exception {
        Date date = getDate(new Date(), 1);
        System.out.println(date);
    }

    /**
     * 给特定日期增加或减少相应的天数
     *
     * @param date
     * @param diffDays
     * @return
     */
    public static Date getDate(Date date, int diffDays) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN5);
        if (date == null) {
            return null;
        }
        String specifiedDay = format.format(date);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(specifiedDay));
            c.set(Calendar.DATE, c.get(Calendar.DATE) + diffDays);
            return format.parse(format.format(c.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 计算给定日期和当前日期的天数差值
     *
     * @param date
     * @return
     */
    public static int diffDateDays(String date) {
        Date date0 = getdate(date);
        Date date1 = new Date();
        return (int) ((getMillis(getOnlyDay(date0)) - getMillis(getOnlyDay(date1))) / (24 * 3600 * 1000));
    }

    /**
     * 计算两个日期的天数差值
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int diffDaysByDate(String beginDate, String endDate) {
        Date date0 = getdate(beginDate);
        Date date1 = getdate(endDate);
        return (int) ((getMillis(getOnlyDay(date1)) - getMillis(getOnlyDay(date0))) / (24 * 3600 * 1000));
    }

    public static Date getdate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN5);
        Date resultDate = null;
        try {
            resultDate = sdf.parse(date);
        } catch (Exception e) {
        }
        return resultDate;
    }

    /**
     * @param date
     * @return Tue Jul 10 00:00:00 CST 2018
     */
    public static Date getOnlyDay(Date date) {
        try {
            String pattern = DATE_PATTERN5;
            String formatted = format(date, pattern);
            Date onlyDay = getDateByFormat(formatted, pattern);
            return onlyDay;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据日期获取年份
     *
     * @param date
     * @return 2018
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static LocalDateTime getDate(String s, int before) {
        LocalDateTime date = LocalDateTime.now();
        return date.minusDays(before);
    }

    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    public static int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    public static int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    public static String getDate(Date date, String formatStr) {
        try {
            return format(date, formatStr);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getTime(Date date) {
        try {
            return format(date, "HH:mm:ss");
        } catch (Exception e) {
            return null;
        }

    }

    public static String getDateTime(Date date) {
        try {
            return format(date, DATE_PATTERN);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 日期相加
     *
     * @param date
     * @param day
     * @return Wed Jul 11 11:23:23 CST 2018
     */
    public static Date addDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
        return c.getTime();
    }

    /**
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + ((long) minute) * 60 * 1000);
        return c.getTime();
    }

    /**
     * @param date
     * @return
     */
    public static Date addMillSecond(Date date, long millSecond) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + millSecond);
        return c.getTime();
    }

    /**
     * 日期相减(第一个参数减第二个参数返回天数) 例：2号的00:00:01 和 1号的23:59:59 相差1天
     *
     * @param date  Date
     * @param date1 Date
     * @return int
     */
    public static int diffDateDays(Date date, Date date1) {
        return (int) ((getMillis(getOnlyDay(date)) - getMillis(getOnlyDay(date1))) / (24 * 3600 * 1000));
    }

    /**
     * 日期相减
     *
     * @param date  Date
     * @param date1 Date
     * @return int
     */
    public static int diffDate(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
    }

    /**
     * 日期相减(返回秒值)
     *
     * @param date  Date
     * @param date1 Date
     * @return int
     * @author
     */
    public static Long diffDateTime(Date date, Date date1) {
        return (Long) ((getMillis(date) - getMillis(date1)) / 1000);
    }

    /**
     * @param date
     * @return Thu Dec 07 00:00:00 CST 2017
     * @throws Exception
     */
    public static Date getPayDate(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date1 = null;
        try {
            date1 = sdf.parse(date);
        } catch (Exception e) {
            try {
                sdf = new SimpleDateFormat("yyyyMMdd");
                date1 = sdf.parse(date);
            } catch (Exception e1) {
                try {
                    date1 = getdateFromString(date, DATE_PATTERN);
                } catch (Exception e2) {

                }
            }
        }
        return date1;
    }

    public static java.util.Date getdateFromString(String dateString, String format) {
        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取今天的最大时间
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static Date getMaxTimeByStringDate(String date) throws Exception {
        String maxTime = date + " 23:59:59";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(maxTime);
    }

    /**
     * 获取今天的最小时间
     *
     * @param date
     * @return Tue Jul 10 00:00:00 CST 2018
     * @throws Exception
     */
    public static Date getMinTimeByStringDate(String date) throws Exception {
        String maxTime = date + " 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(maxTime);
    }

    /**
     * 当前天数+days
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDaysOnToday(Date date, int days) {
        Calendar calendar = new java.util.GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        return date;
    }

    /**
     * 计算当前时间与当天结束时间相差的秒数
     *
     * @return
     * @throws Exception
     */
    public static int getSecondsToNextDay() throws Exception {
        String simpleDate = getCurrentSimpleDate();
        Date maxDate = getMaxTimeByStringDate(simpleDate);
        return (int) ((maxDate.getTime() - System.currentTimeMillis()) / 1000);

    }

    /**
     * "yyyyMMdd"
     *
     * @param date           开始时间
     * @param startAfterDays 时间区开始时间与开始时间
     * @param endAfterDays   e.g: LocalDate.now()=20170907
     *                       getDayRangList(LocalDate.now(),1,3,"yyyyMMdd") = [20170908, 20170909]
     * @return List<String> [(startDay+startAfterDays), (startDay+endAfterDays) )
     */
    public static List<String> getDayRangList(LocalDate date, Integer startAfterDays, Integer endAfterDays, String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        List<String> dayRangeList = new LinkedList<>();
        LocalDate startDay = date.plusDays(startAfterDays);
        for (int i = 0, end = endAfterDays - startAfterDays; i < end; i++) {
            dayRangeList.add(dtf.format(startDay.plusDays(i)));
        }
        return dayRangeList;
    }

    /**
     * 获取指定时间范围内日期串
     *
     * @param startDate
     * @param plusDays  startDate 之后多少天
     * @param pattern
     * @return plusDays + 1 个日志字符串列表
     */
    public static List<String> getDayRangList(LocalDate startDate, Integer plusDays, String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        List<String> dayRangeList = new LinkedList<>();
        LocalDate endDate = startDate.plusDays(plusDays);
        Stream.iterate(startDate, d -> d.plusDays(1))
                .limit(ChronoUnit.DAYS.between(startDate, endDate) + 1).forEach(o -> dayRangeList.add(dtf.format(o))
        );
        return dayRangeList;
    }

    /**
     * @param startDateStr 例如: 20161118
     * @param endDateStr   例如：20161120
     * @return 20161118, 20161119 注意 不包括20161120
     */
    public static List<String> getDateRange(String startDateStr, String endDateStr) {

        List<String> dateStrList = new ArrayList<String>();
        Date startDate = DateUtils.getOnlyDay(getdateFromString(startDateStr, DATE_PATTERN3));
        Date endDate = DateUtils.getOnlyDay(getdateFromString(endDateStr, DATE_PATTERN3));

        for (Date date = startDate; date.getTime() < endDate.getTime(); date = DateUtils.addDate(date, 1)) {
            dateStrList.add(DateUtils.getDate(date, DATE_PATTERN3));
        }
        return dateStrList;
    }

    /**
     * @param startDate
     * @param endDate
     * @return 20161118, 20161119 注意 不包括20161120
     */
    public static List<String> getDateRange(Date startDate, Date endDate) {
        startDate = getOnlyDay(startDate);
        endDate = getOnlyDay(endDate);
        List<String> dateStrList = new ArrayList<String>();

        for (Date date = startDate; date.getTime() < endDate.getTime(); date = DateUtils.addDate(date, 1)) {
            dateStrList.add(DateUtils.getDate(date, DATE_PATTERN3));
        }
        return dateStrList;
    }

    /**
     * @param startDateStr 例如: 20161118
     * @param endDateStr   例如：20161120
     * @return 20161118, 20161119 注意 不包括20161120
     */
    public static List<String> getDateRange(String startDateStr, String endDateStr, String format) {
        List<String> dateStrList = new ArrayList<String>();
        Date startDate = DateUtils.getOnlyDay(getdateFromString(startDateStr, format));
        Date endDate = DateUtils.getOnlyDay(getdateFromString(endDateStr, format));

        for (Date date = startDate; date.getTime() < endDate.getTime(); date = DateUtils.addDate(date, 1)) {
            dateStrList.add(DateUtils.getDate(date, format));
        }
        return dateStrList;
    }

    /**
     * @param startDate
     * @param endDate
     * @return 20161118, 20161119 注意 不包括20161120
     */
    public static List<String> getDateRange(Date startDate, Date endDate, String format) {
        startDate = getOnlyDay(startDate);
        endDate = getOnlyDay(endDate);
        List<String> dateStrList = new ArrayList<String>();
        for (Date date = startDate; date.getTime() < endDate.getTime(); date = DateUtils.addDate(date, 1)) {
            dateStrList.add(DateUtils.getDate(date, format));
        }
        return dateStrList;
    }

    /**
     * @param localDate
     * @return
     */
    public static DayOfWeek getDayOfWeek(LocalDate localDate) {
        return null == localDate ? null : localDate.getDayOfWeek();
    }

    /**
     * @param second    时间范围，单位秒
     * @param times     获取随机时间的个数
     * @param startDate 开始时间，可以为空，默认当前时间
     * @comment 获取从某一时间开始，一定时间内随机的几个时间
     * @date 2017年3月24日 上午11:03:44
     */
    public static List<Date> getRandomDate(Integer second, Integer times, Date startDate) {
        if (second == null || second <= 0) {
            return null;
        }
        if (times == null || times <= 0) {
            return null;
        }
        if (startDate == null) {
            startDate = new Date();
        }
        List<Date> randomDateList = new ArrayList<Date>();
        while (times > 1) {
            Random random = new Random();
            int thisSecond = 2 * second / times;
            int randomSecond = random.nextInt(thisSecond);
            Date randomDate = new Date(startDate.getTime() + randomSecond * 1000);
            randomDateList.add(randomDate);
            startDate = randomDate;
            second = second - randomSecond;
            times--;
        }
        Random random = new Random();
        randomDateList.add(new Date(startDate.getTime() + random.nextInt(second) * 1000));
        return randomDateList;
    }

    public static Date getDate(long millSecond) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millSecond);
        return c.getTime();
    }

    /**
     * @param minDate
     * @param maxDate
     * @Title: getIntervalHours
     * @Description: 【获取时间间隔小时】
     * @return: double
     */
    public static double getIntervalHours(Date minDate, Date maxDate) {
        double days = 0.0;
        if (minDate == null || maxDate == null) {
            return days;
        }
        try {
            days = getIntervalMinutes(minDate, maxDate) / 60;
        } catch (Exception e) {
            return 0;
        }
        return days;
    }

    /**
     * @param minDate
     * @param maxDate
     * @Title: getIntervalHours
     * @Description: 【获取时间间隔分钟】
     * @return: double
     */
    public static double getIntervalMinutes(Date minDate, Date maxDate) {
        double days = 0.0;
        if (minDate == null || maxDate == null) {
            return days;
        }
        try {
            long interval = maxDate.getTime() - minDate.getTime();
            System.out.println(maxDate.getTime() + "--" + minDate.getTime());
            days = Double.valueOf(interval) / 1000 / 60;
        } catch (Exception e) {
            return 0;
        }
        return days;
    }

    /**
     * @param date 所要获取星期几的日期
     * @param type 所要获取周几的类型：1:中文 星期一，2:英文大写全拼
     * @return
     */
    public static String getWeekdayByDate(Date date, int type) {
        List<String> weeks = new ArrayList<>();
        if (type == 1) {
            weeks.add(0, "星期日");
            weeks.add(1, "星期一");
            weeks.add(2, "星期二");
            weeks.add(3, "星期三");
            weeks.add(4, "星期四");
            weeks.add(5, "星期五");
            weeks.add(6, "星期六");
        } else if (type == 2) {
            weeks.add(0, "SUNDAY");
            weeks.add(1, "MONDAY");
            weeks.add(2, "TUESDAY");
            weeks.add(3, "WEDNESDAY");
            weeks.add(4, "THURSDAY");
            weeks.add(5, "FRIDAY");
            weeks.add(6, "SATURDAY");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int weekIndex = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (weekIndex < 0) {
            weekIndex = 0;
        }
        return weeks.get(weekIndex);
    }

    /**
     * 传入2018-12-12 返回 2018-12-12 00:00:00
     *
     * @param date
     * @return
     * @Description 获取传入日期当天的开始时刻
     */
    public static Date getStartDay(Date date) {
        if (null == date) {
            date = new Date();
        }
        String startDay = DateUtils.formatDate(date) + " 00:00:00";
        return DateUtils.getDateByFormat(startDay, DateUtils.DATE_PATTERN);
    }

    /**
     * 传入2018-12-12 返回 2018-12-12 23:59:59
     *
     * @param date
     * @return
     * @Description 获取传入日期当天的结束时刻
     */
    public static Date getEndDay(Date date) {
        if (null == date) {
            date = new Date();
        }
        String endDay = DateUtils.formatDate(date) + " 23:59:59";
        return DateUtils.getDateByFormat(endDay, DateUtils.DATE_PATTERN);
    }

    /**
     * 机票的计划起飞日期和时间 拼成一个日期时间类型返回
     *
     * @param date
     * @param timeStr
     * @return
     */
    public static Date getDateTime(String date, String timeStr) {
        Date dateNew = null;
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
        if (StringUtils.isBlank(date) || StringUtils.isBlank(timeStr)){
            return null;
        }
        String dateStr = date + " " + timeStr.substring(0, 2) + ":" + timeStr.substring(2, 4) + ":00";
        try {
            dateNew = format.parse(dateStr);
        } catch (ParseException e) {
            logger.info("日期格式转换失败  date：{}" + dateStr);
            e.printStackTrace();
        }
        return dateNew;
    }

    public static Integer getLeftSeconds(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date condition = sdf.parse(date);
        long n = condition.getTime();
        long s = sdf.parse(getCurrentDateTimeToStr2()).getTime();
        return (int) ((s - n) / 1000);
    }
}
