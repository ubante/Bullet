package com.ubante.bullet.model;

/**
 * Created by J on 4/7/14.
 */
public class MasterShooter extends Shooter {

    MasterShooter(String name) {
        this.humanName = name;
        this.device = new Device();
    }

    MasterShooter() {
        this("defaultmastershootername");
    }

    void startBulletPhoto() {
        shooterList.shootBulletPhoto();
    }

    void printBulletPhotos() {
        shooterList.printBulletPhotos();
    }

    void reorderPhotos() {}

    void editPhotos() {}

    public static void main(String[] args) {
        MasterShooter m = new MasterShooter("Me");
        m.print();
    }
}
