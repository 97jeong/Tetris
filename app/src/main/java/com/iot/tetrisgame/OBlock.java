package com.iot.tetrisgame;

public class OBlock extends Block{

    public OBlock(){
        x = 4;
        y = 0;
        currtype = 0;
        height = 2;
        width = 2;
        rotatetype = 1;
        shape = 2;

        this.block = new int[][][]{
                {
                        {1, 1},
                        {1, 1}
                }
        };
    }

    public int[][] getBlock() {
        return block[currtype];
    }
}
