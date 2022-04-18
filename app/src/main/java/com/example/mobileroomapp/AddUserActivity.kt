package com.example.mobileroomapp

import android.app.Activity
import android.content.Intent
import android.icu.text.Transliterator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.mobileroomapp.database.UserDatabase
import com.example.mobileroomapp.database.model.UserModel
import kotlinx.android.synthetic.main.activity_add_user.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddUserActivity : AppCompatActivity() {



    val genderPilih:Array<String> = arrayOf("Male","Female")
    val statusPilih:Array<String> = arrayOf("Single", "Married")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

            val arrayAdapterGender = ArrayAdapter(this@AddUserActivity, android.R.layout.simple_spinner_dropdown_item, genderPilih)
            val arrayAdapterStatus = ArrayAdapter(this@AddUserActivity, android.R.layout.simple_spinner_dropdown_item, statusPilih)

            spinnerGender.adapter = arrayAdapterGender
            spinnerStatus.adapter = arrayAdapterStatus

            spinnerGender.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }

            spinnerStatus.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }

        btnSendData.setOnClickListener({
            val userdata = UserModel(0,txtEditFieldName.text.toString(), spinnerGender.selectedItem.toString(), txtEditFieldAge.text.toString(),spinnerStatus.selectedItem.toString(),txtEditFieldLocation.text.toString())


            GlobalScope.launch {
                UserDatabase.getInstance(this@AddUserActivity).userDao().insertAll(userdata)

                val intent = Intent()
                intent.putExtra("data",userdata)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }

        })
    }
}