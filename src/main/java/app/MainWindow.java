package app;

// 导入Swing组件相关的类
import javax.swing.*;
// 导入AWT组件相关的类
import java.awt.*;
// 导入事件处理相关的类
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// 导入日期时间相关的类
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 宠物养成系统的主窗口类
 * 这个类创建了系统的主界面，包含：
 * 1. 顶部时间显示
 * 2. 六个主要功能按钮
 * 3. 各种功能窗口的打开方法
 *
 * 主要功能：
 * - 显示实时时间
 * - 提供健康度、饱和度、喂食、信息、游戏和工具等功能入口
 * - 管理各个功能窗口的创建和显示
 */
public class MainWindow extends JFrame {
    // 显示时间的标签
    private JLabel timeLabel;
    // 用于更新时间的定时器
    private Timer timer;
    // 主内容面板
    private JPanel contentPanel;
    
    /**
     * 构造函数
     * 初始化主窗口的所有组件和布局
     * 包括：
     * 1. 设置窗口基本属性
     * 2. 创建时间显示面板
     * 3. 创建功能按钮面板
     * 4. 设置整体布局
     */
    public MainWindow() {
        // 设置窗口标题和基本属性
        setTitle("宠物养成系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 关闭窗口时退出程序
        setSize(600, 400);                              // 设置窗口大小
        setLocationRelativeTo(null);                    // 窗口居中显示
        
        // 创建顶部面板，用于显示当前时间
        JPanel topPanel = new JPanel(new BorderLayout());
        timeLabel = new JLabel();
        updateTimeLabel();  // 更新时间显示
        topPanel.add(timeLabel, BorderLayout.EAST);  // 将时间标签添加到面板右侧
        
        // 创建定时器，每秒更新一次时间显示
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimeLabel();
            }
        });
        timer.start();  // 启动定时器
        
        // 创建主菜单按钮面板，使用3x2的网格布局
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        
        // 创建六个功能按钮
        JButton healthButton = createMenuButton("健康度界面", e -> showHealthWindow());
        JButton satietyButton = createMenuButton("饱和度界面", e -> showSatietyWindow());
        JButton feedingButton = createMenuButton("喂食界面", e -> showFeedingWindow());
        JButton infoButton = createMenuButton("宠物信息界面", e -> showInfoWindow());
        JButton gameButton = createMenuButton("游戏", e -> showGameWindow());
        JButton toolsButton = createMenuButton("实用工具", e -> showToolsWindow());
        
        // 将按钮添加到面板中
        buttonPanel.add(healthButton);
        buttonPanel.add(satietyButton);
        buttonPanel.add(feedingButton);
        buttonPanel.add(infoButton);
        buttonPanel.add(gameButton);
        buttonPanel.add(toolsButton);
        
        // 创建主内容面板，设置边距
        contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.add(topPanel, BorderLayout.NORTH);    // 添加顶部时间面板
        contentPanel.add(buttonPanel, BorderLayout.CENTER); // 添加按钮面板
        
        setContentPane(contentPanel);  // 设置窗口的内容面板
    }
    
    /**
     * 创建菜单按钮的辅助方法
     * 统一设置按钮的字体和样式
     * 
     * @param text 按钮显示的文本
     * @param listener 按钮点击时的事件监听器
     * @return 创建好的按钮
     */
    private JButton createMenuButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));  // 设置按钮字体
        button.addActionListener(listener);  // 添加点击事件监听器
        return button;
    }
    
    /**
     * 更新时间标签的显示
     * 从peizhi类获取当前日期和时间
     * 格式：当前时间: YYYY-MM-DD HH:mm:ss
     */
    private void updateTimeLabel() {
        String dateStr = peizhi.getCurrentDate();  // 获取当前日期
        String timeStr = peizhi.getCurrentTime();  // 获取当前时间
        timeLabel.setText("当前时间: " + dateStr + " " + timeStr);  // 更新标签文本
    }
    
    /**
     * 显示健康度窗口
     * 读取宠物信息并创建健康度窗口
     * 健康度信息存储在D:\Animal\1.txt文件中
     */
    private void showHealthWindow() {
        int[] info = PersonalInfoManager.readInfo("D:\\Animal\\1.txt");  // 读取宠物信息
        HealthWindow healthWindow = new HealthWindow(this, info[1]);     // 创建健康度窗口
        healthWindow.setVisible(true);                                   // 显示窗口
    }
    
    /**
     * 显示饱和度窗口
     * 读取宠物信息并创建饱和度窗口
     * 饱和度信息存储在D:\Animal\1.txt文件中
     */
    private void showSatietyWindow() {
        int[] info = PersonalInfoManager.readInfo("D:\\Animal\\1.txt");  // 读取宠物信息
        SatietyWindow satietyWindow = new SatietyWindow(this, info[0]); // 创建饱和度窗口
        satietyWindow.setVisible(true);                                  // 显示窗口
    }
    
    /**
     * 显示喂食窗口
     * 读取宠物信息并创建喂食窗口
     * 饱和度信息存储在D:\Animal\1.txt文件中
     */
    private void showFeedingWindow() {
        int[] info = PersonalInfoManager.readInfo("D:\\Animal\\1.txt");  // 读取宠物信息
        FeedingWindow feedingWindow = new FeedingWindow(this, info[0]); // 创建喂食窗口
        feedingWindow.setVisible(true);                                  // 显示窗口
    }
    
    /**
     * 显示宠物信息窗口
     * 读取宠物信息并创建信息显示窗口
     * 宠物信息存储在D:\Animal\1.txt文件中
     */
    private void showInfoWindow() {
        int[] info = PersonalInfoManager.readInfo("D:\\Animal\\1.txt");  // 读取宠物信息
        InfoWindow infoWindow = new InfoWindow(this, info);              // 创建信息窗口
        infoWindow.setVisible(true);                                     // 显示窗口
    }
    
    /**
     * 显示游戏窗口
     * 读取宠物信息并创建游戏窗口
     * 金币信息存储在D:\Animal\1.txt文件中
     */
    private void showGameWindow() {
        int[] info = PersonalInfoManager.readInfo("D:\\Animal\\1.txt");  // 读取宠物信息
        GameWindow gameWindow = new GameWindow(this, info[2]);           // 创建游戏窗口
        gameWindow.setVisible(true);                                     // 显示窗口
    }
    
    /**
     * 显示工具窗口
     * 创建并显示工具窗口
     * 工具窗口包含天气查询等功能
     */
    private void showToolsWindow() {
        ToolsWindow toolsWindow = new ToolsWindow(this);                 // 创建工具窗口
        toolsWindow.setVisible(true);                                    // 显示窗口
    }
    
    /**
     * 重写dispose方法
     * 在关闭窗口时停止定时器，防止内存泄漏
     * 这是Java Swing应用程序的最佳实践
     */
    @Override
    public void dispose() {
        timer.stop();  // 停止定时器
        super.dispose();  // 调用父类的dispose方法
    }
} 