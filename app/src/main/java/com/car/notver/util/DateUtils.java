package com.car.notver.util;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils {


    public static String longTime(long endTime) {
        long second = (endTime - new Date().getTime()) / 1000;//获取所有秒数
        long minute1 = second / 60;//获得分钟数
        long second1 = second % 60;//不足一分钟的秒
        long hour = minute1 / 60;//获得小时数
        long minute = minute1 % 60;//不足一小时的分钟数
        long day = hour / 24; //获得天数
        long t1 = hour % 24;//不足一天的小时数
        return day + "天" + t1 + "时" + minute + "分" + second1 + "秒";
    }


    public static String getLongTime(long endTime) {
        long second = endTime / 1000;//获取所有秒数
        long minute1 = second / 60;//获得分钟数
        long second1 = second % 60;//不足一分钟的秒
        return minute1 + ":" + second1;
    }


    public static String getTimes(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getTime1(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public static String getNextDay(long endTime) {
        long second = (endTime - new Date().getTime()) / 1000;//获取所有秒数
        long minute1 = second / 60;//获得分钟数
        long hour = minute1 / 60;//获得小时数
        long day = hour / 24; //获得天数
        if (day > 0) {
            return day + "天";
        }
        if (day > 0.5 && day < 1) {
            return "半个小时前";
        }
        return "刚刚";
    }

    private DateUtils() {
    }

    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    public static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("HH:mm", Locale.CHINA);
    public static final SimpleDateFormat FORMAT_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    public static final SimpleDateFormat FORMAT_ORDER = new SimpleDateFormat("MM月dd日 HH:mm", Locale.CHINA);

    private static final Calendar INSTANCE = Calendar.getInstance();

    public static int getYear() {
        return getTimeByType(Calendar.YEAR);
    }

    public static int getMonth() {
        return getTimeByType(Calendar.MONTH);
    }

    public static int getDay() {
        return getTimeByType(Calendar.DAY_OF_MONTH);
    }

    public static int getHours() {
        return getTimeByType(Calendar.HOUR_OF_DAY);
    }

    public static int getMinutes() {
        return getTimeByType(Calendar.MINUTE);
    }

    public static int getSeconds() {
        return getTimeByType(Calendar.SECOND);
    }

    private static int getTimeByType(int type) {
        INSTANCE.setTimeInMillis(System.currentTimeMillis());
        return INSTANCE.get(type);
    }

    /******
     *
     * @param format 时间格式化
     * @param time java时间戳  秒或者毫秒 均可以格式化
     * @return
     */
    public static String formatDate(SimpleDateFormat format, Long time) {
        if (format == null) {
            return FORMAT.format(new Date(time));
        }
        return format.format(new Date(time));
    }

    /**
     * 20 * 字符串转换成日期
     * 21 * @param str
     * 22 * @return date
     * 23
     */
    public static Date StrToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static String DateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }


    /*****字符串转换为时间戳*****/
    public static long formatData(String date_str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            long dex = (sdf.parse(date_str).getTime());
            return dex;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*****字符串转换为时间戳*****/
    public static long formatTimeData(String date_str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH时mm分");
            long dex = (sdf.parse(date_str).getTime());
            return dex;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据月份获得最大天数
     *
     * @param year  年
     * @param month 月
     * @return 最大天数
     */
    public static int getMaxDayByMonth(int year, int month) {
        Calendar time = Calendar.getInstance();//使用默认时区和语言环境获得一个日历
        time.clear();
        time.set(Calendar.YEAR, year);
        time.set(Calendar.MONTH, month);//注意Calendar对象默认一月是为零的
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);//获得本月份的天数
        return day;
    }

    /**
     * 获取N天前、N天后的 日期
     *
     * @param nowDate   当前日期;
     * @param dayAddNum 正数：N天前，负数：N天后;
     * @return
     */
    public static Date getAddDay(Date nowDate, int dayAddNum) {
        Calendar tday = new GregorianCalendar();
        tday.setTime(nowDate);
        tday.add(Calendar.DAY_OF_MONTH, dayAddNum);
        Date preDate = tday.getTime();
        return preDate;
    }


    /**
     * 获取N天前、N天后的 日期
     *
     * @param nowDate   当前时间戳;
     * @param dayAddNum 正数：N天前，负数：N天后;
     * @return
     */
    public static Date getAddDay(long nowDate, int dayAddNum) {
        return getAddDay(new Date(nowDate), dayAddNum);
    }


    /*****时间比较大小*****/
    public static boolean isCurrentTime(String hoursStart, String hourseEnd) {
        DateFormat df = new SimpleDateFormat("HH:mm");
        try {
            Date dt1 = df.parse(hourseEnd);
            Date dt2 = df.parse(hoursStart);
            if (dt1.getTime() <= dt2.getTime()) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*****年月日比较大小*****/
    public static boolean isCurrentTimeYeal(String hoursStart, String hourseEnd) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = dateFormat.parse(hourseEnd);
            Date dt2 = dateFormat.parse(hoursStart);
            if (dt1.getTime() <= dt2.getTime()) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取某一周第一天
     *
     * @return
     */
    public static Date getFirstDayByWeek(@NonNull Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int min = calendar.getActualMinimum(Calendar.DAY_OF_WEEK); //获取周开始基准
        int current = calendar.get(Calendar.DAY_OF_WEEK); //获取当天周内天数
        calendar.add(Calendar.DAY_OF_WEEK, min - current); //当天-基准，获取周开始日期
        return calendar.getTime();
    }

    /**
     * 获取某一周最后一天
     *
     * @return
     */
    public static Date getLastDayByWeek(@NonNull Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int min = calendar.getActualMinimum(Calendar.DAY_OF_WEEK);
        int current = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_WEEK, min - current + 6);
        return calendar.getTime();
    }

    /**
     * 返回某月份的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayByMonth(@NonNull Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 返回某月份的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayByMonth(@NonNull Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    public static Date stringToDate(String strTime, SimpleDateFormat format) throws ParseException {
        Date date = null;
        date = format.parse(strTime);
        return date;
    }


    /**
     * 将秒转换为（ 分：秒 格式）
     *
     * @param time 单位：秒
     * @return
     */
    public static String getTimeFromInt(int time) {
        if (time <= 0) return "00:00";
        int secondnd = time / 60;
        int million = time % 60;
        String f = secondnd >= 10 ? String.valueOf(secondnd) : "0" + String.valueOf(secondnd);
        String m = million >= 10 ? String.valueOf(million) : "0" + String.valueOf(million);
        return f + ":" + m;
    }

    /**
     * 时间格式化（刚刚、几分钟前、几小时前、昨天、前天、年-月-日）
     *
     * @param time
     * @return
     */
    public static String getShortTime(long time) {
        String shortString = "";
        if (time == 0)
            return shortString;

        long now = Calendar.getInstance().getTimeInMillis();
        long datetime = (now - time) / 1000;
        if (datetime > 365 * 24 * 60 * 60) {
            shortString = FORMAT_DATE.format(new Date(time));
        } else if (datetime > 24 * 60 * 60 && ((int) (datetime / (24 * 60 * 60))) == 2) {
            shortString = "前天";
        } else if (datetime > 24 * 60 * 60 && ((int) (datetime / (24 * 60 * 60))) == 1) {
            shortString = "昨天";
        } else if (datetime > 60 * 60) {
            shortString = (int) (datetime / (60 * 60)) + "小时前";
        } else if (datetime > 60) {
            shortString = (int) (datetime / (60)) + "分钟前";
        } else {
            shortString = "刚刚";
        }
        return shortString;
    }

    public static String timestampToDate(long time) {
        if (time < 10000000000L) {
            time = time * 1000;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(time))));
        return sd;
    }


    /**
     * 时间格式化(秒，分，小时，天)
     *
     * @param time
     * @return
     */
    public static long getSystemDate(long time) {
        long shortString = 0;
        if (time == 0)
            return shortString;
        long now = System.currentTimeMillis();
        long datetime = (now - time) / 1000; // 秒
        return datetime;
    }

    /*******时间对比大小*******/
    public static double getTimeDate(String time) {
        double day = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date start = sdf.parse(time);
            return start.getTime();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return day;
    }

    /*******时间对比大小*******/
    public static String getTime(String time) {
        long hour, day = 0, min = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date start = sdf.parse(time);
            Date end = new Date();
            min = (end.getTime() - start.getTime()) / (60 * 1000);
            hour = (end.getTime() - start.getTime()) / (60 * 60 * 1000);
            day = (end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000);
            if (min < 60) {
                if (min == 0) {
                    return "1分钟前";
                }
                return min + "分钟前";
            } else if (hour >= 1 && hour < 24) {
                return hour + "小时前";
            } else if (hour >= 24) {
                return day + "天前";
            } else if (day >= 7 && day < 30) {
                return "一周前";
            } else if (day > 30 && day < 180) {
                return "一月前";
            } else if (day >= 180) {
                return "半年前";
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return String.valueOf(day);
    }

    /*******获取当前时间*******/
    public static String getNextTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }


    /*******时间对比大小*******/
    public static boolean checkTim(String str, String str1) {
        boolean isDay = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date start = sdf.parse(str);
            Date end = sdf.parse(str1);
            long day = end.getTime() - start.getTime();
            if (day >= 0) {
                isDay = true;
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return isDay;
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        if (lt < 10000000000L) {
            lt = lt * 1000;
        }
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
