package com.iot.tetrisgame;

public class SBlock extends Block{

    public SBlock(){
        x = 4;
        y = 0;
        currtype = 0;
        height = 3;
        width = 3;
        rotatetype = 4;
        shape = 7;

        this.block = new int[][][]{
                {
                        {0, 1, 1},
                        {1, 1, 0},
                        {0, 0, 0},
                },
                {
                        {1, 0, 0},
                        {1, 1, 0},
                        {0, 1, 0},
                },
                {
                        {0, 1, 1},
                        {1, 1, 0},
                        {0, 0, 0},
                },
                {
                        {1, 0, 0},
                        {1, 1, 0},
                        {0, 1, 0},
                }
        };
    }

    public int[][] getBlock() {
        return block[currtype];
    }
}
