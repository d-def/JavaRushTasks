package com.javarush.games.minesweeper;
import com.javarush.engine.cell.*;

public class MinesweeperGame extends Game {

    private static final int SIDE=9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField=0;

    @Override
    public void initialize() {
        setScreenSize(SIDE,SIDE);
        createGame();
    }

    private void createGame(){
        boolean isMine;
            for (int x=0; x < SIDE; x++)
            {
                for (int y=0; y < SIDE; y++){
                    isMine=false;
                    if (getRandomNumber(10)==1)
                    {
                        countMinesOnField++;
                        isMine=true;
                    }
                    gameField[x][y]=new GameObject(y,x,isMine);//мать моя хардкод
                    setCellColor(x, y, Color.CORAL);
                }
            }
        }
}
