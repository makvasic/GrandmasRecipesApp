package mak.onelinecoding.grandmasrecipesapp.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mak.onelinecoding.grandmasrecipesapp.AppDatabase

class CategoriesViewModel(application: Application) : AndroidViewModel(application) {

    private val _categoriesLiveData = MutableLiveData<List<Category>>()
    val categoriesLiveData: LiveData<List<Category>> = _categoriesLiveData

    private val categoriesRepository by lazy {
        CategoriesRepository(AppDatabase.getInstance(getApplication()).categoriesDao())
    }

    fun getCategories(bigCategoryId: Int) {
        if (_categoriesLiveData.value != null) return

        viewModelScope.launch {
            _categoriesLiveData.value = categoriesRepository.getCategories(bigCategoryId)
        }

    }

}