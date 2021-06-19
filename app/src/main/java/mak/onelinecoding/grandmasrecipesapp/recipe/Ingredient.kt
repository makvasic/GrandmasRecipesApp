package mak.onelinecoding.grandmasrecipesapp.recipe

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "ingredients")
data class Ingredient(
    @PrimaryKey var id: Int = 0,
    var recipe_id: Int = 0,
    var name: String = "",
    var quantity: String = ""
)