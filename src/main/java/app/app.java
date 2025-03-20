package app;  // 声明该类所在的包名为app

// 导入PersonalInfoManager类的静态方法，用于处理用户信息
// 静态导入允许直接使用这些方法，而不需要通过类名调用
import static app.PersonalInfoManager.readInfo;
import static app.PersonalInfoManager.readPersonalInfo;
import static app.PersonalInfoManager.writeInfo;

// 导入Swing库，用于创建图形用户界面
import javax.swing.*;

/**
 * 宠物养成系统主类
 * 这个类是整个宠物养成系统的入口点，负责：
 * 1. 系统初始化
 * 2. 用户登录状态检查
 * 3. 启动主界面
 * 
 * 文件结构说明：
 * - 静态初始化块：在类加载时自动执行，用于初始化系统
 * - main方法：程序的入口点，控制程序流程
 */
public class app {
    /**
     * 静态初始化块
     * 在类加载时执行，用于：
     * 1. 初始化系统
     * 2. 读取宠物信息
     * 3. 更新宠物状态
     * 4. 记录当前日期
     * 
     * 静态初始化块特点：
     * - 在程序启动时自动执行，不需要创建对象
     * - 在main方法执行前执行
     * - 用于初始化静态变量或执行一次性的初始化工作
     */
    static {
        // 输出系统初始化提示
        System.out.println("正在初始化宠物养成系统...");
        try {
            // 从文件读取宠物的基本信息（饱和度、健康度和金币）
            // D:\Animal\1.txt存储宠物的基本属性信息
            int[] info = readInfo("D:\\Animal\\1.txt");
            
            // 对宠物的饱和度和健康度进行衰减处理
            // 模拟宠物随时间属性自然减少的过程
            // 使用peizhi类的shuaijian方法计算衰减后的值
            writeInfo("D:\\Animal\\1.txt", 
                     peizhi.shuaijian(info[0]),  // 衰减饱和度（数组索引0）
                     peizhi.shuaijian(info[1]),  // 衰减健康度（数组索引1）
                     info[2]);                   // 保持金币数量不变（数组索引2）
                     
            // 将当前日期写入日期记录文件
            // 用于跟踪上次登录时间，以便计算属性衰减
            datejisuan.writeCurrentDateToFile("D:\\Animal\\date.txt");
            System.out.println("初始化完成！");
        } catch (Exception e) {
            // 如果初始化过程中出现错误（如文件不存在或无法写入），输出错误信息
            // System.err用于输出错误信息，会显示为红色
            System.err.println("初始化过程中出现错误: " + e.getMessage());
        }
    }

    /**
     * 程序的主入口方法
     * 负责：
     * 1. 设置应用程序的外观
     * 2. 检查用户登录状态
     * 3. 根据登录状态显示相应的窗口
     *
     * @param args 命令行参数（本程序未使用）
     */
    public static void main(String[] args) {
        try {
            // 设置应用程序的外观为系统默认外观
            // 这样应用程序会根据操作系统自动调整界面风格
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // 如果设置外观失败，输出错误信息
            System.err.println("设置外观风格失败: " + e.getMessage());
        }
        
        // 使用SwingUtilities确保在事件分发线程(EDT)中创建和显示窗口
        // 这是Swing应用程序的最佳实践，可以避免线程安全问题
        // invokeLater方法会将传入的Runnable对象的执行推迟到EDT线程空闲时运行
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // 读取用户个人信息
                    // personalInfo数组包含用户的注册信息（用户名、密码等）
                    // D:\Animal\2.txt存储用户的个人信息
                    String[] personalInfo = readPersonalInfo("D:\\Animal\\2.txt");
                    
                    // 检查用户是否已经注册
                    // personalInfo[3]存储用户的注册状态
                    // 如果值为"true"，表示用户已经完成注册
                    if (personalInfo[3] != null && personalInfo[3].equals("true")) {
                        // 用户已注册，直接显示主窗口
                        // 创建MainWindow对象并设置为可见
                        MainWindow mainWindow = new MainWindow();
                        mainWindow.setVisible(true);
                    } else {
                        // 用户未注册，显示登录窗口
                        // 创建LoginWindow对象并设置为可见
                        LoginWindow loginWindow = new LoginWindow();
                        loginWindow.setVisible(true);
                    }
                } catch (Exception e) {
                    // 如果读取用户信息失败（如文件不存在或格式错误），显示错误对话框
                    System.err.println("加载用户信息失败: " + e.getMessage());
                    
                    // 显示一个错误消息对话框，通知用户需要重新注册
                    JOptionPane.showMessageDialog(null, 
                        "无法加载用户信息，请重新注册。", 
                        "错误", 
                        JOptionPane.ERROR_MESSAGE);
                    
                    // 显示登录窗口让用户重新注册
                    LoginWindow loginWindow = new LoginWindow();
                    loginWindow.setVisible(true);
                }
            }
        });
    }
}
