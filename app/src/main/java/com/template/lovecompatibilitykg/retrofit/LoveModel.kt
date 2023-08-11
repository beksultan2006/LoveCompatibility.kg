package com.template.lovecompatibilitykg.retrofit

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Author: Dzhaparov Bekmamat
 */
@Entity(tableName = "loveModel")
data class LoveModel(
    @SerializedName("fname") var firstName: String,
    @SerializedName("sname") var secondName: String,
    var percentage: String,
    var result: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int
) : Serializable