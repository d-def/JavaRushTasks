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

    @Override
    public void onKeyPress(Key key){
        //в идеале нужно бы библиотечку на проверку енамов
        if (key.LEFT.equals(key)) {snake.setDirection(Direction.LEFT);}
        if (key.UP.equals(key)) {snake.setDirection(Direction.UP);}
        if (key.DOWN.equals(key)) {snake.setDirection(Direction.DOWN);}
        if (key.RIGHT.equals(key)) {snake.setDirection(Direction.RIGHT);}
    }

    private void createGame() {
        snake = new Snake(WIDTH/2,HEIGHT/2);
        turnDelay=1000;
        setTurnTimer(turnDelay);
        drawScene();
    }

    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.DARKGREEN, "");
            }
        }
        snake.draw(this);
    }

}
