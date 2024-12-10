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

    public static JPanel screenOverlay(String text, String buttonText, ActionListener actionListener){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,resolution.width,resolution.height);
        panel.setBackground(new Color(0,0,0, 100));

        JLabel label = new JLabel("<html><h1>"+text+"</h1></html>",SwingConstants.CENTER);
        label.setBounds(new Geometry(0.5f,0.4f,0.4f,0.2f));
        panel.add(label);

        JButton button = new JButton("<html><h3>"+buttonText+"</h3></html>");
        button.setBounds(new Geometry(0.5f,0.6f,0.2f,0.1f));
        button.addActionListener(actionListener);
        panel.add(button);

        return panel;
    }
}
