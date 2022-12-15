package com.mochire.tech.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mochire.tech.models.Conditions
import com.mochire.tech.repository.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConditionsViewModel : ViewModel() {
    private val repository = ApiRepository()
    var returnedConditions = mutableListOf<Conditions>()

    fun getConditions() {
        CoroutineScope(Dispatchers.Main.immediate).launch {
            repository.getConditions(30)
            val conditions = mutableListOf<Conditions>()
            repository.allConditions.forEach() {
                conditions.add(it)
            }
            returnedConditions = conditions

            Log.d("conditions", returnedConditions.size.toString())
        }
    }

}