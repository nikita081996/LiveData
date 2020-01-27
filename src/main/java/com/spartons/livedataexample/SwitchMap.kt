package com.spartons.livedataexample

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.live_data_swicth_map_example.addNewEditText
import kotlinx.android.synthetic.main.live_data_swicth_map_example.addUserButton
import kotlinx.android.synthetic.main.switch_map.*

class SwitchMap : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.switch_map)

        val viewModel = ViewModelProviders.of(this).get(UserviewModel::class.java)
        addUserButton.setOnClickListener { viewModel.setId(addNewEditText.text.toString().toInt()) }
        updateUserButton.setOnClickListener { viewModel.updateUser(updateUser.text.toString().toInt()) }
        viewModel.userLiveData.observe(this, Observer {
            Toast.makeText(applicationContext, it?.username + " " + it?.id, Toast.LENGTH_SHORT).show()
        })
    }
}

class UserviewModel : ViewModel() {
    private val userRepo = UserRepo()

    private val userIdLiveData = MutableLiveData<Int>()
    val userLiveData: LiveData<UserModel> = Transformations.switchMap(userIdLiveData, ::searchUserById)

    private fun searchUserById(id: Int) = userRepo.searchUserWithId(id)

    fun updateUser(id: Int) = userRepo.updateUser(id)

    fun setId(id: Int) = apply { userIdLiveData.value = id }
}