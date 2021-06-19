package mak.onelinecoding.grandmasrecipesapp.recipes

import androidx.room.Dao
import androidx.room.Query

@Dao
interface RecipesDao {
    @Query("SELECT * FROM recipes WHERE category_id = :categoryId")
    suspend fun getRecipes(categoryId: Int): List<Recipe>

    @Query("SELECT * FROM recipes WHERE name LIKE :searchQuery")
    suspend fun recipesByName(searchQuery: String): List<Recipe>

    @Query("SELECT * FROM recipes WHERE id IN (SELECT ingredients.recipe_id FROM ingredients WHERE ingredients.name LIKE :searchQuery)")
    suspend fun recipesByIngredient(searchQuery: String): List<Recipe>

}