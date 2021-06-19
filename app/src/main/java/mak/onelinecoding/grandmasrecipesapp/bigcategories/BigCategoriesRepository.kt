package mak.onelinecoding.grandmasrecipesapp.bigcategories

class BigCategoriesRepository(private val bigCategoriesDao: BigCategoriesDao) {
    suspend fun getBigCategories(): List<BigCategory> {
        return bigCategoriesDao.getBigCategories()
    }
}