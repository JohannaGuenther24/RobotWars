package com.btcag.bootcamp;

public class Spielfeld {
    public static void main(String[] args) {

        int x = 0;
        int y = 0;
        int positionX = 0;
        int positionY = 0;

        while (y < 14) {
            while (x < 10) {
                while (x == positionX) {
                    while (y == positionY) {
                        System.out.print("[x]");
                        y = y + 1;
                        x = x + 1;
                    }
                    x = x + 1;
                    System.out.print("[ ]");
                }
                System.out.print("[ ]");
                x = x + 1;
            }
            x = 0;
            System.out.println();
            y = y + 1;
        }


        System.out.println("Dein Roboter befindet sich auf der Position " + positionX + "/" + positionY);
    }
}
