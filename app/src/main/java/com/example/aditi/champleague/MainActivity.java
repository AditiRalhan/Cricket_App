package com.example.aditi.champleague;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {


    ProgressDialog pd;

    MediaPlayer m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        m=MediaPlayer.create(getApplicationContext(),R.raw.abc);
        m.start();
        prog();

    /*    new Handler().postDelayed(new Runnable()

        {
            public void run()
            {
              prog();

               // pd.show();
            }
        }, 1000);

*/




    }
    public void prog()
    {


        pd = new ProgressDialog(MainActivity.this);

        pd.setMessage("Loading..");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMax(100);
        pd.getWindow().setGravity(Gravity.BOTTOM);
        //pd.setIndeterminate(true);
        pd.show();


        new Thread(new Runnable() {
            @Override
            public void run() {

                try{

                    while (pd.getProgress()<=pd.getMax())
                    {
                        Thread.sleep(200);
                        pd.incrementProgressBy(5);
                        if(pd.getProgress()==pd.getMax())
                        {
                            pd.dismiss();
                            Intent mainIntent = new Intent(MainActivity.this, Main2Activity.class);
                            startActivity(mainIntent);
                            break;




                        }
                    }



                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        }).start();

    }


    public void onPause()
    {
        super.onPause();
        m.release();
        finish();

    }
}











