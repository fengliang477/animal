package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 天气查询窗口类
 * 这个类实现了一个天气查询界面，包括：
 * 1. 城市和日期输入
 * 2. 天气信息查询
 * 3. 结果显示
 * 4. 异步查询处理
 */
public class WeatherWindow extends JDialog {
    // 界面组件
    private JTextField cityField;    // 城市名称输入框
    private JTextField dateField;    // 日期输入框
    private JButton queryButton;     // 查询按钮
    private JButton backButton;      // 返回按钮
    private JTextArea resultArea;    // 结果显示区域
    
    /**
     * 构造函数，初始化天气查询窗口界面
     * 
     * @param parent 父窗口，用于设置对话框的位置
     */
    public WeatherWindow(JDialog parent) {
        // 调用父类构造函数，设置对话框标题和模态
        super(parent, "天气查询", true);
        // 设置窗口大小和位置
        setSize(500, 400);
        setLocationRelativeTo(parent);
        
        // 创建主面板，使用垂直布局
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // 创建并添加标题标签
        JLabel titleLabel = new JLabel("天气查询");
        titleLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // 创建输入面板，使用网格布局
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        
        // 创建并添加城市输入区域
        JLabel cityLabel = new JLabel("城市名称(不要包含市、区、县):");
        cityField = new JTextField(20);
        // 创建并添加日期输入区域
        JLabel dateLabel = new JLabel("日期:");
        dateField = new JTextField(20);
        
        // 将组件添加到输入面板
        inputPanel.add(cityLabel);
        inputPanel.add(cityField);
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);
        
        // 设置输入面板的最大宽度
        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, inputPanel.getPreferredSize().height));
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        
        // 创建并配置查询按钮
        queryButton = new JButton("查询");
        queryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryWeather();
            }
        });
        mainPanel.add(queryButton);
        mainPanel.add(Box.createVerticalStrut(15));
        
        // 创建并配置结果显示区域
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);  // 设置为只读
        resultArea.setWrapStyleWord(true);  // 按单词换行
        resultArea.setLineWrap(true);  // 启用自动换行
        // 创建滚动面板
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        mainPanel.add(scrollPane);
        mainPanel.add(Box.createVerticalStrut(15));
        
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
     * 查询天气信息
     * 包括：
     * 1. 输入验证
     * 2. 日期格式化
     * 3. 异步天气查询
     * 4. 结果显示
     */
    private void queryWeather() {
        // 获取用户输入的城市和日期
        String city = cityField.getText().trim();
        String date = dateField.getText().trim();
        
        // 验证输入是否为空
        if (city.isEmpty() || date.isEmpty()) {
            resultArea.setText("请输入城市名称和日期！");
            return;
        }
        
        try {
            // 格式化日期为标准格式
            String formattedDate = datejisuan.dategueifan(date);
            
            // 显示查询中的提示信息
            resultArea.setText("正在查询天气信息，请稍候...");
            
            // 创建新线程执行天气查询，避免界面冻结
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final StringBuilder result = new StringBuilder();
                    
                    try {
                        // 调用天气查询方法，将结果写入StringBuilder
                        weather.getdateweather(formattedDate, city, result);
                        
                        // 在事件分发线程中更新界面
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                if (result.length() > 0) {
                                    // 显示查询结果
                                    resultArea.setText(result.toString());
                                } else {
                                    // 显示错误信息
                                    resultArea.setText("无法获取天气信息，请检查网络连接或输入信息是否正确。");
                                }
                            }
                        });
                    } catch (Exception e) {
                        // 处理查询过程中的异常
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                resultArea.setText("查询出错: " + e.getMessage());
                            }
                        });
                    }
                }
            }).start();
            
        } catch (Exception e) {
            // 处理日期格式错误
            resultArea.setText("日期格式错误，请使用正确的日期格式！");
        }
    }
} 