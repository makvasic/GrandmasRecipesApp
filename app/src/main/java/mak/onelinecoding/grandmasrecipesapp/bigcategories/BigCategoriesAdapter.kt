package mak.onelinecoding.grandmasrecipesapp.bigcategories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_big_category.view.*
import mak.onelinecoding.grandmasrecipesapp.R

class BigCategoriesAdapter :
    ListAdapter<BigCategory, BigCategoriesAdapter.BigCategoryHolder>(BigCategoryCallback()) {

    override fun onCreateViewHolder(
        root: ViewGroup,
        position: Int
    ): BigCategoriesAdapter.BigCategoryHolder =
        BigCategoriesAdapter.BigCategoryHolder(
            LayoutInflater.from(root.context)
                .inflate(R.layout.item_big_category, root, false)
        )

    override fun onBindViewHolder(holder: BigCategoriesAdapter.BigCategoryHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BigCategoryCallback : DiffUtil.ItemCallback<BigCategory>() {
        override fun areItemsTheSame(oldItem: BigCategory, newItem: BigCategory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BigCategory, newItem: BigCategory): Boolean {
            return oldItem == newItem
        }

    }

    class BigCategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val bigCategoryImagesResIds = intArrayOf(
            R.drawable.big_category_0,
            R.drawable.big_category_1,
            R.drawable.big_category_2,
            R.drawable.big_category_3,
            R.drawable.big_category_4,
            R.drawable.big_category_5
        )

        fun bind(bigCategory: BigCategory) = with(itemView) {
            bigCategoryImageView.setImageResource(bigCategoryImagesResIds[adapterPosition])
            bigCategoryTextView.text = bigCategory.name
            setOnClickListener {
                val action =
                    BigCategoriesFragmentDirections.actionBigCategoriesFragmentToCategoriesFragment(
                        bigCategory
                    )
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}
