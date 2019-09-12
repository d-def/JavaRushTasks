package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public final static int HEIGHT=15;
    public final static int WIDTH=15;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        startGame();
    }

    private void startGame(){
        drawScene();
    }
    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellColor(x, y, Color.DARKGREEN);
            }
        }
    }

}