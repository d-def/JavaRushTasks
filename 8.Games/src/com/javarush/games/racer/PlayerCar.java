package com.javarush.games.racer;


public class PlayerCar extends GameObject {
    private static int playerCarHeight = ShapeMatrix.PLAYER.length;
    public int speed = 1;
    private Direction direction=Direction.NONE;

    public PlayerCar() {
        super(RacerGame.WIDTH / 2 + 2, RacerGame.HEIGHT - playerCarHeight - 1, ShapeMatrix.PLAYER);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void move() {
        switch (direction){
            case LEFT:
                x=x-1;
                break;
            case RIGHT:
                x=x+1;
                break;
        }
    }
}
