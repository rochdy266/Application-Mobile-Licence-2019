package com.example.devoir_maison;

/**
 * Created by mac on 05/03/2019.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;



public class Splash extends AppCompatActivity {
    /* temps d attente */
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    /* appelé a la creation de l activité. */
    @Override
    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.splash);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splash.this,MainActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

