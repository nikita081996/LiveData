package com.spartons.livedataexample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
            startActivity(Intent(this, SwitchMap::class.java))
        }

    }
}
