package app;

import static app.PersonalInfoManager.readPersonalInfo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
/*
 * 该文件配置
 *1.名字
 *2.年龄
 *3.性别
 *4.当前时间
 *5.当前日期
 *6.日期转换
 *
 */
public class peizhi {
    final static double shuaijian = 0.3;

    

    public static String name() {
        String[] personalInfo = readPersonalInfo("D:\\\\Animal\\\\2.txt");
        return personalInfo[0];
    }

    public static int age() {
        String[] personalInfo = readPersonalInfo("D:\\\\Animal\\\\2.txt");
        return Integer.parseInt(personalInfo[2]);
    }

    public static String sex() {
        String[] personalInfo = readPersonalInfo("D:\\\\Animal\\\\2.txt");
        return personalInfo[1];
        
    }

    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(date);
    }
    public static String datechange(int day) {
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // 在当前日期上增加一天
        calendar.add(Calendar.DAY_OF_MONTH, day);
        Date tomorrow = calendar.getTime();

        return tomorrow.toString();
    }
    public static int shuaijian(int shuzhi) {
        try {
            int daysDiff = datejisuan.getDaysDifferenceFromFile("D:\\Animal\\date.txt");
            daysDiff = (int) (daysDiff *shuaijian);
            return shuzhi-daysDiff;
        } catch (IOException | ParseException e) {
            System.out.println("处理文件时发生错误：" + e.getMessage());
            return 0;
        }
    }

    

   

   
}
