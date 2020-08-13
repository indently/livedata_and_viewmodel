package com.federicocotogno.architectureplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var model: TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.
        model = ViewModelProvider(this).get(TestViewModel::class.java)

        //observe our changes
        model.currentUsers.observe(this, Observer {
            // Log that the observer is successfully observing
            Log.d("Main", "Observing users")

            // update UI
            tv_textView.text = model.listOfUsers.toString()
        })

        addUsers()
        clearUsers()
    }

    private fun addUsers() {
        btn_button.setOnClickListener {
            //increment age each time we click on the button
            model.age++

            //change the name between Tom and Steve on each click
            val name = if (model.age % 2 == 0) "Tom" else "Steve"

            //add a user to our list of users
            model.listOfUsers.add(User(name, model.age))

            //notify the observer that the value has changed
            model.currentUsers.value = model.listOfUsers

            // log that the user has been added after onClick
            Log.d("Main", "${User(name, model.age)} added")
        }
    }

    private fun clearUsers() {
        btn_clearList.setOnClickListener {

            //clear list of users
            model.listOfUsers.clear()

            //notify list being cleared to our observer
            model.currentUsers.value = model.listOfUsers

            //reset age to 0
            model.age = 0
        }

    }
}