package tamagochi;

import scenes.RoundScene;
import scenes.SceneManager;
import scripts.Main;
import utils.Geometry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TamagochiButton extends JButton implements ActionListener{
    public Tamagochi tamagochi;
    public Geometry geometry;
    public TamagochiButton(Tamagochi tamagochi){
        super();
        this.tamagochi = tamagochi;
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setToolTipText(this.tamagochi.toString());
        addActionListener(this);
    }

    public void setBounds(Geometry geometry){
        super.setBounds(geometry);
        setIcon(tamagochi.getScaledImage(geometry.width, geometry.height));
        this.geometry = geometry;
    }

    public void actionPerformed(ActionEvent e){

    }

    public void toolTipUpdate(){
        setToolTipText(tamagochi.toString());
    }

    public void toolTipUpdate(boolean isEnemy){
        setToolTipText(tamagochi.toString(isEnemy));
    }

    public void update(boolean isEnemy){
        setIcon(tamagochi.getScaledImage(geometry.width, geometry.height));
        toolTipUpdate(isEnemy);
    }
}
