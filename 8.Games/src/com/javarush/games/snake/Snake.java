package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    private List<GameObject> snakeParts;
    public Snake (int x, int y)
    {
        snakeParts=new ArrayList<>();
        snakeParts.add(new GameObject(x,y));
        snakeParts.add(new GameObject(x+1,y));
        snakeParts.add(new GameObject(x+2,y));
    }
    public void draw(Game game){
        GameObject snakeHead = snakeParts.get(0);
        game.setCellValue(snakeHead.x,snakeHead.y,HEAD_SIGN);
        for (int i=1; i<snakeParts.size(); i++) {
            GameObject snakeBodyPart = snakeParts.get(i);
            game.setCellValue(snakeBodyPart.x,snakeBodyPart.y,BODY_SIGN);
        }
    }

    private void drawOneUnit (Game game, int x, int y, String sign){
        game.setCellValue(x,y,sign);
    }
}
