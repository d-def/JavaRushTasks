package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class Apple extends GameObject {
    private final static String APPLE_SIGN = "\uD83C\uDF4E";

    public void draw(Game game) {
        game.setCellValueEx(this.x, this.y, Color.NONE, APPLE_SIGN, Color.RED, 75);
    }

    public Apple(int x, int y) {
        super(x,y);
    }
}
