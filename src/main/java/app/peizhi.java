package app;

import static app.PersonalInfoManager.readPersonalInfo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/**
 * 配置类
 * 这个类提供了系统的基本配置和工具方法，包括：
 * 1. 用户信息获取（姓名、年龄、性别）
 * 2. 时间日期处理
 * 3. 宠物状态衰减计算
 */
public class peizhi {
    // 定义衰减系数，用于计算宠物状态的衰减
    final static double shuaijian = 0.3;

    /**
     * 获取用户姓名
     * 从个人信息文件中读取用户姓名
     * 
     * @return 用户姓名
     */
    public static String name() {
        String[] personalInfo = readPersonalInfo("D:\\\\Animal\\\\2.txt");
        return personalInfo[0];  // 返回姓名（数组的第一个元素）
    }

    /**
     * 获取用户年龄
     * 从个人信息文件中读取用户年龄
     * 
     * @return 用户年龄（整数）
     */
    public static int age() {
        String[] personalInfo = readPersonalInfo("D:\\\\Animal\\\\2.txt");
        return Integer.parseInt(personalInfo[2]);  // 将年龄字符串转换为整数
    }

    /**
     * 获取用户性别
     * 从个人信息文件中读取用户性别
     * 
     * @return 用户性别
     */
    public static String sex() {
        String[] personalInfo = readPersonalInfo("D:\\\\Animal\\\\2.txt");
        return personalInfo[1];  // 返回性别（数组的第二个元素）
    }

    /**
     * 获取当前日期
     * 格式化为yyyy-MM-dd格式
     * 
     * @return 当前日期字符串
     */
    public static String getCurrentDate() {
        Date date = new Date();  // 获取当前日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  // 创建日期格式化器
        return dateFormat.format(date);  // 格式化日期
    }

    /**
     * 获取当前时间
     * 格式化为HH:mm:ss格式
     * 
     * @return 当前时间字符串
     */
    public static String getCurrentTime() {
        Date date = new Date();  // 获取当前时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");  // 创建时间格式化器
        return dateFormat.format(date);  // 格式化时间
    }

    /**
     * 计算指定天数后的日期
     * 
     * @param day 要增加的天数
     * @return 计算后的日期字符串
     */
    public static String datechange(int day) {
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // 在当前日期上增加指定天数
        calendar.add(Calendar.DAY_OF_MONTH, day);
        Date tomorrow = calendar.getTime();

        return tomorrow.toString();  // 返回计算后的日期
    }

    /**
     * 计算宠物状态的衰减值
     * 根据上次记录的时间计算衰减量
     * 
     * @param shuzhi 当前状态值
     * @return 衰减后的状态值
     */
    public static int shuaijian(int shuzhi) {
        try {
            // 获取距离上次记录的天数
            int daysDiff = datejisuan.getDaysDifferenceFromFile("D:\\Animal\\date.txt");
            // 计算衰减量（天数 * 衰减系数）
            daysDiff = (int) (daysDiff * shuaijian);
            // 返回衰减后的值
            return shuzhi - daysDiff;
        } catch (IOException | ParseException e) {
            // 如果处理过程中出现错误，输出错误信息并返回0
            System.out.println("处理文件时发生错误：" + e.getMessage());
            return 0;
        }
    }
}
