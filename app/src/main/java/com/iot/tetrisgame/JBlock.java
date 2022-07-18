package com.iot.tetrisgame;

public class JBlock extends Block{

    public JBlock(){
        x = 4;
        y = 0;
        currtype = 0;
        height = 3;
        width = 3;
        rotatetype = 4;
        shape = 5;

        this.block = new int[][][]{
                {
                        {0, 1, 0},
                        {0, 1, 0},
                        {1, 1, 0},
                },
                {
                        {1, 0, 0},
                        {1, 1, 1},
                        {0, 0, 0},
                },
                {
                        {1, 1, 0},
                        {1, 0, 0},
                        {1, 0, 0},
                },
                {
                        {1, 1, 1},
                        {0, 0, 1},
                        {0, 0, 0},
                }
        };
    }

    public int[][] getBlock() {
        return block[currtype];
    }
}
