package com.example.mobileroomapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.mobileroomapp.adapter.UserAdapter
import com.example.mobileroomapp.database.UserDatabase
import com.example.mobileroomapp.database.model.UserModel
import kotlinx.android.synthetic.main.activity_add_user.*
import kotlinx.android.synthetic.main.activity_update_user.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateUserActivity : AppCompatActivity() {
    val genderPilih:Array<String> = arrayOf("Male","Female")
    val statusPilih:Array<String> = arrayOf("Single", "Married")

    //reciever
    val bundleIntent :Bundle? = intent.extras
    val nama = bundleIntent?.get("nama")
    val gender = bundleIntent?.get("gender")
    val umur = bundleIntent?.get("umur")
    val status = bundleIntent?.get("status")
    val location = bundleIntent?.get("location")
    var indexGender:Int = 0
    var indexStatus:Int = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        if(gender == "Male"){
            indexGender = 0
        }else {
            indexGender = 1
        }
        if(status == "Single"){
            indexStatus = 0
        }else {
            indexStatus = 1
        }


        txtUpdateFieldName.setText(nama.toString())
        txtEditFieldAge.setText(umur.toString())
        txtUpdateFieldLocation.setText(location.toString())
        spinnerUpdateGender.setSelection(indexGender)
        spinnerUpdateStatus.setSelection(indexStatus)


        val arrayAdapterGender = ArrayAdapter(this@UpdateUserActivity, android.R.layout.simple_spinner_dropdown_item, genderPilih)
        val arrayAdapterStatus = ArrayAdapter(this@UpdateUserActivity, android.R.layout.simple_spinner_dropdown_item, statusPilih)

        spinnerGender.adapter = arrayAdapterGender
        spinnerStatus.adapter = arrayAdapterStatus

        spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        spinnerStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        btnSendData.setOnClickListener({
            val updatedUserData = UserModel(0,txtEditFieldName.text.toString(), spinnerGender.selectedItem.toString(), txtEditFieldAge.text.toString(),spinnerStatus.selectedItem.toString(),txtEditFieldLocation.text.toString())


            GlobalScope.launch {
                UserDatabase.getInstance(this@UpdateUserActivity).userDao().updateUser(updatedUserData)

                val intent = Intent()
                startActivity(intent)
                finish()
            }

        })
    }
}