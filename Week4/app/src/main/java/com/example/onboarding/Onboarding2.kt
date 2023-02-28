package com.example.onboarding

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import kotlinx.coroutines.NonCancellable

class Onboarding2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding2)
        val button = findViewById<ImageButton>(R.id.movescreen1)
        val it3 = Intent(this, Onboarding3::class.java)
        button.setOnClickListener(){
            startActivity(it3)
        }

    }
}