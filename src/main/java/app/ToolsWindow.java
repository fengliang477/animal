package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 工具窗口类
 * 这个类实现了一个工具菜单界面，包括：
 * 1. 天气查询功能
 * 2. 预留其他工具功能
 * 3. 界面布局管理
 */
public class ToolsWindow extends JDialog {
    // 界面组件
    private JButton weatherButton;  // 天气查询按钮
    private JButton backButton;     // 返回按钮
    
    /**
     * 构造函数，初始化工具窗口界面
     * 
     * @param parent 父窗口，用于设置对话框的位置
     */
    public ToolsWindow(JFrame parent) {
        // 调用父类构造函数，设置对话框标题和模态
        super(parent, "实用工具", true);
        // 设置窗口大小和位置
        setSize(400, 300);
        setLocationRelativeTo(parent);
        
        // 创建主面板，使用垂直布局
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // 创建并添加标题标签
        JLabel titleLabel = new JLabel("工具菜单");
        titleLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        
        // 创建工具按钮面板，使用网格布局
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        
        // 创建并配置天气查询按钮
        weatherButton = new JButton("天气查询");
        weatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWeatherWindow();
            }
        });
        
        // 创建并配置开发中按钮（当前禁用）
        JButton developButton = new JButton("开发中...");
        developButton.setEnabled(false);
        
        // 将按钮添加到按钮面板
        buttonPanel.add(weatherButton);
        buttonPanel.add(developButton);
        
        // 设置按钮面板的最大宽度和居中对齐
        buttonPanel.setMaximumSize(new Dimension(400, buttonPanel.getPreferredSize().height));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(buttonPanel);
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
     * 打开天气查询窗口
     * 创建并显示WeatherWindow实例
     */
    private void openWeatherWindow() {
        WeatherWindow weatherWindow = new WeatherWindow(this);
        weatherWindow.setVisible(true);
    }
} 