package com.ubante.bullet.model;

/**
 * Created by J on 4/7/14.
 */
public class Shooter {
    String humanName;
    Device device;
    Shooter rightSide = null;
    Shooter leftSide = null;
    ShooterList shooterList;

    public Shooter(String name) {
        this.humanName = name;
        this.device = new Device();
    }

    Shooter() {}

    void setDevice(Device d) { this.device = d; }

    public void shoot() {
        device.takePhoto();
    }

    public void shoot(int shots) {
        for (int i=1; i<=shots; i++) {
            device.takePhoto();
        }
    }

    public void setRightSide(Shooter s) {
        rightSide = s;

        // Don't try this because it causes an infinite loop and overflow.
        // Instead, handle it in the ShooterRing.g
//        s.setLeftSide(this);
    }

    public void setLeftSide(Shooter s) {
        leftSide = s;
    }

    public void joinShooterList(ShooterList sl) {
        shooterList = sl;
        sl.join(this);
    }

    void leaveShooterList() {
        shooterList.leave(this);
    }

    int getGallerySize() { return device.getGallerySize(); }

    Photo getLastPhoto() {
        return device.getLastPhoto();
    }

    void print() {
        System.out.println("------------");
        System.out.println(humanName);
        device.print();
        device.printGallery();
        System.out.println("");
        System.out.printf("On %s leftside is ",humanName);
        if ( leftSide == null ) {
            System.out.printf("unknown");
        } else {
            System.out.printf(leftSide.humanName);
        }
        System.out.printf(" and on rightside is ");
        if ( rightSide == null ) {
            System.out.println("unknown");
        } else {
            System.out.println(rightSide.humanName);
        }
    }

    public static void main(String[] args) {
        Shooter annie = new Shooter("Annie");
        annie.setDevice(new Device());
        annie.shoot();
        annie.print();

        Shooter charles = new Shooter("Charles");
        charles.setDevice(new Device("HTC 1000"));
        charles.setLeftSide(annie);
        charles.shoot(2);
        charles.print();
        annie.print();

        Shooter bert = new Shooter("Bert");
        bert.setDevice(new Device("Apple iPad"));
        bert.setLeftSide(annie);
        bert.setRightSide(charles);
        bert.print();

    }
}
