package utils;

import scenes.SceneManager;
import tamagochi.Tamagochi;
import tamagochi.TamagochiButton;

import javax.swing.*;
import java.awt.*;

public class HPBar extends JProgressBar {
    public TamagochiButton tamagochiButton;
    static final Dimension size = new Dimension(160,20);

    public HPBar(TamagochiButton tamagochiButton){
        super(0, tamagochiButton.tamagochi.maxHealth);
        this.tamagochiButton = tamagochiButton;
        Tamagochi tamagochi = tamagochiButton.tamagochi;

        setBounds(tamagochiButton.getX(),tamagochiButton.getY()-30,size.width,size.height);
        setValue(tamagochi.health);
        setString(tamagochi.health + "/" + tamagochi.maxHealth);
        setStringPainted(true);
        setForeground(Color.RED);
    }

    public void update(){
        Tamagochi tamagochi = tamagochiButton.tamagochi;
        boolean positionDownward = (tamagochiButton.getY() + tamagochiButton.getHeight()/2) < (SceneManager.resolution.height/2);
        if(positionDownward) {
            setBounds(tamagochiButton.getX() + tamagochiButton.getWidth() / 2 - size.width / 2,
                    tamagochiButton.getY() + tamagochiButton.getHeight() + 30 - size.height,
                    size.width,
                    size.height);
        }else{
            setBounds(tamagochiButton.getX() + tamagochiButton.getWidth() / 2 - size.width / 2, tamagochiButton.getY() - 30, size.width, size.height);
        }

        setMaximum(tamagochi.maxHealth);
        setValue(tamagochi.health);
    }

    public void attacked(){
        Tamagochi tamagochi = tamagochiButton.tamagochi;
        while(getValue() != tamagochi.health && getValue() > 0){
            setValue(getValue() - Math.max( Math.abs(getValue()-tamagochi.health)/2 , 1 ));
            setValue(Math.max(0,getValue()));
            setString(getValue() + "/" + tamagochi.maxHealth);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}