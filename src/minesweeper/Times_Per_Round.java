package minesweeper;

import components.GridComponent;
import controller.GameController;
import entity.Player;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

public class Times_Per_Round implements Serializable {
    JFrame times = new JFrame("Times Per Round");

    private int xCount;
    private int yCount;
    private int mineCount;

    public JFrame getTimes(){
        return times;
    }

public void Round(int xCount, int yCount,int mineCount){

        this.xCount = xCount;
        this.yCount = yCount;
        this.mineCount = mineCount;

        int chooseWidth = 600;
        int chooseHeight = 90;
        times.setResizable(false);
        times.setLayout(new FlowLayout(FlowLayout.CENTER));
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screesize = defaultToolkit.getScreenSize();
        times.setBounds((screesize.width - chooseWidth) / 2, (screesize.height - chooseHeight) / 2, chooseWidth, chooseHeight);
        Toolkit tool = times.getToolkit(); //得到一个Toolkit对象
        Image myimage = tool.getImage("src\\components\\pic\\framePic.png"); //由tool获取图像
        times.setIconImage(myimage);
        JLabel tip = new JLabel("Please choose the number of clicks per round per player --");
        JButton per1 = new JButton("1");
        JButton per2 = new JButton("2");
        JButton per3 = new JButton("3");
        JButton per4 = new JButton("4");
        JButton per5 = new JButton("5");

        times.add(tip);
        times.add(per1);
        times.add(per2);
        times.add(per3);
        times.add(per4);
        times.add(per5);

        times.setVisible(false);
        times.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        per1.addActionListener(f -> {
            //游戏开始
            MainFrame mainFrame = null;
            try {
                mainFrame = new MainFrame(this.xCount,this.yCount,this.mineCount,1);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }

            mainFrame.setVisible(true);
            times.dispose();
        });

        per2.addActionListener(f -> {
            //游戏开始
            MainFrame mainFrame = null;
            try {
                mainFrame = new MainFrame(this.xCount,this.yCount,this.mineCount,2);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }

            mainFrame.setVisible(true);
            times.dispose();
        });

        per3.addActionListener(f -> {
            //游戏开始
            MainFrame mainFrame = null;
            try {
                mainFrame = new MainFrame(this.xCount,this.yCount,this.mineCount,3);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }

            mainFrame.setVisible(true);
            times.dispose();
        });

        per4.addActionListener(f -> {
            //游戏开始
            MainFrame mainFrame = null;
            try {
                mainFrame = new MainFrame(this.xCount,this.yCount,this.mineCount,4);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }

            mainFrame.setVisible(true);
            times.dispose();
        });

        per5.addActionListener(f -> {
            //游戏开始
            MainFrame mainFrame = null;
            try {
                mainFrame = new MainFrame(this.xCount,this.yCount,this.mineCount,5);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }

            mainFrame.setVisible(true);
            times.dispose();
        });

    }
}
