package components;

import entity.GridStatus;
import minesweeper.GamePanel;
import minesweeper.MainFrame;
import sun.applet.Main;

import java.awt.*;

public class GridComponent extends BasicComponent {
    public static int gridSize = 30;
    private int statusToInt;

    //保证第一次不会触雷
    private static boolean ifFirstClick = true;

    private static final Image covered = Toolkit.getDefaultToolkit().getImage("src/components/pic/Covered.png");
    private static final Image flagright = Toolkit.getDefaultToolkit().getImage("src/components/pic/flagright.png");
    private static final Image errorFlag = Toolkit.getDefaultToolkit().getImage("src/components/pic/errorFlag.png");
    private static final Image boom = Toolkit.getDefaultToolkit().getImage("src/components/pic/boom.png");
    private static final Image clickright = Toolkit.getDefaultToolkit().getImage("src/components/pic/clickright.png");

    private GamePanel gamePanel;
    private int row;
    private int col;
    private GridStatus status = GridStatus.Covered;
    private int content = 0;

    public GridComponent(int x, int y, GamePanel g) {
        this.setSize(gridSize, gridSize);
        this.row = x;
        this.col = y;
        this.gamePanel = g;

        ifFirstClick = true;

    }

    @Override
    public void onMouseLeftClicked() {
        System.out.printf("Gird (%d,%d) is left-clicked.\n", row, col);
        if (this.status == GridStatus.Covered) {
            if (this.content != -1) {
                ifFirstClick = false;
                theHeavenlyMaidsScatterBlossoms();
                MainFrame.controller.nextTurn();
            } else {
                //防止第一次触雷机制
                if (ifFirstClick) {
                    gamePanel.initialGame(gamePanel.getxCount(), gamePanel.getyCount(), gamePanel.getMineCount(), gamePanel.getMineField());
                    onMouseLeftClicked();
                    ifFirstClick = false;
                } else {
                    MainFrame.controller.getOnTurnPlayer().costScore();//现在这轮的玩家扣一分
                    this.status = GridStatus.ClickedWrong;
                    gamePanel.setLeftMineCount(gamePanel.getLeftMineCount() - 1);
                    repaint();
                    MainFrame.controller.nextTurn();
                }
            }
        }

        //TODO: 在左键点击一个格子的时候，还需要做什么？
    }

