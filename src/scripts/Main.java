package scripts;

import scenes.*;
import tamagochi.Tamagochi;

import javax.swing.*;
import java.awt.*;

public class Main {


    public static int round = 0;

    //returns round number as string
    public static String getRoundString() {
        if (round < 10) {
            return "0" + round;
        } else {
            return "" + round;
        }
    }

    public static void main(String[] args) {
        if(args[0].equals("debug")){
            debug();
        }else{
            startGame();
        }
    }

    private static void startGame() {
        SceneManager.resolution = new Dimension(1024, 576);
        JFrame frame = new JFrame("다마고치 브리더");
        frame.setSize(SceneManager.resolution);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        SceneManager.frame = frame;
        SceneManager.playScene(new MainMenu());
    }

    public static void debug() {
        SceneManager.resolution = new Dimension(1024, 576);
        JFrame frame = new JFrame("다마고치 브리더");
        frame.setSize(SceneManager.resolution);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        SceneManager.frame = frame;

        Storage.add(Tamagochi.getRandom());
        Storage.add(Tamagochi.getRandom());
        Storage.add(Tamagochi.getRandom());

        SceneManager.playScene(new FightScene());
    }
}