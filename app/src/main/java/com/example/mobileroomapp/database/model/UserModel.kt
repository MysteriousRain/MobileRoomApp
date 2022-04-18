package com.example.mobileroomapp.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class UserModel(
    @PrimaryKey(autoGenerate = true) val user_id:Int,
    val nama:String,
    val gender:String,
    val umur:String,
    val status:String,
    val Location:String
):Parcelable
