package com.zxy.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener,ActionListener{


    //创建一个二维数组:管理数据 会根据二维数组中的数据进行加载
    int[][]data=new int [4][4];
    int x=0;
    int y=0;//记录空白方块在二维数组中的位置
    String path="D:\\JAVA学习\\Java\\Puzzlegame\\picture\\plane\\";
    //定义一个二维数组 储存正确的数据
    int [][]win={
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}

    };

    //定义变量来统计步数
    int step=0;

    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reloginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("助力我");


    public GameJFrame()
    {
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据
        initData();

        //初始化图片
        initImage();


        this.setVisible(true);
    }

    private void initData() {
        //把已知数组打乱顺序放进二维数组中
        int []tempArr={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        //1.打乱数组
        Random random=new Random();
        for(int i=0;i<tempArr.length;i++)
        {
            //获取随机索引
            int index=random.nextInt(tempArr.length);
            //拿着遍历的每一个数据 与随机索引中的数进行交换
            int temp=tempArr[i];
            tempArr[i]=tempArr[index];
            tempArr[index]=temp;
        }

        //给二维数组添加数据
        //一：遍历一维数组tempArr得到每一个元素 把每一个元素添加到二维数组中
        //二：遍历二维数组 给每一个变量赋值
        for(int i=0;i< tempArr.length;i++)
        {
            if(tempArr[i]==0)
            {
                x=i/4;
                y=i%4;
            }
                data[i/4][i%4]=tempArr[i];

        }
    }

    //初始化一个图片
    private void initImage() {
        //清空原本出现的所有图片
        this.getContentPane().removeAll();

        if(victory())
        {
            //显示胜利图标
            JLabel winJLabel=new JLabel(new ImageIcon("D:\\JAVA学习\\Java\\Puzzlegame\\picture\\win\\33648fe9fda3eb80761c55ca7db8958e.jpg"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount=new JLabel("步数"+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);
        //外循环：把内部循环重复4次
        for(int i=0;i<4;i++)
        {
            //内循环：在一行添加4张图片
            for (int j= 0; j < 4; j++)
            {
                int num=data[i][j];
                //创建一个图片InmageIcon对象
                ImageIcon icon = new ImageIcon(path+ num +".png");
                //创建一个JLable的对象
                JLabel jLabel = new JLabel(icon);
                //指定图片位置
                jLabel.setBounds(105*j+83,105*i+134,105,105);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }

        }
        //添加图片
        JLabel background=new JLabel(new ImageIcon("Puzzlegame\\picture\\back\\屏幕截图 2026-10-08 173950.jpg"));
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();


    }

    private void initJFrame() {
        JMenuBar jMenuBar = new JMenuBar();
        //设置长宽高
        this.setSize(603, 680);
        //设置界面标题
        this.setTitle("拼图单机v1.0");
        //设置界面指定
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        //取消默认的居中位置，才能按指定位置摆放图片
        this.setLayout(null);
        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //按下不松时会调用
    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==65)
        {
            //把界面所有图片全部删除
            this.getContentPane().removeAll();
            //加载一张完整图片
            JLabel all=new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            //添加背景图片
            JLabel background=new JLabel(new ImageIcon("Puzzlegame\\picture\\back\\屏幕截图 2026-10-08 173950.jpg"));
            background.setBounds(40,40,508,560);
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        //判断游戏是否胜利 若胜利 不能继续游戏
        if(victory())
        {
            //1.返回结果2.结束方法
            return;
        }
        int code = e.getKeyCode();
        if (code == 37) {
            System.out.println("向左移动");
            if (y == 3) {
                return;
            }
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            step++;
            initImage();

        } else if (code == 38) {
            System.out.println("向上移动");
            if (x == 3) {
                return;
            }
            //把空白格子向上移动 x，y表示空白方格下方的数字
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            step++;
            initImage();
        } else if (code == 39) {
            System.out.println("向右移动");
            if (y == 0) {
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;
            initImage();
        } else if (code == 40) {
            System.out.println("向下移动");
            if (x == 0) {
                return;
            }
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;
            initImage();
            //调用方法按照最新的数组加载图片

        } else if (code == 65) {
            initImage();
        } else if (code == 87) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0},
            };
            initImage();
        }
    }

    private void initJMenuBar() {
        //初始化菜单 创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上面两个选项：功能 关于我们
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        //创建选项下面的条目对象

        //将每个条目添加到对应的两个选项中
        functionJMenu.add(replayItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);

        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);



        //将菜单的两个选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);
        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    public boolean victory()
    {
        for (int i = 0; i < data.length; i++) {
            //i:表示二维数组data里面的索引
            //data[i]:依次表示每一个一维数组
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j])
                {
                    return false;
                }
            }

        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object obj=e.getSource();
        //判断
        if(obj==replayItem)
        {
            System.out.println("重新游戏");
            //计步器清零
            step=0;
            //再次打乱数据
            initData();
            //重新加载图片
            initImage();

        }
        else if(obj==reloginItem)
        {
            System.out.println("重新登录");
            //关闭当前游戏界面
            this.dispose();
            //打开登录界面
            new LoginJFrame();
        }
        else if(obj==closeItem)
        {
            System.out.println("关闭游戏");
            //关闭虚拟机即可
            System.exit(0);
        }
        else if(obj==accountItem)
        {
            System.out.println("助力我");
            JDialog jDialog=new JDialog();
            JLabel jLabel=new JLabel(new ImageIcon("D:\\JAVA学习\\Java\\Puzzlegame\\picture\\win\\about.jpg"));
            jLabel.setBounds(0,0,258,258);
            //把图片添加到弹框中
            jDialog.getContentPane().add(jLabel);
            //弹框设置大小
            jDialog.setSize(344,344);
            //弹框置顶
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭 无法操作下面界面
            jDialog.setModal(true);
            jDialog.setVisible(true);

        }

    }

}
