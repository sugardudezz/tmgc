package scenes;

import scripts.Main;
import scripts.Storage;
import tamagochi.Tamagochi;
import utils.BackgroundImage;
import utils.Geometry;

import javax.swing.*;

public class HealScene extends JPanel {
    String imagePath = "./src/images/";

    public HealScene(){
        setLayout(null);
        for (Tamagochi tamagochi : Storage.tamagochis) {
            tamagochi.health = tamagochi.maxHealth;
        }
        JButton HealButton = new JButton("회복 완료");
        HealButton.setBounds(new Geometry(0.5f,0.5f,0.8f,0.4f));
        Geometry.setIcon(HealButton, imagePath + "YellowPanel.png");

        HealButton.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트 중앙 정렬
        HealButton.setVerticalTextPosition(SwingConstants.CENTER);    // 텍스트 중앙 정렬

        HealButton.addActionListener(e -> {
            Main.round++;
            SceneManager.playScene(new RoundScene());
        });
        add(HealButton);

        BackgroundImage bg = new BackgroundImage(imagePath + "RepairScene.png");
        add(bg);

    }
}
