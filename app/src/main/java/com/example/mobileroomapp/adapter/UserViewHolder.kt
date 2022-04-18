package com.example.mobileroomapp.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileroomapp.AddUserActivity
import com.example.mobileroomapp.MainActivity
import com.example.mobileroomapp.UpdateUserActivity
import kotlinx.android.synthetic.main.activity_add_user.view.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val namauser = view.txtFieldName
    val genderuser = view.txtFielGender
    val umuruser = view.txtFieldAge
    val statususer = view.txtFieldStatus
    val update = view.imgBtnEditUser

    fun bindData(adapter:UserAdapter,position: Int) {


        namauser.setText(adapter.data.get(position).nama)
        genderuser.setText(adapter.data.get(position).gender)
        umuruser.setText(adapter.data.get(position).umur)
        statususer.setText(adapter.data.get(position).status)


//        update.setOnClickListener ({
//            val intent = Intent(View.Conte, UpdateUserActivity::class.java)
//            // kirim data dengan intent extras package
//            intent.putExtra("nama", adapter.data.get(position).nama)
//            intent.putExtra("gender", adapter.data.get(position).gender)
//            intent.putExtra("umur", adapter.data.get(position).umur)
//            intent.putExtra("status", adapter.data.get(position).status)
//            intent.putExtra("location", adapter.data.get(position).Location)
//            itemView.context(startActivity(intent))
//        })
    }


}