package com.example.exam3

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(var name:String,var secondName:String,var email:String):Parcelable