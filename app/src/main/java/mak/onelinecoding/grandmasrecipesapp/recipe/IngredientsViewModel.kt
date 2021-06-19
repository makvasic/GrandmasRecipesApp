package mak.onelinecoding.grandmasrecipesapp.recipe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mak.onelinecoding.grandmasrecipesapp.AppDatabase

class IngredientsViewModel(application: Application) : AndroidViewModel(application) {

    private val _ingredientsLiveData = MutableLiveData<List<Ingredient>>()
    val ingredientsLiveData: LiveData<List<Ingredient>> = _ingredientsLiveData

    private val ingredientsRepository by lazy {
        IngredientsRepository(AppDatabase.getInstance(getApplication()).ingredientsDao())
    }

    fun getIngredients(recipeId: Int) {
        if (_ingredientsLiveData.value != null) return

        viewModelScope.launch {
            _ingredientsLiveData.value = ingredientsRepository.ingredients(recipeId)

        }

    }

}