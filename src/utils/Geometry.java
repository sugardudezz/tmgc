package utils;

import javax.swing.*;
import java.awt.*;

import static scenes.SceneManager.resolution;

//위치와 크기를 편하게 나타낼 수 있는 유틸리티. setBounds의 전달인자로 사용한다.
public class Geometry extends Rectangle {
    //왼쪽 위 꼭짓점이 아닌 중심의 위치를 받는 사각형
    public Geometry(int centerX, int centerY, int width, int height){
        super(centerX-width/2, centerY-height/2, width, height);
    }

    //위치를 비율로 받는 사각형. 0.5, 0.5는 정가운데가 된다.
    public Geometry(float centerX, float centerY, int width, int height){
        this((int)(centerX*(float) resolution.width), (int)(centerY*(float) resolution.height), width, height);
    }

    //크기도 비율로 받는 사각형
    public Geometry(float centerX, float centerY, float width, float height){
        this((int)(centerX*(float) resolution.width),
                (int)(centerY*(float) resolution.height),
                (int)(width*(float) resolution.width),
                (int)(height*(float) resolution.height));
    }

    //가로 길이만 받는 정사각형
    public Geometry(float centerX, float centerY, float width){
        this((int)(centerX*(float) resolution.width),
                (int)(centerY*(float) resolution.height),
                (int)(width*(float) resolution.width),
                (int)(width*(float) resolution.width));
    }
}
