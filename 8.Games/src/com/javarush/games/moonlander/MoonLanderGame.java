package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;

    private boolean isUpPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;

    private Rocket rocket;
    private GameObject landscape;
    private GameObject platform;


    @Override
    public void onTurn(int step) {
        rocket.move(isUpPressed, isLeftPressed,isRightPressed );
        check();
        drawScene();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x>=0 && x<WIDTH && y>=0 && y<HEIGHT){
            super.setCellColor(x, y, color);
        }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case LEFT:
                isLeftPressed=true;
                isRightPressed=false;
                break;
            case UP:
                isUpPressed=true;
                break;
            case RIGHT:
                isRightPressed=true;
                isLeftPressed=false;
                break;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case LEFT:
                isLeftPressed=false;
                break;
            case RIGHT:
                isRightPressed=false;
                break;
            case UP:
                isUpPressed=false;
                break;
        }
    }

    public void initialize() {
        setScreenSize(WIDTH,HEIGHT);
        showGrid(false);
        createGame();
    }

    private void drawScene(){
        for (int i=0;i<WIDTH;i++){
            for (int j=0;j<HEIGHT;j++){
                setCellColor(i,j,Color.ORANGE);
            }
        }
        rocket.draw(this);
        landscape.draw(this);
    }

    private void createGame(){
        isLeftPressed=false;
        isRightPressed=false;
        isUpPressed=false;
        setTurnTimer(50);
        createGameObjects();
        drawScene();
    }

    private void createGameObjects() {
        rocket=new Rocket(WIDTH/2,0);
        landscape=new GameObject(0,25,ShapeMatrix.LANDSCAPE);
        platform =new GameObject( 23, MoonLanderGame.HEIGHT - 1, ShapeMatrix.PLATFORM);
    }

    private void check(){
        if (rocket.isCollision(landscape)){
            gameOver();
        } else {
            if (rocket.isCollision(platform)) {
                if (rocket.isStopped()) {
                    win();
                } else {
                    gameOver();
                }
            }
        }
    }

    private void gameOver() {}
    private void win(){}
}
