package mak.onelinecoding.grandmasrecipesapp.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_categories.*
import mak.onelinecoding.grandmasrecipesapp.BaseFragment
import mak.onelinecoding.grandmasrecipesapp.R
import mak.onelinecoding.grandmasrecipesapp.bigcategories.BigCategory

class CategoriesFragment : BaseFragment() {

    private val adapter = CategoriesAdapter()

    private lateinit var bigCategory: BigCategory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bigCategory = CategoriesFragmentArgs.fromBundle(arguments!!).bigCategory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_categories, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val categoriesViewModel = ViewModelProviders
            .of(this)
            .get(CategoriesViewModel::class.java)
        categoriesViewModel.categoriesLiveData
            .observe(this, Observer<List<Category>> { categories ->
                adapter.submitList(categories)
            })
        categoriesViewModel.getCategories(bigCategory.id)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = bigCategory.name
    }
}