package com.test.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyJFrame extends JFrame implements ActionListener {
    //创建两个按钮对象
    JButton jbt1=new JButton("点我啊");
    JButton jbt2=new JButton("再点我啊");

    public MyJFrame() {
        this.setSize(603,600);
        this.setTitle("高文博大笨蛋");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setLayout(null);

        //给按钮设置位置和高
        jbt1.setBounds(0,0,100,50);
        jbt1.addActionListener(this);
        jbt2.setBounds(100,50,100,50);
        jbt2.addActionListener(this);

        //将按钮添加到整个界面中
        this.getContentPane().add(jbt1);
        this.getContentPane().add(jbt2);
        //让整个界面显示
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //对当前按钮进行判断
        //获取当前被操作的按钮对象
        Object source=e.getSource();
        if(source==jbt1){
            jbt1.setSize(200,200);
        }else if(source==jbt2){
            Random random=new Random();
            jbt2.setLocation(random.nextInt(500),random.nextInt(500));
        }


    }
}
