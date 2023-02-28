package com.example.onboarding

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.onboarding.databinding.ProfileScreenBinding
import com.example.onboarding.databinding.SignUpBinding

class Profile:AppCompatActivity() {

    private lateinit var profileFUllName: TextView
    private lateinit var profileEmail: TextView
    private lateinit var profilePhone: TextView
    private lateinit var profileName: TextView
    private lateinit var editInformation: EditText
    private lateinit var binding: ProfileScreenBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.profile_screen)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)


        binding.fullNameProfile.text = DataStore.currentUser["fullname"]
        binding.emailProfile.text = DataStore.currentUser["email"]
        binding.textView24.text = DataStore.currentUser["full name"]
        binding.phoneProfile.text = DataStore.currentUser["phone"]

        showDialogEditText()
    }

    private fun showDialogEditText() {
        profileFUllName = findViewById(R.id.fullNameProfile)
        profileEmail = findViewById(R.id.emailProfile)
        profilePhone = findViewById(R.id.phoneProfile)
        profileName = findViewById(R.id.textView24)


        profileFUllName.setOnClickListener() {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.custom_dialog, null)
            editInformation = dialogLayout.findViewById<EditText>(R.id.changeInforation)
            with(builder) {

                setPositiveButton(R.string.confirm) { dialog, which ->
                    confirmName()
                }
                setNegativeButton(R.string.cancel) { dialog, which ->
                    Log.d("Main", "Negative Button Clicked")
                }
                setView(dialogLayout)
                show()
            }

        }

        profileEmail.setOnClickListener() {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.custom_dialog, null)


            editInformation = dialogLayout.findViewById<EditText>(R.id.changeInforation)


            with(builder) {

                setPositiveButton(R.string.confirm) { dialog, which ->
                    confirmEmail()
                }
                setNegativeButton(R.string.cancel) { dialog, which ->
                    Log.d("Main", "Negative Button Clicked")
                }
                setView(dialogLayout)
                show()
            }

        }

        profilePhone.setOnClickListener() {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.custom_dialog, null)
            editInformation = dialogLayout.findViewById<EditText>(R.id.changeInforation)
            with(builder) {

                setPositiveButton(R.string.confirm) { dialog, which ->
                    confirmPhone()
                }
                setNegativeButton(R.string.cancel) { dialog, which ->
                    Log.d("Main", "Negative Button Clicked")
                }
                setView(dialogLayout)
                show()
            }
        }


    }

    private fun confirmName() {
        if (editInformation.text.isNullOrBlank()) {
            Toast.makeText(this, "Blank", Toast.LENGTH_SHORT).show()
        } else {
            binding.fullNameProfile.text = editInformation.text
            binding.textView24.text = editInformation.text.toString()

        }
    }

    private fun confirmEmail() {
        if (editInformation.text.isNullOrBlank()) {
            Toast.makeText(this, "Blank", Toast.LENGTH_SHORT).show()
        } else {
            binding.emailProfile.text = editInformation.text
        }
    }

    private fun confirmPhone() {
        if (editInformation.text.isNullOrBlank()) {
            Toast.makeText(this, "Blank", Toast.LENGTH_SHORT).show()
        } else {
            binding.phoneProfile.text = editInformation.text
        }
    }



    }

