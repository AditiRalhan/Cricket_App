package com.example.aditi.champleague;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class Main3Activity extends AppCompatActivity {

    static int score=0,score1=0;
    int n,overs;
    static String Bat,Bowl;
    static int balls,balls2=0;
    static int overnum=1,overballs=0;
    static int w=0,nballs=0;
    int min=1;
    int max=8;
    String s1,s2,s3,score2;
    String i1,i2,win;
    static String runs,color;
    int i11,i22;
    TextView t4,t1,t2,t3,t5,t6,t7,t8,t9,tw,tn;
    static TextView on;
    static GridView gv;
    Context context;
    int[] imgs;
    static ArrayList<String> al;
    Button b1,b2;
    Button button1,button2,button3,button4,button5,button6;
    dbhelper db;
    SQLiteDatabase sd;
    Cursor c;
    Thread t;
    Animation animation;



   @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api=Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main3);

        Intent i=getIntent();
        Bundle b=i.getExtras();

         imgs =new int[]{R.drawable.bat1, R.drawable.ball};

         Bat = b.getString("text4");
         Bowl = b.getString("text5");
         al = new ArrayList<String>();
        al.add(Bat);
        al.add(Bowl);
        context=this;

        gv = (GridView) findViewById(R.id.gridview);
        gv.setAdapter(new CricAdapter(al,imgs,this));

        Toast.makeText(getApplicationContext(),al.get(0),Toast.LENGTH_SHORT).show();

        db=new dbhelper(getApplicationContext());
        db.getReadableDatabase();
        sd = openOrCreateDatabase("mydb.db", Context.MODE_PRIVATE, null);
        c=sd.rawQuery("select * from userinfo ",null,null);
        while(c.moveToNext())
        {
            s1= c.getString(0);
            s2=  c.getString(1);
            s3=c.getString(2);
        }


        overs=Integer.parseInt(s3);
        balls=overs*6;

         t1=(TextView) findViewById(R.id.PT1);
         t1.setText(s1);
         t2=(TextView) findViewById(R.id.PT2);
         t2.setText(s2);
         t3=(TextView) findViewById(R.id.O1);
         t3.setText(s3);
         t4=(TextView) findViewById(R.id.S1);

         //t5 = (TextView) findViewById(bat);
         //t5.setText(Bat);

         //t6 = (TextView) findViewById(R.id.bowl);
         //t6.setText(Bowl);


         t7 = (TextView) findViewById(R.id.PT11);
         t8 = (TextView) findViewById(R.id.PT12);

         t9 = (TextView) findViewById(R.id.left);
          tw = (TextView) findViewById(R.id.wide);
          tn = (TextView) findViewById(R.id.noball);
         tw.setText(""+w);
         tn.setText(""+nballs);
        animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom);


         on= (TextView) findViewById(R.id.overnumber);
          on.setText("Over "+1);



         b1 = (Button) findViewById(R.id.buttonplay);

         b2 = (Button) findViewById(R.id.buttonnext);
         b2.setEnabled(false);

        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);
        button3 = (Button) findViewById(R.id.btn3);
        button4 = (Button) findViewById(R.id.btn4);
        button5 = (Button) findViewById(R.id.btn5);
        button6 = (Button) findViewById(R.id.btn6);

    }




      public void play(View view)
      {

                playing();
                overSummary();


             t4.setText(""+score);
                 t9.setText(""+balls);
                // t9.startAnimation(animation);


                 if((balls==0))
                 {
                     out();
                 }

      }



      public void next(View view)
      {
          b1.setEnabled(true);
          b2.setEnabled(false);
          overnum=1;
          on.setText("Over "+overnum);
          nextOver();

          if((t7.getText().equals(""))||(t8.getText().equals("")))
         {

             AlertDialog.Builder ab=new AlertDialog.Builder(Main3Activity.this);
             ab.setMessage("Start 2nd Innings?");


                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Bat.equals("Player 1")) {
                            al.removeAll(al);
                            al.add("Player 2");

                            Toast.makeText(getApplicationContext(),al.get(0),Toast.LENGTH_SHORT).show();

                            al.add("Player 1");
                            Bat="Player 2";
                            gv.setAdapter(new CricAdapter(al,imgs,Main3Activity.this));
                        }
                        else if (Bat.equals("Player 2"))
                        {
                            al.removeAll(al);
                            al.add("Player 1");
                            al.add("Player 2");
                            Toast.makeText(getApplicationContext(),al.get(0),Toast.LENGTH_SHORT).show();
                            Bat="Player 1";
                            gv.setAdapter(new CricAdapter(al,imgs,Main3Activity.this));
                        }

                        balls=overs * 6;

                        t4.setText("" + score);
                        t9.setText("" + balls);
                    }
                });




             ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     dialog.cancel();
                 }
             });


             AlertDialog ad=ab.create();
             ad.setCanceledOnTouchOutside(false);
             ad.show();

         }

          else
         {
             i1 = t7.getText().toString();
             i2 = t8.getText().toString();

             i11 =Integer.parseInt(i1);
             i22=Integer.parseInt(i2);

             if(i11>i22)
             {
                 win="Player 1";
                 score2 = i1;
             }
             else
                 if(i11<i22)
             {
                 win="Player 2";
                 score2 = i2;
             }
             else
                 if(i11==i22)
                 {
                   Intent a= new Intent(Main3Activity.this,Main4Activity.class);
                     startActivity(a);
                 }

             Intent i= new Intent(Main3Activity.this,Main5Activity.class);
             i.putExtra("w",win);
             i.putExtra("s",score2);
             startActivity(i);
         }

      }






    public void playing()
    {
        Random rand=new Random();
        n=rand.nextInt((max-min)+1)+min;
        System.out.print(n);
        if(n==1)
        {
            score=score+1;
            runs = "1" ;
            --balls;
            balls2++;
            color="#FFFF00";

        }
        else
        if(n==2)
        {
            score=score+1;
            w=w+1;
            tw.setText(""+w);
            tw.startAnimation(animation);
        }
        else
        if(n==3)
        {
            score=score+1;
            nballs=nballs+1;
            tn.setText(""+nballs);
            tn.startAnimation(animation);
        }
        else
        if(n==4)
        {
            runs= "4";
            score=score+4;
            --balls;
            balls2++;
            color="#00FF00";
        }
        else
        if(n==5)
        {
            runs= "W";
            --balls;
            balls2++;
            Toast.makeText(getApplicationContext(),"Out",Toast.LENGTH_SHORT).show();
            color="#FF0000";
            out();
        }
        else
        if(n==6)
        {
            runs= "6";
            score=score+6;
            --balls;
            balls2++;
            color="#00FF00";
        }
        else
            if(n==7)
            {
                runs="2";
                score=score+2;
                --balls;
                balls2++;
                color="#FFFF00";
            }
            else
                if(n==8)
                {
                    runs="3";
                    score=score+3;
                    --balls;
                    balls2++;
                    color="#FFFF00";

                }


    }







    public void out()
    {

        score1=score;
        b1.setEnabled(false);
        b2.setEnabled(true);

        if(Bat.equals("Player 1"))
        {
            t7.setText(""+score1);
        }
        if(Bat.equals("Player 2"))
        {
            t8.setText(""+score1);
        }

        score=0;
        w=0;
        nballs=0;
        tn.setText(""+nballs);
        tw.setText(""+w);

    }








    public void overSummary()
    {
        if(balls2==1)
        {
            button1.setText(""+runs);
            GradientDrawable gd= (GradientDrawable) button1.getBackground();
           gd.setColor(Color.parseColor(color));
        }
        else if(balls2==2)
        {
            button2.setText(""+runs);
            GradientDrawable gd= (GradientDrawable) button2.getBackground();
            gd.setColor(Color.parseColor(color));
        }
        else if(balls2==3)
        {
            button3.setText(""+runs);
            GradientDrawable gd= (GradientDrawable) button3.getBackground();
            gd.setColor(Color.parseColor(color));
        }
        else
            if(balls2==4)
            {
                button4.setText(""+runs);
                GradientDrawable gd= (GradientDrawable) button4.getBackground();
                gd.setColor(Color.parseColor(color));
            }
            else
                if(balls2==5)
                {
                    button5.setText(""+runs);
                    GradientDrawable gd= (GradientDrawable) button5.getBackground();
                    gd.setColor(Color.parseColor(color));
                }
                else if(balls2==6)
                    {
                        button6.setText(""+runs);
                        GradientDrawable gd= (GradientDrawable) button6.getBackground();
                        gd.setColor(Color.parseColor(color));
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run()
                            {
                                nextOver();
                                overnum++;
                                on.setText("Over "+overnum);
                            }
                        },1500);

                    }

    }




    public void nextOver()
    {
        balls2=0;
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");


        GradientDrawable gd1= (GradientDrawable) button1.getBackground();
        gd1.setColor(Color.parseColor("#FFFFFF"));

        GradientDrawable gd2= (GradientDrawable) button2.getBackground();
        gd2.setColor(Color.parseColor("#FFFFFF"));

        GradientDrawable gd3= (GradientDrawable) button3.getBackground();
        gd3.setColor(Color.parseColor("#FFFFFF"));

        GradientDrawable gd4= (GradientDrawable) button4.getBackground();
        gd4.setColor(Color.parseColor("#FFFFFF"));

        GradientDrawable gd5= (GradientDrawable) button5.getBackground();
        gd5.setColor(Color.parseColor("#FFFFFF"));

        GradientDrawable gd6= (GradientDrawable) button6.getBackground();
        gd6.setColor(Color.parseColor("#FFFFFF"));
    }



    @Override
    protected void onPause()
    {
        finish();
    }


}


