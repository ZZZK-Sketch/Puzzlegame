package com.test.ui;

import java.util.Random;

public class Test1 {
   public static void main(String[] args) {
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
       //创建一个二维数组
       int[][]data=new int [4][4];
       //给二维数组添加数据
       //一：遍历一维数组tempArr得到每一个元素 把每一个元素添加到二维数组中
       //二：遍历二维数组 给每一个变量赋值
       for(int i=0;i< tempArr.length;i++)
       {
           data[i/4][i%4]=tempArr[i];
       }
       //遍历二维数组
       for (int i = 0; i < data.length; i++) {
           for (int j = 0; j < data[i].length; j++) {
               System.out.print(data[i][j]+" ");
           }
           System.out.println();

       }

    }
}
