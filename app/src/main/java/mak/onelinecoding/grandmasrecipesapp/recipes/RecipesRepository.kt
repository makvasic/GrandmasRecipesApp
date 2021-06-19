package mak.onelinecoding.grandmasrecipesapp.recipes

class RecipesRepository(private val recipesDao: RecipesDao) {
    suspend fun getRecipes(categoryId: Int): List<Recipe> =
        recipesDao.getRecipes(categoryId)

    suspend fun searchByName(query: String): List<Recipe> =
        recipesDao.recipesByName(query)

    suspend fun searchByIngredient(query: String): List<Recipe> =
        recipesDao.recipesByIngredient(query)
}