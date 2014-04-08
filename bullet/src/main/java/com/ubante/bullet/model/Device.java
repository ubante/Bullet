package com.ubante.bullet.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J on 4/7/14.
 */
public class Device {
    Camera camera;
    String modelName;
    String androidId;

    class Camera {
        List<Photo> gallery = new ArrayList<Photo>();

        Camera() {}

        void takePhoto() {
            gallery.add(new Photo());
        }

        void print() {
            System.out.printf("There are %d photos in the gallery\n",gallery.size());
            for (Photo p : gallery) {
                p.print();
            }
        }
    }

    Device(String model) {
        modelName = model;
        camera = new Camera();
        androidId = modelName; // later this will be the Android ID
    }

    Device() {
        this("defaultcameraname");
    }

    void takePhoto() {
        camera.takePhoto();
    }

    void printGallery() {
        camera.print();
    }

    int getGallerySize() { return camera.gallery.size(); }

    Photo getLastPhoto() {
        return camera.gallery.get(getGallerySize() - 1);
    }

    void print() {
        System.out.println(modelName);
    }

    public static void main(String[] args) {
        Device d = new Device("Samsung 17");
        d.print();
        d.printGallery();
        System.out.println("Shooting....");
        d.takePhoto();
        try {
            System.out.println("Saving....");
            Thread.sleep(2000);
        } catch(InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Shooting....");
        d.takePhoto();
        d.printGallery();
    }
}
