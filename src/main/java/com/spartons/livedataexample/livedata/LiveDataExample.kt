package com.spartons.livedataexample.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.spartons.livedataexample.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.live_data_example.*
import java.util.*
import java.util.concurrent.TimeUnit

class LiveDataExample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.live_data_example)
        val timeChangerViewModel = ViewModelProviders.of(this).get(LiveDataViewModel::class.java)
        val calendar = Calendar.getInstance()
        timeChangerViewModel.timerValue.observe(this, Observer<Long> { t ->
            calendar?.timeInMillis = t!!
            timTextView.text = calendar.time.toString()
        })
    }
}

