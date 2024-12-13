package scenes;

import scripts.Main;
import scripts.Storage;
import tamagochi.Tamagochi;
import utils.BackgroundImage;
import utils.Geometry;

import javax.swing.*;
import java.awt.*;

public class WinScene extends JPanel {
    String imagePath = "./src/images/";
    public WinScene(){
        setLayout(null);
        //랜덤이벤트 발생
        

        JButton button1 = new JButton("승리했다!");
        button1.setBounds(new Geometry(0.5f,0.5f,100,100));
        Geometry.setIcon(button1,imagePath+"YellowPanel.png");

        button1.setHorizontalTextPosition(SwingConstants.CENTER);  // 텍스트 중앙 정렬
        button1.setVerticalTextPosition(SwingConstants.CENTER);    // 텍스트 중앙 정렬


        add(button1);
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

}
