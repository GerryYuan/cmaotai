package com.cmaotai.service.main;

import com.cmaotai.service.service.CMaotaiServiceImpl;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import org.apache.logging.log4j.util.Strings;
import org.joda.time.DateTime;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.NumberUtils;

@SpringBootApplication
public class GUIMain extends JFrame {

    private JPanel contentPane;
    private JButton invoke;
    private JRadioButton submit;
    private JRadioButton order;
    private JPasswordField pwd;
    private JTextField qyt;
    private JTextArea textArea;
    private JButton clean;
    private JTextField timer;
    ExecutorService newCachedThread = Executors.newCachedThreadPool();//创建一个缓冲线程池

    public GUIMain() {
        setContentPane(contentPane);
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
            newCachedThread.execute(() -> {
                try {
                    invoke();
                } catch (IOException ee) {
                    System.err.println(ee.getMessage());
                }
            });
        });
        pwd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                String password = new String(pwd.getPassword());
                if (Strings.isBlank(password)) {
                    show("密码不能为空！");
                    pwd.requestFocus();
                }
            }
        });
        clean.addActionListener(e -> textArea.setText(""));
        textArea.setLineWrap(true);//激活自动换行功能
        textArea.setWrapStyleWord(true);
        outputUI();
    }

    private void show(String msg) {
        JOptionPane.showMessageDialog(null, msg, "错误提示框", JOptionPane.WARNING_MESSAGE);
    }

    private void invoke() throws IOException {
        if (!succ) {
            show("上次操作正在执行");
            return;
        }
        String password = new String(pwd.getPassword());
        String start = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        if (submit.isSelected()) {
            succ = false;
            System.out.println(start + "，开始执行下单操作........");
            CMaotaiServiceImpl.signUp(password, NumberUtils.parseNumber(timer.getText(), Integer.class));
        }
        if (order.isSelected()) {
            succ = false;
            System.out.println(start + "，开始执行查询订单操作........");
            CMaotaiServiceImpl.getOrderStatus(password);
        }
        String end = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        System.out.println(end + "，结束");
        succ = true;
    }

    private boolean succ = true;

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
        dialog.setLocation(screenWidth / 4 - windowWidth / 4, screenHeight / 4 - windowHeight / 4);//设置窗口居中显示
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

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) {
            return null;
        }
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(),
            size >= 0 ? size : currentFont.getSize());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer >>> IMPORTANT!! <<< DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.setEnabled(false);
        contentPane.setBorder(BorderFactory
            .createTitledBorder(null, "茅台云商操作系统", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                this.$$$getFont$$$("Ayuthaya", -1, 16, contentPane.getFont()), new Color(-16777216)));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1,
            new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0,
                false));
        final JLabel label1 = new JLabel();
        label1.setText("下单瓶数（默认6）：");
        panel1.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("执行操作");
        panel1.add(label2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        submit = new JRadioButton();
        submit.setText("下单");
        panel1.add(submit, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED, null, null, null, 9, false));
        final JLabel label3 = new JLabel();
        label3.setText("密码（必填）：");
        panel1.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pwd = new JPasswordField();
        pwd.setText("");
        panel1.add(pwd, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
        qyt = new JTextField();
        qyt.setText("6");
        panel1.add(qyt, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
        order = new JRadioButton();
        order.setText("查询订单");
        panel1.add(order, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED, null, null, null, 5, false));
        final JLabel label4 = new JLabel();
        label4.setText("下单时间间隔（分钟）：");
        panel1.add(label4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        timer = new JTextField();
        timer.setText("2");
        panel1.add(timer, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel2,
            new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_SOUTHEAST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0,
                false));
        invoke = new JButton();
        invoke.setEnabled(true);
        Font invokeFont = this.$$$getFont$$$(null, -1, 16, invoke.getFont());
        if (invokeFont != null) {
            invoke.setFont(invokeFont);
        }
        invoke.setText("开始执行");
        panel2.add(invoke, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clean = new JButton();
        Font cleanFont = this.$$$getFont$$$(null, -1, 16, clean.getFont());
        if (cleanFont != null) {
            clean.setFont(cleanFont);
        }
        clean.setText("清除日志");
        panel2.add(clean,
            new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        Font panel3Font = this.$$$getFont$$$("Ayuthaya", -1, 16, panel3.getFont());
        if (panel3Font != null) {
            panel3.setFont(panel3Font);
        }
        panel3.setForeground(new Color(-63428));
        contentPane.add(panel3,
            new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0,
                false));
        panel3.setBorder(BorderFactory.createTitledBorder("注意事项："));
        final JLabel label5 = new JLabel();
        label5.setForeground(new Color(-63428));
        label5.setText("1、云商系统稍微差点，大家轻点，下单、查单不要太频繁");
        panel3.add(label5, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setForeground(new Color(-1301708));
        label6.setText("2、由于网点接单规则不定，软件会不定时更新，大家关注群");
        panel3.add(label6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setBackground(new Color(-1));
        label7.setEnabled(true);
        label7.setForeground(new Color(-1301708));
        label7.setText("3、最后祝大家赚钱，有什么软件问题可以联系我，或者有什么需求可以提给我");
        panel3.add(label7, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel4,
            new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0,
                false));
        panel4.setBorder(BorderFactory.createTitledBorder("操作记录"));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel4.add(scrollPane1,
            new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0,
                false));
        textArea = new JTextArea();
        textArea.setText("");
        scrollPane1.setViewportView(textArea);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
