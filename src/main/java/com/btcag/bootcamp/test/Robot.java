package com.btcag.bootcamp.test;

public class Robot {
    private String name = "";
    private String symbol = "";
    private int posX = 0;
    private int posY = 0;
    private int movementRate = 0;
    private int attackRange = 0;
    private int attackDamage = 0;
    private int health = 0;
    private String color = "";
    private String blue = "\u001B[34m";
    private String red = "\u001B[31m";

    Robot(String name, String symbol, int movementRate, String color, int attackRange, int attackDamage, int health){
        this.name = name;
        this.symbol = symbol;
        this.movementRate = movementRate;
        this.color = color;
        this.attackDamage = attackDamage;
        this.attackRange = attackRange;
        this.health = health;
    }

    public String getSymbol(){
        return symbol;
    }

    public void setX(int x){
        this.posX = x;
    }

    public void setHealth(int x){
        this.health = x;
    }

    public void setAttackDamage(int x){
        this.attackDamage = x;
    }

    public void setMovementRate(int x){
        this.movementRate = x;
    }

    public void setAttackRange(int x){
        this.attackRange = x;
    }

    public void setY(int y){
        this.posY = y;
    }

    public int getX(){
        return posX;
    }

    public int getY(){
        return posY;
    }

    public String getColor(){
        return color;
    }

    public int getMovementRate(){
        return movementRate;
    }

    public int getAttackRange(){
        return attackRange;
    }

    public String getName(){
        return name;
    }

    public int getAttackDamage(){
        return attackDamage;
    }

    public int getHealth(){
        return health;
    }

    public void reduceHealth(int damagePoints){
        health = health - damagePoints;
    }

    public boolean isKnockedOut(){
        if (health <= 0){
            return false;
        } else {
            return true;
        }
    }
}
