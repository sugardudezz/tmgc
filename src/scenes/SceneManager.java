package scenes;

import utils.Geometry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SceneManager {
    public static Dimension resolution;
    public static JFrame frame;

    public static void playScene(JPanel scene){
        frame.setContentPane(scene);

        if(!frame.isVisible()){
            frame.setVisible(true);
        }
        frame.validate();
        frame.repaint();
        System.out.print("\""+scene.getClass().getName() + "\" played.");
    }

    public SceneManager(JFrame frame){
        SceneManager.frame = frame;
    }
}
