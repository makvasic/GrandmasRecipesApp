package mak.onelinecoding.grandmasrecipesapp.bigcategories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mak.onelinecoding.grandmasrecipesapp.AppDatabase


class BigCategoriesViewModel(application: Application) : AndroidViewModel(application) {

    var selectedPosition = 0

    private val _bigCategoriesLiveData = MutableLiveData<List<BigCategory>>()

    val bigCategoriesLiveData: LiveData<List<BigCategory>> = _bigCategoriesLiveData

    private val bigCategoriesRepository by lazy {
        BigCategoriesRepository(
            AppDatabase.getInstance(
                getApplication()
            ).bigCategoriesDao()
        )
    }

    fun getBigCategories() {
        if (_bigCategoriesLiveData.value != null) return

        viewModelScope.launch {
            _bigCategoriesLiveData.value = bigCategoriesRepository.getBigCategories()
        }

    }

}