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


    @Override
    public void onTurn(int step) {
        rocket.move();
        drawScene();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x>=0 && x<WIDTH && y>=0 && y<HEIGHT){
            super.setCellColor(x, y, color);
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

    }
}
