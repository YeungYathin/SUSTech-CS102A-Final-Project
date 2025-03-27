package minesweeper;

import components.GridComponent;
import controller.GameController;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class loadingFrame implements Serializable {

    private final String userName = "1";
    private final String password = "1";

    public void load (Choose chooseeeeeeeee) {
        //登录系统
        int loadWidth = 300;
        int loadHeight = 150;
        JFrame loadingFrame = new JFrame("Load");
        loadingFrame.setResizable(false);
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screesize = defaultToolkit.getScreenSize();
        loadingFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        loadingFrame.setBounds((screesize.width - loadWidth) / 2, (screesize.height - loadHeight) / 2, loadWidth, loadHeight);
        Toolkit tool=loadingFrame.getToolkit(); //得到一个Toolkit对象
        Image myimage=tool.getImage("src\\components\\pic\\framePic.png"); //由tool获取图像
        loadingFrame.setIconImage(myimage);
        JTextField username = new JTextField("", 20);
        JLabel label1 = new JLabel("账号:");
        JLabel label2 = new JLabel("密码:");
        JPasswordField pwd = new JPasswordField("", 20);
        pwd.setEchoChar('*');  //设置回旋字符
        JButton button = new JButton("登陆");
        button.setSize(40, 20);

        loadingFrame.add(label1);
        loadingFrame.add(username);
        loadingFrame.add(label2);
        loadingFrame.add(pwd);
        loadingFrame.add(button);
        loadingFrame.setVisible(true);
        loadingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //监听是否登陆成功
        button.addActionListener(e -> {
            char[] getPassword = pwd.getPassword();  //获取密码的字符数组
            int ifPassWordRight;
            for (ifPassWordRight = 0; ifPassWordRight < getPassword.length; ifPassWordRight++) {
                if(getPassword[ifPassWordRight]!=password.charAt(ifPassWordRight)){
                    break;
                }
            }
            if (username.getText().equals(userName) && ifPassWordRight==password.length()) {
                chooseeeeeeeee.getChoose().setVisible(true);
                loadingFrame.dispose();
            }
        });

    }
}
