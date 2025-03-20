package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 宠物喂食窗口类
 * 这个类实现了一个宠物喂食界面，包括：
 * 1. 食物选择和数量设置
 * 2. 饱和度显示和更新
 * 3. 喂食效果反馈
 * 4. 数据保存功能
 */
public class FeedingWindow extends JDialog {
    // 界面组件
    private JComboBox<String> foodComboBox;  // 食物选择下拉框
    private JSpinner quantitySpinner;        // 数量选择器
    private JButton feedButton;              // 喂食按钮
    private JButton backButton;              // 返回按钮
    private JLabel resultLabel;              // 结果显示标签
    private int satietyValue;                // 当前饱和度值
    private JProgressBar satietyBar;         // 饱和度进度条
    
    /**
     * 构造函数，初始化喂食窗口界面
     * 
     * @param parent 父窗口，用于设置对话框的位置
     * @param currentSatiety 当前饱和度值
     */
    public FeedingWindow(JFrame parent, int currentSatiety) {
        // 调用父类构造函数，设置对话框标题和模态
        super(parent, "喂食界面", true);
        // 设置窗口大小和位置
        setSize(450, 350);
        setLocationRelativeTo(parent);
        
        // 保存当前饱和度值
        this.satietyValue = currentSatiety;
        
        // 创建主面板，使用垂直布局
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // 创建并添加标题标签
        JLabel titleLabel = new JLabel("给" + peizhi.name() + "喂食");
        titleLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // 创建并配置饱和度进度条
        satietyBar = new JProgressBar(0, 100);
        satietyBar.setValue(satietyValue);
        satietyBar.setStringPainted(true);  // 显示进度条文字
        satietyBar.setString("当前饱和度: " + satietyValue + "%");
        JPanel barPanel = new JPanel(new BorderLayout());
        barPanel.add(satietyBar);
        barPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, satietyBar.getPreferredSize().height));
        mainPanel.add(barPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // 创建食物选择和数量面板
        JPanel foodPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        
        // 创建食物选择下拉框
        JLabel foodLabel = new JLabel("选择食物:");
        String[] foods = {"冯亮", "文件夹", "文档", "高梓涵", "垃圾"};
        foodComboBox = new JComboBox<>(foods);
        
        // 创建数量选择器
        JLabel quantityLabel = new JLabel("选择数量:");
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 10, 1);  // 最小值1，最大值10，步长1
        quantitySpinner = new JSpinner(model);
        
        // 将组件添加到食物面板
        foodPanel.add(foodLabel);
        foodPanel.add(foodComboBox);
        foodPanel.add(quantityLabel);
        foodPanel.add(quantitySpinner);
        
        // 设置食物面板的最大宽度
        foodPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, foodPanel.getPreferredSize().height));
        mainPanel.add(foodPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // 创建并添加结果显示标签
        resultLabel = new JLabel(" ");
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
        mainPanel.add(resultLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        
        // 创建按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        // 创建并配置喂食按钮
        feedButton = new JButton("喂食");
        feedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedPet();
            }
        });
        
        // 创建并配置返回按钮
        backButton = new JButton("返回");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // 关闭窗口
            }
        });
        
        // 将按钮添加到按钮面板
        buttonPanel.add(feedButton);
        buttonPanel.add(backButton);
        
        // 设置按钮面板的最大宽度
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonPanel.getPreferredSize().height));
        mainPanel.add(buttonPanel);
        
        // 设置主面板为对话框的内容面板
        setContentPane(mainPanel);
    }
    
    /**
     * 处理喂食逻辑
     * 包括：
     * 1. 获取选择的食物和数量
     * 2. 计算增加的饱和度
     * 3. 更新界面显示
     * 4. 保存数据到文件
     */
    private void feedPet() {
        // 获取用户选择的食物和数量
        String selectedFood = (String) foodComboBox.getSelectedItem();
        int quantity = (int) quantitySpinner.getValue();
        
        // 定义各种食物对应的饱和度增加值
        int[] foodValues = {25, 7, 9, 5, 4};  // 对应五种食物的饱和度增加值
        int foodIndex = foodComboBox.getSelectedIndex();
        
        // 计算实际增加的饱和度
        int increase = foodValues[foodIndex] * quantity;
        satietyValue += increase;
        
        // 更新饱和度进度条显示
        satietyBar.setValue(satietyValue);
        satietyBar.setString("当前饱和度: " + satietyValue + "%");
        
        // 更新结果显示
        resultLabel.setText(selectedFood + " 喂食成功，饱和度增加了 " + increase);
        
        // 读取并更新宠物信息文件
        int[] info = PersonalInfoManager.readInfo("D:\\Animal\\1.txt");
        PersonalInfoManager.writeInfo("D:\\Animal\\1.txt", satietyValue, info[1], info[2]);
        
        // 根据新的饱和度更新进度条颜色
        updateProgressBarColor();
    }
    
    /**
     * 更新进度条颜色
     * 根据饱和度值设置不同的颜色：
     * - 大于70%：绿色
     * - 30%-70%：橙色
     * - 小于30%：红色
     */
    private void updateProgressBarColor() {
        if (satietyValue > 70) {
            satietyBar.setForeground(new Color(0, 153, 0));  // 绿色
        } else if (satietyValue > 30) {
            satietyBar.setForeground(new Color(255, 153, 0));  // 橙色
        } else {
            satietyBar.setForeground(new Color(204, 0, 0));  // 红色
        }
    }
} 