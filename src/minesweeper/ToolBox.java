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

public class ToolBox implements Serializable {
    JPanel toolBox = new JPanel();
    JFrame mainFrame = new JFrame();
    private final String password = "1";

    public JPanel getToolBox() {
        return toolBox;
    }

    public void tool(JFrame mainFrame) {
        //透视功能键
        int trickWidth = 300;
        int trickHeight = 110;
        JFrame confirmThatAuthorityYes = new JFrame();
        confirmThatAuthorityYes.setResizable(false);
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screesize = defaultToolkit.getScreenSize();
        confirmThatAuthorityYes.setLayout(new FlowLayout(FlowLayout.CENTER));
        confirmThatAuthorityYes.setBounds((screesize.width - trickWidth) / 2, (screesize.height - trickHeight) / 2, trickWidth, trickHeight);
        Toolkit tool = confirmThatAuthorityYes.getToolkit(); //得到一个Toolkit对象
        Image myimage = tool.getImage("src\\components\\pic\\framePic.png"); //由tool获取图像
        confirmThatAuthorityYes.setIconImage(myimage);
        confirmThatAuthorityYes.setVisible(false);
        JLabel label = new JLabel("密码:");
        JPasswordField pwd = new JPasswordField("", 20);
        pwd.setEchoChar('*');  //设置回旋字符
        JButton button = new JButton("确认");
        button.setSize(40, 20);
        confirmThatAuthorityYes.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        confirmThatAuthorityYes.add(label);
        confirmThatAuthorityYes.add(pwd);
        confirmThatAuthorityYes.add(button);

        this.mainFrame = mainFrame;
        toolBox.setBackground(Color.ORANGE);
        toolBox.setSize(320, 38);
        toolBox.setLocation(18 * GridComponent.gridSize - 160, 0);
        toolBox.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton Save = new JButton("Save");
        Save.setSize(200, 80);
        JButton Restart = new JButton("Restart");
        Restart.setSize(200, 80);
        JButton trick = new JButton("Trick");
        trick.setSize(200, 80);
        JButton back = new JButton("Back");
        back.setSize(200,80);

        toolBox.add(trick);
        toolBox.add(back);
        toolBox.add(Save);
        toolBox.add(Restart);
        toolBox.setVisible(true);

        trick.addActionListener(e -> {
            confirmThatAuthorityYes.setVisible(true);
        });

        back.addActionListener(e -> {
            GridComponent[][] a = MainFrame.getGamePanel().getMineField();
            for (int i = 0; i < MainFrame.getGamePanel().getxCount(); i++) {
                for (int j = 0; j < MainFrame.getGamePanel().getyCount(); j++) {
                    if (a[i][j].getStatus() == GridStatus.treatedClickedRight||a[i][j].getStatus() == GridStatus.treatedClickedWrong) {
                            a[i][j].setStatus(GridStatus.Covered);
                    }
                }
            }
        });

        button.addActionListener(e -> {

            char[] getPassword = pwd.getPassword();  //获取密码的字符数组
            int ifPassWordRight;
            for (ifPassWordRight = 0; ifPassWordRight < getPassword.length; ifPassWordRight++) {
                if (getPassword[ifPassWordRight] != password.charAt(ifPassWordRight)) {
                    break;
                }
            }
            if (ifPassWordRight == password.length()) {
                GridComponent[][] a = MainFrame.getGamePanel().getMineField();
                for (int i = 0; i < MainFrame.getGamePanel().getxCount(); i++) {
                    for (int j = 0; j < MainFrame.getGamePanel().getyCount(); j++) {
                        if (a[i][j].getStatus() == GridStatus.Covered) {
                            if (a[i][j].getContent() == -1) {
                                a[i][j].setStatus(GridStatus.treatedClickedWrong);
                            } else {
                                a[i][j].setStatus(GridStatus.treatedClickedRight);
                            }
                        }
                    }
                }
                confirmThatAuthorityYes.dispose();
            }
        });

        Save.addActionListener(e -> {

            GameController controller = MainFrame.getController();

            JFileChooser jfc = new JFileChooser(new File("."));
            jfc.showOpenDialog(Save);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(jfc.getSelectedFile()));
                writer.append(String.format("%d,%d,%d,%d\n",MainFrame.getGamePanel().getxCount(),MainFrame.getGamePanel().getyCount(),MainFrame.getGamePanel().getMineCount(),MainFrame.getPerRounddddd()));
                writer.append(String.format("%d,%d\n",controller.getHelperrrrrrr(),controller.getCounter()));
                writer.append(String.format("%d,%d\n",MainFrame.getP1().getScore(),MainFrame.getP1().getMistake()));
                writer.append(String.format("%d,%d\n",MainFrame.getP2().getScore(),MainFrame.getP2().getMistake()));
                writer.append(String.format("%d\n",MainFrame.getGamePanel().getLeftMineCount()));
                for (int i = 0; i < MainFrame.getGamePanel().getxCount(); i++) {
                    for (int j = 0; j < MainFrame.getGamePanel().getyCount(); j++) {
                        writer.append(String.format("%d,%d\n",MainFrame.getGamePanel().getMineField()[i][j].getStatusToInt(),MainFrame.getGamePanel().getMineField()[i][j].getContent()));
                    }
                }
                writer.flush();
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        Restart.addActionListener(e -> {

            Mode modeeeeee = new Mode();
            modeeeeee.modeeeeee();

            Choose chooseeeeeeeeee = new Choose();
            chooseeeeeeeeee.chooseee(modeeeeee);

            chooseeeeeeeeee.getChoose().setVisible(true);

            mainFrame.setVisible(false);
        });

    }
}
