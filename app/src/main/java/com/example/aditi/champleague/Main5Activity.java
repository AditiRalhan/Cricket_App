package com.example.aditi.champleague;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity
{
    TextView winner,winner1,name,score,winnerAnim;
    String w1,w2;
    String s1,s2;
    Button game;
    LinearLayout cong;

    dbhelper db;
    SQLiteDatabase sd;
    Cursor c;


    @Override
    protected void onPause()
    {
        finish();
    }

    @RequiresApi(api=Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        winnerAnim = (TextView) findViewById(R.id.winn);
        winner = (TextView) findViewById(R.id.result);
        winner1= (TextView) findViewById(R.id.tr1);
        name =(TextView) findViewById(R.id.tr2);
        score=(TextView) findViewById(R.id.tr3);
        game=(Button) findViewById(R.id.ng);
        cong = (LinearLayout) findViewById(R.id.linearcong);


        Animation in =AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
       cong.startAnimation(in);




        Intent i = getIntent();
        Bundle b = i.getExtras();
        w1 = b.getString("w");
        w2 = b.getString("s");
        winner.setText(w1);
        winner1.setText(w1);
        score.setText(w2);


        db = new dbhelper(getApplicationContext());
        db.getReadableDatabase();
        sd = openOrCreateDatabase("mydb.db", Context.MODE_PRIVATE,null);
        c= sd.rawQuery("select * from userinfo",null,null);
        while (c.moveToNext())
        {
            s1=c.getString(0);
            s2=c.getString(1);
        }


        if(w1.equals("Player 1"))
        {
            name.setText(s1);
        }
        else
            if(w1.equals("Player 2"))
            {
                name.setText(s2);
            }


            game.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    AlertDialog.Builder ab = new AlertDialog.Builder(Main5Activity.this);
                    ab.setMessage("Restart Game?");

                    ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent i=new Intent(Main5Activity.this,MainActivity.class);
                            startActivity(i);
                        }
                    });
                    ab.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                           finish();
                        }
                    });
                    AlertDialog ad =ab.create();

                   /* TextView tv = new TextView(Main5Activity.this);
                    tv.setText("Restart Game");
                    Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/otp");
                    tv.setTypeface(typeface);
                    ad.setView(tv); */
                    ad.setCanceledOnTouchOutside(false);
                    ad.show();
                }
            });



    }
}
