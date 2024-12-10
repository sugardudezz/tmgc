package scenes;

import scripts.Main;
import scripts.Storage;

import static scenes.FightScene.MyButton.buttons;

public class AttackThread extends Thread {
    public static final int PLAYER = 1;
    public static final int ENEMY = 2;
    private final int attacker;
    private final int delayBeforeNextScene = 700;
    public AttackThread(int attacker){
        this.attacker = attacker;
    }
    //모든 버튼 임시 비활성화 후 공격
    @Override
    public void run() {
        if(attacker == ENEMY){
            for (FightScene.MyButton button : buttons){
                button.setEnabled(false);
            }

            FightScene.enemy.attack(FightScene.player);
            new HitAnimation().start();

            if(FightScene.player.tamagochi.health <= 0){
                Main.round++;
                Storage.setDead(FightScene.player.tamagochi);

                //짐
                try {
                    sleep(delayBeforeNextScene);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                SceneManager.playScene(new LoseScene());
                return;
            }

            for (FightScene.MyButton button : buttons){
                // 교체할 다마고치가 없는 경우 교체 버튼을 활성화하면 안 되기 때문에 넣은 조건
                if (!button.getName().equals("substitute") || !FightScene.substitutions.isEmpty()) {
                    button.setEnabled(true);
                }
            }
        }else if(attacker == PLAYER){
            for (FightScene.MyButton button : buttons){
                button.setEnabled(false);
            }

            FightScene.player.attack(FightScene.enemy);
            new HitAnimation().start();

            if(FightScene.enemy.tamagochi.health <= 0){
                Main.round++;

                //이김
                try {
                    sleep(delayBeforeNextScene);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                SceneManager.playScene(new WinScene());
                return;
            }

            FightScene.enemy.attack(FightScene.player);
            new HitAnimation().start();

            if(FightScene.player.tamagochi.health <= 0){
                Main.round++;
                Storage.setDead(FightScene.player.tamagochi);

                //짐
                try {
                    sleep(delayBeforeNextScene);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                SceneManager.playScene(new LoseScene());
                return;
            }

            for (FightScene.MyButton button : buttons){
                // 교체할 다마고치가 없는 경우 교체 버튼을 활성화하면 안 되기 때문에 넣은 조건
                if (!button.getName().equals("substitute") || !FightScene.substitutions.isEmpty()) {
                    button.setEnabled(true);
                }
            }
        }
    }
}

class HitAnimation extends Thread {
    @Override
    public void run() {
        try {
            //애니메이션
            Thread.sleep(1800);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}