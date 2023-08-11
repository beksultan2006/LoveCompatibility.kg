package com.template.lovecompatibilitykg.sharedPreferences.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE

class Preferences(context: Context) {
    //Это класс, который будет использоваться для работы с настройками SharedPreferences.
    //Класс Preferences имеет конструктор, который принимает context,
    //чтобы можно было получить доступ к настройкам приложения.
    companion object {
        const val PREFERENCES_NAME = "MyPreferences"
    }

    private var preferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
    /*
    SharedPreferences - это механизм
    для сохранения простых данных в виде пар ключ-значение.
    Здесь мы создаем экземпляр SharedPreferences, используя имя файла "MyPreferences" и режим
    MODE_PRIVATE, что означает, что файл настроек будет доступен только для текущего приложения.
     */
    fun saveBoardState() {
        preferences.edit().putBoolean("isShow", true).apply()
    }
    /*
    Это метод, который сохраняет состояние "доски" (или чего-то еще) в настройках приложения.
    Здесь, используя метод edit() объекта SharedPreferences, мы получаем экземпляр
    SharedPreferences.Editor, который позволяет нам редактировать настройки.
    Затем мы вызываем putBoolean("isShow", true), чтобы сохранить значение "true" по ключу "isShow".
    Затем вызываем apply(), чтобы применить изменения в файле настроек.
     */
    fun isBoardShow(): Boolean {
        return preferences.getBoolean("isShow", false)
    }
    /*
    Это метод, который возвращает состояние "доски". Здесь, используя метод getBoolean("isShow", false),
    мы извлекаем значение по ключу "isShow" из файла настроек. Если значение "isShow" не найдено в файле,
    то метод вернет значение по умолчанию "false".
     */
}
