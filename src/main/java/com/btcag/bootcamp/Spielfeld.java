package com.btcag.bootcamp;

public class Spielfeld {
    public static void main(String[] args) {

        int x = 0;
        int y = 0;

        System.out.println("[x][ ][ ][ ][ ][ ][ ][ ][ ][ ]");

        while (y < 14) {
            while (x < 10) {
                System.out.print("[ ]");
                x = x + 1;
            }
            x = 0;
            System.out.println();
            y = y + 1;
        }

        System.out.println("Dein Roboter befindet sich auf der Position 1/1.");
    }
}
