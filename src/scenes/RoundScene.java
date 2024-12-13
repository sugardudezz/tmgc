package scenes;

import scripts.Main;
import utils.BackgroundImage;
import utils.Geometry;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class RoundScene extends JPanel {
    String imagePath = "./src/images/";
    public RoundScene(){
        setLayout(null);

        JLabel label = new JLabel("<html><h1>ROUND "+ Main.getRoundString() +"</h1></html>",SwingConstants.CENTER);
        label.setBounds(new Geometry(0.5f,0.25f,0.5f,0.2f));
        add(label);

        JButton fightButton = new JButton();
        fightButton.setBounds(new Geometry(0.27f,0.55f,0.3f,0.2f));
        fightButton.setText("전투");
        Geometry.setIcon(fightButton, imagePath + "YellowPanel.png");

        fightButton.addActionListener(e -> SceneManager.playScene(new FightScene()));
        fightButton.setContentAreaFilled(false);
        add(fightButton);

        JButton repairButton = new JButton();
        repairButton.setBounds(new Geometry(0.73f,0.55f,0.3f,0.2f));
        repairButton.setText("정비");
        Geometry.setIcon(repairButton, imagePath + "YellowPanel.png");

        repairButton.addActionListener(e -> SceneManager.playScene(new RepairScene()));
        repairButton.setContentAreaFilled(false);
        add(repairButton);

        fightButton.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트 중앙 정렬
        fightButton.setVerticalTextPosition(SwingConstants.CENTER);    // 텍스트 중앙 정렬

        Font font = new Font("woodPanel", Font.BOLD, 40);  // 폰트 크기를 20으로 설정
        fightButton.setFont(font);

        repairButton.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트 중앙 정렬
        repairButton.setVerticalTextPosition(SwingConstants.CENTER);    // 텍스트 중앙 정렬

        repairButton.setFont(font);

        CheckRound();
    }
    public void CheckRound() {
        if ( Main.round >= 0 && Main.round <= 10 ) {
            BackgroundImage bg = new BackgroundImage("./src/images/FightScene1.png"); add(bg);
        }
        else if ( Main.round >= 11 && Main.round <= 20 ) {
            BackgroundImage bg = new BackgroundImage("./src/images/FightScene2.png"); add(bg);
        }

        else {
            BackgroundImage bg = new BackgroundImage( "./src/images/FightScene3.png"); add(bg);
        }
    }

}

