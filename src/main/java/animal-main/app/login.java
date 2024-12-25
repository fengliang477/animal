package app;



import static app.PersonalInfoManager.readPersonalInfo;
import static app.PersonalInfoManager.writePersonalInfo;
import java.util.Scanner;
/*
 * 该文件为登录界面
 * 仅用于修改宠物信息和第一次登录使用
 */
public class login {

    public static void login() {

    }

    public static boolean createACharacter() {
        System.out.println("请输入名字");
        Scanner sc = new Scanner(System.in, "UTF-8");
    String name = sc.nextLine();
    System.out.println("请输入性别");
    String sex = sc.nextLine();
       System.out.println("请输入年龄");
        int age = sc.nextInt();
        System.out.println("正在保存，请稍等");
        writePersonalInfo("D:\\Animal\\2.txt", name, sex, age,true);
        System.out.println("保存成功，正在调整");
        String[] personalInfo = readPersonalInfo("D:\\Animal\\2.txt");
        
        
        if (personalInfo != null) {
            if (personalInfo[0].equals(name)) {
                if (personalInfo[1].equals(sex)) {

                    if (Integer.parseInt(personalInfo[2]) == age) {
                        System.out.println("创建成功");
                        return true;

                    }else {
                        System.out.println("创建失败，错误代码4");
                    }
                }else {
                    System.out.println("创建失败，错误代码3");
                }
            }else {
                System.out.println("创建失败，错误代码2");
               
            }

        }else {
            System.out.println("创建失败,错误代码1");
        }

        return false;
    }
   public static void main(String[] args) {
    createACharacter();
   }
}