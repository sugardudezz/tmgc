package scenes;

import scripts.Storage;
import tamagochi.Tamago;
import tamagochi.Tamagochi;
import tamagochi.TamagochiButton;
import utils.Geometry;

import javax.swing.*;

public class WinScene extends JPanel {
    String imagePath = "./src/tamagochi/img/";
    Tamagochi returnTamagochi;
    JButton storageFull;
    Tamago tamago;
    public WinScene(Tamagochi enemy){
        setLayout(null);

        JLabel winLabel = new JLabel("<html><h1>승리!</h1></html>",SwingConstants.CENTER);
        winLabel.setBounds(new Geometry(0.5f,0.2f,0.5f,0.2f));
        add(winLabel);

        //부화
        for(Tamagochi tamagochi : Storage.tamagochis){
            if(tamagochi instanceof Tamago){
                ((Tamago) tamagochi).hatch();

                TamagochiButton newTamagochi = new TamagochiButton(tamagochi);
                newTamagochi.setBounds(new Geometry(0.4f, 0.7f, 120, 120));
                add(newTamagochi);

                JLabel newTamagochiLabel = new JLabel("인벤토리의 다마고치가 부화했습니다!",SwingConstants.CENTER);
                newTamagochiLabel.setBounds(new Geometry(0.6f, 0.7f, 200, 100));
                add(newTamagochiLabel);
            }
        }

        int rng = (int)(Math.random()*4+1);

        rng /= rng;

        if (rng <= 2) {
            JLabel eggLabel = new JLabel("알 획득!", SwingConstants.CENTER);
            eggLabel.setBounds(new Geometry(0.6f, 0.4f, 200, 100));
            add(eggLabel);

            tamago = new Tamago(enemy.prop);
            TamagochiButton egg = new TamagochiButton(tamago);
            egg.setBounds(new Geometry(0.4f, 0.4f, 120, 120));
            add(egg);

            storageFull = new JButton("저장소 아이템과 맞바꾸기");
            storageFull.setBounds(new Geometry(0.5f, 0.55f, 220, 30));
            storageFull.addActionListener(e -> SceneManager.playScene(new InventorySelectScene(this)));

            if (Storage.tamagochis.size() >= 5) {
                add(storageFull);
            } else Storage.add(tamago);
        } else if (rng == 3) {
            for(Tamagochi tamagochi : Storage.tamagochis){
                tamagochi.health = tamagochi.maxHealth;

            }
            JLabel healLabel = new JLabel("모든 다마고치가 회복되었습니다!", SwingConstants.CENTER);
            healLabel.setBounds(new Geometry(0.5f, 0.4f, 200, 100));
            add(healLabel);
        } else {
            JLabel swapLabel = new JLabel("다마고치 맞교환 (추가예정)",SwingConstants.CENTER);
            swapLabel.setBounds(new Geometry(0.5f, 0.4f, 200, 100));
            add(swapLabel);
        }

        JButton next = new JButton("다음으로");
        next.setBounds(new Geometry(0.5f,0.85f,200,50));
        next.addActionListener(e -> SceneManager.playScene(new RoundScene()));
        add(next);
    }

    public void setReturnTamagochi(Tamagochi tamagochi){
        returnTamagochi = tamagochi;
        Storage.tamagochis.remove(returnTamagochi);
        Storage.add(tamago);
        storageFull.setEnabled(false);
        storageFull.setText("완료");
    }
}
