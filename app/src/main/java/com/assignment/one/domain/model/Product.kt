package com.assignment.one.domain.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("title")
    var productName: String,

    @SerializedName("image")
    var imageUrl: String
)