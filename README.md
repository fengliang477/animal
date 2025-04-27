# 宠物养成系统项目文档

## 一、项目概述
本宠物养成系统是一个基于 Java Swing 开发的桌面应用程序，为用户提供了一个虚拟宠物养成的体验。用户可以通过该系统管理宠物的健康、饱食度，与宠物进行游戏获得金币，还能使用实用工具查询天气等信息。系统包含多个功能窗口，如主窗口、健康度窗口、饱和度窗口、喂食窗口、游戏窗口和工具窗口等，通过这些窗口实现了系统的各项功能。

## 二、模块说明

### 1. 主窗口模块（MainWindow）
- **功能**：系统的主界面，包含顶部时间显示和六个主要功能按钮，用于打开各个功能窗口。
- **关键方法**：
  - `updateTimeLabel()`：更新时间标签的显示，每秒更新一次。
  - `showHealthWindow()`：显示健康度窗口。
  - `showSatietyWindow()`：显示饱和度窗口。
  - `showFeedingWindow()`：显示喂食窗口。
  - `showInfoWindow()`：显示宠物信息窗口。
  - `showGameWindow()`：显示游戏窗口。
  - `showToolsWindow()`：显示工具窗口。

### 2. 健康度窗口模块（HealthWindow）
- **功能**：显示宠物的健康状态，包括健康度进度条、状态文字描述和颜色状态反馈。
- **关键方法**：
  - `getHealthStatus(int healthValue)`：根据健康度值返回对应的状态描述。

### 3. 饱和度窗口模块（SatietyWindow）
- **功能**：显示宠物的饱腹状态，包括饱和度进度条、状态文字描述和颜色状态反馈。

### 4. 喂食窗口模块（FeedingWindow）
- **功能**：用于给宠物喂食，更新宠物的饱和度信息。

### 5. 游戏窗口模块（GameWindow）
- **功能**：提供一个猜数字游戏，用户猜对可获得金币奖励。
- **关键方法**：
  - `makeGuess()`：处理用户的猜测，包括验证输入、更新尝试次数、判断猜测结果、更新金币奖励和处理游戏结束状态。

### 6. 工具窗口模块（ToolsWindow）
- **功能**：包含天气查询等实用工具。
- **关键方法**：
  - `openWeatherWindow()`：打开天气查询窗口。

### 7. 个人信息管理模块（PersonalInfoManager）
- **功能**：负责处理用户个人信息和宠物状态信息的读写操作。
- **关键方法**：
  - `writePersonalInfo(String filePath, String name, String gender, int age, boolean isAdmin)`：向文件中写入用户个人信息。
  - `readPersonalInfo(String filePath)`：从文件中读取用户个人信息。
  - `writeInfo(String filePath, int baoshi, int health, int goldCoin)`：向文件中写入宠物状态信息。
  - `readInfo(String filePath)`：从文件中读取宠物状态信息。

### 8. 登录窗口模块（LoginWindow）
- **功能**：用户注册界面，用户输入姓名、性别和年龄进行注册。
- **关键方法**：
  - `registerUser()`：点击注册按钮时调用，将用户信息写入文件。

## 三、类说明

### 1. MainWindow 类
```java
public class MainWindow extends JFrame {
    // 显示时间的标签
    private JLabel timeLabel;
    // 用于更新时间的定时器
    private Timer timer;
    // 主内容面板
    private JPanel contentPanel;

    // 构造函数，初始化主窗口的所有组件和布局
    public MainWindow() { ... }

    // 创建菜单按钮的辅助方法
    private JButton createMenuButton(String text, ActionListener listener) { ... }

    // 更新时间标签的显示
    private void updateTimeLabel() { ... }

    // 显示健康度窗口
    private void showHealthWindow() { ... }

    // 显示饱和度窗口
    private void showSatietyWindow() { ... }

    // 显示喂食窗口
    private void showFeedingWindow() { ... }

    // 显示宠物信息窗口
    private void showInfoWindow() { ... }

    // 显示游戏窗口
    private void showGameWindow() { ... }

    // 显示工具窗口
    private void showToolsWindow() { ... }

    // 重写dispose方法，在关闭窗口时停止定时器
    @Override
    public void dispose() { ... }
}
```

