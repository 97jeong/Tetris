package com.iot.tetrisgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class GameView extends View {
    protected boolean gameover = false;
    protected int score = 0;
    protected Block currBlock;
    private Block nextBlock;
    protected gameBoard gameBoard = new gameBoard(width, height);
    private int piece_size;
    static final int WHAT_UPDATE = 1;
    static final int x = 0;
    static final int y = 0;
    static final int width = 10;
    static final int height = 20;
    private Bitmap piece[] = new Bitmap[8];
    private int board[][] = gameBoard.getBoard();
    private int View_w;
    private int View_h;
    protected long GAMESPEED = 1000;


    protected Handler drawBoardHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(gameover == true){
                drawBoardHandler.removeMessages(WHAT_UPDATE);
            }

            else{
                invalidate();
                score += gameBoard.clearLine() * 100;
                GAMESPEED = 1000 - 100 * (score / 1000);
            }
            drawBoardHandler.sendEmptyMessageDelayed(WHAT_UPDATE, GAMESPEED);
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        super.onDraw(canvas);
        View_w = getWidth();
        View_h = getHeight();

        // draw Board
        int piece_width = View_w / gameBoard.getWidth();
        int piece_height = View_h / gameBoard.getHeight();
        piece_size = Math.min(piece_width,piece_height);
        board = gameBoard.getBoard();
        gameBoard.clearLine();
        for (int i = 0; i < gameBoard.getHeight(); i++){
            for (int j = 0; j < gameBoard.getWidth(); j++){
                if(board[i][j] == 0){
                    canvas.drawBitmap(piece[board[i][j]], null, new Rect(j * piece_size + x,
                            i * piece_size + y,
                            j * piece_size + x + piece_size,
                            i * piece_size + y + piece_size), paint);
                }
                else{
                    canvas.drawBitmap(piece[board[i][j]], null, new Rect(j * piece_size + x,
                            i * piece_size + y,
                            j * piece_size + x + piece_size,
                            i * piece_size + y + piece_size), paint);
                }
            }
        }
        onDrawNextBlock(canvas, nextBlock);
        onDrawBlock(canvas, currBlock);
    }
    public void onDrawNextBlock(Canvas canvas, Block n_Block){
        Paint paint = new Paint();

        int nextblock[][] = n_Block.getBlock();
        int x = n_Block.getX();
        int y = n_Block.getY();
        int shape = n_Block.getShape();
        int h = n_Block.getHeight();
        int w = n_Block.getWidth();

        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(nextblock[i][j] != 0){
                    canvas.drawBitmap(piece[shape], null, new Rect(piece_size * 8 + j * piece_size + x * piece_size,
                             piece_size * 6 + i * piece_size + y * piece_size,
                            piece_size * 8 + j * piece_size + x * piece_size + piece_size,
                            piece_size * 6 + i * piece_size + y * piece_size + piece_size), paint);
                }
            }
        }

    }
    // draw block
    public void onDrawBlock(Canvas canvas, Block c_Block){
        Paint paint = new Paint();

        int currblock[][] = c_Block.getBlock();
        int x = c_Block.getX();
        int y = c_Block.getY();
        int shape = c_Block.getShape();
        int h = c_Block.getHeight();
        int w = c_Block.getWidth();

        int iscrashed = gameBoard.iscrashed(c_Block);

        switch (iscrashed){
            case 0:
                for(int i = 0; i < h; i++){
                    for(int j = 0; j < w; j++){
                        if(currblock[i][j] != 0){
                            canvas.drawBitmap(piece[shape], null, new Rect(j * piece_size + x * piece_size,
                                    i * piece_size + y * piece_size,
                                    j * piece_size + x * piece_size + piece_size,
                                    i * piece_size + y * piece_size + piece_size), paint);
                        }
                    }
                }
                currBlock.moveDown();
                break;
            case 1:
                Log.e("iscrashed", "block.Y < 0");
                break;
            case 2:
                Log.i("iscrashed", "block can't move down");
                currBlock.moveUp();
                gameBoard.addBlock(c_Block);
                currBlock = nextBlock;
                nextBlock = BlockCreater.create();
                break;

            case 3:
                Log.i("iscrashed", "block can't move left");
                currBlock.moveRight();
                break;

            case 4:
                Log.i("iscrashed", "block can't move right");
                currBlock.moveLeft();
                break;

            case 5:
                if(currBlock.getY() == 0){
                    Log.i("iscrashed", "block can't draw board. game over!");
                    gameover = true;
                    gameBoard.addBlock(c_Block);
                }
                else{
                    Log.i("iscrashed", "block is clashed other block");
                    currBlock.moveUp();
                    gameBoard.addBlock(c_Block);
                    currBlock = nextBlock;
                    nextBlock = BlockCreater.create();
                }
                break;
        }

    }

    public Block getBlock(){
        return nextBlock;
    }

    public GameView(Context context, AttributeSet attrs){
        super(context, attrs);
        Resources r = context.getResources();

        piece[0] = BitmapFactory.decodeResource(r, R.drawable.black);
        piece[1] = BitmapFactory.decodeResource(r, R.drawable.cyan);
        piece[2] = BitmapFactory.decodeResource(r, R.drawable.blue);
        piece[3] = BitmapFactory.decodeResource(r, R.drawable.yellow);
        piece[4] = BitmapFactory.decodeResource(r, R.drawable.orange);
        piece[5] = BitmapFactory.decodeResource(r, R.drawable.gray);
        piece[6] = BitmapFactory.decodeResource(r, R.drawable.green);
        piece[7] = BitmapFactory.decodeResource(r, R.drawable.red);

        currBlock = BlockCreater.create();
        nextBlock = BlockCreater.create();
        drawBoardHandler.sendEmptyMessageDelayed(WHAT_UPDATE,GAMESPEED);
    }
}
