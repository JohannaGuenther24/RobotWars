package com.btcag.bootcamp.test;

import java.awt.*;
import java.util.Scanner;

import static com.btcag.bootcamp.Game.*;

public class Game {
    public static void main(String[] args) {
        String red = "\u001B[31m";
        String blue = "\u001B[34m";
        boolean gameOn = true;


        intro();
        String spielerEins = username();

        Robot r1 = new Robot(spielerEins, "o", 3, blue, 3, 2,5);
        r1.setX(1);
        r1.setY(1);
        setSkills(r1);

        Robot enemy = new Robot("Wall-E", "x", 3, red,1,1,7);
        enemy.setX(14);
        enemy.setY(10);
        Map map = new Map();

        map.drawMap(r1, enemy);


        do{
            gameOn = turn(r1, enemy, map);
            if(gameOn){
            gameOn = turn(enemy, r1, map);}
        } while (gameOn);

    }
    private static void intro(){
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

    public static String username() {
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
        return name;
    }

    public static void move(Robot robot) {
        Scanner scan = new Scanner(System.in);
        String move = "";
        int y = robot.getY();
        int x = robot.getX();
        String reset = "\u001B[0m";

            boolean check = false;
            System.out.println(robot.getColor()+robot.getName()+"""
                    ! Du bist am Zug!
                    In welche Richtung möchtest du dich bewegen?\s
                    w = oben
                    a = links
                    s = unten
                    d = rechts"""+reset);
            move = scan.nextLine();
            while (!check) {
                if (move.matches("[wasdWASD]+")) {
                    if (move.equals("w") || move.equals("W")) {
                        if (y > 1) {
                            robot.setY(y - 1);
                            check = true;
                        } else {
                            System.out.println("Das Spielfeld geht nicht weiter nach oben.\nWähle eine andere Richtung.");
                            move = scan.nextLine();
                        }
                    } else if (move.equals("d") || move.equals("D")) {
                        if (x < 14) {
                            robot.setX(x+1);
                            check = true;
                        } else {
                            System.out.println("Das Spielfeld geht nicht weiter nach rechts.\nWähle eine andere Richtung.");
                            move = scan.nextLine();
                        }
                    } else if (move.equals("s") || move.equals("S")) {
                        if (y < 10) {
                            robot.setY(y+1);
                            check = true;
                        } else {
                            System.out.println("Das Spielfeld geht nicht weiter nach unten.\nWähle eine andere Richtung.");
                            move = scan.nextLine();
                        }
                    } else if (move.equals("a") || move.equals("A")) {
                        if (x > 1) {
                            robot.setX(x-1);
                            check = true;
                        } else {
                            System.out.println("Das Spielfeld geht nicht weiter nach links.\nWähle eine andere Richtung.");
                            move = scan.nextLine();
                        }
                    }
                } else {
                    System.out.println("Falsche Eingabe!");
                    move = scan.nextLine();
                }
            }
    }

    static boolean knocked = true;

    public static boolean turn(Robot robot, Robot enemy, Map map){
        for (int i = 1; i <= robot.getMovementRate(); i++) {
                move(robot);
                map.drawMap(robot, enemy);
                System.out.println("Du befindest du auf der Position " + robot.getX() + "/" + robot.getY());
            }
        for (int x = robot.getX()- robot.getAttackRange(); x <= robot.getX() + robot.getAttackRange(); x++){
            if (x == enemy.getX() && robot.getY() == enemy.getY()){
                attack(robot, enemy);
            }
        }
        for (int y = robot.getY()- robot.getAttackRange(); y <= robot.getY() + robot.getAttackRange(); y++){
            if (y == enemy.getY() && robot.getX() == enemy.getX()){
                attack(robot, enemy);
            }
        }
        return knocked;
    }

    public static void attack(Robot robot, Robot enemy){
        Scanner scan = new Scanner(System.in);
        boolean check = true;

        while(check) {
            System.out.println("Möchtest du einen Angriff ausfuehren?\n1 - Ja\n2 - Nein");
            int entscheidung = scan.nextInt();
            if (entscheidung == 1) {
                enemy.reduceHealth(robot.getAttackDamage());
                System.out.println(enemy.getName()+"'s Leben: "+enemy.getHealth());
                knocked = enemy.isKnockedOut();
                if (!knocked) {
                    System.out.println(robot.getColor() + robot.getName() + " hat Gewonnen!");
                }
                check = false;
            } else if (entscheidung == 2) {
                check = false;
            } else {
                System.out.println("Falsche Eingabe!");
                entscheidung = scan.nextInt();
            }
        }
    }

    public static void setSkills(Robot robot){
        boolean check = true;
        Scanner scan = new Scanner(System.in);
        int skillpunkte = 13;
        int points;

        System.out.println("Verteile deine Fähigkeitspunkte für dein Leben, Bewegungsrate, Schaden und Angriffsreichweite.\nDu hast 13 Punkte zum vergeben.\nJeder Wert muss mindestens 1 sein.");
        System.out.println("Wie viel Leben möchtest du haben?");
        while(check) {
            points = scan.nextInt();
            if (points >= 1 && points <= skillpunkte-3){
                robot.setHealth(points);
                skillpunkte = skillpunkte - points;
                System.out.println("Du hast dein Leben auf " + points + " gesetzt.");
                check = false;
            } else{
                System.out.println("Falsche Eingabe! Bitte richtigen Wert eingeben.");
            }
        }
        check = true;
        System.out.println("Wie hoch soll dein Schaden sein?");
        while(check) {
            points = scan.nextInt();
            if (points >= 1 && points <= skillpunkte-2){
                robot.setAttackDamage(points);
                skillpunkte = skillpunkte - points;
                System.out.println("Du hast dein Schaden auf " + points + " gesetzt.");
                check = false;
            } else{
                System.out.println("Falsche Eingabe! Bitte richtigen Wert eingeben.");
            }
        }
        check = true;
        System.out.println("Wie hoch soll deine Bewegungsrate sein?");
        while(check) {
            points = scan.nextInt();
            if (points >= 1 && points <= skillpunkte-1){
                robot.setMovementRate(points);
                skillpunkte = skillpunkte - points;
                System.out.println("Du hast deine Bewegungsrate auf " + points + " gesetzt.");
                check = false;
            } else{
                System.out.println("Falsche Eingabe! Bitte richtigen Wert eingeben.");
            }
        }
        check = true;
        System.out.println("Wie hoch soll deine Angriffsreichweite sein?");
        while(check) {
            points = scan.nextInt();
            if (points >= 1 && points <= skillpunkte){
                robot.setAttackRange(points);
                skillpunkte = skillpunkte - points;
                System.out.println("Du hast deine Angriffsreichweite auf " + points + " gesetzt.");
                check = false;
            } else{
                System.out.println("Falsche Eingabe! Bitte richtigen Wert eingeben.");
            }
        }
    }
}
