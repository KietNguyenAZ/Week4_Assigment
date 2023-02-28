package com.example.onboarding

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
        val splashScreen = installSplashScreen()
        setContentView(R.layout.activity_main)
        val button = findViewById<ImageButton>(R.id.move_screen)
        val it2 = Intent(this, Onboarding2::class.java)
        button.setOnClickListener(){
            startActivity(it2)
        }

    }

}