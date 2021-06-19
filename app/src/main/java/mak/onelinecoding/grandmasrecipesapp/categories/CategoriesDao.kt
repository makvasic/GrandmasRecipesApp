package mak.onelinecoding.grandmasrecipesapp.categories

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CategoriesDao {
    @Query("SELECT * FROM categories WHERE big_category_id = :bigCategoryId")
    suspend fun categories(bigCategoryId: Int): List<Category>

}