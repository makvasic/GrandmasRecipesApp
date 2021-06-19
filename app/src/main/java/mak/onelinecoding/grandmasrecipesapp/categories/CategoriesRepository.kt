package mak.onelinecoding.grandmasrecipesapp.categories

class CategoriesRepository(private val categoriesDao: CategoriesDao) {
    suspend fun getCategories(bigCategoryId: Int): List<Category> =
        categoriesDao.categories(bigCategoryId)
}