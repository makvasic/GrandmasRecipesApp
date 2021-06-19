package mak.onelinecoding.grandmasrecipesapp.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_category.view.*
import mak.onelinecoding.grandmasrecipesapp.R

class CategoriesAdapter :
    ListAdapter<Category, CategoriesAdapter.CategoryHolder>(CategoryCallback()) {

    override fun onCreateViewHolder(root: ViewGroup, position: Int): CategoryHolder {
        return CategoryHolder(
            LayoutInflater.from(root.context)
                .inflate(R.layout.item_category, root, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class CategoryCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }

    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val resDrawableIds = arrayOf(
            R.drawable.category_appetisers,
            R.drawable.category_soups,
            R.drawable.category_with_meat,
            R.drawable.category_chicken,
            R.drawable.category_veal,
            R.drawable.category_beef,
            R.drawable.category_lamb,
            R.drawable.category_kid,
            R.drawable.category_mutton,
            R.drawable.category_pork,
            R.drawable.category_fish,
            R.drawable.category_sauces,
            R.drawable.category_salads,
            R.drawable.category_salty_pies,
            R.drawable.category_sweet_pies,
            R.drawable.category_pastry,
            R.drawable.category_dough,
            R.drawable.category_cookies,
            R.drawable.category_cakes,
            R.drawable.category_toppings,
            R.drawable.category_ice_cream,
            R.drawable.category_creams,
            R.drawable.category_drinks,
            R.drawable.category_soups1
        )

        fun bind(category: Category) = with(itemView) {
            itemView.setOnClickListener {
                val action =
                    CategoriesFragmentDirections.actionCategoriesFragmentToRecipesFragment(category)
                Navigation.findNavController(it).navigate(action)
            }
            categoryNameTextView.text = category.name
            categoryImageView.setImageResource(resDrawableIds[category.id - 1])

        }
    }
}
