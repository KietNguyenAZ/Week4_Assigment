package com.example.onboarding

import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel:ViewModel() {
    private var _isSuccessEvent: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessEvent: LiveData<Boolean>
        get() = _isSuccessEvent

    private var _isErrorEvent: MutableLiveData<String> = MutableLiveData()
    val isErrorEvent: LiveData<String>
        get() = _isErrorEvent

    fun checkOnClick(email: String, password: String) {
        var _error = ""
        val findData = DataStore.userData.find { userData -> userData["email"] == email }

        if (findData == null) {
            _error += "Invalid Email/Password"
        }
        else
        {
            if(password != findData["password"])
                _error += "Password is not correct!"
        }

        if(_error.isNotEmpty())
            return _isErrorEvent.postValue(_error)

        if (findData != null) {
            DataStore.currentUser = findData
        }

        _isSuccessEvent.postValue(true)
    }
}
