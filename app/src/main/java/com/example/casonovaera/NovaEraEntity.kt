package com.example.casonovaera

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "novaEra_table")
data class NovaEraEntity(@SerializedName("id")
                         @PrimaryKey val id: Int,
                         @SerializedName("name") val name: String,
                         @SerializedName("price") val price: Int,
                         @SerializedName ("image") val image: String) {

}
