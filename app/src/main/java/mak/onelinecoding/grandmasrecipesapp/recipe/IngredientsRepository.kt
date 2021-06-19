package mak.onelinecoding.grandmasrecipesapp.recipe

class IngredientsRepository(private val ingredientsDao: IngredientsDao) {
    suspend fun ingredients(recipeId: Int): List<Ingredient> =
        ingredientsDao.ingredients(recipeId)
}