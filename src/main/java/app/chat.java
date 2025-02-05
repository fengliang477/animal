package app;

import javax.swing.*;

public class chat extends JFrame {
    static int i = 0;

    public static void main(String[] args) {

        chat m = new chat();
        m.setSize(400, 300);
        m.setLocationRelativeTo(null);
        JPanel chat = new JPanel();
        JScrollPane JScrollPane  = new JScrollPane();
        JTextArea JTextArea = new JTextArea(13, 40);
        JScrollPane.setViewportView(JTextArea);
        chat.add(JScrollPane);


        JPanel input = new JPanel();
        JLabel JLabel = new JLabel("请输入");
        JTextField JTextField = new JTextField(20);
        JButton JButton = new JButton("发送");
        JButton jButton = new JButton("新建会话");
        input.add(JLabel);
        input.add(JTextField);
        input.add(JButton);
        input.add(jButton);

        // 点击新建会话按钮，新建会话
        jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                i++;
                JTextArea.append("新建会话"+i+"\n");
            }
        });
        JButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String str = JTextField.getText();
                String model = "moonshot-v1-8k"; // 模型名称
                int maxTokens = 1024; // 最大返回Token数
                JTextArea.append("用户提问："+str+"\n");
                JTextArea.append("AI回复："+kimipost.chat(str,"chatid"+i,model,maxTokens)+ "\n");
                JTextField.setText("");
            }
        });

        m.add(chat);
        m.add(input, "South");
        m.setDefaultCloseOperation(EXIT_ON_CLOSE);


        m.setVisible(true);
    }

}