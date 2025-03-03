package app;
import javax.swing.*;

import static app.PersonalInfoManager.*;
import static app.app.menu;
import static app.login.createACharacter;

/**
 * chhuangko 类继承自 JFrame，用于创建一个图形用户界面窗口。
 */
public class chhuangko extends JFrame {
    static {
        // 当程序启动时，执行以下代码块
        // 从文件中读取信息
        int[] info = readInfo("D:\\Animal\\1.txt");
        // 将处理后的信息写入文件
        writeInfo("D:\\Animal\\1.txt",  peizhi.shuaijian(info[0]),  peizhi.shuaijian(info[1]), info[2]);
        // 将当前日期写入文件
        datejisuan.writeCurrentDateToFile("D:\\Animal\\date.txt");
    }
    /**
     * 程序的入口点，创建 chhuangko 类的实例并调用 shixian 方法。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 创建 chhuangko 类的实例
        chhuangko chuangko = new chhuangko();
        // 调用 shixian 方法来实现界面
        chuangko.shixian();
    }

    /**
     * shixian 方法用于创建并显示一个图形用户界面窗口。
     * 窗口包含一个文本输入框、一个按钮和一个多行文本输出框。
     * 程序启动时，输出框显示 "Hi, world"，点击按钮将输入框的内容显示在输出框中。
     */
    public void shixian() {
        // 创建一个 JFrame 窗口
        JFrame frame = new JFrame();
        // 创建一个 JPanel 面板
        JPanel panel = new JPanel();
        // 创建一个 JScrollPane 滚动面板
        JScrollPane huadong = new JScrollPane();
        // 创建一个 JTextArea 多行文本输出框
        JTextArea shuchu = new JTextArea();
        // 创建一个 JButton 按钮
        JButton button = new JButton("确定");
        // 创建一个 JTextField 单行文本输入框
        JTextField shuru = new JTextField();
        // 将 shuchu 文本区域添加到滚动面板中
        huadong.setViewportView(shuchu);

        // 程序启动时，在 shuchu 文本区域显示 "Hi, world"
        shuchu.setText("Hi, world");

        // 设置面板的布局为绝对布局
        panel.setLayout(null);
        // 设置滚动面板的位置和大小
        huadong.setBounds(11, 1, 399, 200);
        // 将滚动面板添加到面板中
        panel.add(huadong);
        // 设置输入框的位置和大小
        shuru.setBounds(11, 210, 350, 30);
        // 设置按钮的位置和大小
        button.setBounds(360, 210, 60, 30);

        // 为按钮添加点击事件监听器
        button.addActionListener(e -> {
            // 获取输入框的文本内容
            String input = shuru.getText();
            // 将输入框的内容显示在输出框中
            shuchu.setText(input);
        });

        // 将按钮添加到面板中
        panel.add(button);
        // 将输入框添加到面板中
        panel.add(shuru);
        // 将面板添加到窗口中
        frame.add(panel);



        // 设置窗口的位置和大小
        frame.setBounds(100, 100, 430, 300);
        // 设置窗口关闭时的操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 显示窗口
        frame.setVisible(true);
    }
}
