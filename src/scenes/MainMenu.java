package scenes;

import tamagochi.Tamagochi.*;
import utils.Geometry;

import javax.swing.*;

public class MainMenu extends JPanel {
    public MainMenu(){
        setLayout(null);

        JLabel label = new JLabel("<html><h1>다마고치 브리더</h1></html>", JLabel.CENTER);
        label.setBounds(new Geometry(0.5f,0.3f,0.5f,0.2f));

        JButton button = new JButton("게임 시작");
        button.setBounds(new Geometry(0.5f,0.6f,0.3f,0.1f));
        button.addActionListener(e -> SceneManager.playScene(new PrologueScene()));

        add(label);
        add(button);
    }
}
