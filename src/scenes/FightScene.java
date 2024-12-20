package scenes;

import scripts.Main;
import scripts.Storage;
import tamagochi.Tamagochi;
import tamagochi.TamagochiButton;
import utils.BackgroundImage;
import utils.Geometry;
import utils.HPBar;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FightScene extends JPanel {
    static TamagochiFighter player;
    static TamagochiFighter enemy;
    static ArrayList<TamagochiFighter> substitutions = new ArrayList<>();
    public static int phase = 0;

    String imagePath = "./src/images/";

    public FightScene(){
        setLayout(null);

        player = new TamagochiFighter(Storage.fighters.getFirst());
        player.setBounds(new Geometry(0.21f, 0.56f, 0.16f));
        player.update(false);
        player.addActionListener(e -> MyButton.setSubstitution(false));
        add(player);
        add(player.hpBar);

        enemy = new TamagochiFighter(Tamagochi.getRandom());
        enemy.setBounds(new Geometry(0.79f, 0.33f, 0.16f));
        enemy.setToolTipText(enemy.tamagochi.toString(true));
        enemy.update(true);
        add(enemy);
        add(enemy.hpBar);

        //공격 버튼
        MyButton attack = new MyButton("공격");
        attack.setBounds(new Geometry(0.21f,0.79f,0.2f,0.1f));
        Geometry.setIcon(attack, imagePath + "AttackPanel.png");

        attack.setForeground(Color.WHITE);  // 텍스트 색상을 흰색으로 설정
        attack.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트 중앙 정렬
        attack.setVerticalTextPosition(SwingConstants.CENTER);    // 텍스트 중앙 정렬

        Font font = new Font("woodPanel", Font.BOLD, 30);
        attack.setFont(font);

        attack.addActionListener(e -> {
            AttackThread attackThread = new AttackThread(AttackThread.PLAYER);
            attackThread.start();
        });
        attack.setContentAreaFilled(false);
        add(attack);

        //교체 버튼
        MyButton substitute = new MyButton("교체");
        substitute.setBounds(new Geometry(0.5f,0.79f,0.2f,0.1f));
        Geometry.setIcon(substitute, imagePath + "SwitchPanel.png");

        substitute.setForeground(Color.WHITE);  // 텍스트 색상을 흰색으로 설정
        substitute.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트 중앙 정렬
        substitute.setVerticalTextPosition(SwingConstants.CENTER);    // 텍스트 중앙 정렬
        substitute.setFont(font);

        substitute.addActionListener(e -> MyButton.setSubstitution(true));
        substitute.setName("substitute");
        substitute.setContentAreaFilled(false);
        add(substitute);

        switch(Storage.fighters.size()) {
            case 3:
                TamagochiFighter subs2 = new TamagochiFighter(Storage.fighters.get(2));
                subs2.setBounds(new Geometry(0.6f, 0.75f, 0.13f));
                subs2.setVisible(false);
                substitutions.add(subs2);
            case 2:
                TamagochiFighter subs1 = new TamagochiFighter(Storage.fighters.get(1));
                subs1.setBounds(new Geometry(0.41f, 0.75f, 0.13f));
                subs1.setVisible(false);
                substitutions.add(subs1);
                break;
            case 1:
                substitute.setEnabled(false);
                substitute.setToolTipText("교체할 다마고치가 없습니다.");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + Storage.fighters.size());
        }

        for(TamagochiFighter substitution : substitutions){
            substitution.addActionListener(e -> {
                player.changeFighter((TamagochiFighter) e.getSource());
                MyButton.setSubstitution(false);
            });
            substitution.setContentAreaFilled(false);
            add(substitution);
        }

        //도주 버튼
        MyButton flee = new MyButton("도주");
        flee.setBounds(new Geometry(0.79f,0.79f,0.2f,0.1f));
        Geometry.setIcon(flee, imagePath + "RunPanel.png");

        flee.setForeground(Color.WHITE);  // 텍스트 색상을 흰색으로 설정
        flee.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트 중앙 정렬
        flee.setVerticalTextPosition(SwingConstants.CENTER);    // 텍스트 중앙 정렬
        flee.setFont(font);

        flee.addActionListener(e -> {
            Main.round++;
            SceneManager.playScene(new RoundScene());
        });
        flee.setContentAreaFilled(false);

        if(Main.round == 30){
            flee.setEnabled(false);
            flee.setToolTipText("마지막 라운드는 스킵할 수 없습니다.");
        }

        add(flee);

        //라운드
        JLabel label = new JLabel("<html><h1>ROUND "+ Main.getRoundString() +"</h1></html>",SwingConstants.CENTER);
        label.setBounds(new Geometry(0.5f,0.1f,0.5f,0.2f));
        add(label);


        //적 선공일 경우
        if(enemy.tamagochi.isFaster(player.tamagochi)){
            AttackThread attackThread = new AttackThread(AttackThread.ENEMY);
            attackThread.start();
        }

        CheckRound();
    }

    public void CheckRound() {
        if ( Main.round >= 0 && Main.round <= 10 ) {
            BackgroundImage bg = new BackgroundImage("./src/images/FightScene1.png"); add(bg);
        }
        else if ( Main.round >= 11 && Main.round <= 20 ) {
            BackgroundImage bg = new BackgroundImage("./src/images/FightScene2.png"); add(bg);
        }

        else {
            BackgroundImage bg = new BackgroundImage( "./src/images/FightScene3.png"); add(bg);
        }
    }

    class MyButton extends JButton{
        static ArrayList<MyButton> buttons = new ArrayList<>();
        public MyButton(String text){
            super(text);
            buttons.add(this);
        }
        public static void setSubstitution(boolean setTrue){
            for (MyButton button : buttons) {
                button.setVisible(!setTrue);
            }
            for (TamagochiFighter fighter : substitutions){
                fighter.setVisible(setTrue);
            }
        }

        @Override
        public String getName() {
            String og = super.getName();
            if(og == null){
                return "NoName";
            }
            return og;
        }
    }

    class TamagochiFighter extends TamagochiButton {
        HPBar hpBar;

        public TamagochiFighter(Tamagochi tamagochi){
            super(tamagochi);
            this.hpBar = new HPBar(this);
        }

        public void changeFighter(TamagochiFighter fighter){
            Tamagochi temp = this.tamagochi;
            this.tamagochi = fighter.tamagochi;
            fighter.tamagochi = temp;
            this.setBounds(this.geometry);
            fighter.setBounds(fighter.geometry);
            this.toolTipUpdate();
            hpBar.update();
        }

        public void update(boolean isEnemy){
            super.update(isEnemy);
            hpBar.update();
        }

        public void attack(TamagochiFighter opponent){
            this.tamagochi.attack(opponent.tamagochi);
            boolean isEnemy = this.equals(enemy);
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            opponent.toolTipUpdate(!isEnemy);
            opponent.hpBar.attacked();
        }

    }
}