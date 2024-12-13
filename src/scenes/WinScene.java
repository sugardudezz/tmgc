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
    SwapTamagochi swapTamagochi;
    TamagochiButton playerTamagochi;
    JButton swapButton;
    int eventrng;
    public WinScene(Tamagochi enemy){
        setLayout(null);

        JLabel winLabel = new JLabel("<html><h1>승리!</h1></html>",SwingConstants.CENTER);
        winLabel.setBounds(new Geometry(0.5f,0.17f,0.5f,0.2f));
        add(winLabel);

        //부화
        for(Tamagochi tamagochi : Storage.tamagochis){
            if(tamagochi instanceof Tamago){
                ((Tamago) tamagochi).hatch();

                TamagochiButton newTamagochi = new TamagochiButton(tamagochi);
                newTamagochi.setBounds(new Geometry(0.37f, 0.7f, 120, 120));
                add(newTamagochi);

                JLabel newTamagochiLabel = new JLabel("인벤토리의 다마고치가 부화했습니다!",SwingConstants.CENTER);
                newTamagochiLabel.setBounds(new Geometry(0.57f, 0.7f, 300, 100));
                add(newTamagochiLabel);
            }
        }

        eventrng = (int)(Math.random()*4+1);

        eventrng = 4;

        if (eventrng <= 2) {
            //알 획득
            JLabel eggLabel = new JLabel("알 획득!", SwingConstants.CENTER);
            eggLabel.setBounds(new Geometry(0.55f, 0.4f, 200, 50));
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
        } else if (eventrng == 3) {
            //체력 회복
            for(Tamagochi tamagochi : Storage.tamagochis){
                tamagochi.health = tamagochi.maxHealth;

            }
            JLabel healLabel = new JLabel("모든 다마고치가 회복되었습니다!", SwingConstants.CENTER);
            healLabel.setBounds(new Geometry(0.5f, 0.4f, 200, 100));
            add(healLabel);
        } else {
            //다마고치 맞교환
            playerTamagochi = new TamagochiButton(new Tamagochi.TamagochiUnknown());
            playerTamagochi.setBounds(new Geometry(0.4f,0.4f,120,120));
            playerTamagochi.addActionListener(e -> SceneManager.playScene(new InventorySelectScene(this)));
            add(playerTamagochi);

            swapTamagochi = new SwapTamagochi(Tamagochi.getRandom());
            swapTamagochi.setBounds(new Geometry(0.6f,0.4f,120,120));
            swapTamagochi.update();
            add(swapTamagochi);

            swapButton = new JButton("교환하기");
            swapButton.setBounds(new Geometry(0.5f,0.55f,220,30));
            swapButton.setEnabled(false);
            swapButton.setToolTipText("다마고치를 클릭해서 교환할 다마고치를 고르세요");
            swapButton.addActionListener(e -> {
                Storage.tamagochis.remove(playerTamagochi.tamagochi);
                Storage.add(swapTamagochi.tamagochi);
                swapButton.setEnabled(false);
                swapButton.setText("교환됨");
            });
            add(swapButton);

            JLabel swapLabel = new JLabel("인벤토리의 다마고치를 교환할 수 있습니다!",SwingConstants.CENTER);
            swapLabel.setBounds(new Geometry(0.5f, 0.25f, 400, 100));
            add(swapLabel);
        }

        JButton next = new JButton("다음으로");
        next.setBounds(new Geometry(0.5f,0.86f,200,40));
        next.addActionListener(e -> SceneManager.playScene(new RoundScene()));
        add(next);
    }

    public void setReturnTamagochi(Tamagochi tamagochi){
        returnTamagochi = tamagochi;
        if(eventrng <= 2) {
            Storage.tamagochis.remove(returnTamagochi);
            Storage.add(tamago);
            storageFull.setEnabled(false);
            storageFull.setText("획득함");
        } else if (eventrng == 4) {
            swapTamagochi.tamagochi.setLevel(returnTamagochi.level);
            swapTamagochi.playerLevel = returnTamagochi.level;
            swapTamagochi.update();

            playerTamagochi.tamagochi = returnTamagochi;
            playerTamagochi.update(false);

            swapButton.setEnabled(true);
            swapButton.setToolTipText(null);
        }
    }
}

class SwapTamagochi extends TamagochiButton{
    public int playerLevel = 0;

    public SwapTamagochi(Tamagochi tamagochi){
        super(tamagochi);
    }

    public String toString(){
        if(playerLevel == 0) {
            String str = "";
            str += this.tamagochi.name + "(Lv. ??) \n";
            switch (this.tamagochi.prop) {
                case Tamagochi.WATER:
                    str += "속성: 물 \n";
                    break;
                case Tamagochi.FIRE:
                    str += "속성: 불 \n";
                    break;
                case Tamagochi.GRASS:
                    str += "속성: 풀 \n";
            }
            str += "세부 스탯은 내 다마고치를 선택한 후에 나타납니다.";
            return str;
        }else{
            return tamagochi.toString();
        }
    }

    @Override
    public void toolTipUpdate() {
        setToolTipText(this.toString());
    }

    public void update(){
        setIcon(tamagochi.getScaledImage(geometry.width, geometry.height));
        toolTipUpdate();
    }
}