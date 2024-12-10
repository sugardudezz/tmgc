package scenes;

import scripts.Main;
import scripts.Storage;
import utils.Geometry;

import javax.swing.*;

public class LoseScene extends JPanel {
    public LoseScene(){
        setLayout(null);
        Storage.clear();

        JLabel label = new JLabel("<html><h1>다마고치가 죽었습니다.</h1></html>", JLabel.CENTER);
        label.setBounds(new Geometry(0.5f,0.3f,0.5f,0.2f));

        JButton button = new JButton("처음으로");
        button.setBounds(new Geometry(0.5f,0.7f,0.4f,0.2f));
        button.addActionListener(e -> SceneManager.playScene(new MainMenu()));

        add(label);
        add(button);
    }
}
