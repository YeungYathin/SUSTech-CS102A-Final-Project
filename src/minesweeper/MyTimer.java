package minesweeper;




import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.TimerTask;


public class MyTimer extends JFrame implements Serializable {

    private static final long serialVersionUID = 1L;
    private int clickTime;//一轮点击次数
    JLabel label = new JLabel();
    private int currTime;
    transient java.util.Timer t;

    public MyTimer(int per_round) {
        currTime = clickTime*5;
        setSize(100, 120);
        setLocation(0,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.ORANGE);
        this.add(getLabel(), BorderLayout.NORTH);
        this.add(getTimeLabel(), BorderLayout.PAGE_END);
        this.clickTime = per_round;

//            start();

    }

    private Component getLabel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Time Left"), BorderLayout.CENTER);
        return panel;
    }

    private Component getTimeLabel() {
        JPanel panel = new JPanel();
        setLabel(label);
        updateTime();
        panel.add(label);
        panel.setBackground(Color.ORANGE);
        return panel;
    }

    public void start() {
        label.setFont(new java.awt.Font("Monotype Corsiva", 1, 38));
        t = new java.util.Timer(true);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                currTime--;
//                System.out.println(currTime);
                SwingUtilities.invokeLater(() -> {
                    label.setText(String.format("%d",getCurrTime()));
                    label.repaint();
                });
                if (currTime<=0){
                    t.cancel();
                }
            }
        }, 0, 1000);//延迟1，间隔1秒
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void end() {
        try {
            t.cancel();
        } catch (Exception e) {
            //空指针也没事
        }
    }

    public void restart() {
        end();
        setCurrTime(clickTime*5);
        start();
    }

    public void updateTime() {
        label= new JLabel(String.format("%d", getCurrTime()));
    }

    public int getCurrTime() {
        return currTime;
    }

    public void setCurrTime(int currTime) {
        this.currTime = currTime;
    }
}
