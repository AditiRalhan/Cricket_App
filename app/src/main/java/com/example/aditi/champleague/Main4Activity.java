package com.example.aditi.champleague;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main4Activity extends AppCompatActivity
{
    Button buttonng;


    @Override
    protected void onPause() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        buttonng = (Button) findViewById(R.id.button);
         buttonng.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                 AlertDialog.Builder ab= new AlertDialog.Builder(Main4Activity.this);
                 ab.setMessage("Restart Game?");

                 ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which)
                     {
                         Intent i = new Intent(Main4Activity.this,MainActivity.class);
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

                 AlertDialog ad=ab.create();
                 ad.setCanceledOnTouchOutside(false);
                 ad.show();
             }
         });


    }
}
