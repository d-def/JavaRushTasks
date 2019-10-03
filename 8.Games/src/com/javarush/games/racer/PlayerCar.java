package com.javarush.games.racer;


import com.javarush.games.racer.road.RoadManager;

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
        if (x < RoadManager.LEFT_BORDER) x = RoadManager.LEFT_BORDER;
        if (x > RoadManager.RIGHT_BORDER - width) x = RoadManager.RIGHT_BORDER - width;
        switch (direction){
            case LEFT:
                x = x - 1;
                break;
            case RIGHT:
                x = x + 1;
                break;
        }
    }

//    7. Метод move() класса PlayerCar должен устанавливать значение полю x, равное RoadManager.LEFT_BORDER, если x < RoadManager.LEFT_BORDER.
//8. Метод move() класса PlayerCar должен устанавливать значение полю x, равное RoadManager.RIGHT_BORDER - width, если x > RoadManager.RIGHT_BORDER - width.
}
