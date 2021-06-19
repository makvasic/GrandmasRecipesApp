package mak.onelinecoding.grandmasrecipesapp.recipes

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Keep
@SuppressLint("ParcelCreator")
@Parcelize
@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey var id: Int = 0,
    var category_id: Int = 0,
    var name: String = "",
    var preparation: String = ""
) : Parcelable