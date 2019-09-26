package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH/2;
    public static final int ROADSIDE_WIDTH = 14;

    private RoadMarking roadMarking;

    public void initialize() {
        setScreenSize(WIDTH,HEIGHT);
        showGrid(false);
        createGame();
    }
    
    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x>=0 && x<WIDTH && y>=0 && y<HEIGHT){
            super.setCellColor(x, y, color);
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        super.onKeyReleased(key);
    }

    private void createGame() {
        roadMarking=new RoadMarking();
        drawScene();
    }

    private void drawScene(){
        drawField();
        roadMarking.draw(this);
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
}
