package com.example.onboarding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onboarding.databinding.SignUpBinding

class SignUp:AppCompatActivity() {

    private lateinit var binding: SignUpBinding
    private lateinit var viewModel: SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.sign_up)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        binding.buttonSignUp.setOnClickListener(){
            val fullName = binding.editFullName.text.toString().trim()
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            viewModel.checkSignUpFormat(fullName, email, password)
        }

        binding.txtLogIn.setOnClickListener(){
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
        listenerSuccessEvent()
        listenerErrorEvent()
    }
    private fun listenerErrorEvent(){
        viewModel.isErrorEvent.observe(this){ errMsg ->
            Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show()
        }
    }
    private fun listenerSuccessEvent(){
        viewModel.isSuccessEvent.observe(this) {isSuccess ->
            if(isSuccess){
                val fullName = binding.editFullName.text.toString().trim()
                val email = binding.editTextTextEmailAddress.text.toString().trim()
                val password = binding.editTextTextPassword.text.toString().trim()
                DataStore.userData.add(mutableMapOf("fullname" to fullName,
                "email" to email, "password" to password))
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
        }
    }
}