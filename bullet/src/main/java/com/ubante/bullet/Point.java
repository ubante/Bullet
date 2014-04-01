package com.ubante.bullet;

/**
 * Created by J on 4/1/14.
 */
public class Point {
    float x,y;

    Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() { return x; }

    public float getY() { return y; }

    @Override
    public String toString() { return x + ", " + y; }


}
