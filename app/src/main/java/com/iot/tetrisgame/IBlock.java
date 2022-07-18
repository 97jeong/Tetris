package com.iot.tetrisgame;

public class IBlock extends Block{

    public IBlock(){
        x = 4;
        y = 0;
        currtype = 0;
        height = 4;
        width = 4;
        rotatetype = 2;
        shape = 1;

        this.block = new int[][][]{
                {
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0}
                },
                {
                        {1, 1, 1, 1},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                }
        };
    }

    public int[][] getBlock() {return block[currtype];
    }
}
