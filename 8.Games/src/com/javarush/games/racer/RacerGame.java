package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH/2;
    public static final int ROADSIDE_WIDTH = 14;

    private RoadMarking roadMarking;
    private PlayerCar player;
    private RoadManager roadManager;

    public void initialize() {
        setScreenSize(WIDTH,HEIGHT);
        showGrid(false);
        createGame();
        String a = "2";
        String b = "2";
        System.out.println(a+b);
    }
    
    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x>=0 && x<WIDTH && y>=0 && y<HEIGHT){
            super.setCellColor(x, y, color);
        }
    }

    private void createGame() {
        roadMarking=new RoadMarking();
        player=new PlayerCar();
        roadManager=new RoadManager();
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene(){
        drawField();
        roadManager.draw(this);
        roadMarking.draw(this);
        player.draw(this);
    }

    private void drawField(){
        Color color;
        for (int x=0;x<WIDTH;x++){
            for (int y = 0; y < HEIGHT; y++) {
                if (x>=ROADSIDE_WIDTH && x<WIDTH-ROADSIDE_WIDTH) {
                    color=Color.DIMGRAY;
                } else {
                    color = Color.GREEN;
                }
                if (x==CENTER_X) {color=Color.WHEAT;}
                setCellColor(x,y,color);
            }
        }
    }

    private void moveAll() {
        roadMarking.move(player.speed);
        player.move();

    }

    @Override
    public void onKeyPress(Key key) {
        switch(key){
            case RIGHT: player.setDirection(Direction.RIGHT);
            break;
            case LEFT:player.setDirection(Direction.LEFT);
            break;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        if (player.getDirection()==Direction.RIGHT && key==Key.RIGHT) {
            player.setDirection(Direction.NONE);
        }
        if (player.getDirection()==Direction.LEFT && key==Key.LEFT) {
            player.setDirection(Direction.NONE);
        }
    }

    @Override
    public void onTurn(int step) {
        moveAll();
        drawScene();
    }
}
