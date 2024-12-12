package scenes;

import utils.Geometry;

import javax.swing.*;

public class EndingScene extends JPanel {
    public EndingScene() {
        setLayout(null);

        JLabel label = new JLabel("<html><h1>다마고치 브리더</h1></html>", JLabel.CENTER);
        label.setBounds(new Geometry(0.5f,0.3f,0.5f,0.2f));

        JButton button1 = new JButton("다시하기");
        button1.setBounds(new Geometry(0.5f,0.5f,0.3f,0.1f));
        button1.addActionListener(e -> SceneManager.playScene(new PrologueScene()));

        JButton button2 = new JButton("게임 종료");
        button2.setBounds(new Geometry(0.5f,0.7f,0.3f,0.1f));
        button2.addActionListener(e -> System.exit(0));

        add(label);
        add(button1);
        add(button2);
    }
}
