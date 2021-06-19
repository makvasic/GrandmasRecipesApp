package mak.onelinecoding.grandmasrecipesapp.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recipe.*
import mak.onelinecoding.grandmasrecipesapp.BaseFragment
import mak.onelinecoding.grandmasrecipesapp.R
import mak.onelinecoding.grandmasrecipesapp.recipes.Recipe

class RecipeFragment : BaseFragment() {

    val recipe: Recipe by lazy { RecipeFragmentArgs.fromBundle(arguments!!).recipe }
    private val adapter by lazy { RecipeAdapter() }
    private val items by lazy {
        arrayListOf(
            getString(R.string.recipe_ingredients),
            getString(R.string.recipe_preparation),
            recipe
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_recipe, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.submitList(items)
        recyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val ingredientsViewModel = ViewModelProviders.of(this).get(IngredientsViewModel::class.java)
        ingredientsViewModel.ingredientsLiveData
            .observe(this, Observer<List<Ingredient>> {
                val items = ArrayList(this.items)
                items.addAll(1, it as ArrayList)
                adapter.submitList(items)
            })
        ingredientsViewModel.getIngredients(recipe.id)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = recipe.name
    }
}