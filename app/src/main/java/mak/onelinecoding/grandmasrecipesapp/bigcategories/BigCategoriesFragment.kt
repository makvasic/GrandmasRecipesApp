package mak.onelinecoding.grandmasrecipesapp.bigcategories

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_big_categories.*
import mak.onelinecoding.grandmasrecipesapp.R
import mak.onelinecoding.grandmasrecipesapp.TabLayoutMediator


class BigCategoriesFragment : Fragment() {

    private val adapter by lazy { BigCategoriesAdapter() }
    private val bigCategoryViewModel by lazy {
        ViewModelProviders.of(this).get(BigCategoriesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_big_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager.adapter = adapter
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon = ContextCompat.getDrawable(
                    view.context,
                    R.drawable.ic_radio_button_unchecked_white_12dp
                )

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.icon = ContextCompat.getDrawable(
                    view.context,
                    R.drawable.ic_radio_button_checked_white_12dp
                )
            }

        })

        TabLayoutMediator(tabLayout, viewPager) { tab, _ ->
            tab.text = null
            tab.icon = ContextCompat.getDrawable(
                view.context,
                R.drawable.ic_radio_button_unchecked_white_12dp
            )
            viewPager.currentItem = bigCategoryViewModel.selectedPosition
        }.attach()

        bigCategoryViewModel.bigCategoriesLiveData
            .observe(this, Observer<List<BigCategory>> { bigCategories ->
                adapter.submitList(bigCategories)
            })
        bigCategoryViewModel.getBigCategories()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.aboutUsFragment -> {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_bigCategoriesFragment_to_aboutUsFragment)
            true
        }

        R.id.searchFragment -> {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_bigCategoriesFragment_to_searchFragment)
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        bigCategoryViewModel.selectedPosition = tabLayout.selectedTabPosition
        super.onStop()
    }
}