package com.ubante.bullet.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J on 4/7/14.
 */
public class ShooterList {
    List<Shooter> shooters = new ArrayList<Shooter>();
    String name;
    List<Photo> bulletPhotos;
    int countdownDuration = 5; // seconds

    public ShooterList(String name) { this.name = name; }

    public ShooterList() { this("defaultshooterlistname"); }

    void join(Shooter s) {
        shooters.add(s);
    }

    void leave(Shooter s) {
        shooters.remove(s);
    }

    List<Shooter> getShooters() { return shooters; }

    public StringBuffer getShooterStringList() {
        StringBuffer list = new StringBuffer();

        for (Shooter s : getShooters()) {
            list.append(s.humanName+"\n");
        }

        return list;
    }

    /**
     * Only the master shooter can initiate this.
     */
    void shootBulletPhoto() {
        // Need the countdown here.

        // Do ping to make sure shooters are still available

        List<Photo> photoList = new ArrayList<Photo>();
        for (Shooter s : getShooters()) {
            s.shoot();
            Photo p = s.getLastPhoto();
            photoList.add(p);
        }
        bulletPhotos = photoList;
    }

    void printBulletPhotos() {
        for (Photo p : bulletPhotos) {
            p.print();
        }
    }

    public int size() {
        return shooters.size();
    }

    void printStatus() {
        System.out.println("ShooterList: "+name);
        System.out.printf("Size: %d\n",size());
        System.out.printf("Members: ");
        for (Shooter s : shooters) {
            System.out.printf("%s ",s.humanName);
        }
        System.out.println();

    }

    /**
     * Test main.
     * @param args
     */
    public static void main(String[] args) {
        ShooterList pl = new ShooterList();

        Shooter annie = new Shooter();

    }
}
