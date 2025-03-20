package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期计算类
 * 这个类提供了日期相关的工具方法，包括：
 * 1. 将当前日期写入文件
 * 2. 计算文件中的日期与当前日期的差值
 * 3. 读取文件中的日期
 * 4. 日期格式规范化
 */
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

    /**
     * 将当前日期写入指定文件
     * 使用yyyy-MM-dd格式写入日期
     * 
     * @param filePath 要写入的文件路径
     */
    public static void writeCurrentDateToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(peizhi.getCurrentDate());
            System.out.println("当前日期已写入文件：" + filePath);
        } catch (IOException e) {
            System.out.println("写入文件时发生错误：" + e.getMessage());
        }
    }

    /**
     * 计算文件中的日期与当前日期的差值（天数）
     * 
     * @param filePath 包含日期的文件路径
     * @return 相差的天数
     * @throws IOException 如果文件读取失败
     * @throws ParseException 如果日期格式解析失败
     */
    public static int getDaysDifferenceFromFile(String filePath) throws IOException, ParseException {
        // 从文件中读取日期字符串
        String dateStr = readDateFromFile(filePath);
        // 将日期字符串转换为Date对象
        Date dateFromFile = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        // 获取当前日期
        Date currentDate = new Date();
        // 计算两个日期之间的毫秒差
        long diffIn = currentDate.getTime() - dateFromFile.getTime();
        // 将毫秒差转换为天数
        long diffInDays = diffIn / (24 * 60 * 60 * 1000);
        return (int) diffInDays;
    }

    /**
     * 从文件中读取日期字符串
     * 
     * @param filePath 要读取的文件路径
     * @return 文件中的日期字符串
     * @throws IOException 如果文件读取失败
     */
    public static String readDateFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // 读取文件的第一行作为日期
            return reader.readLine();
        }
    }

    /**
     * 将日期字符串格式化为标准格式（yyyy-MM-dd）
     * 
     * @param date 要格式化的日期字符串
     * @return 格式化后的日期字符串
     * @throws IllegalArgumentException 如果输入的日期格式不正确
     */
    public static String dategueifan(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 将输入的字符串解析为Date对象
            Date date2 = dateFormat.parse(date);
            // 将Date对象格式化为标准格式的字符串
            return dateFormat.format(date2);
        } catch (ParseException e) {
            // 如果解析失败，抛出带有详细信息的异常
            throw new IllegalArgumentException("输入的日期格式不正确: " + date, e);
        }
    }
}
