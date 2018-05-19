package com.cmaotai.service.main;

import com.cmaotai.service.service.CMaotaiServiceImpl;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import org.apache.logging.log4j.util.Strings;
import org.joda.time.DateTime;

public class GUIMain extends JDialog {

    private JPanel contentPane;
    private JButton invoke;
    private JRadioButton submit;
    private JRadioButton order;
    private JPasswordField pwd;
    private JTextField qyt;
    private JTextArea textArea;
    private JButton clean;

    public GUIMain() {
        setContentPane(contentPane);
        setModal(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(e -> {
            onCancel();
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        invoke.addActionListener(e -> {
            try {
                invoke();
            } catch (IOException ee) {
                System.err.println(ee.getMessage());
            }
        });
        pwd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                String password = new String(pwd.getPassword());
                if (Strings.isBlank(password)) {
                    String msg = "密码不能为空！";
                    JOptionPane.showMessageDialog(null, "密码不能为空！", "错误提示框", JOptionPane.WARNING_MESSAGE);
                    pwd.requestFocus();
                    System.out.println(msg);
                }
            }
        });
        clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });
        textArea.setLineWrap(true);//激活自动换行功能
        textArea.setWrapStyleWord(true);
        outputUI();
    }

    private void invoke() throws IOException {
        String password = new String(pwd.getPassword());
        String start = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        if (submit.isSelected()) {
            CMaotaiServiceImpl.signUp(password);
        }
        if (order.isSelected()) {
            System.out.println(start + "，开始执行查询订单操作........");
            CMaotaiServiceImpl.getOrderStatus(password);
        }
        String end = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        System.out.println(end + "，结束");
    }

    private void onCancel() {
        System.exit(0);
    }

    public static void main(String[] args) {
        GUIMain dialog = new GUIMain();
        int windowWidth = dialog.getWidth(); //获得窗口宽
        int windowHeight = dialog.getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        dialog.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
        dialog.pack();
        dialog.setVisible(true);
        dialog.setSize(600, 500);
    }

    protected void outputUI() {
        PrintStream myOut = new PrintStream(new OutputStream() {
            public void write(int b) throws IOException {
                textArea.append(String.valueOf((char) b));
            }

            public void write(byte b[]) throws IOException {
                textArea.append(new String(b));
            }

            public void write(byte b[], int off, int len) throws IOException {
                textArea.append(new String(b, off, len));
            }
        });
        System.setOut(myOut);
        System.setErr(myOut);
    }

}
