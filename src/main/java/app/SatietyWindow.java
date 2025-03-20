package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 宠物饱和度窗口类
 * 这个类用于显示宠物的饱腹状态，包括：
 * 1. 饱和度进度条显示（0-100%）
 * 2. 不同状态下的颜色反馈（绿色、橙色、红色）
 * 3. 文字状态描述
 * 4. 界面布局管理
 */
public class SatietyWindow extends JDialog {
    // 界面组件声明
    private JLabel satietyLabel;    // 显示具体饱和度数值的标签
    private JLabel statusLabel;      // 显示状态描述的标签
    private JProgressBar satietyBar; // 显示饱和度的进度条
    private JButton backButton;      // 返回按钮
    
    /**
     * 构造函数，初始化饱和度窗口界面
     * 
     * @param parent 父窗口，用于设置对话框的位置
     * @param satietyValue 当前饱和度值（0-100）
     */
    public SatietyWindow(JFrame parent, int satietyValue) {
        // 调用父类JDialog的构造函数，设置窗口标题和模态
        super(parent, "饱和度界面", true);
        // 设置窗口大小和位置（居中显示）
        setSize(400, 300);
        setLocationRelativeTo(parent);
        
        // 创建主面板，使用垂直布局（BoxLayout）
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        // 设置面板边距，提升界面美观度
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // 创建并配置标题标签
        JLabel titleLabel = new JLabel(peizhi.name() + "的饱腹状态");
        titleLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));  // 设置字体
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // 居中对齐
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));  // 添加垂直间距
        
        // 创建并配置饱和度进度条
        satietyBar = new JProgressBar(0, 100);  // 进度条范围：0-100
        satietyBar.setValue(satietyValue);      // 设置当前值
        satietyBar.setStringPainted(true);      // 显示进度文字
        satietyBar.setString(satietyValue + "%");
        
        // 根据饱和度值设置进度条颜色
        if (satietyValue > 70) {
            satietyBar.setForeground(new Color(0, 153, 0));     // 绿色：状态良好
        } else if (satietyValue > 30) {
            satietyBar.setForeground(new Color(255, 153, 0));   // 橙色：状态一般
        } else {
            satietyBar.setForeground(new Color(204, 0, 0));     // 红色：状态危险
        }
        
        // 创建进度条面板并设置布局
        JPanel barPanel = new JPanel(new BorderLayout());
        barPanel.add(satietyBar);
        // 限制进度条面板的最大宽度，保持界面协调
        barPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, satietyBar.getPreferredSize().height));
        mainPanel.add(barPanel);
        mainPanel.add(Box.createVerticalStrut(20));  // 添加垂直间距
        
        // 创建并配置饱和度数值标签
        satietyLabel = new JLabel("当前饱和度: " + satietyValue);
        satietyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // 居中对齐
        satietyLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
        mainPanel.add(satietyLabel);
        mainPanel.add(Box.createVerticalStrut(10));  // 添加垂直间距
        
        // 创建并配置状态描述标签
        statusLabel = new JLabel("当前状态: " + getSatietyStatus(satietyValue));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // 居中对齐
        statusLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
        mainPanel.add(statusLabel);
        mainPanel.add(Box.createVerticalStrut(30));  // 添加垂直间距
        
        // 创建并配置返回按钮
        backButton = new JButton("返回");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);  // 居中对齐
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // 关闭当前窗口
            }
        });
        mainPanel.add(backButton);
        
        // 设置主面板为窗口的内容面板
        setContentPane(mainPanel);
    }
    
    /**
     * 根据饱和度值获取对应的状态描述
     * 饱和度值范围对应的状态：
     * - >90%: 饱了
     * - 70-90%: 有点饿了
     * - 50-70%: 饿了
     * - 30-50%: 饥饿
     * - 10-30%: 快要饿死了
     * - <=10%: 已经饿死了
     * 
     * @param satietyValue 饱和度值（0-100）
     * @return 对应的状态描述文字
     */
    private String getSatietyStatus(int satietyValue) {
        if (satietyValue > 90) {
            return "饱了";  // 最佳状态
        } else if (satietyValue > 70) {
            return "有点饿了";  // 良好状态
        } else if (satietyValue > 50) {
            return "饿了";  // 需要注意
        } else if (satietyValue > 30) {
            return "饥饿";  // 警告状态
        } else if (satietyValue > 10) {
            return "快要饿死了";  // 危险状态
        } else {
            return peizhi.name() + "已经饿死了";  // 致命状态
        }
    }
} 