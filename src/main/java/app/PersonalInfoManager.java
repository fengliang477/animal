package app;

// 导入文件操作相关的类
import java.io.BufferedReader;      // 用于读取文本文件
import java.io.BufferedWriter;      // 用于写入文本文件
import java.io.FileOutputStream;    // 用于创建文件输出流
import java.io.FileReader;          // 用于读取文件
import java.io.FileWriter;          // 用于写入文件
import java.io.IOException;         // 用于处理IO异常
import java.io.OutputStreamWriter;  // 用于处理字符编码

/**
 * 个人信息管理类
 * 这个类负责处理用户个人信息的读写操作，包括：
 * 1. 用户基本信息（姓名、性别、年龄）
 * 2. 宠物状态信息（饱食度、健康度、金币）
 */
public class PersonalInfoManager {

    /**
     * 向文件中写入用户个人信息
     * 使用UTF-8编码，以追加模式写入文件
     *
     * @param filePath 文件路径
     * @param name 用户姓名
     * @param gender 用户性别
     * @param age 用户年龄
     * @param isAdmin 是否是管理员
     */
    public static void writePersonalInfo(String filePath, String name, String gender, int age, boolean isAdmin) {
        // 使用try-with-resources语句，确保资源正确关闭
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8"))) {
            // 按顺序写入用户信息，每项信息占一行
            writer.write(name);
            writer.newLine();
            writer.write(gender);
            writer.newLine();
            writer.write(String.valueOf(age));
            writer.newLine();
            writer.write(String.valueOf(isAdmin));
        } catch (IOException e) {
            // 如果写入过程中出现错误，打印错误信息
            e.printStackTrace();
            System.out.println("An error occurred while writing to the file: " + filePath);
        }
    }

    /**
     * 从文件中读取用户个人信息
     * 读取顺序：姓名、性别、年龄、注册状态
     *
     * @param filePath 文件路径
     * @return 包含用户信息的字符串数组，如果读取失败则返回null
     */
    public static String[] readPersonalInfo(String filePath) {
        // 初始化数组，用于存储用户信息
        String[] info = {null, null, null, null}; 
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // 按顺序读取用户信息
            info[0] = reader.readLine(); // 读取姓名
            info[1] = reader.readLine(); // 读取性别
            info[2] = reader.readLine(); // 读取年龄
            info[3] = reader.readLine(); // 读取注册状态
        } catch (IOException e) {
            // 如果读取过程中出现错误，打印错误信息
            e.printStackTrace();
            System.out.println("An error occurred while reading the file: " + filePath);
        }
        return info;
    }

    /**
     * 向文件中写入宠物状态信息
     * 写入顺序：饱食度、健康度、金币数量
     *
     * @param filePath 文件路径
     * @param baoshi 饱食度
     * @param health 健康度
     * @param goldCoin 金币数量
     */
    public static void writeInfo(String filePath, int baoshi, int health, int goldCoin) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // 按顺序写入宠物状态信息，每项信息占一行
            writer.write(String.valueOf(baoshi));
            writer.newLine();
            writer.write(String.valueOf(health));
            writer.newLine();
            writer.write(String.valueOf(goldCoin));
        } catch (IOException e) {
            // 如果写入过程中出现错误，打印错误信息
            e.printStackTrace();
            System.out.println("An error occurred while writing to the file: " + filePath);
        }
    }

    /**
     * 从文件中读取宠物状态信息
     * 读取顺序：饱食度、健康度、金币数量
     *
     * @param filePath 文件路径
     * @return 包含宠物状态信息的整型数组，如果读取失败则返回[0,0,0]
     */
    public static int[] readInfo(String filePath) {
        // 初始化数组，用于存储宠物状态信息
        int[] values = new int[3]; 
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // 按顺序读取宠物状态信息
            for (int i = 0; i < 3; i++) {
                String line = reader.readLine();
                if (line != null) {
                    // 将读取的字符串转换为整数
                    values[i] = Integer.parseInt(line.trim());
                }
            }
        } catch (IOException e) {
            // 如果读取过程中出现错误，打印错误信息
            e.printStackTrace();
            System.out.println("An error occurred while reading the file: " + filePath);
        } catch (NumberFormatException e) {
            // 如果数据格式不正确，打印错误信息
            e.printStackTrace();
            System.out.println("One of the values in the file is not a valid integer.");
        }
        return values;
    }

    /*public static void main(String[] args) {
        // 写入个人信息
        //writePersonalInfo("D:\\Animal.txt", name(),sex(), age());

        // 读取个人信息，并打印
        String[] personalInfo = readPersonalInfo("D:\\Animal.txt");
        if (personalInfo != null) {
            System.out.println("Name: " + personalInfo[0]);
            System.out.println("Gender: " + personalInfo[1]);
            System.out.println("Age: " + personalInfo[2]);
        }
    }*/
}
