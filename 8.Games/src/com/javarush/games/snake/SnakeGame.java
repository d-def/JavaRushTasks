package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public final static int HEIGHT = 15;
    public final static int WIDTH = 15;
    private Snake snake;
    private int turnDelay;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn (int step)
    {
        snake.move();
        drawScene();
    }

    private void createGame() {
        snake = new Snake(WIDTH/2,HEIGHT/2);
        turnDelay=300;
        setTurnTimer(turnDelay);
        drawScene();
    }

    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellColor(x, y, Color.DARKGREEN);
            }
        }
        snake.draw(this);
    }

}
