package com.assignment.one.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @SerializedName("title")
    var productName: String,

    @SerializedName("price")
    var price: Float,

    @SerializedName("image")
    var imageUrl: String,

    @SerializedName("description")
    var description: String
) : Parcelable