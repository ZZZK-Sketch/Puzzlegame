package com.zxy.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginJFrame extends JFrame implements ActionListener {
    //静态集合：全局存储所有注册的账号密码，静态可以被RegistJFrame访问
    public static HashMap<String, String> userMap = new HashMap<>();

    //界面组件
    JTextField usernameField;     //账号输入框
    JPasswordField passwordField; //密码输入框
    JButton loginBtn;              //登录按钮
    JButton toRegisterBtn;         //跳转到注册页面按钮

    //构造方法：创建登录窗口
    public LoginJFrame() {
        initFrame();       //初始化窗口基础属性
        initComponent();   //初始化所有组件（标签、输入框、按钮）
        this.setVisible(true); //显示窗口
    }

    //初始化窗口大小、标题、关闭方式等基础设置
    private void initFrame() {
        this.setTitle("拼图游戏 - 登录");
        this.setSize(400, 250); //窗口宽400，高250
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口就退出程序
        this.setLocationRelativeTo(null); //窗口居中显示
        this.setLayout(null); //取消默认布局，手动setBounds摆放组件（和你的拼图项目一致）
    }

    //创建并添加所有组件到窗口
    private void initComponent() {
        //账号标签
        JLabel userLabel = new JLabel("账号：");
        userLabel.setBounds(80, 40, 60, 25);
        this.add(userLabel);

        //账号输入框
        usernameField = new JTextField();
        usernameField.setBounds(130, 40, 160, 25);
        this.add(usernameField);

        //密码标签
        JLabel pwdLabel = new JLabel("密码：");
        pwdLabel.setBounds(80, 80, 60, 25);
        this.add(pwdLabel);

        //密码输入框（输入内容隐藏）
        passwordField = new JPasswordField();
        passwordField.setBounds(130, 80, 160, 25);
        this.add(passwordField);

        //登录按钮
        loginBtn = new JButton("登录");
        loginBtn.setBounds(100, 130, 80, 30);
        loginBtn.addActionListener(this); //绑定点击监听
        this.add(loginBtn);

        //跳转注册按钮
        toRegisterBtn = new JButton("注册账号");
        toRegisterBtn.setBounds(200, 130, 100, 30);
        toRegisterBtn.addActionListener(this); //绑定点击监听
        this.add(toRegisterBtn);
    }

    //按钮点击事件
    @Override
    public void actionPerformed(ActionEvent e) {
        //点击【登录】按钮
        if (e.getSource() == loginBtn) {
            //获取输入的账号和密码
            String username = usernameField.getText();
            String pwd = new String(passwordField.getPassword());

            //判断账号密码不能为空
            if (username.isEmpty() || pwd.isEmpty()) {
                JOptionPane.showMessageDialog(this, "账号和密码不能为空！");
                return; //结束方法，不再往下执行
            }

            //判断集合中是否存在这个账号
            if (userMap.containsKey(username)) {
                //账号存在，对比密码
                if (userMap.get(username).equals(pwd)) {
                    JOptionPane.showMessageDialog(this, "登录成功！");
                    this.dispose(); //关闭当前登录窗口
                    new GameJFrame(); //打开拼图游戏窗口
                } else {
                    JOptionPane.showMessageDialog(this, "密码错误！");
                }
            } else {
                JOptionPane.showMessageDialog(this, "账号不存在，请先注册！");
            }
        }
        //点击【注册账号】按钮，跳转到注册页面
        else if (e.getSource() == toRegisterBtn) {
            this.dispose(); //关闭登录窗口
            new RegistJFrame(); //打开注册窗口
        }
    }
}
