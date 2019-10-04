package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

import java.util.ArrayList;
import java.util.List;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH / 2;
    public static final int ROADSIDE_WIDTH = 14;
    private static final int RACE_GOAL_CARS_COUNT=40;

    private boolean isGameStopped;
    private int score;

    private FinishLine finishLine;
    private RoadMarking roadMarking;
    private PlayerCar player;
    private RoadManager roadManager;
    private ProgressBar progressBar;
    private List<Drawable> drawableList;

    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        showGrid(false);
        createGame();
        String a = "2";
        String b = "2";
        System.out.println(a + b);
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            super.setCellColor(x, y, color);
        }
    }

    private void createGame() {
        score = 3500;
        drawableList = new ArrayList<>();
        isGameStopped = false;
        progressBar=new ProgressBar(RACE_GOAL_CARS_COUNT);
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        roadManager = new RoadManager();
        finishLine = new FinishLine();
        drawableList.add(progressBar);
        drawableList.add(roadMarking);
        drawableList.add(player);
        drawableList.add(roadManager);
        drawableList.add(finishLine);
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
//        for (Drawable drawable : drawableList) {
//            drawable.draw(this);
//        }
        roadManager.draw(this);
        roadMarking.draw(this);
        player.draw(this);
        finishLine.draw(this);
        progressBar.draw(this);
    }

    private void drawField() {
        Color color;
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (x >= ROADSIDE_WIDTH && x < WIDTH - ROADSIDE_WIDTH) {
                    color = Color.DIMGRAY;
                } else {
                    color = Color.GREEN;
                }
                if (x == CENTER_X) {
                    color = Color.WHEAT;
                }
                setCellColor(x, y, color);
            }
        }
    }

    private void moveAll() {
        roadMarking.move(player.speed);
        roadManager.move(player.speed);
        finishLine.move(player.speed);
        player.move();
        progressBar.move(roadManager.getPassedCarsCount());
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case RIGHT:
                player.setDirection(Direction.RIGHT);
                break;
            case LEFT:
                player.setDirection(Direction.LEFT);
                break;
            case SPACE:
                if (isGameStopped) {
                    createGame();
                }
                break;
            case UP:
                player.speed = 2;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        if (player.getDirection() == Direction.RIGHT && key == Key.RIGHT) {
            player.setDirection(Direction.NONE);
        }
        if (player.getDirection() == Direction.LEFT && key == Key.LEFT) {
            player.setDirection(Direction.NONE);
        }
        if (key == Key.UP) {
            player.speed = 1;
        }
    }

    @Override
    public void onTurn(int step) {
        score=score-5;
        setScore(score);
        if (finishLine.isCrossed(player)) {
            win();
            drawScene();
            return;
        }
        if (roadManager.checkCrush(player)) {
            gameOver();
            drawScene();
            return;
        }
        if (roadManager.getPassedCarsCount()>=RACE_GOAL_CARS_COUNT) {
            finishLine.show();
        }
        moveAll();
        roadManager.generateNewRoadObjects(this);
        drawScene();
    }

    private void gameOver() {
        player.stop();
        isGameStopped = true;
        showMessageDialog(Color.BEIGE, "YOU LOSE!", Color.BLUE, 75);
        stopTurnTimer();
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.BEIGE, "YOU WIN!", Color.BLUE, 75);
        stopTurnTimer();
    }
}
