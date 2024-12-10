package scenes;

import scripts.Storage;
import tamagochi.Tamagochi;
import utils.Geometry;

import javax.swing.*;

public class WinScene extends JPanel {
    public WinScene(){
        setLayout(null);
        //랜덤이벤트 발생
        JButton button1 = new JButton("테스트");
        button1.setBounds(new Geometry(0.5f,0.5f,100,100));
        add(button1);
    }
}
