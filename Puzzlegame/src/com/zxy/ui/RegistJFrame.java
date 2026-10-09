package com.zxy.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistJFrame extends JFrame implements ActionListener {
    //组件
    JTextField usernameField;     //账号输入框
    JPasswordField passwordField; //密码输入框
    JPasswordField rePwdField;     //确认密码输入框
    JButton registBtn;             //注册按钮
    JButton backLoginBtn;          //返回登录按钮

    //构造方法：创建注册窗口
    public RegistJFrame() {
        initFrame();
        initComponent();
        this.setVisible(true);
    }

    //初始化窗口基础信息
    private void initFrame() {
        this.setTitle("拼图游戏 - 注册");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
    }

    //初始化界面组件
    private void initComponent() {
        JLabel userLabel = new JLabel("账号：");
        userLabel.setBounds(80, 40, 60, 25);
        this.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(130, 40, 160, 25);
        this.add(usernameField);

        JLabel pwdLabel = new JLabel("密码：");
        pwdLabel.setBounds(80, 80, 60, 25);
        this.add(pwdLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 80, 160, 25);
        this.add(passwordField);

        JLabel rePwdLabel = new JLabel("确认密码：");
        rePwdLabel.setBounds(60, 120, 80, 25);
        this.add(rePwdLabel);

        rePwdField = new JPasswordField();
        rePwdField.setBounds(130, 120, 160, 25);
        this.add(rePwdField);

        registBtn = new JButton("注册");
        registBtn.setBounds(100, 180, 80, 30);
        registBtn.addActionListener(this);
        this.add(registBtn);

        backLoginBtn = new JButton("返回登录");
        backLoginBtn.setBounds(200, 180, 100, 30);
        backLoginBtn.addActionListener(this);
        this.add(backLoginBtn);
    }

    //按钮点击事件
    @Override
    public void actionPerformed(ActionEvent e) {
        //点击注册按钮
        if (e.getSource() == registBtn) {
            String username = usernameField.getText();
            String pwd = new String(passwordField.getPassword());
            String rePwd = new String(rePwdField.getPassword());

            //校验：输入框不能为空
            if (username.isEmpty() || pwd.isEmpty() || rePwd.isEmpty()) {
                JOptionPane.showMessageDialog(this, "账号、密码不能为空！");
                return;
            }

            //校验：两次输入密码必须一致
            if (!pwd.equals(rePwd)) {
                JOptionPane.showMessageDialog(this, "两次输入密码不一致！");
                return;
            }

            //校验：账号不能重复注册，读取LoginJFrame静态集合
            if (LoginJFrame.userMap.containsKey(username)) {
                JOptionPane.showMessageDialog(this, "该账号已经被注册！");
                return;
            }

            //全部校验通过，存入账号密码
            LoginJFrame.userMap.put(username, pwd);
            JOptionPane.showMessageDialog(this, "注册成功！请登录");
            this.dispose();
            new LoginJFrame(); //注册成功跳回登录页面
        }
        //点击返回登录按钮
        else if (e.getSource() == backLoginBtn) {
            this.dispose();
            new LoginJFrame();
        }
    }
}