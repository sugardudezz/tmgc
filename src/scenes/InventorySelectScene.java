package scenes;

import scripts.Storage;
import tamagochi.Tamagochi;
import tamagochi.TamagochiButton;
import utils.Geometry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class InventorySelectScene extends JPanel {
    String imagePath = "./src/images/";
    static JPanel returnScene;

    public InventorySelectScene(JPanel originalScene){
        setLayout(null);
        returnScene = originalScene;
        ArrayList<TamagochiCatalog> tamagochiCatalogs = new ArrayList<>();

        for (int i = 0; i < Storage.tamagochis.size(); i++) {
            TamagochiCatalog tamagochiCatalog = new TamagochiCatalog(Storage.tamagochis.get(i));
            tamagochiCatalogs.add(tamagochiCatalog);
            tamagochiCatalogs.get(i).setBounds(new Geometry(0.165f * (i + 1), 0.2f, 150, 150));
            add(tamagochiCatalogs.get(i));
        }

        JButton inventoryButton = new JButton("취소");
        inventoryButton.setBounds(new Geometry(0.5f,0.8f,0.3f,0.06f));
        Geometry.setIcon(inventoryButton, imagePath + "YellowPanel.png");

        inventoryButton.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트 중앙 정렬
        inventoryButton.setVerticalTextPosition(SwingConstants.CENTER);    // 텍스트 중앙 정렬

        inventoryButton.addActionListener(e -> SceneManager.playScene(returnScene));
        add(inventoryButton);
    }

    private static class TamagochiCatalog extends TamagochiButton {
        public TamagochiCatalog(Tamagochi tamagochi){
            super(tamagochi);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Storage.tamagochis.remove(this.tamagochi);
            SceneManager.playScene(returnScene);
        }
    }
}
