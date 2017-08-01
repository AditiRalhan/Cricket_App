package com.example.aditi.champleague;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main2Activity extends AppCompatActivity
{
    TextView tv;
    int min=1;
    int max=2;
    int n;
    String bat,ball;
    String et1;
    String et2;
    String et3;
    int aa;
    EditText e1, e2;
    Spinner e3;

    SQLiteDatabase sd;

    dbhelper dd;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);

        tv= (TextView) findViewById(R.id.Details);
        tv.setPaintFlags(tv.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        e1 = (EditText) findViewById(R.id.edittext1);
        e2 = (EditText) findViewById(R.id.edittext2);
        e3 = (Spinner) findViewById(R.id.spinner1);


    }



    public void toss(View view) {
        Random r=new Random();
        n=r.nextInt((max - min) + 1) + min;
        if (n == 1) {
            bat="Player 1";
            ball="Player 2";

        } else if (n == 2) {

            bat="Player 2";
            ball="Player 1";

        }
        if((e1.getText().toString().equals(""))||(e2.getText().toString().equals("")))
        {
            AlertDialog.Builder ab=new AlertDialog.Builder(Main2Activity.this);
            ab.setMessage("Enter all details");

            ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                }
            });

            AlertDialog ad =ab.create();
            ad.setCanceledOnTouchOutside(false);
            ad.show();

        }
        else
       {
           AlertDialog.Builder ab=new AlertDialog.Builder(Main2Activity.this);
           ab.setMessage("Do you want to start the game?");


           ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {


                   et1=e1.getText().toString();
                   et2=e2.getText().toString();
                   et3=e3.getSelectedItem().toString();
                   Intent i=new Intent(getBaseContext(), Main3Activity.class);
                   i.putExtra("text1", et1);
                   i.putExtra("text2", et2);
                   i.putExtra("text3", et3);
                   i.putExtra("text4", bat);
                   i.putExtra("text5", ball);
                   System.out.println("value is" + aa);
                   startActivity(i);

                   dd=new dbhelper(getApplicationContext());
                   sd=openOrCreateDatabase("mydb.db", Context.MODE_PRIVATE, null);
                   try {
                       dd.insertcontact(et1, et2, et3, "");
                       Toast.makeText(getApplicationContext(), "Data Saved Successfuly", Toast.LENGTH_SHORT).show();


                   } catch (Exception e) {
                       Toast.makeText(getApplicationContext(), "Data not Saved Successfuly", Toast.LENGTH_SHORT).show();
                       e.printStackTrace();
                   }

               }
           });


           ab.setNegativeButton("NO", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   dialog.cancel();
               }
           });


           AlertDialog ad=ab.create();
           ad.setCanceledOnTouchOutside(false);
           ad.show();
       }


    }



    @Override
    protected void onPause() {
        finish();
    }


  /*  protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode==2)
        {
            String m1 = data.getStringExtra("t1");
            Toast.makeText(this,m1,Toast.LENGTH_SHORT).show();
        }
    }*/

}