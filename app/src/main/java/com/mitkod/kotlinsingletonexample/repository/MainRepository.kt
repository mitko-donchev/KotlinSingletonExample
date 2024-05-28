package com.mitkod.kotlinsingletonexample.repository

import androidx.lifecycle.LiveData
import com.mitkod.kotlinsingletonexample.api.RetrofitBuilder
import com.mitkod.kotlinsingletonexample.model.User
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object MainRepository {

    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<User> {
        job = Job()
        return object : LiveData<User>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = RetrofitBuilder.apiService.getUser(userId)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs() = job?.cancel()
}