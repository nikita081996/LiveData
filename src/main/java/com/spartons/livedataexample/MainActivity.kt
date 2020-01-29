package com.spartons.livedataexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spartons.livedataexample.livedata.LiveDataExample
import com.spartons.livedataexample.map.LiveDataMapExample
import com.spartons.livedataexample.mediator_livedata.LiveDataMediatorExample
import com.spartons.livedataexample.swichmap.LiveDataSwitchMapExample
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        liveDataExampleButton.setOnClickListener {
            startActivity(Intent(this, LiveDataExample::class.java))
        }
        liveDataMediatorExampleButton.setOnClickListener {
            startActivity(Intent(this, LiveDataMediatorExample::class.java))
        }
        liveDataMapExampleButton.setOnClickListener {
            startActivity(Intent(this, LiveDataMapExample::class.java))
        }
        liveDataSwitchMapExampleButton.setOnClickListener {
            startActivity(Intent(this, LiveDataSwitchMapExample::class.java))
        }

    }
}
