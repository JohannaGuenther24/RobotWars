package com.btcag.bootcamp.test;

public class Map {
    private int rows =10;
    private int colums =14;
    private String[][] spielfeld = new String[rows][colums];
    private String reset = "\u001B[0m";

    public void drawMap(Robot r1, Robot enemy) {
        int x = r1.getX() - 1;
        int y = r1.getY()-1;
        int x2 = enemy.getX()-1;
        int y2 = enemy.getY()-1;


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                if (i == y && j == x) {
                    spielfeld[i][j] = "["+r1.getColor()+ r1.getSymbol() +reset+"]";
                } else if (i == y2 && j == x2) {
                    spielfeld[i][j] = "["+enemy.getColor()+ enemy.getSymbol() +reset+"]";
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
}
