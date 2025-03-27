package controller;

import minesweeper.EndFrame;
import minesweeper.GamePanel;
import entity.Player;
import minesweeper.ScoreBoard;
import minesweeper.WHOSETURN;

import javax.swing.*;
import java.io.Serializable;


public class GameController implements Serializable {

    private Player p1;
    private Player p2;

    private int perRounddddddddddd;
    private int oneRound = 1;
    private int counter = 1;
    private int helperrrrrrr;

    private WHOSETURN whoseturn;
    private Player onTurn;
    private String whoseTurn;
    private EndFrame endFrame;
    private GamePanel gamePanel;
    private ScoreBoard scoreBoard1;
    private ScoreBoard scoreBoard2;

    public GameController(Player p1, Player p2, int perRounddddddddddd, WHOSETURN whoseturn, EndFrame endFrame) {
        this.endFrame = endFrame;
        this.init(p1, p2);
        this.onTurn = p1;
        this.perRounddddddddddd = perRounddddddddddd;
        this.whoseturn = whoseturn;
    }

    /**
     * 初始化游戏。在开始游戏前，应先调用此方法，给予游戏必要的参数。
     *
     * @param p1 玩家1
     * @param p2 玩家2
     */
    public void init(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.onTurn = p1;
        this.helperrrrrrr = 1;
        //TODO: 在初始化游戏的时候，还需要做什么？
    }

    /**
     * 进行下一个回合时应调用本方法。
     * 在这里执行每个回合结束时需要进行的操作。
     * <p>
     * (目前这里没有每个玩家进行n回合的计数机制的，请自行修改完成哦~）
     */
    public void nextTurn() {
        //假如所有雷已经出现，揭晓结果
        if (gamePanel.getLeftMineCount() == 0) {
            //双方分数相同
            if (p1.getScore() == p2.getScore()) {
                if (p1.getMistake() > p2.getMistake()) {
                    //p2获胜
                    endFrame.updateee("Player2!!");
                    endFrame.getEndframe().setVisible(true);
                }

                if (p2.getMistake() > p1.getMistake()) {
                    //p1获胜
                    endFrame.updateee("Player1!!");
                    endFrame.getEndframe().setVisible(true);
                }

                if (p1.getMistake() == p2.getMistake()) {
                    //双方平局
                    endFrame.updateee("Both!Of!");
                    endFrame.getEndframe().setVisible(true);
                }
            }
            //双方分数不同
            else {
                if (p1.getScore() > p2.getScore()) {
                    endFrame.updateee("Player1!!");
                    endFrame.getEndframe().setVisible(true);
                }
                if (p2.getScore() > p1.getScore()) {
                    endFrame.updateee("Player2!!");
                    endFrame.getEndframe().setVisible(true);
                }
                if (p1.getScore() == p2.getScore()) {
                    endFrame.updateee("Both!Of!");
                    endFrame.getEndframe().setVisible(true);
                }
            }
        }

        //一轮结尾结算
        if (oneRound == 2 && counter == perRounddddddddddd) {
            if (p1.getScore() > p2.getScore() && p1.getScore() - p2.getScore() > gamePanel.getLeftMineCount()) {
                //双方的分数差距大于游戏区中未揭晓的雷数，p1获胜，游戏结束
                endFrame.updateee("Player1!!");
                endFrame.getEndframe().setVisible(true);
            }
            if (p2.getScore() > p1.getScore() && p2.getScore() - p1.getScore() > gamePanel.getLeftMineCount()) {
                //双方的分数差距大于游戏区中未揭晓的雷数，p2获胜，游戏结束
                endFrame.updateee("Player2!!");
                endFrame.getEndframe().setVisible(true);
            }
        }
        //一人点击玩本轮该点次数后oneRound+1
        switch (oneRound) {
            case 1:
                if (counter == perRounddddddddddd) {
                    counter = 1;
                    oneRound++;

                    if (onTurn == p1) {
                        onTurn = p2;
                        this.helperrrrrrr = 2;
                    } else if (onTurn == p2) {
                        onTurn = p1;
                        this.helperrrrrrr = 1;
                    }

                } else {
                    counter++;
                }
                break;
            case 2:
                if (counter == perRounddddddddddd) {
                    counter = 1;
                    oneRound = 1;

                    if (onTurn == p1) {
                        onTurn = p2;
                        this.helperrrrrrr = 2;
                    } else if (onTurn == p2) {
                        onTurn = p1;
                        this.helperrrrrrr = 1;
                    }

                } else {
                    counter++;
                }
                break;
        }

        whoseTurn = String.format("%s!!Your!!Turn!!", onTurn.getUserName());
        whoseturn.updateee(whoseTurn);
        whoseturn.updateeeeeeeee(String.format("Mines~Not~found:%d",gamePanel.getLeftMineCount()));
        System.out.println(whoseTurn);
        scoreBoard1.update("Player1");
        scoreBoard2.update("Player2");
        //TODO: 在每个回合结束的时候，还需要做什么 (例如...检查游戏是否结束？)
    }


    /**
     * 获取正在进行当前回合的玩家。
     *
     * @return 正在进行当前回合的玩家
     */
    public Player getOnTurnPlayer() {
        return onTurn;
    }


    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ScoreBoard getScoreBoard1() {
        return scoreBoard1;
    }

    public ScoreBoard getScoreBoard2(){return scoreBoard2;}

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setScoreBoard(ScoreBoard scoreBoard1, ScoreBoard scoreBoard2) {
        this.scoreBoard1 = scoreBoard1;
        this.scoreBoard2 = scoreBoard2;
    }


    public void readFileData(String fileName) {
        //todo: read date from file

    }

    public void writeDataToFile(String fileName) {
        //todo: write data into file
    }

    public String getWhoseTurn() {
        return whoseTurn;
    }

    public int getCounter(){return counter;}

    public void setCounter(int Counter){this.counter = Counter;}

    public void setOnTurn(int onturnnnn){
        if(onturnnnn==1){
            onTurn = p1;
        }
        else{
            onTurn = p2;
        }
    }

    public int getHelperrrrrrr(){return helperrrrrrr;}
}
