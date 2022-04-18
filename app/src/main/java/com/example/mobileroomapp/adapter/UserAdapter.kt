package com.example.mobileroomapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileroomapp.R
import com.example.mobileroomapp.database.UserDatabase
import com.example.mobileroomapp.database.model.UserModel
import kotlinx.android.synthetic.main.item_user.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserAdapter(val data :ArrayList<UserModel>) : RecyclerView.Adapter<UserViewHolder>() {
    lateinit var parent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        this.parent =parent

        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false))


    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(this@UserAdapter ,position)



    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun deleteDataAt( position: Int){
        GlobalScope.launch {

            val deleteData = UserDatabase.getInstance(parent.context).userDao()
                .delete(data.get(position))

            data.clear()
            data.addAll( ArrayList(UserDatabase.getInstance(parent.context).userDao().getAll()))
            val mainExecutor = ContextCompat.getMainExecutor(parent.context)

            // Execute a task in the main thread
            mainExecutor.execute {
                // You code logic goes here.
                notifyDataSetChanged()
            }

        }

    }
}