package scenes;

import scripts.Main;
import scripts.Storage;
import tamagochi.Tamagochi;
import tamagochi.TamagochiButton;
import utils.BackgroundImage;
import utils.Geometry;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChooseTamagochi extends JPanel {
    String imagePath = "./src/images/";

    public ChooseTamagochi(){
        setLayout(null);

        ArrayList<TamagochiOption> tamagochiOptions = new ArrayList<>();

        for(int i=0;i<3;i++) {
            while(true) {
                TamagochiOption tamagochiOption = new TamagochiOption(Tamagochi.getRandom());
                if (tamagochiOptions.contains(tamagochiOption)) {
                    tamagochiOptions.removeLast();
                }else{
                    tamagochiOptions.add(tamagochiOption);
                    break;
                }
            }
            tamagochiOptions.get(i).setBounds(new Geometry(0.25f*(i+1),0.6f,150,150));
            add(tamagochiOptions.get(i));
        }

        JLabel label = new JLabel("<html><h1>원하는 다마고치를 선택하세요</h1></html>",SwingConstants.CENTER);
        label.setBounds(new Geometry(0.5f,0.25f,0.5f,0.2f));

        add(label);

        BackgroundImage bg = new BackgroundImage(imagePath + "RoundScene.png");
        add(bg);
    }
}

class TamagochiOption extends TamagochiButton{
    public TamagochiOption(Tamagochi tamagochi){
        super(tamagochi);
    }
    public void actionPerformed(ActionEvent e){
        Storage.add(tamagochi);
        SceneManager.playScene(new RoundScene());
    }
}