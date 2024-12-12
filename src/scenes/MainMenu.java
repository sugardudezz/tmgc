package scenes;

import tamagochi.Tamagochi.*;
import utils.Geometry;
import utils.BackgroundImage;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class MainMenu extends JPanel {
    String imagePath = "./src/images/";

    public MainMenu(){
        setLayout(null);

        JLabel label = new JLabel("<html><h1>다마고치 브리더</h1></html>", JLabel.CENTER);
        label.setBounds(new Geometry(0.5f,0.3f,0.5f,0.2f));

        JButton button = new JButton("게임 시작");
        button.setBounds(new Geometry(0.5f,0.6f,0.3f,0.1f));
        button.addActionListener(e -> SceneManager.playScene(new PrologueScene()));

        Geometry.setIcon(button, imagePath + "PlayButton.png");

        button.setForeground(Color.WHITE);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);

        Font font = new Font("woodPanel", Font.BOLD, 30);
        button.setFont(font);


        add(label);
        add(button);

        BackgroundImage bg = new BackgroundImage(imagePath + "MainMenu.png");
        add(bg);
    }
}
