package com.iot.tetrisgame;

public class LBlock extends Block{

    public LBlock(){
        x = 4;
        y = 0;
        currtype = 0;
        height = 3;
        width = 3;
        rotatetype = 4;
        shape = 4;

        this.block = new int[][][]{
                {
                        {1, 0, 0},
                        {1, 0, 0},
                        {1, 1, 0},
                },
                {
                        {1, 1, 1},
                        {1, 0, 0},
                        {0, 0, 0},
                },
                {
                        {1, 1, 0},
                        {0, 1, 0},
                        {0, 1, 0},
                },
                {
                        {0, 0, 0},
                        {0, 0, 1},
                        {1, 1, 1},
                }
        };
    }

    public int[][] getBlock() {
        return block[currtype];
    }
}
