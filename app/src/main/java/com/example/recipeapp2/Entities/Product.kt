package com.example.recipeapp2.Entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id:String,
    val title : String,
    val price : Double,
    val description:String,
    val category : String,
    val image : String,
    val rating : Rating
):Parcelable

@Parcelize
data class Rating(
    val rate:Double,
    val count:Int
):Parcelable
