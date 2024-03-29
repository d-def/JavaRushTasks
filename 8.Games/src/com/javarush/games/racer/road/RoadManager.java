package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.Drawable;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoadManager implements Drawable{
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private static final int PLAYER_CAR_DISTANCE = 12;

    private List<RoadObject> items = new ArrayList<>();

    private int passedCarsCount=0;

    public int getPassedCarsCount() {
        return passedCarsCount;
    }

    private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
        switch (type) {
            case THORN:
                return new Thorn(x, y);
            case DRUNK_CAR:
                return new MovingCar(x, y);
        }
        return new Car(type, x, y);
    }

    //createRoadObject(RoadObjectType, int, int)
    private void addRoadObject(RoadObjectType type, Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject temp = createRoadObject(type, x, y);
        if (isRoadSpaceFree(temp)) {
            items.add(temp);
        }
    }

    public void draw(Game game) {
        for (RoadObject item : items) {
            item.draw(game);
        }
    }

    public void move(int boost) {
        for (RoadObject item : items) {
            item.move(boost + item.speed, items);
        }
        deletePassedItems();
    }

    private boolean isThornExists() {
        for (RoadObject item : items) {
            if (item.type == RoadObjectType.THORN) {
                return true;
            }
        }
        return false;
    }

    private void generateThorn(Game game) {
        int rand = game.getRandomNumber(100);
        if (!isThornExists() && rand < 10) {
            addRoadObject(RoadObjectType.THORN, game);
        }
    }

    private void generateRegularCar (Game game) {
        int carTypeNumber = game.getRandomNumber(4);
        int rand = game.getRandomNumber(100);
        if (rand < 30) {
            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
        }

    }

    public void generateNewRoadObjects(Game game) {
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }

    private void deletePassedItems() {
        Iterator<RoadObject> iterator = items.iterator();
        //можно через лямбды, но я типа так не умею еще
        while (iterator.hasNext()) {
            RoadObject object = iterator.next();
            if (object.y >= RacerGame.HEIGHT) {
                if (object.type!= RoadObjectType.THORN) {
                    passedCarsCount++;
                }
                iterator.remove();
            }
        }
    }

    public boolean checkCrush(PlayerCar car) {
        for (RoadObject item : items) {
            if (item.isCollision(car)) {
                return true;
            }
        }
        return false;
    }

    private boolean isRoadSpaceFree (RoadObject object) {
        for (RoadObject item : items) {
            if (item.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE)) {
                return false;
            }
        }
        return true;
    }

    private boolean isMovingCarExists () {
        for (RoadObject item : items) {
            if (RoadObjectType.DRUNK_CAR.equals(item.type)) {
                return true;
            }
        }
        return false;
    }


    private void generateMovingCar (Game game) {
        int rand = game.getRandomNumber(100);
        if (rand < 10 && !isMovingCarExists()) {
            addRoadObject(RoadObjectType.DRUNK_CAR, game);
        }

    }
}
