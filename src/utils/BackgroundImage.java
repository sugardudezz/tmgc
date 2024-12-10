package utils;

import scenes.SceneManager;

import javax.swing.*;

public class BackgroundImage extends JLabel {
    public BackgroundImage(String imagePath){
        super();
        setBounds(new Geometry(0.5f,0.5f,1f,1f));
        setIcon(Geometry.getScaledImage(imagePath, SceneManager.resolution.width,SceneManager.resolution.height));
    }
}
