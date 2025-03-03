package app;

import static app.PersonalInfoManager.readInfo;
import static app.PersonalInfoManager.readPersonalInfo;
import static app.PersonalInfoManager.writeInfo;
import static app.login.createACharacter;
import java.util.Scanner;

/**
 * 宠物养成系统主类，包含系统的主要功能和菜单逻辑。
 */
public class app {
    static {
        // 程序启动时输出欢迎信息
        System.out.println("欢迎使用宠物养成系统");
        // 读取宠物信息文件
        int[] info = readInfo("D:\\Animal\\1.txt");
        // 对宠物的饱和度和健康度进行衰减处理，并写入文件
        writeInfo("D:\\Animal\\1.txt",  peizhi.shuaijian(info[0]),  peizhi.shuaijian(info[1]), info[2]);
        // 将当前日期写入文件
        datejisuan.writeCurrentDateToFile("D:\\Animal\\date.txt");
    }

    /**
     * 菜单界面 menu 健康界面 health 饱和度界面 satiety 喂食界面 feeding
     *
     * 程序入口方法，检查用户是否注册，根据情况引导用户注册或进入菜单。
     *
     * @param args 命令行参数，本方法未使用。
     */
    public static void main(String[] args) {
        // 读取用户个人信息文件
        String[] personalInfo = readPersonalInfo("D:\\Animal\\2.txt");
        // 检查用户是否已注册
        if (personalInfo[3] != null) {
            if (personalInfo[3].equals("true")) {
                // 已注册，进入菜单
                menu();
            } else {
                // 未注册，提示注册并创建角色，然后进入菜单
                System.out.println("请先注册");
                createACharacter();
                menu();
            }
        } else {
            // 未注册，提示注册并创建角色，然后进入菜单
            System.out.println("请先注册");
            createACharacter();
            menu();
        }
    }

    /**
     * 获取用户输入的整数。
     *
     * @return 用户输入的整数。
     */
    public static int shuru() {
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        return w;
    }

    /**
     * 显示主菜单，根据用户输入执行相应操作。
     */
    public static void menu() {
        // 显示菜单选项和当前时间
        System.out.println("请输入选项\t\t\t当前时间"+ peizhi.getCurrentDate()+" "+ peizhi.getCurrentTime());
        System.out.println("1.健康度界面\n2.饱和度界面\n3.喂食界面\n4.宠物信息界面\n5.游戏\n6.实用工具");
        System.out.println("返回上一级，请输入0");
        // 读取宠物信息文件
        int[] info = readInfo("D:\\Animal\\1.txt");

        // 根据用户输入执行相应操作
        switch (shuru()) {
            case 1:
                // 进入健康度界面
                health(info[1]);
                break;
            case 2:
                // 进入饱和度界面
                satiety(info[0]);
                break;
            case 3:
                // 进入喂食界面
                int a = feeding(info[0]);
                // 更新宠物信息文件
                writeInfo("D:\\Animal\\1.txt", a, info[1], info[2]);
                // 重新显示菜单
                menu();
                break;
            case 4:
                // 进入宠物信息界面
                jiese(info);
                break;
            case 5:
                // 进入游戏界面
                int b = game(info[2]);
                // 更新宠物信息文件
                writeInfo("D:\\Animal\\1.txt", info[0], info[1], b);
                // 显示当前金币数量
                int[] info2 = readInfo("D:\\Animal\\1.txt");
                System.out.println("当前金币数量:" + info2[2]);
                // 重新显示菜单
                menu();
                break;
            case 6:
                // 进入实用工具界面
                gongju();
                break;
            case 0:
                // 退出程序
                System.exit(0);
        }
    }

