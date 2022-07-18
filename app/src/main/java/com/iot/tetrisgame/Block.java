package com.iot.tetrisgame;

public abstract class Block {
    protected int x;
    protected int y;
    protected int currtype;
    protected int height;
    protected int width;
    protected int shape;
    protected int[][][] block;
    protected int rotatetype;


    public Block(){
    }

    public void moveLeft(){
        x--;
    }

    public void moveRight(){
        x++;
    }

    public void moveDown(){
        y++;
    }

    public void moveUp(){ y--; }

    public void rotate(){
        currtype = (currtype+1) % rotatetype;
    }

    public void preRotate() {
        currtype = (currtype-1+rotatetype) % rotatetype;
    }


    public int getShape() {
        return shape;
    }

    public int getCurrtype() {
        return currtype;
    }

    public int getRotatetype() {
        return rotatetype;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setBlock(int[][][] block) {
        this.block = block;
    }

    public void setCurrtype(int currtype) {
        this.currtype = currtype;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setRotatetype(int rotatetype) {
        this.rotatetype = rotatetype;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[][] getBlock(){ return null;}
}
