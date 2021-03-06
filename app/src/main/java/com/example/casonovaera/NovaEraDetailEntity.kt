package com.example.casonovaera

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "detail_table")
data class NovaEraDetailEntity(@PrimaryKey val id:Int,
                               @SerializedName("name") val name: String,
                               @SerializedName("price") val price: Int,
                               @SerializedName("image") val image: String,
                               @SerializedName("description") val description: String,
                               @SerializedName("lastPrice") val lastPrice: Int,
                               @SerializedName("credit") val credit: Boolean)
