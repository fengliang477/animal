package app;

import static app.PersonalInfoManager.readPersonalInfo;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
 * 该文件配置
 *1.名字
 *2.年龄
 *3.性别
 *4.当前时间
 *5.当前日期
 *
 */
public class peizhi {

    

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

    

   

   
}
