package minesweeper;

import components.GridComponent;
import controller.GameController;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class WHOSETURN implements Serializable {
    JPanel whoseturn = new JPanel();
    JFrame mainFrame = new JFrame();
    JLabel who = new JLabel();
    JLabel leftMineCount = new JLabel();
    Box vBox = Box.createVerticalBox();

    public JPanel getWhoseturn(){
        return whoseturn;
    }

    public void whoseturnnnnnnnnnnnn(JFrame mainFrame) throws IOException, FontFormatException {

        //字体设置
        Font font = Font.createFont(Font.TRUETYPE_FONT,new FileInputStream("src/components/Font/FrozenNeutra.otf"));
        font = font.deriveFont(Font.PLAIN,30);

        vBox.setSize(300,90);

        who.setFont(font);
        who.setForeground(Color.MAGENTA);
        who.setText("Player1!!Your!!Turn!!");

        leftMineCount.setFont(font);
        leftMineCount.setForeground(Color.MAGENTA);
        leftMineCount.setText(String.format("Mines~Not~found:%d",MainFrame.getGamePanel().getLeftMineCount()));

        this.mainFrame = mainFrame;
        whoseturn.setBackground(Color.BLACK);
        whoseturn.setSize(300,90);
        whoseturn.setLocation(18 * GridComponent.gridSize-150,48);
        whoseturn.setLayout(new FlowLayout(FlowLayout.CENTER));

        vBox.add(who);
        vBox.add(leftMineCount);
        whoseturn.add(vBox);
        whoseturn.setVisible(true);
        who.setVisible(true);
    }

    public void updateee(String str){
        who.setText(str);
    }

    public void updateeeeeeeee(String str){
        leftMineCount.setText(str);
    }
}
