package app;

import static app.PersonalInfoManager.readInfo;
import static app.PersonalInfoManager.readPersonalInfo;
import static app.PersonalInfoManager.writeInfo;
import static app.login.createACharacter;
import java.util.Scanner;

public class app {

    /**
     * 菜单界面 menu 健康界面 health 饱和度界面 satiety 喂食界面 feeding
     */
    public static void main(String[] args) {
        String[] personalInfo = readPersonalInfo("D:\\Animal\\2.txt");
        if (personalInfo[3] != null) {
            if (personalInfo[3].equals("true")) {
                menu();
            } else {
                System.out.println("请先注册");
                createACharacter();
                menu();
            }

        } else {
            System.out.println("请先注册");
            createACharacter();
            menu();
        }

    }

    public static void menu() {
        peizhi p = new peizhi();
        System.out.println("请输入选项\t\t\t当前时间"+p.getCurrentDate()+" "+p.getCurrentTime());
        System.out.println("1.健康度界面\n2.饱和度界面\n3.喂食界面\n4.宠物信息界面\n5.游戏");
        System.out.println("返回上一级，请输入0");
        int info[] = readInfo("D:\\Animal\\1.txt");

        switch (shuru()) {
            case 1:
                health(info[1]);

                break;
            case 2:
                satiety(info[0]);

                break;
            case 3:
                int a = feeding(info[0]);
                writeInfo("D:\\Animal\\1.txt", a, info[1], info[2]);
                //int info2[] = readInfo("D:\\Animal\\1.txt");
                //System.out.println("当前饱和度:" + info2[0]);
                menu();
                break;
            case 4:
                jiese(info);
                break;
            case 5:
                int b=game(info[2]);
                writeInfo("D:\\Animal\\1.txt", info[0], info[1], b);
                int info2[] = readInfo("D:\\Animal\\1.txt");
                System.out.println("当前金币数量:" + info2[2]);
                menu();
                break;

            case 0:
                System.exit(0);

        }

        /*if (options == 1) {
                            System.out.println("1.健康都界面");
                        } else if (options == 2) {
                            System.out.println("饱和度界面");
                        } else if (options == 3) {
                            System.out.println("退出");
                        } */
    }

    public static void health(int status) {
        peizhi p = new peizhi();
        //status 状态0-100
        System.out.println(p.name() + "当前健康度:" + status);
        System.out.print("当前状态:");
        if (status > 90) {
            System.out.println("健康");
        } else if (status > 70) {
            System.out.println("感到疲惫");
        } else if (status > 50) {
            System.out.println("生病了");
        } else if (status > 30) {
            System.out.println("急需治疗");
        } else if (status > 10) {
            System.out.println("即将死亡");
        } else {
            System.out.println("冯亮已经死了");
        }
        System.out.println("返回上一级，请输入0");
        if (shuru() == 0) {
            menu();
        } else {
            System.exit(0);
        }

    }

    //satiety 饱和度
    public static void satiety(int status) {
        //status 状态0-100
        peizhi p = new peizhi();
        System.out.println(p.name() + "当前饱和度：" + status);
        System.out.print("当前状态：");
        if (status > 90) {
            System.out.println("饱了");
        } else if (status > 70) {
            System.out.println("有点饿了");
        } else if (status > 50) {
            System.out.println("饿了");
        } else if (status > 30) {
            System.out.println("饥饿");
        } else if (status > 10) {
            System.out.println("快要饿死了");
        } else {
            System.out.println("冯亮已经饿死了");
        }
        System.out.println("返回上一级，请输入0");
        if (shuru() == 0) {
            menu();
        } else {
            System.exit(0);
        }
    }

    public static int shuru() {
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        return w;

    }

    //喂食
    public static int feeding(int status) {
        String s[] = {"冯亮", "文件夹", "文档", "高梓涵", "垃圾"};
        System.out.println("1." + s[0] + "\t2." + s[1] + "\t3." + s[2] + "\t4." + s[3] + "\t5." + s[4]);
        System.out.println("请输入选项");
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        System.out.println("请输入数量");
        int b = sc.nextInt();
        int a[] = {25, 7, 9, 5, 4};
        status = status + a[w - 1] * b;
        System.out.println(s[w] + "喂食成功");
        System.out.println("返回上一级，请输入0");
        int c = sc.nextInt();

        if (c == 0) {

            return status;
        } else {
            System.exit(0);
        }
        return status;

    }

    public static void jiese(int zhuang[]) {
        peizhi p = new peizhi();

        System.out.println("宠物当前状态信息");
        System.out.println("名字：" + p.name());
        System.out.println("性别：" + p.sex());
        System.out.println("年龄：" + p.age());
        System.out.println("健康度：" + zhuang[1]);
        System.out.println("饱和度：" + zhuang[0]);
        System.out.println("金币数量：" + zhuang[2]);
        System.out.println("返回上一级，请输入0");
        if (shuru() == 0) {
            menu();
        } else {
            System.exit(0);

        }
    }
    public static boolean   csz() {
        int number, guess=0;
        Scanner sc = new Scanner(System.in);
        number = (int) (Math.random() * 30 + 1);
        System.out.println("欢迎赌狗老登来猜数");
        System.err.println("请输入你猜的数字,你有三次机会");
        
        int i = 0;

        while (guess != number) {
            if (i == 3) {
               
                return false;
            }else {
                guess = sc.nextInt();
            if (guess > number) {
                System.out.println("老登，你傻逼呀，猜大啦，重新猜");
                
                i++;

            } else {
                System.out.println("老登，你傻逼呀，猜小啦，重新猜");
                
                i++;
            }
            
            }
            
        }
        
        System.out.println("猜对了老登，滚吧");
        System.err.println("!!!!!!!!!!!!!!!");
        return true;
    }
    public static int game(int jin) {
        System.out.println("1.猜数游戏\n2.开发中");
        System.out.println("返回上一级，请输入其他数字");
        if (shuru() == 1) {
            if (csz()) {
                System.out.println("恭喜你，获得了100金币");
                jin=jin+100;
                return jin;
    
            }else {
                System.out.println("你输了，获得了1金币");
                jin=jin+1;
                return jin;
            }
        }else {
            
            return 0;
        }

       
    }

}
