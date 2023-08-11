package com.template.lovecompatibilitykg.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.template.lovecompatibilitykg.R
import com.template.lovecompatibilitykg.sharedPreferences.utils.Preferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    // В этом классе используется внедрение зависимостей (dependency injection) для получения экземпляра
    // Preferences с помощью аннотации @Inject.
    @Inject
    lateinit var preferences: Preferences
    private lateinit var navController: NavController
    // Здесь также объявляется переменная navController, которая представляет контроллер навигации,
    // с помощью которого будет управляться переход между фрагментами.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.host_fragment)
    }
}
