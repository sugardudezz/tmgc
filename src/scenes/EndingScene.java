package scenes;

import utils.BackgroundImage;
import utils.Geometry;

import javax.swing.*;
import java.awt.*;

public class EndingScene extends JPanel {
    String imagePath = "./src/images/";

    public EndingScene() {
        setLayout(null);

        JLabel label = new JLabel("<html><h1>다마고치 브리더</h1></html>", JLabel.CENTER);
        label.setBounds(new Geometry(0.5f,0.35f,0.5f,0.7f));

        Geometry.setIcon(label, imagePath + "Logo.gif");

        JButton button1 = new JButton("다시하기");
        button1.setBounds(new Geometry(0.5f,0.55f,0.3f,0.15f));
        button1.addActionListener(e -> SceneManager.playScene(new PrologueScene()));

        Geometry.setIcon(button1, imagePath + "PlayButton.png");

        button1.setForeground(Color.WHITE);
        button1.setHorizontalTextPosition(SwingConstants.CENTER);
        button1.setVerticalTextPosition(SwingConstants.CENTER);

        Font font = new Font("woodPanel", Font.BOLD, 30);
        button1.setFont(font);

        JButton button2 = new JButton("게임 종료");
        button2.setBounds(new Geometry(0.5f,0.75f,0.3f,0.15f));
        button2.addActionListener(e -> System.exit(0));

        Geometry.setIcon(button2, imagePath + "PlayButton.png");

        button2.setForeground(Color.WHITE);
        button2.setHorizontalTextPosition(SwingConstants.CENTER);
        button2.setVerticalTextPosition(SwingConstants.CENTER);

        button2.setFont(font);

        button1.setContentAreaFilled(false);
        button2.setContentAreaFilled(false);

        add(label);
        add(button1);
        add(button2);

        BackgroundImage bg = new BackgroundImage(imagePath + "Ending.png");
        add(bg);
    }
}
