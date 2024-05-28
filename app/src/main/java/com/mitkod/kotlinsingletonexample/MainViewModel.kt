package com.mitkod.kotlinsingletonexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.mitkod.kotlinsingletonexample.model.User
import com.mitkod.kotlinsingletonexample.repository.MainRepository

class MainViewModel : ViewModel() {

    private val userId: MutableLiveData<String> = MutableLiveData()

    val user: LiveData<User> = userId.switchMap {
        MainRepository.getUser(it)
    }

    fun setUserId(newUserId: String) {
        if (userId.value == newUserId) return
        userId.value = newUserId
    }

    fun cancelJobs() = MainRepository.cancelJobs()
}