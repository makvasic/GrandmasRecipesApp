package mak.onelinecoding.grandmasrecipesapp.recipes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mak.onelinecoding.grandmasrecipesapp.AppDatabase
import mak.onelinecoding.grandmasrecipesapp.R

class RecipesViewModel(application: Application) : AndroidViewModel(application) {

    private val _recipesLiveData = MutableLiveData<List<Recipe>>()
    val recipesLiveData: LiveData<List<Recipe>> = _recipesLiveData


    private val _searchLiveData = MutableLiveData<List<Recipe>>()
    val searchLiveData: LiveData<List<Recipe>> = _searchLiveData

    private val recipesRepository by lazy {
        RecipesRepository(AppDatabase.getInstance(getApplication()).recipesDao())
    }

    fun recipes(categoryId: Int) {
        if (_recipesLiveData.value != null) return
        viewModelScope.launch {
            _recipesLiveData.value = recipesRepository.getRecipes(categoryId)
        }

    }

    fun search(searchQuery: String, searchBy: Int) {
        val query = "%$searchQuery%"
        viewModelScope.launch {
            _searchLiveData.value = when (searchBy) {
                R.id.byIngredientRadioButton -> recipesRepository.searchByIngredient(query)
                else -> recipesRepository.searchByName(query)
            }
        }
    }

}