    public void theHeavenlyMaidsScatterBlossoms() {
        if (this.status == GridStatus.Covered) {
            if (this.content != 0) {
                this.status = GridStatus.ClickedRight;
                repaint();
            } else {
                this.status = GridStatus.ClickedRight;
                repaint();

                int x = gamePanel.getMineField().length;
                int y = gamePanel.getMineField()[0].length;

                //分类
                //left up
                if (row == 0 && col == 0) {
                    this.gamePanel.getGrid(0, 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(1, 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(1, 0).theHeavenlyMaidsScatterBlossoms();
                }

                //right up
                else if (row == 0 && col == y - 1) {
                    this.gamePanel.getGrid(0, y - 2).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(1, y - 2).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(1, y - 1).theHeavenlyMaidsScatterBlossoms();
                }

                //left down
                else if (row == x - 1 && col == 0) {
                    this.gamePanel.getGrid(x - 2, 0).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(x - 2, 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(x - 1, 1).theHeavenlyMaidsScatterBlossoms();
                }

                //right down
                else if (row == x - 1 && col == y - 1) {
                    this.gamePanel.getGrid(x - 2, y - 2).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(x - 2, y - 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(x - 1, y - 2).theHeavenlyMaidsScatterBlossoms();
                }

                //upper
                else if (row == 0) {
                    this.gamePanel.getGrid(0, col - 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(0, col + 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(1, col - 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(1, col).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(1, col + 1).theHeavenlyMaidsScatterBlossoms();
                }

                //down
                else if (row == x - 1) {
                    this.gamePanel.getGrid(x - 2, col - 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(x - 2, col + 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(x - 2, col).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(x - 1, col - 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(x - 1, col + 1).theHeavenlyMaidsScatterBlossoms();
                }

                //left
                else if (col == 0) {
                    this.gamePanel.getGrid(row - 1, 0).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(row + 1, 0).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(row - 1, 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(row, 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(row + 1, 1).theHeavenlyMaidsScatterBlossoms();
                }

                //right
                else if (col == y - 1) {
                    this.gamePanel.getGrid(row - 1, y - 2).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(row, y - 2).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(row + 1, y - 2).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(row - 1, y - 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(row + 1, y - 1).theHeavenlyMaidsScatterBlossoms();
                }

                //middle part
                else {
                    this.gamePanel.getGrid(row - 1, col - 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(row - 1, col).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(row - 1, col + 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(row, col - 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(row, col + 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(row + 1, col - 1).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(row + 1, col).theHeavenlyMaidsScatterBlossoms();
                    this.gamePanel.getGrid(row + 1, col + 1).theHeavenlyMaidsScatterBlossoms();
                }
            }
        }
    }

    @Override
    public void onMouseRightClicked() {
        System.out.printf("Gird (%d,%d) is right-clicked.\n", row, col);
        if (this.status == GridStatus.Covered) {
            if (this.content == -1) {
                ifFirstClick = false;
                MainFrame.controller.getOnTurnPlayer().addScore();
                this.status = GridStatus.FlagRight;
                gamePanel.setLeftMineCount(gamePanel.getLeftMineCount() - 1);
                repaint();
                MainFrame.controller.nextTurn();
            } else {
                ifFirstClick = false;
                MainFrame.controller.getOnTurnPlayer().addMistake();
                this.status = GridStatus.FlagWrong;
                repaint();
                MainFrame.controller.nextTurn();
            }
        }

        //TODO: 在右键点击一个格子的时候，还需要做什么？
    }


    public void draw(Graphics g) {

        if (this.status == GridStatus.Covered) {
            g.drawImage(covered, 1, 1, getWidth() - 1, getHeight() - 1, this);
            statusToInt = 1;
        }
        if (this.status == GridStatus.ClickedRight) {
            if (this.content != 0) {
                g.drawImage(clickright, 1, 1, getWidth() - 1, getHeight() - 1, this);
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(content), getWidth() / 2 - 3, getHeight() / 2 + 5);
            } else {
                g.drawImage(clickright, 1, 1, getWidth() - 1, getHeight() - 1, this);
            }
            statusToInt = 3;
        }
        if (this.status == GridStatus.FlagRight) {
            g.drawImage(flagright, 1, 1, getWidth() - 1, getHeight() - 1, this);
            statusToInt = 2;
        }
        if (this.status == GridStatus.ClickedWrong) {
            g.drawImage(boom, 1, 1, getWidth() - 1, getHeight() - 1, this);
            statusToInt = 5;
        }
        if (this.status == GridStatus.FlagWrong) {
            g.drawImage(errorFlag, 1, 1, getWidth() - 1, getHeight() - 1, this);
            statusToInt = 4;
        }
        if (this.status == GridStatus.treatedClickedRight) {
            if (this.content != 0) {
                g.drawImage(clickright, 1, 1, getWidth() - 1, getHeight() - 1, this);
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(content), getWidth() / 2 - 3, getHeight() / 2 + 5);
            } else {
                g.drawImage(clickright, 1, 1, getWidth() - 1, getHeight() - 1, this);
            }
            statusToInt = 6;
        }
        if (this.status == GridStatus.treatedClickedWrong) {
            g.drawImage(boom, 1, 1, getWidth() - 1, getHeight() - 1, this);
            statusToInt = 7;
        }

    }

    public void setContent(int content) {
        this.content = content;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        draw(g);
    }

    public void setStatus(GridStatus status){
        this.status = status;
    }

    public void setStatus(int statusToIntttt){
        ifFirstClick = false;
        if(statusToIntttt==1){
            this.status = GridStatus.Covered;
        }
        else if(statusToIntttt==2){
            this.status = GridStatus.FlagRight;
        }
        else if(statusToIntttt==3){
            this.status = GridStatus.ClickedRight;
        }
        else if(statusToIntttt==4){
            this.status = GridStatus.FlagWrong;
        }
        else if(statusToIntttt==5){
            this.status = GridStatus.ClickedWrong;
        }
        else if(statusToIntttt==6){
            this.status = GridStatus.treatedClickedRight;
        }
        else{
            this.status = GridStatus.treatedClickedWrong;
        }
    }

    public GridStatus getStatus(){
        return this.status;
    }

    public int getContent(){
        return this.content;
    }

    public int getStatusToInt(){return this.statusToInt;}
}
