package mak.onelinecoding.grandmasrecipesapp.categories

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Keep
@SuppressLint("ParcelCreator")
@Parcelize
@Entity(tableName = "categories")
data class Category(
    @PrimaryKey var id: Int = 0,
    var name: String = "",
    @ColumnInfo(name = "big_category_id") var bigCategoryId: Int = 0
) : Parcelable