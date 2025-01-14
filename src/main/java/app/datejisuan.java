package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class datejisuan {
   /* public static void main(String[] args) {
        // 调用方法获取当前日期并写入文件
        //writeCurrentDateToFile("D:\\Animal\\date.txt");
        // 调用方法获取文本里的时间与当前时间对比差了多少天
        try {
            int daysDiff = getDaysDifferenceFromFile("D:\\Animal\\date.txt");
            System.out.println("文本里的时间与当前时间相差 " + daysDiff + " 天");
        } catch (IOException | ParseException e) {
            System.out.println("处理文件时发生错误：" + e.getMessage());
        }
    }*/

    public static void writeCurrentDateToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(peizhi.getCurrentDate());
            System.out.println("当前日期已写入文件：" + filePath);
        } catch (IOException e) {
            System.out.println("写入文件时发生错误：" + e.getMessage());
        }
    }

    public static int getDaysDifferenceFromFile(String filePath) throws IOException, ParseException {
        // 从文件中读取日期
        String dateStr = readDateFromFile(filePath);
        // 将日期字符串转换为日期对象
        Date dateFromFile = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        // 获取当前日期
        Date currentDate = new Date();
        // 计算日期差（以毫秒为单位）
        long diffIn = currentDate.getTime() - dateFromFile.getTime();
        // 将毫秒转换为天
        long diffInDays = diffIn / (24 * 60 * 60 * 1000);
        return (int) diffInDays;
    }

    public static String readDateFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.readLine();
        }
    }

    public static String dategueifan(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 将输入的字符串解析为日期对象
            Date date2 = dateFormat.parse(date);
            // 将日期对象格式化为字符串
            return dateFormat.format(date2);
        } catch (ParseException e) {
            // 如果解析失败，抛出异常
            throw new IllegalArgumentException("输入的日期格式不正确: " +date, e);
        }
    }

}
