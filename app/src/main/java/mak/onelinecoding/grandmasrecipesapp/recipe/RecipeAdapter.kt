package mak.onelinecoding.grandmasrecipesapp.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_ingredient.view.*
import kotlinx.android.synthetic.main.item_label.view.*
import kotlinx.android.synthetic.main.item_recipe_prep.view.*
import mak.onelinecoding.grandmasrecipesapp.R
import mak.onelinecoding.grandmasrecipesapp.recipes.Recipe

class RecipeAdapter : ListAdapter<Any, RecipeAdapter.ItemHolder>(RecipeAdapter.ItemCallback()) {

    override fun onCreateViewHolder(root: ViewGroup, viewType: Int): ItemHolder = when (viewType) {
        LABEL_VIEW_TYPE -> LabelHolder(
            LayoutInflater.from(root.context).inflate(R.layout.item_label, root, false)
        )
        RECIPE_PREP_VIEW_TYPE -> RecipePrepHolder(
            LayoutInflater.from(root.context).inflate(R.layout.item_recipe_prep, root, false)
        )
        else -> IngredientHolder(
            LayoutInflater.from(root.context).inflate(R.layout.item_ingredient, root, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        when (val item = getItem(position)) {
            is String -> (holder as LabelHolder).bind(item)
            is Recipe -> (holder as RecipePrepHolder).bind(item)
            is Ingredient -> (holder as IngredientHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        0, itemCount - 2 -> LABEL_VIEW_TYPE
        itemCount - 1 -> RECIPE_PREP_VIEW_TYPE
        else -> INGREDIENT_VIEW_TYPE
    }

    companion object {
        const val LABEL_VIEW_TYPE = 0
        const val RECIPE_PREP_VIEW_TYPE = 1
        const val INGREDIENT_VIEW_TYPE = 2
    }

    class ItemCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }

    }

    abstract class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class LabelHolder(itemView: View) : ItemHolder(itemView) {
        fun bind(label: String) = with(itemView) {
            labelTextView.text = label
        }
    }

    class IngredientHolder(itemView: View) : ItemHolder(itemView) {
        fun bind(ingredient: Ingredient) = with(itemView) {
            nameTextView.text = ingredient.name
            quantityTextView.text = ingredient.quantity
        }
    }

    class RecipePrepHolder(itemView: View) : ItemHolder(itemView) {
        fun bind(recipe: Recipe) = with(itemView) {
            prepTextView.text = recipe.preparation
        }
    }
}
