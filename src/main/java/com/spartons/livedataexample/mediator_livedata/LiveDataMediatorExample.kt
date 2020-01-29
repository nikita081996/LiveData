package com.spartons.livedataexample.mediator_livedata

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.spartons.livedataexample.R
import com.spartons.livedataexample.utility.HideKeyboard.hideKeyboard
import kotlinx.android.synthetic.main.live_data_mediator_example.*

class LiveDataMediatorExample : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.addFirstNameButton -> {
                if (firstNameEt.text.toString() != "") {
                    viewModel.addName(firstNameEt.text.toString(), true)
                    firstNameEt.text.clear()
                    hideKeyboard()
                }
            }
            R.id.addLastNameButton -> {
                if (lastNameEt.text.toString() != "") {
                    viewModel.addName(lastNameEt.text.toString(), false)
                    lastNameEt.text.clear()
                    hideKeyboard()
                }
            }
        }
    }

    private lateinit var viewModel: MediatorLiveDataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.live_data_mediator_example)
        viewModel = ViewModelProviders.of(this).get(MediatorLiveDataViewModel::class.java)

        addFirstNameButton.setOnClickListener(this)
        addLastNameButton.setOnClickListener(this)

        viewModel.fullName.observe(this, Observer<String> { it ->
            lengthTv.text = "Your name is $it"
        })
    }
}

