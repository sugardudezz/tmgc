package scenes;

import utils.Geometry;

import javax.swing.*;

public class RepairScene extends JPanel {
    public RepairScene(){
        setLayout(null);

        JLabel label = new JLabel("<html><h1>정비</h1></html>",SwingConstants.CENTER);
        label.setBounds(new Geometry(0.5f,0.25f,0.5f,0.2f));
        add(label);

        JButton fightButton = new JButton();
        fightButton.setBounds(new Geometry(0.27f,0.55f,0.3f,0.2f));
        fightButton.setText("전투");
        fightButton.addActionListener(e -> SceneManager.playScene(new FightScene()));
        add(fightButton);

        JButton repairButton = new JButton();
        repairButton.setBounds(new Geometry(0.73f,0.55f,0.3f,0.2f));
        repairButton.setText("정비");
        repairButton.addActionListener(e -> SceneManager.playScene(new RepairScene()));
        add(repairButton);
    }
}

