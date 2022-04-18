package com.example.mobileroomapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mobileroomapp.adapter.UserAdapter
import com.example.mobileroomapp.database.UserDatabase
import com.example.mobileroomapp.database.model.UserModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == Activity.RESULT_OK){
            if(result.data?.hasExtra("data")!!){
                listUser.add(result.data!!.extras?.getParcelable<UserModel>("data")!!)
            }
            GlobalScope.launch {

                listUser.clear()
                listUser.addAll(ArrayList(getAllData()))

                this@MainActivity.runOnUiThread({
                    userAdapter.notifyDataSetChanged()
                })


            }
        }
    }

    lateinit var userAdapter: UserAdapter
    var listUser = ArrayList<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {

            listUser = ArrayList(getAllData())

            this@MainActivity.runOnUiThread({
                userAdapter = UserAdapter(listUser)
                listItemUser.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = userAdapter
                }
            })



        }

        btnMainAddUser.setOnClickListener({
            val intent = Intent(this@MainActivity,AddUserActivity::class.java)
            resultLauncher.launch(intent)
        })
    }

    fun getAllData():List<UserModel>{

        return UserDatabase.getInstance(this@MainActivity).userDao().getAll()

    }

    fun initData(){

    }

    fun selectAllData(){

    }

    fun deleteData(){

    }

}