package minesweeper;

import components.GridComponent;
import controller.GameController;
import entity.Player;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.MissingFormatArgumentException;

public class Choose implements Serializable {

    JFrame choose = new JFrame("是否开始新游戏");

    public void chooseee(Mode modeeeeee) {
        int chooseWidth = 600;
        int chooseHeight = 85;
        choose.setResizable(false);
        choose.setLayout(new FlowLayout(FlowLayout.CENTER));
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screesize = defaultToolkit.getScreenSize();
        choose.setLayout(new FlowLayout(FlowLayout.CENTER));
        choose.setBounds((screesize.width - chooseWidth) / 2, (screesize.height - chooseHeight) / 2, chooseWidth, chooseHeight);
        Toolkit tool = choose.getToolkit(); //得到一个Toolkit对象
        Image myimage = tool.getImage("src\\components\\pic\\framePic.png"); //由tool获取图像
        choose.setIconImage(myimage);
        JButton newGame = new JButton("Start a new game!");
        newGame.setSize(80,40);
        JButton lastTime = new JButton("Begin from the history");
        lastTime.setSize(80,40);

        choose.add(newGame);
        choose.add(lastTime);


        choose.setVisible(false);
        choose.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        newGame.addActionListener(e -> {
            modeeeeee.getMode().setVisible(true);
            choose.dispose();
        });


        lastTime.addActionListener(e -> {

            JFileChooser jfc = new JFileChooser(new File("."));
            jfc.showOpenDialog(lastTime);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(jfc.getSelectedFile()));
                String line = "";

                //Unchanged
                line = reader.readLine();
                String[] read = line.split(",");
                int xCount = Integer.parseInt(read[0]);
                int yCount = Integer.parseInt(read[1]);
                int mineCount = Integer.parseInt(read[2]);
                int Times_Per_Round = Integer.parseInt(read[3]);
                MainFrame mainFrame = new MainFrame(xCount,yCount,mineCount,Times_Per_Round);

                //Controller
                line = reader.readLine();
                read = line.split(",");
                int onTurn = Integer.parseInt(read[0]);
                int counter = Integer.parseInt(read[1]);
                MainFrame.getController().setCounter(counter);
                MainFrame.getController().setOnTurn(onTurn);

                //player1
                line = reader.readLine();
                read = line.split(",");
                int score1 = Integer.parseInt(read[0]);
                int mistake1 = Integer.parseInt(read[1]);
                MainFrame.getP1().setScore(score1);
                MainFrame.getP1().setMistake(mistake1);

                //player2
                line = reader.readLine();
                read = line.split(",");
                int score2 = Integer.parseInt(read[0]);
                int mistake2 = Integer.parseInt(read[1]);
                MainFrame.getP2().setScore(score2);
                MainFrame.getP2().setMistake(mistake2);

                //Gamepanel
                line = reader.readLine();
                read = line.split(",");
                int leftMineCount = Integer.parseInt(read[0]);
                MainFrame.getGamePanel().setLeftMineCount(leftMineCount);
                MainFrame.getWhoseturnn().updateeeeeeeee(String.format("Mines~Not~found:%d",MainFrame.getGamePanel().getLeftMineCount()));
                MainFrame.getWhoseturnn().updateee(String.format("%s!!Your!!Turn!!", MainFrame.controller.getOnTurnPlayer().getUserName()));

                //Every single grid
                int content;
                int statusToInt;
                for (int i = 0; i < xCount; i++) {
                    for (int j = 0; j < yCount; j++) {
                        line = reader.readLine();
                        read = line.split(",");
                        statusToInt = Integer.parseInt(read[0]);
                        content = Integer.parseInt(read[1]);
                        MainFrame.getGamePanel().getMineField()[i][j].setContent(content);
                        MainFrame.getGamePanel().getMineField()[i][j].setStatus(statusToInt);
                    }
                }

                MainFrame.getController().getScoreBoard1().update("Player1");
                MainFrame.getController().getScoreBoard2().update("Player2");
                mainFrame.setVisible(true);
            } catch (IOException | FontFormatException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

            choose.dispose();

        });
    }

    public JFrame getChoose(){
        return choose;
    }

}
