package com.javarush.games.moonlander;

public class Rocket extends GameObject {
    private double speedY;
    private double speedX = 0;
    private double boost = 0.05;

    public Rocket(double x, double y) {
        super(x, y, ShapeMatrix.ROCKET);
    }

    public void move(boolean isUpPressed, boolean isLeftPressed, boolean isRightpressed) {
        if (isUpPressed) {
            speedY = speedY - boost;
        } else {
            speedY = speedY + boost;
        }
        if (isLeftPressed) {
            speedX = speedX - boost;
            x=x+speedX;
        }
        if (isRightpressed) {
            speedX = speedX + boost;
            x=x+speedX;
        }
        y = y + speedY;
    }
}
