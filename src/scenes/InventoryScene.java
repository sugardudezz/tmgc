package scenes;

import scripts.Storage;
import tamagochi.Tamagochi;
import tamagochi.TamagochiButton;
import utils.BackgroundImage;
import utils.Geometry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class InventoryScene extends JPanel {
    String imagePath = "./src/images/";

    public InventoryScene(){
        setLayout(null);

        ArrayList<TamagochiButton> TamagochiButtons = new ArrayList<>();

        for (int i = 0; i < Storage.tamagochis.size(); i++) {
            TamagochiButton TamagochiButton = new TamagochiButton(Storage.tamagochis.get(i));
            TamagochiButtons.add(TamagochiButton);
            TamagochiButtons.get(i).setBounds(new Geometry(0.165f * (i + 1), 0.2f, 150, 150));
            add(TamagochiButtons.get(i));
        }

        JButton inventoryButton = new JButton("확인 완료");
        inventoryButton.setBounds(new Geometry(0.5f,0.8f,0.3f,0.06f));
        Geometry.setIcon(inventoryButton, imagePath + "YellowPanel.png");

        inventoryButton.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트 중앙 정렬
        inventoryButton.setVerticalTextPosition(SwingConstants.CENTER);    // 텍스트 중앙 정렬

        inventoryButton.addActionListener(e -> SceneManager.playScene(new RepairScene()));

        inventoryButton.setContentAreaFilled(false);
        add(inventoryButton);

        BackgroundImage bg = new BackgroundImage(imagePath + "RepairScene.png");
        add(bg);
    }
}
