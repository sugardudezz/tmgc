package scenes;

import scripts.Main;
import scripts.Storage;

import javax.swing.*;

public class HealScene extends JPanel {
    public HealScene(){
        setLayout(null);
        for (int i = 0; i < Storage.tamagochis.size(); i++) {
            Storage.tamagochis.get(i).health = Storage.tamagochis.get(i).maxHealth;
        }
        Main.round++;
        SceneManager.playScene(new RoundScene());
    }
}
