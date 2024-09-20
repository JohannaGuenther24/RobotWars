package com.btcag.bootcamp;

import java.util.Scanner;

public class Avatar {
    public static void main(String[] args) {

        boolean check = false;
        String name = "";
        
        while (!check) {
            System.out.println("Bitte geben Sie einen Namen für Ihren Roboter ein:");
            Scanner sc = new Scanner(System.in);
            name = sc.nextLine();

            if (name.matches("[a-zA-Zß]+")) {
                System.out.println("Danke!");
                check = true;
            } else {
                System.out.println("Falsch!");
            }
        }

        System.out.println("Der Name des Roboter lautet:" + name);
        
    }
}
