package com.iot.tetrisgame;

public class TBlock extends Block{

    public TBlock(){
        x = 4;
        y = 0;
        currtype = 0;
        height = 3;
        width = 3;
        rotatetype = 4;
        shape = 3;

        this.block = new int[][][]{
                {
                        {1, 0, 0},
                        {1, 1, 0},
                        {1, 0, 0},
                },
                {
                        {1, 1, 1},
                        {0, 1, 0},
                        {0, 0, 0},
                },
                {
                        {0, 1, 0},
                        {1, 1, 0},
                        {0, 1, 0},
                },
                {
                        {0, 1, 0},
                        {1, 1, 1},
                        {0, 0, 0},
                }
        };
    }

    public int[][] getBlock() {
        return block[currtype];
    }
}
