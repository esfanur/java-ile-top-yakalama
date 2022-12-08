package com.deneme.top_yakala;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.media.Image;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView,textView2;
    ImageView image;
    Runnable runnable;
    int screenx,screeny;
    Handler handler;
    int sayac=60;
    int skor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        skor=0;
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        image=findViewById(R.id.imageView);
        screenx=354;
        screeny=402;
        textView2.setText("SKOR:"+skor);

        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                sayac--;
                karistir();
                textView.setText("KALAN SÜRE:"+sayac);
                handler.postDelayed(runnable,500);
                if(sayac<=0)
                {
                    handler.removeCallbacks(runnable);
                    //Toast.makeText(getApplicationContext(),"sure bitti",Toast.LENGTH_LONG).show();
                    AlertDialog.Builder mesaj=new AlertDialog.Builder(MainActivity.this);
                    mesaj.setTitle("BŞATAN BAŞLA");
                    mesaj.setMessage("yeniden baslamak istediginize eminmimisiniz?");
                    mesaj.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent=getIntent();
                            finish();
                            startActivity(intent);
                        }
                    });
                    mesaj.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"GAME OVER",Toast.LENGTH_LONG).show();

                        }
                    });
                    mesaj.show();
                }
            }
        };
        handler.post(runnable);

    }

    public void skorartir(View view)
    {
        skor++;
        textView2.setText("SKOR:"+skor);
    }

    public void karistir()
    {

                float rndmX = new Random().nextInt(screenx*2 );
                float rndmY = new Random().nextInt(screeny*2 );
                image.setX(rndmX);
                image.setY(rndmY);



    }
}