### 2. HealthWindow 类
```java
public class HealthWindow extends JDialog {
    // 界面组件
    private JLabel healthLabel;     // 健康度数值标签
    private JLabel statusLabel;     // 状态描述标签
    private JProgressBar healthBar; // 健康度进度条
    private JButton backButton;     // 返回按钮

    // 构造函数，初始化健康度窗口界面
    public HealthWindow(JFrame parent, int healthValue) { ... }

    // 根据健康度值获取对应的状态描述
    private String getHealthStatus(int healthValue) { ... }
}
```

### 3. SatietyWindow 类
```java
public class SatietyWindow extends JDialog {
    // 界面组件
    private JLabel satietyLabel;    // 饱和度数值标签
    private JLabel statusLabel;     // 状态描述标签
    private JProgressBar satietyBar; // 饱和度进度条
    private JButton backButton;     // 返回按钮

    // 构造函数，初始化饱和度窗口界面
    public SatietyWindow(JFrame parent, int satietyValue) { ... }
}
```

### 4. GameWindow 类
```java
public class GameWindow extends JDialog {
    // 界面组件
    private JTextField guessField;  // 用户输入猜测数字的文本框
    private JLabel resultLabel;     // 显示猜测结果的标签
    private JLabel attemptsLabel;   // 显示剩余尝试次数的标签
    private JLabel coinsLabel;      // 显示当前金币数量的标签
    private JButton guessButton;    // 猜测按钮

    // 游戏相关变量
    private int randomNumber;       // 随机生成的数字
    private int attempts;           // 已尝试次数
    private int maxAttempts;        // 最大尝试次数
    private int currentCoins;       // 当前金币数量
    private boolean gameOver;       // 游戏是否结束

    // 构造函数，初始化游戏窗口界面
    public GameWindow(JFrame parent, int coins) { ... }

    // 处理用户的猜测
    private void makeGuess() { ... }
}
```

### 5. PersonalInfoManager 类
```java
public class PersonalInfoManager {
    // 向文件中写入用户个人信息
    public static void writePersonalInfo(String filePath, String name, String gender, int age, boolean isAdmin) { ... }

    // 从文件中读取用户个人信息
    public static String[] readPersonalInfo(String filePath) { ... }

    // 向文件中写入宠物状态信息
    public static void writeInfo(String filePath, int baoshi, int health, int goldCoin) { ... }

    // 从文件中读取宠物状态信息
    public static int[] readInfo(String filePath) { ... }
}
```

### 6. LoginWindow 类
```java
public class LoginWindow extends JFrame {
    // 界面组件
    private JTextField nameField;   // 姓名输入框
    private JTextField sexField;    // 性别输入框
    private JTextField ageField;    // 年龄输入框
    private JButton registerButton; // 注册按钮
    private JLabel statusLabel;     // 显示注册状态的标签

    // 构造函数，初始化登录窗口的所有组件和布局
    public LoginWindow() { ... }

    // 注册用户
    private void registerUser() { ... }
}
```

## 四、依赖管理
项目使用 Maven 进行依赖管理，主要依赖如下：
```xml
<dependencies>
    <!-- Apache HttpClient依赖 -->
    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpclient</artifactId>
        <version>4.5.13</version>
    </dependency>
    <dependency>
        <groupId>com.googlecode.json-simple</groupId>
        <artifactId>json-simple</artifactId>
        <version>1.1.1</version>
    </dependency>
    <dependency>
        <groupId>org.json</groupId>
        <artifactId>json</artifactId>
        <version>20210307</version>
    </dependency>
</dependencies>
```

## 五、项目配置
项目的 Maven 配置如下：
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <source>21</source>
                <target>21</target>
                <compilerArgs>--enable-preview</compilerArgs>
            </configuration>
        </plugin>
    </plugins>
</build>
<properties>
    <maven.compiler.source>8</maven.compiler.source>
    <maven.compiler.target>8</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

## 六、使用说明
1. 运行 `app` 类的 `main` 方法启动程序。
2. 如果用户未注册，会显示登录窗口，用户输入姓名、性别和年龄进行注册。
3. 注册成功后，显示主窗口，用户可以通过主窗口的按钮打开各个功能窗口。
4. 在游戏窗口中，用户可以进行猜数字游戏，猜对可获得金币奖励。
5. 在工具窗口中，用户可以打开天气查询窗口查询天气信息。
