package com.template.lovecompatibilitykg.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.template.lovecompatibilitykg.retrofit.LoveModel

/**
 * Author: Dzhaparov Bekmamat
 */
@Database(entities = [LoveModel::class], version = 2)
abstract class LoveDatabase : RoomDatabase() {
    abstract fun loveDao(): LoveDao
}