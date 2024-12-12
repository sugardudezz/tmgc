package scenes;

import utils.Geometry;

import javax.swing.*;
import java.awt.*;

public class RepairScene extends JPanel {
    public RepairScene(){
        setLayout(null);

        JLabel label = new JLabel("<html><h1>정비</h1></html>",SwingConstants.CENTER);
        label.setBounds(new Geometry(0.5f,0.25f,0.5f,0.2f));
        add(label);

        JButton upgradeButton = new JButton("성장");
        upgradeButton.setBounds(new Geometry(0.25f,0.5f,0.2f,0.14f));
        upgradeButton.addActionListener(e -> SceneManager.playScene(new UpgradeScene()));
        upgradeButton.setToolTipText("다마고치를 합쳐 강화하세요");
        add(upgradeButton);

        JButton healButton = new JButton("회복");
        healButton.setBounds(new Geometry(0.75f,0.5f,0.2f,0.14f));
        healButton.addActionListener(e -> SceneManager.playScene(new HealScene()));
        healButton.setToolTipText("부상당한 다마고치를 치료하세요");
        add(healButton);

        JButton partyButton = new JButton("편성");
        partyButton.setBounds(new Geometry(0.5f,0.5f,0.2f,0.14f));
        partyButton.addActionListener(e -> SceneManager.playScene(new PartyScene()));
        partyButton.setToolTipText("전투에 출전할 다마고치 팀을 편성하세요");
        add(partyButton);

        JButton inventoryButton = new JButton("인벤토리 확인");
        inventoryButton.setBounds(new Geometry(0.5f,0.7f,0.3f,0.06f));
        inventoryButton.addActionListener(e -> SceneManager.playScene(new InventoryScene()));
        add(inventoryButton);
    }
}

