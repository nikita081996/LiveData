package com.spartons.livedataexample.livedata

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class LiveDataViewModel : ViewModel() {

    val timerValue = MutableLiveData<Long>()

    init {
        Log.e("Model", "initialize")
        timerValue.value = System.currentTimeMillis()
        startTimer()
    }

    private fun startTimer() {
        Observable.interval(1, 1, TimeUnit.SECONDS)
                .subscribe({
                    Log.e("Model", it.toString())
                    timerValue.postValue(System.currentTimeMillis())
                }, Throwable::printStackTrace)
    }

}