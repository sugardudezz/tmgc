package scenes;

import scripts.Main;
import scripts.Storage;
import tamagochi.Tamagochi;
import tamagochi.TamagochiButton;
import utils.Geometry;
import utils.HPBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static tamagochi.Tamagochi.*;

public class UpgradeScene extends JPanel {
    public UpgradeScene() {
        setLayout(null);

        JLabel label = new JLabel("<html><h1>성장</h1></html>", SwingConstants.CENTER);
        label.setBounds(new Geometry(0.5f, 0.1f, 0.5f, 0.2f));
        add(label);

        ArrayList<TamagochiPick> tamagochiPicks = new ArrayList<>();

        for (int i = 0; i < Storage.tamagochis.size(); i++) {
            TamagochiPick tamagochiPick = new TamagochiPick(Storage.tamagochis.get(i));
            tamagochiPicks.add(tamagochiPick);
            tamagochiPicks.get(i).setBounds(new Geometry(0.165f * (i + 1), 0.8f, 100, 100));
            add(tamagochiPicks.get(i));
        }
        JButton completeButton = new JButton();
        completeButton.setBounds(new Geometry(0.85f,0.18f,0.2f,0.1f));
        completeButton.setText("완료");
        completeButton.addActionListener(e -> {
            TamagochiPick.pickedtamagochi.clear();
            Main.round++;
            SceneManager.playScene(new RoundScene());
        });
        add(completeButton);

        JButton upgradeButton = new JButton();
        upgradeButton.setBounds(new Geometry(0.85f,0.65f,0.2f,0.1f));
        upgradeButton.setText("성장시키기");
        upgradeButton.addActionListener(e ->{
                if (TamagochiPick.pickedtamagochi.get(0).name == TamagochiPick.pickedtamagochi.get(1).name && TamagochiPick.pickedtamagochi.get(0).level == TamagochiPick.pickedtamagochi.get(1).level) {
                    Storage.tamagochis.remove(TamagochiPick.pickedtamagochi.get(0));
                    Storage.tamagochis.remove(TamagochiPick.pickedtamagochi.get(1));
                    Tamagochi upgradetamagochi = new Tamagochi();
                    upgradetamagochi = TamagochiPick.pickedtamagochi.get(0);
                    upgradetamagochi.level++;
                    int stat = upgradetamagochi.level - 1;
                    if (upgradetamagochi.prop == WATER) {
                        upgradetamagochi.maxHealth += 8 * stat;
                        upgradetamagochi.health += 8 * stat;
                        upgradetamagochi.attack += stat;
                        upgradetamagochi.speed += stat;
                    } else if (upgradetamagochi.prop == FIRE) {
                        upgradetamagochi.maxHealth += 2 * stat;
                        upgradetamagochi.health += 2 * stat;
                        upgradetamagochi.attack += 3 * stat;
                        upgradetamagochi.speed += stat;
                    } else if (upgradetamagochi.prop == GRASS) {
                        upgradetamagochi.maxHealth += 4 * stat;
                        upgradetamagochi.health += 4 * stat;
                        upgradetamagochi.attack += 2 * stat;
                        upgradetamagochi.speed += stat;

                    }
                    Storage.tamagochis.add(upgradetamagochi);
                }
            TamagochiPick.pickedtamagochi.clear();
            SceneManager.playScene(new UpgradeScene());
        });
        add(upgradeButton);
    }
}

class TamagochiPick extends TamagochiButton {
    public static ArrayList<Tamagochi> pickedtamagochi = new ArrayList<>();
    boolean picked = false;
    public TamagochiPick(Tamagochi tamagochi) {
        super(tamagochi);
    }
    public void actionPerformed(ActionEvent e) {
        if (pickedtamagochi.size() < 2) {
            if (!picked) {
                if (pickedtamagochi.size() == 0) {
                    setBounds(new Geometry(0.25f, 0.4f, 150, 150));
                }
                else {
                    setBounds(new Geometry(0.5f, 0.4f, 150, 150));
                }
                pickedtamagochi.add(tamagochi);
                picked = true;
                System.out.print("picked!");
            }
            else {
                pickedtamagochi.remove(tamagochi);
                setBounds(new Geometry(0.165f * (Storage.tamagochis.indexOf(tamagochi) + 1), 0.8f, 100, 100));
                picked = false;
                System.out.print("unpicked!");
            }
        }
        else {
            if (picked) {
                pickedtamagochi.remove(tamagochi);
                setBounds(new Geometry(0.165f * (Storage.tamagochis.indexOf(tamagochi) + 1), 0.8f, 100, 100));
                picked = false;
                System.out.print("unpicked!");
            }
        }
    }
}