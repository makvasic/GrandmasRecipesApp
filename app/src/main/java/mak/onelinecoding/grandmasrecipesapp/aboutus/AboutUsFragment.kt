package mak.onelinecoding.grandmasrecipesapp.aboutus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mak.onelinecoding.grandmasrecipesapp.BaseFragment
import mak.onelinecoding.grandmasrecipesapp.R

class AboutUsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_about_us, container, false)
}