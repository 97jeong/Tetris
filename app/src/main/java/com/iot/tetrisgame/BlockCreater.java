package com.iot.tetrisgame;

import android.util.Log;

public class BlockCreater {

    public static Block create(){
        return create ((int) (Math.random() * 7)+1);
    }

    private static Block create(int shape) {
        switch (shape) {
            case 1:
                return new IBlock();
            case 2:
                return new OBlock();
            case 3:
                return new TBlock();
            case 4:
                return new LBlock();
            case 5:
                return new JBlock();
            case 6:
                return new ZBlock();
            case 7:
                return new SBlock();
            default:
                Log.e("error", "7종류의 블록에 포함되지 않습니다.");
                return new IBlock();

        }
    }
    private BlockCreater(){

    }
}
