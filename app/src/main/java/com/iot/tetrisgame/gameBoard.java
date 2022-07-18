package com.iot.tetrisgame;

import android.util.Log;

public class gameBoard {

    private int height;
    private int width;
    private int[][] board;


    public gameBoard(int width, int height){
        this.width = width;
        this.height =height;
        init();
    }

    public void init() { // 게임 시작 시 보드 초기화
        board = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = 0;
            }
        }
    }
    public int[][] getBoard() {
        return this.board;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addBlock(Block block) {  // 보드에 블록 추가
        int addBlock[][]= block.getBlock();
        int w = block.getWidth();
        int h = block.getHeight();
        int x = block.getX();
        int y = block.getY();
        int shape = block.getShape();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (addBlock[i][j] != 0) {
                    board[y + i][x + j] = shape;
                }
            }
        }
    }

    public int iscrashed(Block currblock) {  // 충돌 검사
        int[][] block = currblock.getBlock();
        int w = currblock.getWidth();
        int h = currblock.getHeight();
        int x = currblock.getX();
        int y = currblock.getY();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (block[i][j] != 0) {
                    if ((y + i) > (height-1)){  // 블록을 보드의 맨 아래보더 더 내리려는 경우
                        return 2;
                    }
                    if(x < 0){  // 블록을 보드의 왼쪽 끝보다 더 이동하려는 경우
                        return 3;
                    }
                    if((x+j) > (width-1)){ //블록을 보드의 오른쪽 끝보다 더 이동하려는 경우
                        return 4;
                    }
                    if (board[y+i][x+j] != 0) { //보드에 이미 블록이 배치되어 있어 이동하지 못하는 경우
                        if (y < 0)
                            return 1; // 보드의 맨 위보다 더 올라가는 경우
                        else return 5; // 다른 블록과 충돌하는 경우
                    }
                }
            }
        }
        return 0;
    }

    public int clearLine() {
        int x, y, p;
        int cnt = 0;
        int clearLine = 0;

        for (y = height-1; y >= 0; y--) {  // 라인이 블록으로 꽉 채워졌는지 검사
            cnt = 0;
            for (x = 0; x < width; x++) {
                if (board[y][x] != 0) {
                    cnt++;
                }
            }
            if (cnt == width) { //라인이 모두 블록으로 채워지면 그 위에 블록들을 전부 한칸씩 내림
                clearLine++;
                for (x = 0; x < width; x++) {
                    for (p = y; p > 0; p--) {
                        board[p][x] = board[p - 1][x];
                    }
                    board[p][0] = 0;
                }
                y++;
            }
        }
        return clearLine;
    }

}
