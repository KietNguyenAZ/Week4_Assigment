package com.example.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.onboarding.databinding.LogInBinding
import com.example.onboarding.databinding.SignUpBinding
import java.lang.ProcessBuilder.Redirect.to


class SignIn:AppCompatActivity(){
    private lateinit var binding: LogInBinding
    private lateinit var viewModel: SignInViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.log_in)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)

        binding.LoginToFrofile.setOnClickListener(){
            clickLogIn()
        }

        binding.txtSignUp.setOnClickListener(){
            startActivity(Intent(this, SignUp::class.java))
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

                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, RestaurantMainActivity::class.java)
                startActivity(intent)

            }
        }
    }

    private fun clickLogIn(){
        val email = binding.editTextTextEmailAddressForLogIn.text.toString().trim()
        val password = binding.editTextTextPasswordLogIn.text.toString().trim()
        viewModel.checkOnClick(email,password)
}
}

