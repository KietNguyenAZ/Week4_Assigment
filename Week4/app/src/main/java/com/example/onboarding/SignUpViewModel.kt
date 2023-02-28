package com.example.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {
    private var _isSuccessEvent: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessEvent: LiveData<Boolean>
        get() = _isSuccessEvent

    private var _isErrorEvent: MutableLiveData<String> = MutableLiveData()
    val isErrorEvent: LiveData<String>
        get() = _isErrorEvent


    fun checkSignUpFormat(fullname: String, email: String, password: String) {
        //kiem tra format email
        if (fullname.isNullOrEmpty()){
            _isErrorEvent.postValue("Mời nhập tên")
        }
        val isValidEmail = isEmailValid(email)
        if (!isValidEmail) {
            _isErrorEvent.postValue("Email không hợp lệ")
            return
        }
        //password length > 8 && < 10
        val isValidPassword = isPasswordValid(password)
        if (!isValidPassword) {
            _isErrorEvent.postValue("password không hợp lệ")
            return
        }
        if (CheckExistEmail(email))
        {
            _isErrorEvent.postValue("Email exist")
        }
        _isSuccessEvent.postValue(true)
    }

    private fun isEmailValid(email:String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }
    private fun isPasswordValid(password: String): Boolean{
        return password.length in 8..10
    }

    private fun CheckExistEmail(email: String): Boolean {
        val isExistEmail = DataStore.userData.find { userData -> userData["email"] == email }?.isNotEmpty()
        return isExistEmail == true
    }
}