package com.iot.tetrisgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private ImageView rightbtn;
    private ImageView leftbtn;
    private ImageView bottombtn;
    private ImageView rotatebtn;
    private GameView gameView;
    private ImageView gameoverView;
    private TextView scoreView;
    private ImageView isOnPlaybtn;
    private boolean isOnPlay = true;
    private ImageView isOnMusicbtn;
    private boolean isOnMusic = true;
    private long GAMESPEED;
    private MediaPlayer mediaPlayer;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (gameView.gameover == true){
                gameoverView.setVisibility(View.VISIBLE);
                removeMessages(0);
            }
            else {
                scoreView.setText(String.valueOf(gameView.score));
                sendEmptyMessageDelayed(0, 33);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.bgm);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        gameView = findViewById(R.id.gameView);

        gameoverView = findViewById(R.id.gameoverView);
        gameoverView.setVisibility(View.GONE);
        scoreView = findViewById(R.id.scoreView);

        leftbtn = findViewById(R.id.leftbtn);
        leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameView.currBlock.moveLeft();
                if(gameView.gameBoard.iscrashed(gameView.currBlock) == 5){
                    Log.i("test", "crash");
                    gameView.currBlock.moveRight();}
            }
        });

        rightbtn = findViewById(R.id.rightbtn);
        rightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameView.currBlock.moveRight();
                if(gameView.gameBoard.iscrashed(gameView.currBlock) == 5){
                    gameView.currBlock.moveLeft();}

            }
        });

        rotatebtn = findViewById(R.id.rotatebtn);
        rotatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameView.currBlock.rotate();
                if(gameView.gameBoard.iscrashed(gameView.currBlock) != 0){
                    gameView.currBlock.preRotate();}
            }
        });

        bottombtn=findViewById(R.id.bottombtn);
        bottombtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                while(gameView.gameBoard.iscrashed(gameView.currBlock) == 0){
                    gameView.currBlock.moveDown();
                }
            }
        });
        handler.sendEmptyMessageDelayed(1, 33);

        gameoverView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        isOnPlaybtn = findViewById(R.id.isOnplaybtn);
        isOnPlaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnPlay == true){
                    isOnPlaybtn.setImageResource(R.drawable.pause);
                    gameView.drawBoardHandler.removeMessages(1);
                    isOnPlay = false;
                }
                else{
                    GAMESPEED = gameView.GAMESPEED;
                    isOnPlaybtn.setImageResource(R.drawable.play);
                    gameView.drawBoardHandler.sendEmptyMessageDelayed(1, GAMESPEED);
                    Log.i("speed", ""+ GAMESPEED);
                    isOnPlay = true;
                }
            }
        });

        isOnMusicbtn = findViewById(R.id.isOnMusicbtn);
        isOnMusicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnMusic == true){
                    isOnMusic = false;
                    mediaPlayer.pause();
                    Log.i("test", "music on");
                }
                else{
                    isOnMusic = true;
                    mediaPlayer.start();
                    Log.i("test", "music off");
                }
            }
        });
    }
}