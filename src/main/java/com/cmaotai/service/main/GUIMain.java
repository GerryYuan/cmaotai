package com.cmaotai.service.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import lombok.Data;

/**
 * @author gerry
 * @version 1.0, 2018-05-17 11:56
 * @since com.hujiang 1.0.0
 */
@Data
public class GUIMain implements Serializable {

    public static void main(String[] args) {
        JFrame frame = new JFrame("边界布局") ;
        frame.setLayout(new BorderLayout(10,10)) ;
        Font font = new Font("SansSerif", Font.BOLD, 30);

        JPanel nJPanel = new JPanel();
        JPanel cJPanel = new JPanel();
        JPanel sJPanel = new JPanel();

        JButton button1 = new JButton("按钮1");
        JButton button2 = new JButton("按钮2");

        JLabel nJLabel = new JLabel("北xxxxxxxxxxxxxxxx");
        nJLabel.setFont(font);
        nJLabel.setBackground(Color.BLACK);

        nJPanel.setSize(600, 200);
        nJPanel.setBackground(Color.gray);
        nJPanel.add(nJLabel, BorderLayout.CENTER);

        JLabel cJLabel = new JLabel("中");
        cJPanel.setSize(600, 200);
        cJPanel.setBackground(Color.GREEN);
        cJPanel.add(cJLabel, BorderLayout.CENTER);

        JLabel sJLabel = new JLabel("南");
        sJPanel.setSize(600, 200);
        sJPanel.setBackground(Color.WHITE);
        sJPanel.setLayout(new GridLayout(1, 2, 30, 20));
//        sJPanel.add(sJLabel, BorderLayout.CENTER);
        sJPanel.add(button1);
        sJPanel.add(button2);


        frame.add(nJPanel,BorderLayout.NORTH);
        frame.add(cJPanel,BorderLayout.CENTER);
        frame.add(sJPanel,BorderLayout.SOUTH);
//        frame.add(new JButton("东"),BorderLayout.EAST) ;
//        frame.add(new JButton("西"),BorderLayout.WEST) ;
//        frame.add(new JButton("南"),BorderLayout.SOUTH) ;
//        frame.add(new JButton("北"),BorderLayout.NORTH) ;
//        frame.add(new JButton("中"),BorderLayout.CENTER) ;
        frame.setSize(600,600) ;
        frame.setVisible(true) ;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
