package com.cmaotai.service.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CmaotaiStart {

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JLabel label0 = new JLabel("茅台云商操作系统", SwingConstants.CENTER);
        JLabel label1 = new JLabel("云商账户的登录密码:");
        JLabel label2 = new JLabel("下单瓶数:");
        final JLabel lable3 = new JLabel("操作日志", SwingConstants.CENTER);

        JPasswordField jpf = new JPasswordField();
        JTextField field = new JTextField("6");
        JButton button1 = new JButton("立刻下单");
        JButton button2 = new JButton("查询订单");
        Font font1 = new Font("SansSerif", Font.BOLD, 30);
        Font font2 = new Font("SansSerif", Font.BOLD, 14);
        Font font3 = new Font("SansSerif", Font.BOLD, 14);
        frame.add(panel1);
        panel1.setSize(500, 120);
        panel1.setBackground(Color.gray);
        panel1.setLayout(new BorderLayout());
        panel1.add(label0, BorderLayout.CENTER);
        label0.setFont(font1);
        label0.setForeground(Color.BLACK);
        frame.add(panel2);
        panel2.setSize(300, 200);
        panel2.setLocation(60, 150);
        panel2.setLayout(new GridLayout(5, 2, 30, 20));
        panel2.add(label1);
        panel2.add(jpf);
        panel2.add(label2);
        panel2.add(field);
        panel2.add(button1);
        panel2.add(button2);
        label1.setFont(font2);
        label1.setForeground(Color.BLACK);
        label2.setFont(font2);
        label2.setForeground(Color.BLACK);
        button1.setFont(font3);
        button1.setForeground(Color.BLACK);
        button2.setFont(font3);
        button2.setForeground(Color.BLACK);

        //TODO 操作日志输出
        panel3.setSize(500, 120);
        panel3.setBackground(Color.WHITE);
        panel3.setLayout(new BorderLayout());
        panel3.add(lable3);
        lable3.setFont(font1);
        lable3.setForeground(Color.BLACK);
        frame.add(lable3);

        frame.setSize(500, 600);
        frame.setLocation(400, 300);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button1.addActionListener(event -> {
//            CMaotaiServiceImpl.signUp("");
        });
        button2.addActionListener(event -> {
        });
        SwingUtilities.invokeLater(()->{
            lable3.setText("xxxx");
        });
//        StartService.start();
    }
}
