package app;

// 导入Swing库，用于创建图形用户界面组件
import javax.swing.*;
// 导入AWT库，用于布局和事件处理
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 宠物养成系统的登录窗口类
 * 这个类创建了用户注册界面，包含：
 * 1. 用户信息输入表单
 * 2. 注册按钮
 * 3. 状态显示标签
 */
public class LoginWindow extends JFrame {
    // 用户信息输入框
    private JTextField nameField;    // 姓名输入框
    private JTextField sexField;     // 性别输入框
    private JTextField ageField;     // 年龄输入框
    private JButton registerButton;  // 注册按钮
    private JLabel statusLabel;      // 状态显示标签
    
    /**
     * 构造函数
     * 初始化登录窗口的所有组件和布局
     */
    public LoginWindow() {
        // 设置窗口标题和基本属性
        setTitle("宠物养成系统 - 注册");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 关闭窗口时退出程序
        setSize(400, 300);                              // 设置窗口大小
        setLocationRelativeTo(null);                    // 窗口居中显示
        
        // 创建主面板，使用垂直布局
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // 设置边距
        
        // 添加欢迎标题
        JLabel titleLabel = new JLabel("欢迎使用宠物养成系统，请先注册");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // 居中对齐
        titleLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));  // 设置字体
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));  // 添加垂直间距
        
        // 创建表单面板，使用网格布局
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        
        // 创建输入框和标签
        JLabel nameLabel = new JLabel("名字:");
        nameField = new JTextField(20);  // 设置输入框宽度
        JLabel sexLabel = new JLabel("性别:");
        sexField = new JTextField(20);
        JLabel ageLabel = new JLabel("年龄:");
        ageField = new JTextField(20);
        
        // 将标签和输入框添加到表单面板
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(sexLabel);
        formPanel.add(sexField);
        formPanel.add(ageLabel);
        formPanel.add(ageField);
        
        // 设置表单面板的最大宽度
        formPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, formPanel.getPreferredSize().height));
        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(20));  // 添加垂直间距
        
        // 创建注册按钮
        registerButton = new JButton("注册");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);  // 居中对齐
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();  // 点击按钮时调用注册方法
            }
        });
        mainPanel.add(registerButton);
        mainPanel.add(Box.createVerticalStrut(10));  // 添加垂直间距
        
        // 创建状态标签，用于显示操作结果
        statusLabel = new JLabel(" ");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // 居中对齐
        mainPanel.add(statusLabel);
        
        setContentPane(mainPanel);  // 设置窗口的内容面板
    }
    
    /**
     * 处理用户注册的方法
     * 验证输入信息并保存用户数据
     */
    private void registerUser() {
        // 获取并清理输入数据
        String name = nameField.getText().trim();
        String sex = sexField.getText().trim();
        String ageText = ageField.getText().trim();
        
        // 验证所有字段是否都已填写
        if (name.isEmpty() || sex.isEmpty() || ageText.isEmpty()) {
            statusLabel.setText("请填写所有字段！");
            return;
        }
        
        // 验证年龄是否为有效数字
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            statusLabel.setText("年龄必须是数字！");
            return;
        }
        
        // 显示保存状态
        statusLabel.setText("正在保存，请稍等...");
        
        // 保存用户信息到文件
        PersonalInfoManager.writePersonalInfo("D:\\Animal\\2.txt", name, sex, age, true);
        
        // 验证保存是否成功
        String[] personalInfo = PersonalInfoManager.readPersonalInfo("D:\\Animal\\2.txt");
        
        // 检查保存的信息是否正确
        if (personalInfo != null && 
            personalInfo[0].equals(name) && 
            personalInfo[1].equals(sex) && 
            Integer.parseInt(personalInfo[2]) == age) {
            
            // 注册成功，显示成功消息
            JOptionPane.showMessageDialog(this, "注册成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
            dispose();  // 关闭注册窗口
            
            // 打开主窗口
            SwingUtilities.invokeLater(() -> {
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            });
        } else {
            // 注册失败，显示错误消息
            statusLabel.setText("注册失败，请重试！");
        }
    }
} 