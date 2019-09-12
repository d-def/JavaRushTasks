package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    private List<GameObject> snakeParts;
    public boolean isAlive = true;
    private Direction direction;

    public Snake (int x, int y)
    {
        snakeParts=new ArrayList<>();
        snakeParts.add(new GameObject(x,y));
        snakeParts.add(new GameObject(x+1,y));
        snakeParts.add(new GameObject(x+2,y));
        direction = Direction.LEFT;
    }

    public void setDirection(Direction direction) {
        switch (direction){
            case RIGHT:
                if (this.direction == Direction.LEFT) return;
                this.direction = direction;
                break;
            case LEFT:
                if (this.direction == Direction.RIGHT) return;
                this.direction = direction;
                break;
            case UP:
                if (this.direction == Direction.DOWN) return;
                this.direction = direction;
                break;
            case DOWN:
                if (this.direction == Direction.UP) return;
                this.direction = direction;
        }
    }

    public void draw(Game game){
        GameObject snakeHead = snakeParts.get(0);
        Color color;
        if (isAlive){
            color=Color.BLUE;
        } else
        {
            color = Color.RED;
        }
        game.setCellValueEx(snakeHead.x, snakeHead.y, Color.NONE,HEAD_SIGN, color, 75);
        for (int i=1; i<snakeParts.size(); i++) {
            GameObject snakeBodyPart = snakeParts.get(i);
            game.setCellValueEx(snakeBodyPart.x, snakeBodyPart.y, Color.NONE, BODY_SIGN, color, 75);
        }
    }

    public void move()
    {
        GameObject newHead = createNewHead();
        if (newHead.x<0 || newHead.x >=SnakeGame.WIDTH || newHead.y <0 || newHead.y >= SnakeGame.HEIGHT) {
            isAlive = false;
        } else {
            snakeParts.add(0, newHead);
            removeTail();
        }
    }


    public GameObject createNewHead() {
        int deltax=0;
        int deltay=0;
        if (direction==Direction.DOWN) { deltay=1;}
        if (direction==Direction.LEFT) { deltax=-1;}
        if (direction==Direction.RIGHT) { deltax=1;}
        if (direction==Direction.UP) { deltay=-1;}
        GameObject snakeHead = snakeParts.get(0);
        return new GameObject(snakeHead.x+deltax,snakeHead.y+deltay );
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size()-1);
    }
}
