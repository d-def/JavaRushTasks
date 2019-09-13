package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public final static int HEIGHT = 14;
    public final static int WIDTH = 14;
    private final static int GOAL = 28;

    private Snake snake;
    private Apple apple;
    private int turnDelay;
    private boolean isGameStopped;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn (int step)
    {
        if (snake.getLength()>GOAL){win();}
        if (!snake.isAlive){gameOver();}
        snake.move(apple);
        if (!apple.isAlive) {
            score=score+5;
            turnDelay=turnDelay-10;
            setScore(score);
            setTurnTimer(turnDelay);
            createNewApple();
        }
        drawScene();
    }

    @Override
    public void onKeyPress(Key key){
        //в идеале нужно бы библиотечку на проверку енамов
        if (key.SPACE.equals(key) && isGameStopped) {this.createGame();}
        if (key.LEFT.equals(key)) {snake.setDirection(Direction.LEFT);}
        if (key.UP.equals(key)) {snake.setDirection(Direction.UP);}
        if (key.DOWN.equals(key)) {snake.setDirection(Direction.DOWN);}
        if (key.RIGHT.equals(key)) {snake.setDirection(Direction.RIGHT);}
    }

    private void createGame() {
        isGameStopped=false;
        score=0;
        setScore(score);
        snake = new Snake(WIDTH/2,HEIGHT/2);
        createNewApple();
        turnDelay=300;
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
        apple.draw (this);
    }

    private void createNewApple () {
        do {
            apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        } while (snake.checkCollision(apple));
    }

    private void gameOver() {
        stopTurnTimer();
        isGameStopped=true;
        showMessageDialog(Color.ALICEBLUE, "YOU LOSE! GOOD DAY SIR!", Color.BLACK, 75);
    }

    private void win() {
        stopTurnTimer();
        isGameStopped=true;
        showMessageDialog(Color.ALICEBLUE, "WELL PLAYED!", Color.BLACK, 75);
    }

}
