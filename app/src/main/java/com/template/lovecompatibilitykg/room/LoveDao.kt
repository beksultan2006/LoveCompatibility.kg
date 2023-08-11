package com.template.lovecompatibilitykg.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.template.lovecompatibilitykg.retrofit.LoveModel

/**
 * Author: Dzhaparov Bekmamat
 */
@Dao
interface LoveDao {
    @Insert
    fun insert(loveModel: LoveModel)

    @Query("SELECT * FROM loveModel ORDER BY firstName ASC")
    fun getAllData(): List<LoveModel>
}