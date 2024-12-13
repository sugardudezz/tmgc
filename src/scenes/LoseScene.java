package scenes;

import scripts.Main;
import scripts.Storage;
import utils.Geometry;
import utils.BackgroundImage;
import java.awt.Font;
import java.awt.Color;


import javax.swing.*;

public class LoseScene extends JPanel {
    String imagePath = "./src/images/";

    public LoseScene(){
        setLayout(null);

        JLabel label = new JLabel("<html><h1>다마고치가 죽었습니다.</h1></html>", JLabel.CENTER);
        label.setBounds(new Geometry(0.5f,0.3f,0.5f,0.2f));
        label.setForeground(Color.RED);

        JButton button = new JButton("다음으로");
        button.setBounds(new Geometry(0.5f,0.7f,0.4f,0.2f));
        Geometry.setIcon(button, imagePath + "YellowPanel.png");

        button.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트 중앙 정렬
        button.setVerticalTextPosition(SwingConstants.CENTER);    // 텍스트 중앙 정렬

        Font font = new Font("woodPanel", Font.BOLD, 40);
        button.setFont(font);

        button.addActionListener(e -> {
            if (Storage.isGameOver()) {
                Main.round = 0;
                Storage.clear();
                SceneManager.playScene(new MainMenu());
            } else {
                SceneManager.playScene(new RoundScene());
            }
        });

        if(Storage.isGameOver()){
            label.setText("<html><h1>모든 다마고치가 죽었습니다.</h1></html>");
            button.setText("처음으로");
        }

        add(label);
        add(button);

        BackgroundImage bg = new BackgroundImage(imagePath + "LoseScene.png");
        add(bg);
    }
}
