package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * 宠物游戏窗口类
 * 这个类实现了一个简单的猜数字游戏，包括：
 * 1. 游戏界面布局
 * 2. 游戏逻辑处理
 * 3. 金币奖励系统
 * 4. 游戏状态保存
 */
public class GameWindow extends JDialog {
    // 游戏界面组件
    private JButton guessButton;    // 猜测按钮
    private JButton backButton;     // 返回按钮
    private JTextField guessField;  // 输入框
    private JLabel resultLabel;     // 结果显示标签
    private JLabel coinsLabel;      // 金币显示标签
    private JLabel attemptsLabel;   // 尝试次数显示标签
    
    // 游戏状态变量
    private int randomNumber;       // 随机生成的数字
    private int attempts;           // 当前尝试次数
    private int maxAttempts = 3;    // 最大尝试次数
    private int currentCoins;       // 当前金币数
    private boolean gameOver = false; // 游戏是否结束
    
    /**
     * 构造函数，初始化游戏窗口界面
     * 
     * @param parent 父窗口，用于设置对话框的位置
     * @param coins 当前金币数，用于显示和更新
     */
    public GameWindow(JFrame parent, int coins) {
        // 调用父类构造函数，设置对话框标题和模态
        super(parent, "猜数字游戏", true);
        // 设置窗口大小和位置
        setSize(400, 350);
        setLocationRelativeTo(parent);
        
        // 初始化当前金币数
        this.currentCoins = coins;
        
        // 生成1-100之间的随机数作为答案
        randomNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        
        // 创建主面板，使用垂直布局
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // 创建并添加标题标签
        JLabel titleLabel = new JLabel("欢迎来到猜数字游戏");
        titleLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        
        // 创建并添加游戏说明标签
        JLabel infoLabel = new JLabel("<html>请猜一个1到100之间的数字<br>你有3次机会</html>");
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(infoLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // 创建并添加金币显示标签
        coinsLabel = new JLabel("当前金币: " + currentCoins);
        coinsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(coinsLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        
        // 创建并添加尝试次数显示标签
        attemptsLabel = new JLabel("剩余尝试次数: " + (maxAttempts - attempts));
        attemptsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(attemptsLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // 创建输入区域面板
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel guessLabel = new JLabel("你的猜测:");
        guessField = new JTextField(5);
        inputPanel.add(guessLabel);
        inputPanel.add(guessField);
        
        // 设置输入面板的最大宽度
        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputPanel.getPreferredSize().height));
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        
        // 创建并添加结果显示标签
        resultLabel = new JLabel(" ");
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
        mainPanel.add(resultLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // 创建按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        // 创建并配置猜测按钮
        guessButton = new JButton("猜一猜");
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeGuess();
            }
        });
        
        // 创建并配置返回按钮
        backButton = new JButton("返回");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGameResult();
                dispose();
            }
        });
        
        // 添加按钮到按钮面板
        buttonPanel.add(guessButton);
        buttonPanel.add(backButton);
        
        // 设置按钮面板的最大宽度
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonPanel.getPreferredSize().height));
        mainPanel.add(buttonPanel);
        
        // 设置主面板为对话框的内容面板
        setContentPane(mainPanel);
    }
    
    /**
     * 处理用户的猜测
     * 包括：
     * 1. 验证输入的有效性
     * 2. 更新尝试次数
     * 3. 判断猜测结果
     * 4. 更新金币奖励
     * 5. 处理游戏结束状态
     */
    private void makeGuess() {
        // 如果游戏已结束，显示提示信息
        if (gameOver) {
            resultLabel.setText("游戏已结束，请返回主界面");
            return;
        }
        
        try {
            // 获取并解析用户输入的数字
            int guess = Integer.parseInt(guessField.getText().trim());
            
            // 验证输入范围
            if (guess < 1 || guess > 30) {
                resultLabel.setText("请输入1到30之间的数字");
                return;
            }
            
            // 更新尝试次数
            attempts++;
            attemptsLabel.setText("剩余尝试次数: " + (maxAttempts - attempts));
            
            // 判断猜测结果
            if (guess == randomNumber) {
                // 猜对了，奖励100金币
                resultLabel.setText("恭喜你猜对了！奖励100金币");
                currentCoins += 100;
                coinsLabel.setText("当前金币: " + currentCoins);
                gameOver = true;
                guessButton.setEnabled(false);
            } else if (guess > randomNumber) {
                resultLabel.setText("猜大了，请重试");
            } else {
                resultLabel.setText("猜小了，请重试");
            }
            
            // 检查是否达到最大尝试次数
            if (attempts >= maxAttempts && !gameOver) {
                resultLabel.setText("游戏结束，正确答案是 " + randomNumber + "，奖励1金币");
                currentCoins += 1;
                coinsLabel.setText("当前金币: " + currentCoins);
                gameOver = true;
                guessButton.setEnabled(false);
            }
            
            // 清空输入框并获取焦点
            guessField.setText("");
            guessField.requestFocus();
            
        } catch (NumberFormatException e) {
            // 处理无效输入
            resultLabel.setText("请输入有效的数字");
        }
    }
    
    /**
     * 保存游戏结果
     * 将当前的金币数保存到文件中
     */
    private void saveGameResult() {
        // 读取当前宠物信息
        int[] info = PersonalInfoManager.readInfo("D:\\Animal\\1.txt");
        // 更新并保存新的金币数
        PersonalInfoManager.writeInfo("D:\\Animal\\1.txt", info[0], info[1], currentCoins);
    }
} 