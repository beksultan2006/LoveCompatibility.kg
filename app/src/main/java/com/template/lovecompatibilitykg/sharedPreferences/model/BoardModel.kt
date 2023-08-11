package com.template.lovecompatibilitykg.sharedPreferences.model

/**
 * Author: Dzhaparov Bekmamat
 */
data class BoardModel(
    /*
    BoardModel - это класс данных, который использует ключевое слово data class.
    Классы данных в Kotlin предназначены для хранения данных и автоматически создают
    несколько полезных методов, таких как equals(), hashCode(), toString() и другие.
     */
    val title: String? = null, val description: String? = null, val image: Int
)