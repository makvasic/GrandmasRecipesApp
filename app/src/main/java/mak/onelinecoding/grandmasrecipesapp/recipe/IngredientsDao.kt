package mak.onelinecoding.grandmasrecipesapp.recipe

import androidx.room.Dao
import androidx.room.Query

@Dao
interface IngredientsDao {
    @Query("SELECT * FROM ingredients WHERE recipe_id = :recipeId")
    suspend fun ingredients(recipeId: Int): List<Ingredient>

}