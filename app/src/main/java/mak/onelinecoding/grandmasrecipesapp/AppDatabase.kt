package mak.onelinecoding.grandmasrecipesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import mak.onelinecoding.grandmasrecipesapp.bigcategories.BigCategoriesDao
import mak.onelinecoding.grandmasrecipesapp.bigcategories.BigCategory
import mak.onelinecoding.grandmasrecipesapp.categories.CategoriesDao
import mak.onelinecoding.grandmasrecipesapp.categories.Category
import mak.onelinecoding.grandmasrecipesapp.recipe.Ingredient
import mak.onelinecoding.grandmasrecipesapp.recipe.IngredientsDao
import mak.onelinecoding.grandmasrecipesapp.recipes.Recipe
import mak.onelinecoding.grandmasrecipesapp.recipes.RecipesDao

@Database(
    entities = [BigCategory::class, Category::class, Recipe::class, Ingredient::class],
    version = 3,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bigCategoriesDao(): BigCategoriesDao
    abstract fun categoriesDao(): CategoriesDao
    abstract fun recipesDao(): RecipesDao
    abstract fun ingredientsDao(): IngredientsDao

    companion object {

        const val NAME = "recipes.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, NAME
            )
                .createFromAsset("databases/recipes.db")
                .addMigrations(MIGRATION_1_2())
                .addMigrations(MIGRATION_2_3())
                .build()
        }
    }

    class MIGRATION_1_2 : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {

        }
    }

    class MIGRATION_2_3 : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {

        }
    }

}