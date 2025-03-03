// 声明当前类所在的包
package app;

// 导入Swing库中的JFrame类，用于创建窗口
import javax.swing.*;

/**
 * 定义一个名为chat的类，继承自JFrame，用于创建一个聊天窗口界面
 */
public class chat extends JFrame {
    // 静态变量i，用于记录新建会话的数量
    static int i = 0;

    /**
     * 程序的入口方法，创建并初始化聊天窗口
     *
     * @param args 命令行参数，在本方法中未使用
     */
    public static void main(String[] args) {
        // 创建chat类的实例，即聊天窗口
        chat m = new chat();
        // 设置窗口的大小为400像素宽，300像素高
        m.setSize(400, 300);
        // 将窗口置于屏幕中央
        m.setLocationRelativeTo(null);
        // 创建一个JPanel对象，用于放置聊天显示区域
        JPanel chat = new JPanel();
        // 创建一个JScrollPane对象，用于实现滚动功能
        JScrollPane JScrollPane = new JScrollPane();
        // 创建一个JTextArea对象，用于显示聊天内容，设置初始行数为13，列数为40
        JTextArea JTextArea = new JTextArea(13, 40);
        // 将JTextArea添加到JScrollPane中
        JScrollPane.setViewportView(JTextArea);
        // 将JScrollPane添加到chat面板中
        chat.add(JScrollPane);

        // 创建一个JPanel对象，用于放置输入组件
        JPanel input = new JPanel();
        // 创建一个JLabel对象，显示提示信息“请输入”
        JLabel JLabel = new JLabel("请输入");
        // 创建一个JTextField对象，用于用户输入文本，设置列数为20
        JTextField JTextField = new JTextField(20);
        // 创建一个JButton对象，用于触发发送消息的操作
        JButton JButton = new JButton("发送");
        // 创建一个JButton对象，用于触发新建会话的操作
        JButton jButton = new JButton("新建会话");
        // 将JLabel添加到input面板中
        input.add(JLabel);
        // 将JTextField添加到input面板中
        input.add(JTextField);
        // 将JButton添加到input面板中
        input.add(JButton);
        // 将jButton添加到input面板中
        input.add(jButton);
        // 将新建会话按钮添加到输入面板中
        input.add(jButton);

        // 点击新建会话按钮，新建会话
        jButton.addActionListener(e -> {
            // 会话编号加1
            i++;
            // 在文本区域中追加新建会话的信息
            JTextArea.append("新建会话" + i + "\n");
        });

        // 点击发送按钮，处理用户输入并获取AI回复
        JButton.addActionListener(e -> {
            // 获取用户在文本框中输入的内容
            String str = JTextField.getText();
            // 定义使用的模型名称
            String model = "moonshot-v1-8k";
            // 定义最大返回的Token数
            int maxTokens = 1024;
            // 在文本区域中追加用户的提问信息
            JTextArea.append("用户提问：" + str + "\n");
            // 调用kimipost.chat方法获取AI回复，并在文本区域中追加回复信息
            JTextArea.append("AI回复：" + kimipost.chat(str, "chatid" + i, model, maxTokens) + "\n");
            // 清空文本框中的内容
            JTextField.setText("");
        });

        // 将聊天显示面板添加到窗口中
        m.add(chat);
        // 将输入面板添加到窗口的底部
        m.add(input, "South");
        // 设置窗口关闭时的操作，即退出程序
        m.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 使窗口可见
        m.setVisible(true);
    }
}
