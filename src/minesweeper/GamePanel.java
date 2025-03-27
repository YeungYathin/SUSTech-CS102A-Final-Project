package minesweeper;

import components.GridComponent;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class GamePanel extends JPanel implements Serializable {
    private GridComponent[][] mineField;
    private int[][] chessboard;
    private final Random random = new Random();
    private int xCount;
    private int yCount;
    private int mineCount;
    private int leftMineCount;

    /**
     * 初始化一个具有指定行列数格子、并埋放了指定雷数的雷区。
     *
     * @param xCount    count of grid in column
     * @param yCount    count of grid in row
     * @param mineCount mine count
     */

    public GamePanel(int xCount, int yCount, int mineCount, MainFrame mainFrame) {
        this.xCount = xCount;
        this.yCount = yCount;
        this.mineCount = mineCount;
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setSize(GridComponent.gridSize * yCount, GridComponent.gridSize * xCount);
        this.setLocation(mainFrame.getWidth() / 2 - getWidth() / 2, mainFrame.getHeight() / 2 - getHeight() / 2 + 20);
        this.leftMineCount = this.mineCount;

        mineField = new GridComponent[xCount][yCount];
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                GridComponent gridComponent = new GridComponent(i, j, this);
                mineField[i][j] = gridComponent;
            }
        }
        initialGame(xCount, yCount, mineCount, mineField);


        repaint();
    }

    public int getxCount() {
        return this.xCount;
    }

    public int getyCount() {
        return this.yCount;
    }

    public int getMineCount() {
        return this.mineCount;
    }


    public void initialGame(int xCount, int yCount, int mineCount, GridComponent[][] mineField) {
        generateChessBoard(xCount, yCount, mineCount);

        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                mineField[i][j].setContent(chessboard[i][j]);
                mineField[i][j].setLocation(j * GridComponent.gridSize, i * GridComponent.gridSize);
                this.add(mineField[i][j]);
            }
        }
    }


    public void generateChessBoard(int xCount, int yCount, int mineCount) {
        //todo: generate chessboard by your own algorithm
        chessboard = new int[xCount][yCount];

        //start to place the landmine
        for (int i = 0; i < mineCount; i++) {
            for (; ; ) {
                int x = random.nextInt(xCount);
                int y = random.nextInt(yCount);
                boolean IsValidPlace = true;
                if (0 < x && x < xCount - 1 && 0 < y && y < yCount - 1) {
                    if (chessboard[x - 1][y - 1] == -1 &&
                            chessboard[x - 1][y] == -1 &&
                            chessboard[x - 1][y + 1] == -1 &&
                            chessboard[x][y - 1] == -1 &&
                            chessboard[x][y + 1] == -1 &&
                            chessboard[x + 1][y - 1] == -1 &&
                            chessboard[x + 1][y] == -1 &&
                            chessboard[x + 1][y + 1] == -1
                    ) {
                        IsValidPlace = false;
                    }
                }
                if (IsValidPlace && chessboard[x][y] == 0) {
                    chessboard[x][y] = -1;
                    break;
                }
            }
        }

        //start to set number of landmine around each grid
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                if (chessboard[i][j] != -1) {
                    chessboard[i][j] = calculateNum(i, j, xCount, yCount);
                }
            }
        }
    }

    /**
     * 获取一个指定坐标的格子。
     * 注意请不要给一个棋盘之外的坐标哦~
     *
     * @param x 第x列
     * @param y 第y行
     * @return 该坐标的格子
     */
    public GridComponent getGrid(int x, int y) {
        try {
            return mineField[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


    //calculate the number of landmine around each single grid
    public int calculateNum(int i, int j, int xCount, int yCount) {
        int result = 0;

        //left up
        if (i == 0 && j == 0) {
            if (chessboard[0][1] == -1) {
                result++;
            }
            if (chessboard[1][0] == -1) {
                result++;
            }
            if (chessboard[1][1] == -1) {
                result++;
            }
            return result;
        }

        //right up
        if (i == 0 && j == yCount - 1) {
            if (chessboard[0][yCount - 2] == -1) {
                result++;
            }
            if (chessboard[1][yCount - 2] == -1) {
                result++;
            }
            if (chessboard[1][yCount - 1] == -1) {
                result++;
            }
            return result;
        }

        //left down
        if (i == xCount - 1 && j == 0) {
            if (chessboard[xCount - 2][0] == -1) {
                result++;
            }
            if (chessboard[xCount - 2][1] == -1) {
                result++;
            }
            if (chessboard[xCount - 1][1] == -1) {
                result++;
            }
            return result;
        }

        //right down
        if (i == xCount - 1 && j == yCount - 1) {
            if (chessboard[xCount - 2][yCount - 2] == -1) {
                result++;
            }
            if (chessboard[xCount - 2][yCount - 1] == -1) {
                result++;
            }
            if (chessboard[xCount - 1][yCount - 2] == -1) {
                result++;
            }
            return result;
        }

        //upper
        if (i == 0) {
            if (chessboard[0][j - 1] == -1) {
                result++;
            }
            if (chessboard[0][j + 1] == -1) {
                result++;
            }
            if (chessboard[1][j - 1] == -1) {
                result++;
            }
            if (chessboard[1][j] == -1) {
                result++;
            }
            if (chessboard[1][j + 1] == -1) {
                result++;
            }
            return result;
        }

        //down
        if (i == xCount - 1) {
            if (chessboard[xCount - 2][j - 1] == -1) {
                result++;
            }
            if (chessboard[xCount - 2][j + 1] == -1) {
                result++;
            }
            if (chessboard[xCount - 2][j] == -1) {
                result++;
            }
            if (chessboard[xCount - 1][j - 1] == -1) {
                result++;
            }
            if (chessboard[xCount - 1][j + 1] == -1) {
                result++;
            }
            return result;
        }

        //left
        if (j == 0) {
            if (chessboard[i - 1][0] == -1) {
                result++;
            }
            if (chessboard[i + 1][0] == -1) {
                result++;
            }
            if (chessboard[i - 1][1] == -1) {
                result++;
            }
            if (chessboard[i][1] == -1) {
                result++;
            }
            if (chessboard[i + 1][1] == -1) {
                result++;
            }
            return result;
        }

        //right
        if (j == yCount - 1) {
            if (chessboard[i - 1][yCount - 2] == -1) {
                result++;
            }
            if (chessboard[i][yCount - 2] == -1) {
                result++;
            }
            if (chessboard[i + 1][yCount - 2] == -1) {
                result++;
            }
            if (chessboard[i - 1][yCount - 1] == -1) {
                result++;
            }
            if (chessboard[i + 1][yCount - 1] == -1) {
                result++;
            }
            return result;
        }

        //middle part
        if (chessboard[i - 1][j - 1] == -1) {
            result++;
        }
        if (chessboard[i - 1][j] == -1) {
            result++;
        }
        if (chessboard[i - 1][j + 1] == -1) {
            result++;
        }
        if (chessboard[i][j - 1] == -1) {
            result++;
        }
        if (chessboard[i][j + 1] == -1) {
            result++;
        }
        if (chessboard[i + 1][j - 1] == -1) {
            result++;
        }
        if (chessboard[i + 1][j] == -1) {
            result++;
        }
        if (chessboard[i + 1][j + 1] == -1) {
            result++;
        }
        return result;
    }


    public GridComponent[][] getMineField() {
        return mineField;
    }

    public void setLeftMineCount(int leftMineCount){
        this.leftMineCount = leftMineCount;
    }

    public int getLeftMineCount(){
        return this.leftMineCount;
    }
}
