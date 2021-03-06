package com.spartons.livedataexample.map

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.spartons.livedataexample.R
import com.spartons.livedataexample.adapter.UserAdapter
import com.spartons.livedataexample.model.User
import com.spartons.livedataexample.utility.HideKeyboard.hideKeyboard
import kotlinx.android.synthetic.main.live_data_map_example.*

class LiveDataMapExample : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.addUserButton -> {
                if (addNewEditText.text.toString() != "") {
                    viewModel.addNewUser(addNewEditText.text.toString())
                    addNewEditText.text.clear()
                    hideKeyboard()
                }
            }
            R.id.updateUserButton -> {
                if (newnameEditText.text.toString() != "") {
                    viewModel.updateList(newnameEditText.text.toString())
                    newnameEditText.text.clear()
                    hideKeyboard()
                }
            }
        }
    }

    private val userList: MutableList<User> = ArrayList()
    private lateinit var userAdapter: UserAdapter
    private lateinit var viewModel: MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.live_data_map_example)

        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        userAdapter = UserAdapter(this, userList)
        userRecyclerView.adapter = userAdapter

        viewModel = ViewModelProviders.of(this).get(MapViewModel::class.java)

        addUserButton.setOnClickListener(this)
        updateUserButton.setOnClickListener(this)

        viewModel.userAddedData?.observe(this, Observer {
            if (userList.isNotEmpty())
                userList.clear()
            userList.addAll(it!!)
            userAdapter.notifyDataSetChanged()
        })
    }


}

