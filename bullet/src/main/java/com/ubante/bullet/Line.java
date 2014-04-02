package com.ubante.bullet;

/**
 * Created by J on 4/2/14.
 */
public class Line {
    Point a,b;
    float slope, length, degrees;

    Line(Point a, Point b) {
        this.a = a;
        this.b = b;
        slope = (b.getY()-a.getY()) / (b.getX()-a.getX());
        slope = slope * -1; // The y-axis is reversed

        degrees = (float) ( Math.atan( (double) slope) * 180 / Math.PI ); // radians to degrees

        double x = Math.pow((a.getX() - b.getX()),2);
        double y = Math.pow((a.getY() - b.getY()),2);
        double z = Math.sqrt(x + y);
        length = (float) z;
    }

    float getSlope() { return slope; }

    public String getStats() {
// Slope: float  Length: units  Degrees: degrees
        return String.format("Slope: %-5.2f  Length: %-5.2f  Degrees: %-5.2f\n",
                slope,length,degrees);
    }
}
