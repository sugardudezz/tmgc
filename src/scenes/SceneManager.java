package scenes;

import utils.Geometry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SceneManager {
    public static Dimension resolution;
    public static JFrame frame;

    public static void playScene(JPanel scene){
        scene.setPreferredSize(resolution);
        frame.setContentPane(scene);
        frame.pack();
        if(!frame.isVisible()){
            frame.setVisible(true);
        }
        frame.validate();
        frame.repaint();
        System.out.println("\""+scene.getClass().getName() + "\" played.");
    }

    public SceneManager(JFrame frame){
        SceneManager.frame = frame;
    }
}
