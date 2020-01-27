package com.spartons.livedataexample

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.live_data_map_example.mapActivityRootView
import kotlinx.android.synthetic.main.live_data_mediator_example.*

class LiveDataMediatorExample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.live_data_mediator_example)
        val viewModel = ViewModelProviders.of(this).get(MediatorLiveDataViewModel::class.java)
        viewModel.fullName.observe(this, Observer<String> { t ->
            Snackbar.make(mapActivityRootView, t!!, Snackbar.LENGTH_SHORT).show()
        })
        addFirstNameButton.setOnClickListener {
            viewModel.addNewUser(firstNameEt.text.toString(), true)
        }
        addLastNameButton.setOnClickListener {
            viewModel.addNewUser(lastNameEt.text.toString(), false)
        }
    }
}

class MediatorLiveDataViewModel : ViewModel() {

    private val firstNameLiveData = MutableLiveData<String>()

    private val lastNameLiveData = MutableLiveData<String>()

    val fullName = MediatorLiveData<String>()

    /*var userAddedData: LiveData<String> = Transformations.map(fullName, ::someFunc)

    private fun someFunc(user: String) = "New user ${user} added to database!"
*/
    init {
        fullName.addSource(firstNameLiveData) { result: String? ->
            result?.let { fullName.value = concateFullName(it, true) }
        }

        fullName.addSource(lastNameLiveData) { result: String? ->
            result?.let { fullName.value = concateFullName(it, false) }
        }
    }

    fun concateFullName(name: String, bool: Boolean): String {
        if (bool)
            return name + lastNameLiveData.value
        else
            return firstNameLiveData.value + name
    }

    fun addNewUser(user: String, bool: Boolean) = apply {
        if (bool)
            firstNameLiveData.value = user
        else
            lastNameLiveData.value = user
    }
}