package mak.onelinecoding.grandmasrecipesapp.search

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import mak.onelinecoding.grandmasrecipesapp.BaseFragment
import mak.onelinecoding.grandmasrecipesapp.R
import mak.onelinecoding.grandmasrecipesapp.recipes.Recipe
import mak.onelinecoding.grandmasrecipesapp.recipes.RecipesAdapter
import mak.onelinecoding.grandmasrecipesapp.recipes.RecipesViewModel


class SearchFragment : BaseFragment(), SearchView.OnQueryTextListener {

    private val adapter by lazy { RecipesAdapter() }

    private val searchViewModel by lazy {
        ViewModelProviders.of(this).get(RecipesViewModel::class.java)
    }

    private var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        query = savedInstanceState?.getString(QUERY_STATE) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchViewModel.searchLiveData.observe(this, Observer<List<Recipe>> {
            if (it.isEmpty()) {
                emptyTextView.visibility = View.VISIBLE
            } else {
                emptyTextView.visibility = View.GONE
            }
            adapter.submitList(it)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_view, menu)
        val searchView = menu.findItem(R.id.menu_item_search_view).actionView as SearchView
        searchView.setIconifiedByDefault(false)
        searchView.queryHint = getString(R.string.search_query_hint)
        searchView.setQuery(query, false)
        searchView.setOnQueryTextListener(this)
        if (query.trim().length < 2) {
            searchView.requestFocus()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(QUERY_STATE, query)
        super.onSaveInstanceState(outState)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return if (query.trim().length < 2) {
            Toast.makeText(context, R.string.search_error_too_short, Toast.LENGTH_SHORT).show()
            true
        } else {
            searchViewModel.search(query, radioGroup.checkedRadioButtonId)
            false
        }
    }

    override fun onQueryTextChange(newText: String): Boolean {
        query = newText
        return true
    }

    companion object {
        private const val QUERY_STATE = "state_query"
    }
}