package minesweeper;


import components.GridComponent;
import controller.GameController;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

public class MainFrame extends JFrame implements Serializable {
    public static GameController controller;
    private int xCount;
    private int yCount;
    private int mineCount;
    private static GamePanel gamePanel;
    private static int perRounddddd;
    private static WHOSETURN whoseturnn = new WHOSETURN();
    private EndFrame endFrame;
    private static Player p1;
    private static Player p2;
    private static PlayMusic playMusic;

    public MainFrame(int xCount,int yCount,int mineCount,int perRounddddd) throws IOException, FontFormatException {
        //todo: change the count of xCount, yCount and mineCount by passing parameters from constructor
        Font font = Font.createFont(Font.TRUETYPE_FONT,new FileInputStream("src/components/Font/FrozenNeutra.otf"));
        font = font.deriveFont(Font.PLAIN,25);
        setxCount(xCount);
        setyCount(yCount);
        setMineCount(mineCount);
        this.perRounddddd = perRounddddd;

        this.setTitle("Jathin Yeung's Mine Sweeper");
        this.setLayout(null);
        this.setSize(36 * GridComponent.gridSize, 32 * GridComponent.gridSize);
        this.setLocationRelativeTo(null);
        Toolkit tool=this.getToolkit(); //得到一个Toolkit对象
        Image myimage=tool.getImage("src\\components\\pic\\framePic.png"); //由tool获取图像
        this.setIconImage(myimage);
        p1 = new Player("Player1");
        p2 = new Player("Player2");

        endFrame = new EndFrame();
        endFrame.end(this);

        ImageIcon picture = new ImageIcon("src\\components\\pic\\background.jpg");
        picture.setImage(picture.getImage().getScaledInstance(this.getWidth(),this.getHeight(), Image.SCALE_DEFAULT));
        JLabel back = new JLabel(picture);

        back.setSize(this.getSize());
        back.setVisible(true);

        controller = new GameController(p1, p2,this.perRounddddd,whoseturnn,endFrame);
        gamePanel = new GamePanel(xCount, yCount, mineCount,this);
        controller.setGamePanel(gamePanel);
        ScoreBoard scoreBoard1 = new ScoreBoard(p1, xCount, yCount,"PlayerOne:");
        scoreBoard1.setLocation(0,0);
        ScoreBoard scoreBoard2 = new ScoreBoard(p2, xCount, yCount,"PlayerTwo:");
        scoreBoard2.setLocation(36 * GridComponent.gridSize-200,0);
        controller.setScoreBoard(scoreBoard1,scoreBoard2);
        scoreBoard1.setVisible(true);
        scoreBoard2.setVisible(true);

        ToolBox toolBoxxxx = new ToolBox();
        toolBoxxxx.tool(this);
        JPanel toolBox = toolBoxxxx.getToolBox();

        whoseturnn.whoseturnnnnnnnnnnnn(this);
        JPanel whoseturn = whoseturnn.getWhoseturn();

        //专属签名水印部分
        JPanel name = new JPanel();
        name.setBounds(0,32 * GridComponent.gridSize-90,450,40);
        JLabel MyName = new JLabel();
        MyName.setForeground(Color.black);
        MyName.setFont(font);
        MyName.setText("Original~Designed~By~Jathin-Yeung");
        MyName.setVisible(true);
        name.setBackground(Color.YELLOW);
        name.add(MyName);

        //音乐部分
        playMusic = new PlayMusic();
        MusicBox musicBox = new MusicBox();
        musicBox.musiccc();
        JPanel musicboxxx = musicBox.getMusicboxxx();
        playMusic.play();

        this.add(musicboxxx);
        this.add(name);
        this.add(whoseturn);
        this.add(toolBox);
        this.add(gamePanel);
        this.add(scoreBoard1);
        this.add(scoreBoard2);
        this.add(back);


        JButton clickBtn = new JButton("Click");
        clickBtn.setSize(80, 20);
        clickBtn.setLocation(5, scoreBoard1.getHeight());
        add(clickBtn);
        clickBtn.addActionListener(e -> {
            String fileName = JOptionPane.showInputDialog(this, "input here");
            System.out.println("fileName :"+fileName);

//            controller.readFileData(fileName);
//            controller.writeDataToFile(fileName);
        });

        this.setVisible(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void setxCount(int xCount){
        this.xCount = xCount;
    }

    public void setyCount(int yCount){
        this.yCount = yCount;
    }

    public void setMineCount(int mineCount){
        this.mineCount = mineCount;
    }

    public int getMineCount(){return this.mineCount;}

    public static GameController getController() {
        return controller;
    }

    public static GamePanel getGamePanel(){
        return gamePanel;
    }

    public MainFrame(){}

    public static int getPerRounddddd(){return perRounddddd;}

    public static Player getP1(){
        return p1;
    }

    public static Player getP2(){
        return p2;
    }

    public static WHOSETURN getWhoseturnn(){
        return whoseturnn;
    }

    public static PlayMusic getPlayMusic(){return playMusic;}
}
