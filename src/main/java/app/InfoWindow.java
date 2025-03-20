package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 宠物信息窗口类
 * 这个类实现了一个显示宠物详细信息的界面，包括：
 * 1. 基本信息显示（名字、性别、年龄）
 * 2. 状态信息显示（健康度、饱和度、金币）
 * 3. 界面布局管理
 */
public class InfoWindow extends JDialog {
    private JButton backButton;
    
    /**
     * 构造函数，初始化宠物信息窗口界面
     * 
     * @param parent 父窗口，用于设置对话框的位置
     * @param info 宠物信息数组，包含[饱和度, 健康度, 金币数]
     */
    public InfoWindow(JFrame parent, int[] info) {
        super(parent, "宠物信息界面", true);
        setSize(400, 350);
        setLocationRelativeTo(parent);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // 创建标题
        JLabel titleLabel = new JLabel(peizhi.name() + "的信息");
        titleLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        
        // 创建信息面板
        JPanel infoPanel = new JPanel(new GridLayout(6, 1, 5, 10));
        infoPanel.setBorder(BorderFactory.createEtchedBorder());
        
        // 添加各种信息项
        addInfoItem(infoPanel, "名字", peizhi.name());
        addInfoItem(infoPanel, "性别", peizhi.sex());
        addInfoItem(infoPanel, "年龄", String.valueOf(peizhi.age()));
        addInfoItem(infoPanel, "健康度", String.valueOf(info[1]));
        addInfoItem(infoPanel, "饱和度", String.valueOf(info[0]));
        addInfoItem(infoPanel, "金币数量", String.valueOf(info[2]));
        
        infoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, infoPanel.getPreferredSize().height));
        mainPanel.add(infoPanel);
        mainPanel.add(Box.createVerticalStrut(30));
        
        // 创建返回按钮
        backButton = new JButton("返回");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 关闭窗口
            }
        });
        mainPanel.add(backButton);
        
        setContentPane(mainPanel);
    }
    
    /**
     * 向面板添加一个信息项
     * 创建一个包含标签和值的水平布局面板
     * 
     * @param panel 要添加到的面板
     * @param key 信息项名称（如"名字"、"性别"等）
     * @param value 信息项的值
     */
    private void addInfoItem(JPanel panel, String key, String value) {
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel keyLabel = new JLabel(key + ":");
        keyLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
        
        itemPanel.add(keyLabel);
        itemPanel.add(Box.createHorizontalStrut(10));
        itemPanel.add(valueLabel);
        
        panel.add(itemPanel);
    }
} 