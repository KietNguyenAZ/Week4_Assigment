package com.example.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Onboarding3: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding3)
        val button = findViewById<ImageButton>(R.id.move_screen3)
        val it4 = Intent(this, Welcome::class.java)
        button.setOnClickListener(){
            startActivity(it4)
        }

    }
}