package minesweeper;

import components.GridComponent;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.time.Year;
import javax.imageio.ImageIO;
/**
 * 此类的对象是一个计分板容器，通过传入玩家对象，
 * 可以用update()方法实时更新玩家的分数以及失误数。
 */
public class ScoreBoard extends JPanel {

    Player p;

    JLabel player = new JLabel();
    JLabel score = new JLabel();
    JLabel mistake = new JLabel();
    Box vBox = Box.createVerticalBox();
    Font f = new Font("Times New Roman",Font.BOLD,20);

    /**
     * 通过进行游戏的玩家来初始化计分板。这里只考虑了两个玩家的情况。
     * 如果想要2-4人游戏甚至更多，请自行修改(建议把所有玩家存在ArrayList)~
     *
     * @param p 玩家1
     */
    public ScoreBoard(Player p,int xCount, int yCount, String str) throws IOException, FontFormatException {
        //字体设置
        Font font = Font.createFont(Font.TRUETYPE_FONT,new FileInputStream("src/components/Font/FrozenNeutra.otf"));
        font = font.deriveFont(Font.PLAIN,25);

        this.setSize(200, 120);
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.p = p;

        vBox.add(player);
        vBox.add(score);
        vBox.add(mistake);
        vBox.setSize(200,100);

        player.setFont(font);
        player.setForeground(Color.BLUE);
        player.setOpaque(false);
        score.setFont(font);
        score.setForeground(Color.BLUE);
        score.setOpaque(false);
        mistake.setFont(font);
        mistake.setForeground(Color.BLUE);
        mistake.setOpaque(false);
        this.setBackground(Color.YELLOW);
        this.add(vBox);
        update(str);
    }

    /**
     * 刷新计分板的数据。
     * 计分板会自动重新获取玩家的分数，并更新显示。
     */
    public void update(String str) {
        player.setText(String.format("%s", str));
        score.setText(String.format("score:%d",p.getScore()));
        mistake.setText(String.format("mistake:%d",p.getMistake()));
    }
}
