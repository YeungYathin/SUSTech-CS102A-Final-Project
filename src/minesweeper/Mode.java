package minesweeper;

import components.GridComponent;
import controller.GameController;
import entity.Player;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Mode {

    JFrame mode = new JFrame("模式选择");

    public JFrame getMode() {
        return mode;
    }

    public void modeeeeee() {

        int chooseWidth = 100;
        int chooseHeight = 200;
        mode.setResizable(false);
        mode.setLayout(new FlowLayout(FlowLayout.CENTER));
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screesize = defaultToolkit.getScreenSize();
        mode.setBounds((screesize.width - chooseWidth) / 2, (screesize.height - chooseHeight) / 2, chooseWidth, chooseHeight);
        Toolkit tool = mode.getToolkit(); //得到一个Toolkit对象
        Image myimage = tool.getImage("src\\components\\pic\\framePic.png"); //由tool获取图像
        mode.setIconImage(myimage);
        JButton nine_nine = new JButton("09*09");
        nine_nine.setSize(100, 80);
        JButton sixteen_sixteen = new JButton("16*16");
        sixteen_sixteen.setSize(100, 80);
        JButton sixteen_thirty = new JButton("16*30");
        sixteen_thirty.setSize(100, 80);
        JButton user_defined = new JButton("Design by yourself");
        user_defined.setSize(100, 80);
        JLabel x = new JLabel("x:");
        JLabel y = new JLabel("y:");
        JLabel minecounttttt = new JLabel("Minecount:");
        JTextField rowwww = new JTextField("", 2);
        JTextField columnnnn = new JTextField("", 2);
        JTextField mineeeeee = new JTextField("", 3);
        mode.add(nine_nine);
        mode.add(sixteen_sixteen);
        mode.add(sixteen_thirty);
        mode.add(user_defined);
        mode.add(x);
        mode.add(rowwww);
        mode.add(y);
        mode.add(columnnnn);
        mode.add(minecounttttt);
        mode.add(mineeeeee);


        mode.setVisible(false);
        mode.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        nine_nine.addActionListener(f -> {
            Times_Per_Round times_per_round = new Times_Per_Round();
            times_per_round.Round(9, 9, 10);
            times_per_round.getTimes().setVisible(true);
            mode.dispose();
        });


        sixteen_sixteen.addActionListener(f -> {
            Times_Per_Round times_per_round = new Times_Per_Round();
            times_per_round.Round(16, 16, 40);
            times_per_round.getTimes().setVisible(true);
            mode.dispose();
        });

        sixteen_thirty.addActionListener(f -> {
            Times_Per_Round times_per_round = new Times_Per_Round();
            times_per_round.Round(16, 30, 99);
            times_per_round.getTimes().setVisible(true);
            mode.dispose();
        });

        user_defined.addActionListener(f -> {
            if (rowwww.getText().length() <= 2 &&
                    rowwww.getText().length() > 0 &&
                    columnnnn.getText().length() <= 2 &&
                    columnnnn.getText().length() > 0 &&
                    mineeeeee.getText().length() <= 3 &&
                    mineeeeee.getText().length() > 0 &&
                    Integer.parseInt(rowwww.getText()) > 0 &&
                    Integer.parseInt(rowwww.getText()) <= 24 &&
                    Integer.parseInt(columnnnn.getText()) > 0 &&
                    Integer.parseInt(columnnnn.getText()) <= 30 &&
                    Integer.parseInt(mineeeeee.getText()) >= 0) {
                int gridNum = Integer.parseInt(rowwww.getText()) * Integer.parseInt(columnnnn.getText());
                if (Integer.parseInt(mineeeeee.getText()) <= gridNum / 2) {
                    Times_Per_Round times_per_round = new Times_Per_Round();
                    times_per_round.Round(Integer.parseInt(rowwww.getText()), Integer.parseInt(columnnnn.getText()), Integer.parseInt(mineeeeee.getText()));
                    times_per_round.getTimes().setVisible(true);
                    mode.dispose();
                }
            }
        });
    }

}
