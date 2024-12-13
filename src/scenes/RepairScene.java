package scenes;

import scripts.Main;
import utils.BackgroundImage;
import utils.Geometry;
import java.awt.Color;
import javax.swing.*;
import java.awt.Font;
import java.awt.*;

public class RepairScene extends JPanel {
    String imagePath = "./src/images/";

    public RepairScene(){
        setLayout(null);

        JLabel label = new JLabel("<html><h1>정비</h1></html>",SwingConstants.CENTER);
        label.setBounds(new Geometry(0.5f,0.25f,0.5f,0.2f));
        add(label);

        JButton upgradeButton = new JButton("성장");
        upgradeButton.setBounds(new Geometry(0.25f,0.5f,0.2f,0.14f));
        Geometry.setIcon(upgradeButton, imagePath + "AttackPanel.png");

        upgradeButton.setForeground(Color.WHITE);  // 텍스트 색상을 흰색으로 설정
        upgradeButton.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트 중앙 정렬
        upgradeButton.setVerticalTextPosition(SwingConstants.CENTER);    // 텍스트 중앙 정렬

        Font font = new Font("woodPanel", Font.BOLD, 20);
        upgradeButton.setFont(font);


        upgradeButton.addActionListener(e -> SceneManager.playScene(new UpgradeScene()));
        upgradeButton.setToolTipText("다마고치를 합쳐 강화하세요");
        upgradeButton.setContentAreaFilled(false);
        add(upgradeButton);

        JButton healButton = new JButton("회복");
        healButton.setBounds(new Geometry(0.75f,0.5f,0.2f,0.14f));
        Geometry.setIcon(healButton, imagePath + "SwitchPanel.png");

        healButton.setForeground(Color.WHITE);  // 텍스트 색상을 흰색으로 설정
        healButton.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트 중앙 정렬
        healButton.setVerticalTextPosition(SwingConstants.CENTER);    // 텍스트 중앙 정렬

        healButton.setFont(font);

        healButton.addActionListener(e -> SceneManager.playScene(new HealScene()));
        healButton.setToolTipText("부상당한 다마고치를 치료하세요");
        healButton.setContentAreaFilled(false);
        add(healButton);

        JButton partyButton = new JButton("편성");
        partyButton.setBounds(new Geometry(0.5f,0.5f,0.2f,0.14f));
        Geometry.setIcon(partyButton, imagePath + "RunPanel.png");

        partyButton.setForeground(Color.WHITE);  // 텍스트 색상을 흰색으로 설정
        partyButton.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트 중앙 정렬
        partyButton.setVerticalTextPosition(SwingConstants.CENTER);    // 텍스트 중앙 정렬

        partyButton.setFont(font);

        partyButton.addActionListener(e -> SceneManager.playScene(new PartyScene()));
        partyButton.setToolTipText("전투에 출전할 다마고치 팀을 편성하세요");
        partyButton.setContentAreaFilled(false);
        add(partyButton);

        JButton inventoryButton = new JButton("인벤토리 확인");
        inventoryButton.setBounds(new Geometry(0.5f,0.7f,0.3f,0.06f));
        Geometry.setIcon(inventoryButton, imagePath + "YellowPanel.png");

        inventoryButton.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트 중앙 정렬
        inventoryButton.setVerticalTextPosition(SwingConstants.CENTER);    // 텍스트 중앙 정렬

        inventoryButton.addActionListener(e -> SceneManager.playScene(new InventoryScene()));

        inventoryButton.setContentAreaFilled(false);
        add(inventoryButton);

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