    /**
     * 显示宠物健康度信息和状态，根据用户输入返回菜单或退出程序。
     *
     * @param status 宠物的健康度，范围 0 - 100。
     */
    public static void health(int status) {
        // 显示宠物当前健康度
        System.out.println(peizhi.name() + "当前健康度:" + status);
        System.out.print("当前状态:");
        // 根据健康度显示相应状态
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
            System.out.println(peizhi.name()+"已经死了");
        }
        System.out.println("返回上一级，请输入0");
        // 根据用户输入返回菜单或退出程序
        if (shuru() == 0) {
            menu();
        } else {
            System.exit(0);
        }
    }

    /**
     * 显示宠物饱和度信息和状态，根据用户输入返回菜单或退出程序。
     *
     * @param status 宠物的饱和度，范围 0 - 100。
     */
    public static void satiety(int status) {
        // 显示宠物当前饱和度
        System.out.println(peizhi.name() + "当前饱和度：" + status);
        System.out.print("当前状态：");
        // 根据饱和度显示相应状态
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
            System.out.println(peizhi.name()+"已经饿死了");
        }
        System.out.println("返回上一级，请输入0");
        // 根据用户输入返回菜单或退出程序
        if (shuru() == 0) {
            menu();
        } else {
            System.exit(0);
        }
    }

    /**
     * 喂食界面，让用户选择食物和数量，更新宠物饱和度并返回。
     *
     * @param status 宠物当前的饱和度。
     * @return 喂食后宠物的饱和度。
     */
    public static int feeding(int status) {
        // 食物列表
        String[] s = {"冯亮", "文件夹", "文档", "高梓涵", "垃圾"};
        // 显示食物选项
        System.out.println("1." + s[0] + "\t2." + s[1] + "\t3." + s[2] + "\t4." + s[3] + "\t5." + s[4]);
        System.out.println("请输入选项");
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        System.out.println("请输入数量");
        int b = sc.nextInt();
        // 每种食物增加的饱和度
        int[] a = {25, 7, 9, 5, 4};
        // 更新饱和度
        status = status + a[w - 1] * b;
        System.out.println(s[w] + "喂食成功");
        System.out.println("返回上一级，请输入0");
        int c = sc.nextInt();

        // 根据用户输入返回饱和度或退出程序
        if (c == 0) {
            return status;
        } else {
            System.exit(0);
        }
        return status;
    }

    /**
     * 显示宠物的详细信息，根据用户输入返回菜单或退出程序。
     *
     * @param zhuang 包含宠物信息的数组。
     */
    public static void jiese(int[] zhuang) {


        System.out.println("宠物当前状态信息");
        System.out.println("名字：" + peizhi.name());
        System.out.println("性别：" + peizhi.sex());
        System.out.println("年龄：" + peizhi.age());
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
    /**
     * 猜数字游戏的核心方法。
     * 程序随机生成一个1到30之间的数字，用户有三次机会猜中该数字。
     * 如果猜中，返回true；否则返回false。
     *
     * @return boolean 猜中返回true，否则返回false
     */
    public static boolean csz() {
        int number, guess = 0; // 定义随机数字和用户猜测的数字
        Scanner sc = new Scanner(System.in); // 创建输入流
        number = (int) (Math.random() * 30 + 1); // 生成1到30之间的随机数字
        System.out.println("欢迎赌狗老登来猜数");
        System.err.println("请输入你猜的数字,你有三次机会");

        int i = 0; // 记录猜测次数

        while (guess != number) { // 循环直到猜中数字
            if (i == 3) { // 如果猜测次数达到3次
                return false; // 返回false表示失败
            } else {
                guess = sc.nextInt(); // 用户输入猜测数字
                if (guess > number) { // 如果猜测数字大于随机数字
                    System.out.println("老登，你傻逼呀，猜大啦，重新猜");
                } else if (guess < number) { // 如果猜测数字小于随机数字
                    System.out.println("老登，你傻逼呀，猜小啦，重新猜");
                }
                i++; // 增加猜测次数
            }
        }
        System.out.println("猜对了老登，滚吧");
        System.err.println("!!!!!!!!!!!!!!!");
        return true; // 返回true表示猜中
    }

    /**
     * 游戏逻辑方法，用于处理猜数字游戏的奖励逻辑。
     * 如果用户猜中数字，奖励100金币；否则奖励1金币。
     *
     * @param jin 当前金币数量
     * @return int 返回更新后的金币数量
     */
    public static int game(int jin) {
        System.out.println("1.猜数游戏\n2.开发中");
        System.out.println("返回上一级，请输入其他数字");
        if (shuru() == 1) { // 调用shuru()方法获取用户输入
            if (csz()) { // 调用csz()方法进行猜数字游戏
                System.out.println("恭喜你，获得了100金币");
                jin = jin + 100; // 猜中奖励100金币
            } else {
                System.out.println("你输了，获得了1金币");
                jin = jin + 1; // 猜错奖励1金币
            }
            return jin; // 返回更新后的金币数量
        } else {
            return 0; // 如果用户选择返回上一级，返回0
        }
    }

    /**
     * 工具菜单方法，提供天气查询等功能。
     * 用户可以选择不同的功能，或者返回上一级菜单。
     */
    public static void gongju() {
        System.out.println("1.天气查询\n2.开发中");
        System.out.println("返回上一级，请输入0");
        int x = shuru(); // 调用shuru()方法获取用户输入
        if (x == 1) {
            city11(); // 调用city11()方法进行天气查询
        } else if (x == 0) {
            menu(); // 返回上一级菜单
        } else {
            System.out.println("输入错误");
        }
    }

    /**
     * 天气查询方法。
     * 用户输入城市名称和日期，程序调用weather类获取天气信息。
     */
    public static void city11() {
        String city; // 城市名称
        String date; // 日期
        Scanner sc = new Scanner(System.in); // 创建输入流
        System.out.println("请输入城市名称,不要包含市，区，县");
        city = sc.nextLine(); // 用户输入城市名称
        System.out.println("请输入日期");
        date = datejisuan.dategueifan(sc.nextLine()); // 用户输入日期并格式化
        weather.getdateweather(date, city); // 调用weather类获取天气信息
    }





}
