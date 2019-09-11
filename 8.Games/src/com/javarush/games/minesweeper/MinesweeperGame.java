package com.javarush.games.minesweeper;
import com.javarush.engine.cell.*;

public class MinesweeperGame extends Game {

    private static final int SIDE=9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField=0;
    private final static String MINE = "*";
    private final static String FLAG = "\uD83D\uDEA9";
    private int countFlags = 0;

    @Override
    public void initialize() {
        setScreenSize(SIDE,SIDE);
        createGame();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        openTile(x,y);
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
        countMineNeighbors();
        countFlags=countMinesOnField;
        }

    private void countMineNeighbors() {
        for (GameObject[] gArray : gameField){
            for (GameObject g : gArray) {
                for (GameObject possibleMine : getNeighbors(g)){
                    if (possibleMine != null && possibleMine.isMine && !g.isMine) {
                        g.countMineNeighbors++;
                    }
                }
            }
        }
    }
    //знаю что костыли, надо в этом методе и подсчитывать всё
    private GameObject[] getNeighbors(GameObject initialObject){
        int i=0;
        int newObjectX;
        int newObjectY;
        GameObject[] neighbors = new GameObject[8];
        for (int deltaX=-1;deltaX<=1;deltaX++){
            for (int deltaY=-1;deltaY<=1;deltaY++){
                newObjectX = initialObject.x + deltaX;
                newObjectY= initialObject.y + deltaY;
                if ((newObjectX >= 0 && newObjectX <= SIDE-1 && newObjectY >= 0 && newObjectY <= SIDE-1) && !(deltaX==0 && deltaY==0)) {
                    GameObject g= gameField[newObjectY][newObjectX];
                    if (g != null) {
                        neighbors[i++]=g;
                    }
                }
            }
        }
        return neighbors;
    }

    //onclick
    private void openTile (int x, int y) {
        GameObject tile = gameField[y][x];
        if (!tile.isOpen) {
            tile.isOpen=true;
            if (tile.isMine) {
                setCellValue(x, y, MINE);
                setCellColor(x, y, Color.RED);
            } else if (tile.countMineNeighbors !=0) {
                setCellNumber(x, y, tile.countMineNeighbors);
                setCellColor(x, y, Color.BLUE);
            } else {
                setCellColor(x,y,Color.GREEN);
                setCellValue(x, y, "");
                for (GameObject neighbor : getNeighbors(tile)) {
                    if (neighbor != null) {
                        openTile(neighbor.x, neighbor.y);
                    }
                }
            }
        }
    }
}
