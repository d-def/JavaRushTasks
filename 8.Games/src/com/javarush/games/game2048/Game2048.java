package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Game2048 extends Game {
    private static final int SIDE=4;
    private int[][] gameField = new int[SIDE][SIDE];
    private Map<Integer,Color> valueToColor = new HashMap<Integer, Color>();
    private int[] test = new int[4];

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        test();
        putColorsIntoMap();
        createGame();
        drawScene();
    }

    private void test(){
        test[0]=0;
        test[1]=0;
        test[2]=0;
        test[3]=0;
        compressRow(test);
    }

    private void createGame(){
        createNewNumber();
        createNewNumber();
    }

    private void drawScene(){
        for (int x=0;x<SIDE;x++){
            for (int y=0;y<SIDE;y++){
                setCellColoredNumber(x,y,gameField[y][x]);
            }
        }
    }

    private void createNewNumber(){
        int value = getRandomNumber(10);
        boolean isValueSet = false;
        do {
            int x=getRandomNumber(SIDE);
            int y=getRandomNumber(SIDE);
            if (gameField[y][x]==0) {
                isValueSet=true;
                if (value == 9) {
                    gameField[y][x] = 4;
                } else {
                    gameField[y][x] = 2;
                }
            }
        } while (!isValueSet);
    }

    private void putColorsIntoMap() {
        valueToColor.put(0, Color.LIGHTBLUE);
        valueToColor.put(2, Color.ANTIQUEWHITE);
        valueToColor.put(4, Color.AZURE);
        valueToColor.put(8, Color.BEIGE);
        valueToColor.put(16, Color.BLUE);
        valueToColor.put(32, Color.BROWN);
        valueToColor.put(64, Color.CORAL);
        valueToColor.put(128, Color.DARKCYAN);
        valueToColor.put(256, Color.FUCHSIA);
        valueToColor.put(512, Color.GREEN);
        valueToColor.put(1024, Color.RED);
        valueToColor.put(2048, Color.PINK);
    }

    private Color getColorByValue (int value){
        return valueToColor.get(value);
    }

    private void setCellColoredNumber (int x, int y, int value){
        Color color = getColorByValue(value);
        setCellValueEx(x, y, color, value==0?"":Integer.toString(value));
    }

    private boolean compressRow(int[] row) {
        int[] tempRow = new int[4];
        int currentCell = 0;
        boolean wasChanged = false;
        for (int i = 0; i < 4; i++) {
            if (row[i] != 0) {
                tempRow[currentCell] = row[i];
                currentCell++;
            }
        }
        if (!Arrays.equals(tempRow, row)) {
            wasChanged = true;
        }

        for (int i = 0; i < 4; i++) {
            row[i] = tempRow[i];
        }

        return wasChanged;
    }

//    private boolean mergeRow (int[] row){
////        for (int i=0;i<4;i++){
////
////        }
//    }
}
