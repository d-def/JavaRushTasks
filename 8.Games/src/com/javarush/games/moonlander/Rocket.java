package com.javarush.games.moonlander;

public class Rocket extends GameObject {
    private double speedY;
    private double speedX;
    private double boost = 0.05;
    public Rocket(double x, double y) {
        super(x,y,ShapeMatrix.ROCKET);
    }

    public void move (){
        this.speedY=this.speedY+this.boost;
        this.y=this.y+this.speedY;
    }
}
