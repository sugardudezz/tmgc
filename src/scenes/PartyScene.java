package scenes;

import scripts.Main;
import scripts.Storage;
import tamagochi.Tamago;
import tamagochi.Tamagochi;
import tamagochi.TamagochiButton;
import utils.BackgroundImage;
import utils.Geometry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class PartyScene extends JPanel {
    String imagePath = "./src/images/";

    public PartyScene(){
        setLayout(null);

        JLabel label = new JLabel("<html><h1>편성</h1></html>", SwingConstants.CENTER);
        label.setBounds(new Geometry(0.5f, 0.1f, 0.5f, 0.2f));
        label.setForeground(Color.WHITE);
        add(label);

        ArrayList<TamagochiSelect> tamagochiSelects = new ArrayList<>();

        for (int i = 0; i < Storage.tamagochis.size(); i++) {
            if(Storage.tamagochis.get(i) instanceof Tamago){
                continue;
            }
            TamagochiSelect tamagochiSelect = new TamagochiSelect(Storage.tamagochis.get(i));
            tamagochiSelects.add(tamagochiSelect);
            tamagochiSelects.get(i).setBounds(new Geometry(0.165f * (i + 1), 0.8f, 100, 100));
            add(tamagochiSelects.get(i));
        }
        JButton completeButton = new JButton();
        completeButton.setBounds(new Geometry(0.85f,0.18f,0.2f,0.1f));
        completeButton.setText("완료");
        Geometry.setIcon(completeButton, imagePath + "YellowPanel.png");

        completeButton.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트 중앙 정렬
        completeButton.setVerticalTextPosition(SwingConstants.CENTER);    // 텍스트 중앙 정렬

        completeButton.addActionListener(e -> {
            Storage.fighters.clear();
            if (TamagochiSelect.selectedtamagochi.size() == 3) {
                Storage.fighters.add(TamagochiSelect.selectedtamagochi.get(0));
                Storage.fighters.add(TamagochiSelect.selectedtamagochi.get(1));
                Storage.fighters.add(TamagochiSelect.selectedtamagochi.get(2));
            } else if (TamagochiSelect.selectedtamagochi.size() == 2) {
                Storage.fighters.add(TamagochiSelect.selectedtamagochi.get(0));
                Storage.fighters.add(TamagochiSelect.selectedtamagochi.get(1));
            } else if (TamagochiSelect.selectedtamagochi.size() == 1) {
                Storage.fighters.add(TamagochiSelect.selectedtamagochi.get(0));
            } else {
                Storage.fighters.add(Storage.tamagochis.get(0));
            }

            //남는 다마고치 있으면 자동편성
            ArrayList<Tamagochi> tamagochisOnly = Storage.getTamagochisOnly();
            if(Storage.fighters.size() < tamagochisOnly.size()){
                for (Tamagochi tamagochi : tamagochisOnly){
                    if (Storage.fighters.contains(tamagochi)) continue;
                    Storage.fighters.add(tamagochi);
                    if(Storage.fighters.size() >= 3){
                        break;
                    }
                }
            }

            TamagochiSelect.selectedtamagochi.clear();
            Main.round++;
            SceneManager.playScene(new RoundScene());
        });
        completeButton.setContentAreaFilled(false);
        completeButton.setContentAreaFilled(false);
        add(completeButton);

        BackgroundImage bg = new BackgroundImage(imagePath + "RepairScene.png");
        add(bg);

    }
}

class TamagochiSelect extends TamagochiButton {
    public static ArrayList<Tamagochi> selectedtamagochi = new ArrayList<>();
    boolean picked = false;
    public TamagochiSelect(Tamagochi tamagochi) {
        super(tamagochi);
    }
    public void actionPerformed(ActionEvent e) {
        if (selectedtamagochi.size() < 3) {
            if (!picked) {
                if (selectedtamagochi.size() == 0) {
                    setBounds(new Geometry(0.25f, 0.4f, 150, 150));
                }
                else if(selectedtamagochi.size() == 1) {
                    setBounds(new Geometry(0.5f, 0.4f, 150, 150));
                } else {
                    setBounds(new Geometry(0.75f, 0.4f, 150, 150));
                }
                selectedtamagochi.add(tamagochi);
                picked = true;
                System.out.print("picked!");
            }
            else {
                selectedtamagochi.remove(tamagochi);
                setBounds(new Geometry(0.165f * (Storage.tamagochis.indexOf(tamagochi) + 1), 0.8f, 100, 100));
                picked = false;
                System.out.print("unpicked!");
            }
        }
        else {
            if (picked) {
                selectedtamagochi.remove(tamagochi);
                setBounds(new Geometry(0.165f * (Storage.tamagochis.indexOf(tamagochi) + 1), 0.8f, 100, 100));
                picked = false;
                System.out.print("unpicked!");
            }
        }
    }
}
