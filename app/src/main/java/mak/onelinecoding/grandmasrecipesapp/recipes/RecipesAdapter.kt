package mak.onelinecoding.grandmasrecipesapp.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recipe.view.*
import mak.onelinecoding.grandmasrecipesapp.R

class RecipesAdapter : ListAdapter<Recipe, RecipesAdapter.RecipeHolder>(RecipeCallback()) {

    override fun onCreateViewHolder(root: ViewGroup, position: Int): RecipeHolder =
        RecipeHolder(
            LayoutInflater.from(root.context)
                .inflate(R.layout.item_recipe, root, false)
        )

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RecipeCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }

    }

    class RecipeHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(recipe: Recipe) = with(itemView) {
            setOnClickListener {
                Navigation.findNavController(it).navigate(
                    RecipesFragmentDirections.actionRecipesFragmentToRecipeFragment(recipe)
                )
            }
            recipeNameTextView.text = recipe.name
        }
    }
}
