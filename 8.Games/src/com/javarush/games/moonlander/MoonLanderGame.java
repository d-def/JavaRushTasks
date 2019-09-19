package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;


    public void initialize() {
        setScreenSize(WIDTH,HEIGHT);
        createGame();
    }

    private void drawScene(){
        for (int i=0;i<WIDTH;i++){
            for (int j=0;j<HEIGHT;j++){
                setCellColor(i,j,Color.ORANGE);
            }
        }
    }

    private void createGame(){
        drawScene();
    }
}
