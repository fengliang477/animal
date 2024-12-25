package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class PersonalInfoManager {

    /**
     * 向文件中写入个人信息。
     *
     * @param filePath 文件路径
     * @param name 名字
     * @param gender 性别
     * @param age 年龄
     */
    public static void writePersonalInfo(String filePath, String name, String gender, int age, boolean isAdmin) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8"))) {
            writer.write(name);
            writer.newLine();
            writer.write(gender);
            writer.newLine();
            writer.write(String.valueOf(age));
            writer.newLine();
            writer.write(String.valueOf(isAdmin));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while writing to the file: " + filePath);
        }
    }

    /**
     * 从文件中读取个人信息，并返回一个包含名字、性别和年龄，是否注册的数组。
     *
     * @param filePath 文件路径
     * @return 包含名字、性别和年龄的数组
     */
    public static String[] readPersonalInfo(String filePath) {
        String[] info = {null, null, null,null}; // 初始化数组，分别存储名字、性别和年龄
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            info[0] = reader.readLine(); // 读取名字
            info[1] = reader.readLine(); // 读取性别
            info[2] = reader.readLine(); // 读取年龄
            info[3] = reader.readLine(); // 读取是否注册
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while reading the file: " + filePath);
        }
        return info;
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
    public static void writeInfo(String filePath, int baoshi, int health, int goldCoin) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.valueOf(baoshi));
            writer.newLine();
            writer.write(String.valueOf(health));
            writer.newLine();
            writer.write(String.valueOf(goldCoin));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while writing to the file: " + filePath);
        }
    }

      /**
     * 从文件中读取三个数值，并返回一个包含这三个数值的整型数组。
     *
     * @param filePath 文件路径
     * @return 包含三个数值的整型数组，分别为饱食度、健康度和金币数量
     */
    public static int[] readInfo(String filePath) {
        int[] values = new int[3]; // 初始化数组，分别存储三个数值
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (int i = 0; i < 3; i++) {
                String line = reader.readLine();
             
                if (line != null) {
                    values[i] = Integer.parseInt(line.trim()); // 读取数值并转换为整数
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while reading the file: " + filePath);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("One of the values in the file is not a valid integer.");
        }
        return values;
    }

   

}
