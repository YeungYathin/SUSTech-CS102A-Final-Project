package minesweeper;

import components.GridComponent;
import controller.GameController;
import entity.GridStatus;
import entity.Player;
import javafx.beans.property.ObjectProperty;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MusicBox {
    JPanel musicboxxx = new JPanel();

    public void musiccc() throws IOException, FontFormatException {
        Font font1 = Font.createFont(Font.TRUETYPE_FONT,new FileInputStream("src/components/Font/FrozenNeutra.otf"));
        font1 = font1.deriveFont(Font.PLAIN,20);
        musicboxxx.setBackground(Color.MAGENTA);
        musicboxxx.setLayout(new FlowLayout(FlowLayout.CENTER));
        musicboxxx.setLocation(0,300);
        musicboxxx.setSize(68,210);
        JLabel label = new JLabel("Music");
        label.setFont(font1);
        label.setForeground(Color.black);
        JButton play = new JButton("Play");
        JButton stop = new JButton("Stop");
        JButton loop = new JButton("Loop");

        Font font2 = Font.createFont(Font.TRUETYPE_FONT,new FileInputStream("src/components/Font/FrozenNeutra.otf"));
        font2 = font2.deriveFont(Font.PLAIN,15);
        JLabel yellow = new JLabel("\"Yellow\"");
        yellow.setFont(font1);
        yellow.setForeground(Color.black);
        JLabel by = new JLabel("By");
        by.setFont(font2);
        by.setForeground(Color.black);
        JLabel coldplay = new JLabel("Coldplay");
        coldplay.setFont(font2);
        coldplay.setForeground(Color.black);


        musicboxxx.add(label);
        musicboxxx.add(play);
        musicboxxx.add(stop);
        musicboxxx.add(loop);
        musicboxxx.add(yellow);
        musicboxxx.add(by);
        musicboxxx.add(coldplay);

        musicboxxx.setVisible(true);

        play.addActionListener(e -> {
            MainFrame.getPlayMusic().play();
        });

        stop.addActionListener(e -> {
            MainFrame.getPlayMusic().stop();
        });

        loop.addActionListener(e -> {
            MainFrame.getPlayMusic().loop();
        });

    }

    public JPanel getMusicboxxx(){return this.musicboxxx;}
}
