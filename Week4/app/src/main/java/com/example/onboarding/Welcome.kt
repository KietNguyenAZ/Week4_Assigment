package com.example.onboarding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Welcome:AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)
        val button = findViewById<ImageButton>(R.id.startwithEorP)
        val clickTxT = findViewById<TextView>(R.id.txtSignIn)
        val signup = Intent(this, SignUp::class.java)
        val signIn = Intent(this, SignIn::class.java )
        clickTxT.setOnClickListener(){
            startActivity(signIn)
        }
        button.setOnClickListener(){
            startActivity(signup)
        }

    }
}