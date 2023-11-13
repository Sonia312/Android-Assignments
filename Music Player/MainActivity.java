package com.example.assign5;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class MainActivity extends AppCompatActivity {
    int curr;
    ImageButton play_pause, next, previous, exit, loop, restart;
    SeekBar seek;
    private boolean isResume, isLoop;
    MediaPlayer music;
    @Override
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play_pause = findViewById(R.id.button4);
        next = findViewById(R.id.button6);
        previous = findViewById(R.id.button5);
        music = MediaPlayer.create(this, R.raw.song1);
        seek = findViewById(R.id.seekBar);
        exit = findViewById(R.id.button18);
        loop = findViewById(R.id.imageButton);
        restart = findViewById(R.id.imageButton2);
        ArrayList<Integer> songs = new ArrayList<>();
        curr = 0;
        songs.add(0, R.raw.song1);
        songs.add(1, R.raw.song2);
        songs.add(2, R.raw.song3);
        songs.add(3, R.raw.song4);
        play_pause.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!isResume){
                isResume = true;
                
                play_pause.setImageDrawable(getResources().getDrawable(R.drawable.baseline_pause_24));
                    
                music.start();
            }
            else{
                isResume = false;
                    
                play_pause.setImageDrawable(getResources().getDrawable(R.drawable.baseline_play_arrow_24));
                    
                music.pause();
                }
            }
        });
        
        seek.setMax(music.getDuration());
        
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seek.setProgress(music.getCurrentPosition());
                if(seek.getProgress() == music.getDuration()){
                if(isLoop){
                    curr = curr;
                }
                else if(curr < songs.size()){
                    curr++;
                }
                else{
                    curr = 0;
                }
                if(music.isPlaying() || music.isLooping()){
                    music.stop();
                }
                music = MediaPlayer.create(getApplicationContext(), songs.get(curr));
                
                music.start();
                }
            }
        }, 0, 5000);
        
        seek.setOnSeekBarChangeListener(new
        
        SeekBar.OnSeekBarChangeListener() {
        
            @Override
            public void onProgressChanged(SeekBar seekBar, int i,boolean b) {
            
                music.seekTo(i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            
            }
        });
        
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoop = false;
                loop.setBackgroundColor(Color.WHITE);
                if(curr < songs.size()){
                    curr++;
                }
                else{
                    curr = 0;
                }
                if(music.isPlaying() || music.isLooping()){
                    music.stop();
                }
                music = MediaPlayer.create(getApplicationContext(),songs.get(curr));
                
                music.start();
            }
        });
        
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoop = false;
                loop.setBackgroundColor(Color.WHITE);
                if(curr > 0){
                    curr--;
                }
                else{
                    curr = songs.size() - 1;
                }
                if(music.isPlaying() || music.isLooping()){
                    music.stop();
                }
                music = MediaPlayer.create(getApplicationContext(), songs.get(curr));
                
                music.start();
                }
        
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new
                
                AlertDialog.Builder(MainActivity.this);
                
                builder.setMessage("Do you want to exit?");
                builder.setTitle("Alert!");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes",
                (DialogInterface.OnClickListener)(dialog, which) ->{
                
                music.stop();
                finish();
        });
        
        builder.setNegativeButton("No",(DialogInterface.OnClickListener)
        (dialog, which) ->{
        
                dialog.cancel();
            });
            AlertDialog alert = builder.create();
                alert.show();
            }
        });
        
        loop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLoop){
                    isLoop = true;
                    music.setLooping(true);
                    loop.setBackgroundColor(Color.GREEN);
                }
                else{
                    isLoop = false;
                    music.setLooping(false);
                    loop.setBackgroundColor(Color.WHITE);
                }
            }
        });
        
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.reset();
                music = MediaPlayer.create(getApplicationContext(), songs.get(curr));
                
                music.start();
            }
        });
    }
}
