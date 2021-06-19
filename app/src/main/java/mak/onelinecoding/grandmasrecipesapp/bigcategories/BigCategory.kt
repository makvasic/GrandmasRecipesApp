package mak.onelinecoding.grandmasrecipesapp.bigcategories

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Keep
@SuppressLint("ParcelCreator")
@Parcelize
@Entity(tableName = "big_categories")
data class BigCategory(
    @PrimaryKey var id: Int = 0,
    var name: String = ""
) : Parcelable