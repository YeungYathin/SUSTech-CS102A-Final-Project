package minesweeper;

import components.GridComponent;
import controller.GameController;
import entity.Player;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class EndFrame implements Serializable {
    JFrame endframe = new JFrame("游戏结束！");
    JLabel a = new JLabel("Player1!!");
    JLabel b = new JLabel("You!!");
    JLabel c = new JLabel("Have!!");
    JLabel d = new JLabel("Won!!");
    MainFrame mainFrame;

    public JFrame getEndframe(){
        return endframe;
    }

    public void end(MainFrame mainFrame) throws IOException, FontFormatException {
        this.mainFrame = mainFrame;
        int endWidth = 400;
        int endHeight = 137;
        endframe.setResizable(false);
        endframe.setLayout(new FlowLayout(FlowLayout.CENTER));
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screesize = defaultToolkit.getScreenSize();
        endframe.setLayout(new FlowLayout(FlowLayout.CENTER));
        endframe.setBounds((screesize.width - endWidth) / 2, (screesize.height - endHeight) / 2, endWidth, endHeight);
        Toolkit tool = endframe.getToolkit(); //得到一个Toolkit对象
        Image myimage = tool.getImage("src\\components\\pic\\framePic.png"); //由tool获取图像
        endframe.setIconImage(myimage);
        JButton ok = new JButton("OK");

        Font font = Font.createFont(Font.TRUETYPE_FONT,new FileInputStream("src/components/Font/FrozenNeutra.otf"));
        font = font.deriveFont(Font.PLAIN,30);

        a.setFont(font);
        a.setForeground(Color.RED);
        a.setBackground(Color.cyan);

        b.setFont(font);
        b.setForeground(Color.RED);
        b.setBackground(Color.cyan);

        c.setFont(font);
        c.setForeground(Color.RED);
        c.setBackground(Color.cyan);

        d.setFont(font);
        d.setForeground(Color.RED);
        d.setBackground(Color.cyan);


        endframe.add(a);
        endframe.add(b);
        endframe.add(c);
        endframe.add(d);
        endframe.add(ok);
        endframe.setVisible(false);
        endframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        ok.addActionListener(e -> {
            endframe.setVisible(false);
        });
    }

    public void updateee(String str){
        a.setText(str);
    }
}
