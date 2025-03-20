package app;

import static app.PersonalInfoManager.readPersonalInfo;
import static app.PersonalInfoManager.writePersonalInfo;
import java.util.Scanner;

/**
 * 登录类
 * 这个类提供了用户登录和角色创建的功能，主要用于：
 * 1. 创建新用户角色
 * 2. 修改宠物信息
 * 3. 首次登录使用
 */
public class login {
    /**
     * 创建新用户角色
     * 通过控制台接收用户输入的信息，包括：
     * - 名字
     * - 性别
     * - 年龄
     * 然后将信息保存到文件中并验证是否保存成功
     * 
     * @return 如果创建成功返回true，否则返回false
     */
    public static boolean createACharacter() {
        // 创建Scanner对象，使用UTF-8编码以支持中文输入
        Scanner sc = new Scanner(System.in, "UTF-8");
        
        // 提示用户输入名字
        System.out.println("请输入名字");
        String name = sc.nextLine();
        
        // 提示用户输入性别
        System.out.println("请输入性别");
        String sex = sc.nextLine();
        
        // 提示用户输入年龄
        System.out.println("请输入年龄");
        int age = sc.nextInt();
        
        // 提示用户正在保存信息
        System.out.println("正在保存，请稍等");
        // 将用户信息写入文件，true表示是管理员
        writePersonalInfo("D:\\Animal\\2.txt", name, sex, age, true);
        
        // 提示用户保存成功
        System.out.println("保存成功，正在调整");
        // 从文件中读取保存的信息进行验证
        String[] personalInfo = readPersonalInfo("D:\\Animal\\2.txt");
        
        // 验证保存的信息是否完整
        if (personalInfo != null) {
            // 验证名字是否匹配
            if (personalInfo[0].equals(name)) {
                // 验证性别是否匹配
                if (personalInfo[1].equals(sex)) {
                    // 验证年龄是否匹配
                    if (Integer.parseInt(personalInfo[2]) == age) {
                        System.out.println("创建成功");
                        return true;
                    } else {
                        System.out.println("创建失败，错误代码4");  // 年龄不匹配
                    }
                } else {
                    System.out.println("创建失败，错误代码3");  // 性别不匹配
                }
            } else {
                System.out.println("创建失败，错误代码2");  // 名字不匹配
            }
        } else {
            System.out.println("创建失败,错误代码1");  // 文件读取失败
        }

        return false;
    }

    /* 测试用的main方法，已注释
    public static void main(String[] args) {
        createACharacter();
    }
    */
}