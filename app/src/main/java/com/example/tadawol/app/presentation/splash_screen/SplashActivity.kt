package com.example.tadawol.app.presentation.splash_screen

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.view.MotionEvent
import android.media.MediaPlayer

import android.widget.VideoView
import android.os.Bundle
import android.os.Handler

import android.view.WindowManager

import android.view.View
import com.example.tadawol.R
import com.example.tadawol.app.presentation.login_activity.Login


class SplashActivity : AppCompatActivity (){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash);


        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.

    }




}