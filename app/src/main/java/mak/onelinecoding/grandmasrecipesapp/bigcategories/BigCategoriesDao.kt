package mak.onelinecoding.grandmasrecipesapp.bigcategories

import androidx.room.Dao
import androidx.room.Query

@Dao
interface BigCategoriesDao {
    @Query("SELECT * FROM big_categories")
    suspend fun getBigCategories(): List<BigCategory>

}