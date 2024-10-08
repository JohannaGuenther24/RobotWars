package com.btcag.bootcamp;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
    public static int rows =10;
    public static int colums =14;
    public static String[][] spielfeld = new String[rows][colums];
    public static String gegner = "Wall-E";
    public static String step = "";
    public static boolean gameOn = true;
    public static Scanner sc = new Scanner(System.in);
    public static int positionX =1;
    public static int positionY =1;
    public static int positionX2 =14;
    public static int positionY2 =10;


    public static void main(String[] args) throws InterruptedException {
        intro();
        spielername();
        zeichneSpielfeld(positionX,positionY, positionX2, positionY2);
        while (gameOn == true) {
        zug();
        checkWin();
        System.out.println(gegner + " führt einen Zug aus...");
        TimeUnit.SECONDS.sleep(2);
        gegnerZug();
        checkWinPC();
        }



    }
    public static void checkWin (){
        if (positionX == positionX2 && positionY == positionY2){
            System.out.println("Du hast " + gegner + " besiegt!");
            gameOn = false;
        }
    }

    public static void checkWinPC (){
        if (positionX == positionX2 && positionY == positionY2){
            System.out.println(gegner + " hat dich besiegt!\nGAME OVER");
            gameOn = false;
        }
    }

    public static void zug(){
        String move = "";
        boolean check = false;
        System.out.println("""
                
                In welche Richtung möchtest du dich bewegen?\s
                w = oben
                a = links
                s = unten
                d = rechts""");
        move = sc.nextLine();

        while(!check){
        if(move.matches("[wasdWASD]+")){
            if (move.equals("w") || move.equals("W")) {
               if (positionY > 1) {
                   positionY -= 1;
                   check = true;
               } else {
                   System.out.println("Das Spielfeld geht nicht weiter nach oben.\nWähle eine andere Richtung.");
                   move = sc.nextLine();
               }
            } else if (move.equals("d") || move.equals("D")) {
                if (positionX < 14) {
                    positionX += 1;
                    check = true;
                } else {
                    System.out.println("Das Spielfeld geht nicht weiter nach rechts.\nWähle eine andere Richtung.");
                    move = sc.nextLine();
                }
            } else if (move.equals("s") || move.equals("S")) {
                if (positionY < 10) {
                    positionY += 1;
                    check = true;
                } else {
                    System.out.println("Das Spielfeld geht nicht weiter nach unten.\nWähle eine andere Richtung.");
                    move = sc.nextLine();
                }
            } else if (move.equals("a") || move.equals("A")) {
                if (positionX > 1) {
                    positionX -= 1;
                    check = true;
                } else {
                    System.out.println("Das Spielfeld geht nicht weiter nach links.\nWähle eine andere Richtung.");
                    move = sc.nextLine();
                }
            }
        }else {
            System.out.println("Falsche Eingabe!");
        }

        }
        zeichneSpielfeld(positionX,positionY, positionX2, positionY2);
        System.out.println("Deine neue Position ist Spalte: " + positionX + " Reihe: " + positionY + ".");

    }

    public static void moveGegnerZufall(){
        Random random = new Random();
        int zahl = random.nextInt(0,4);

        if (zahl == 0) {
            step = "w";
        } else if (zahl == 1) {
            step = "d";
        } else if (zahl == 2) {
            step = "s";
        } else if (zahl == 3) {
            step = "a";
        }
    }

    public static void gegnerZug(){

        boolean check = false;
        while(!check){
            moveGegnerZufall();
                if (step.equals("w")) {
                    if (positionY2 > 1) {
                        positionY2 -= 1;
                        check = true;
                    }
                } else if (step.equals("d")) {
                    if (positionX2 < 14) {
                        positionX2 += 1;
                        check = true;
                    }
                } else if (step.equals("s")) {
                    if (positionY2 < 10) {
                        positionY2 += 1;
                        check = true;
                    }
                } else if (step.equals("a")) {
                    if (positionX2 > 1) {
                        positionX2 -= 1;
                        check = true;
                    }
                }


        }
        zeichneSpielfeld(positionX,positionY, positionX2, positionY2);
        System.out.println("Die neue Position von " + gegner + " ist Spalte: " + positionX2 + " Reihe: " + positionY2 + ".");

    }

    public static void zeichneSpielfeld(int a, int b, int c, int d) {
        a = a-1;
        b = b-1;
        c = c-1;
        d = d-1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                if (i == b && j == a || i == d && j == c) {
                    spielfeld[i][j] = "[x]";
                } else {
                    spielfeld[i][j] = "[ ]";
                }
            }
        }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < colums; j++) {
                    System.out.print(spielfeld[i][j] + " ");
                }
                System.out.println();
        }
    }

    public static void spielername(){
        boolean check = false;
        String name = "";

        while (!check) {
            System.out.println("Bitte geben Sie einen Namen für Ihren Roboter ein: ");
            name = sc.nextLine();

            if (name.matches("[a-zA-Zß]+")) {
                System.out.println("Danke!");
                check = true;
            } else {
                System.out.println("Falsch!");
            }
        }

        System.out.println("Der Name des Roboter lautet: " + name +"\n" +
                           name + " wird gegen "+ gegner+ " kaempfen!\n");
    }

    public static void intro(){
        System.out.println("Willkommen zu Robot Wars!");
        System.out.println();
        System.out.println("""
                 _______               _______
                |@|@|@|@|             |@|@|@|@|
                |@|@|@|@|    _____    |@|@|@|@|
                |@|@|@|@| /\\_T_T_/\\ |@|@|@|@|
                |@|@|@|@||/\\ T T /\\||@|@|@|@|
                 ~~~~/|~||~\\/~T~\\/~||~T~~T\\~
                   /_,|_| \\(-(O)-)/ |_|__|/");
                  /~\\      \\\\8_8//    |_ |_
                 (O_O)  /~~[_____]~~\\   [(@)|
                       (  |       |  )    ~
                      [~` ]       [ '~]
                      |~~|         |~~|
                      |  |         |  |
                     _<\\/>_       _<\\/>_
                    /_====_\\     /_====_\\
                """);

    }


}
