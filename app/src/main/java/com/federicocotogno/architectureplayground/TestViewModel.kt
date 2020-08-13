package com.federicocotogno.architectureplayground

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//must never reference a view

class TestViewModel : ViewModel() {
    //create a list of users
    var listOfUsers = mutableListOf<User>()
    //create age for the users
    var age = 0

    // Create a LiveData with a mutableListOf<User>
    val currentUsers: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>()
    }
}