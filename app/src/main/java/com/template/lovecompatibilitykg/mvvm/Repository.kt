package com.template.lovecompatibilitykg.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.template.lovecompatibilitykg.retrofit.LoveApi
import com.template.lovecompatibilitykg.retrofit.LoveModel
import com.template.lovecompatibilitykg.room.LoveDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Author: Dzhaparov Bekmamat
 */
class Repository @Inject constructor(private val loveApi: LoveApi, private val loveDao: LoveDao) {
    // Класс Repository определен и помечен аннотацией @Inject, что позволяет использовать
    // его с фреймворками для внедрения зависимостей.

    // У него есть конструктор, который принимает объект LoveApi в качестве параметра.
    // LoveApi - это интерфейс Retrofit, используемый для выполнения вызовов API.
    fun getAllInformation(): List<LoveModel> {
        val unsortedData = loveDao.getAllData()
        return unsortedData.sortedBy { it.firstName }
    }
    fun getPercentage(firstName: String, secondName: String): MutableLiveData<LoveModel> {
        // Эта функция getPercentage принимает два параметра firstName и secondName,
        // которые представляют имена двух людей, для которых нужно проверить совместимость в любви.
        // Функция возвращает объект MutableLiveData, содержащий результат проверки совместимости
        // в виде объекта типа LoveModel.
        val liveData = MutableLiveData<LoveModel>()
        // Здесь создается объект MutableLiveData типа LoveModel. Этот объект будет содержать результат
        // вызова API и может быть наблюдаемым из компонентов пользовательского интерфейса.
        loveApi.getPercentage(firstName, secondName)
            .enqueue(object : Callback<LoveModel> {
                override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                    liveData.postValue(response.body())
                    loveDao.insert(response.body()!!)
                }

                override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                    Log.e("ololo", t.message.toString())
                }
            })
        // Этот блок кода выполняет фактический вызов API к конечной точке LoveApi.getPercentage с
        // помощью метода enqueue Retrofit. Здесь устанавливаются два обратных вызова для обработки
        // ответа API:
        // onResponse: Этот метод вызывается, если вызов API успешен, и получает объект Response<LoveModel>.
        // Он обновляет объект liveData с телом ответа, которое содержит данные о совместимости в любви.
        // onFailure: Этот метод вызывается, если произошла ошибка при вызове API. Он записывает сообщение
        // об ошибке с использованием Log.e.
        return liveData
        // Наконец, возвращается объект liveData, который содержит результат вызова API.
        // Компоненты пользовательского интерфейса могут наблюдать за этим LiveData,
        // чтобы получить данные о совместимости в любви и соответствующим образом обновить
        // пользовательский интерфейс.
    }
}
// В целом, класс Repository действует как промежуточный слой между ViewModel и API Retrofit,
// обеспечивая четкое разделение обязанностей в архитектуре MVVM.