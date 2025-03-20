package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 宠物健康度窗口类
 * 这个类实现了一个显示宠物健康状态的界面，包括：
 * 1. 健康度进度条显示
 * 2. 状态文字描述
 * 3. 颜色状态反馈
 * 4. 界面布局管理
 */
public class HealthWindow extends JDialog {
    // 界面组件
    private JLabel healthLabel;     // 健康度数值标签
    private JLabel statusLabel;     // 状态描述标签
    private JProgressBar healthBar; // 健康度进度条
    private JButton backButton;     // 返回按钮
    
    /**
     * 构造函数，初始化健康度窗口界面
     * 
     * @param parent 父窗口，用于设置对话框的位置
     * @param healthValue 当前健康度值
     */
    public HealthWindow(JFrame parent, int healthValue) {
        // 调用父类构造函数，设置对话框标题和模态
        super(parent, "健康度界面", true);
        // 设置窗口大小和位置
        setSize(400, 300);
        setLocationRelativeTo(parent);
        
        // 创建主面板，使用垂直布局
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // 创建并添加标题标签
        JLabel titleLabel = new JLabel(peizhi.name() + "的健康状态");
        titleLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // 创建并配置健康度进度条
        healthBar = new JProgressBar(0, 100);
        healthBar.setValue(healthValue);
        healthBar.setStringPainted(true);  // 显示进度条文字
        healthBar.setString(healthValue + "%");
        
        // 根据健康度值设置进度条颜色
        if (healthValue > 70) {
            healthBar.setForeground(new Color(0, 153, 0));  // 绿色：状态良好
        } else if (healthValue > 30) {
            healthBar.setForeground(new Color(255, 153, 0));  // 橙色：状态一般
        } else {
            healthBar.setForeground(new Color(204, 0, 0));  // 红色：状态危险
        }
        
        // 创建进度条面板并设置最大宽度
        JPanel barPanel = new JPanel(new BorderLayout());
        barPanel.add(healthBar);
        barPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, healthBar.getPreferredSize().height));
        mainPanel.add(barPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // 创建并添加健康度数值标签
        healthLabel = new JLabel("当前健康度: " + healthValue);
        healthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        healthLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
        mainPanel.add(healthLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        
        // 创建并添加状态描述标签
        statusLabel = new JLabel("当前状态: " + getHealthStatus(healthValue));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
        mainPanel.add(statusLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        
        // 创建并配置返回按钮
        backButton = new JButton("返回");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // 关闭窗口
            }
        });
        mainPanel.add(backButton);
        
        // 设置主面板为对话框的内容面板
        setContentPane(mainPanel);
    }
    
    /**
     * 根据健康度值获取对应的状态描述
     * 健康度值范围对应的状态：
     * - >90%: 健康
     * - 70-90%: 感到疲惫
     * - 50-70%: 生病了
     * - 30-50%: 急需治疗
     * - 10-30%: 即将死亡
     * - <=10%: 已经死亡
     * 
     * @param healthValue 健康度值
     * @return 健康状态描述
     */
    private String getHealthStatus(int healthValue) {
        if (healthValue > 90) {
            return "健康";
        } else if (healthValue > 70) {
            return "感到疲惫";
        } else if (healthValue > 50) {
            return "生病了";
        } else if (healthValue > 30) {
            return "急需治疗";
        } else if (healthValue > 10) {
            return "即将死亡";
        } else {
            return peizhi.name() + "已经死了";
        }
    }
} 