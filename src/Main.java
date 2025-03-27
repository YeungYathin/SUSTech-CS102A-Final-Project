import minesweeper.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        SwingUtilities.invokeLater(() -> {

            Mode modeeeeee  = new Mode();
            modeeeeee.modeeeeee();

            Choose chooseeeeeeeeee = new Choose();
            chooseeeeeeeeee.chooseee(modeeeeee);

            loadingFrame loaddd = new loadingFrame();
            loaddd.load(chooseeeeeeeeee);
        });
    }
}
