package com.mubassyir.submissionjetpack3.tabs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mubassyir.submissionjetpack3.ui.network.MovieFragment
import com.mubassyir.submissionjetpack3.R
import com.mubassyir.submissionjetpack3.ui.network.TvShowFragment

class NetworkFragment : Fragment(){


    companion object {
        var items: Int = 2
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_network, null)

        val tabLayout = root.findViewById(R.id.tl_networks) as TabLayout
        val viewPager = root.findViewById(R.id.vp_network) as ViewPager

        viewPager.adapter = MyAdapter(childFragmentManager)
        tabLayout.post{ tabLayout.setupWithViewPager(viewPager)}

        return root
    }

    internal inner class MyAdapter(fm: FragmentManager): FragmentPagerAdapter(fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ){
        override fun getItem(position: Int): Fragment {
            var fragment: Fragment? = null
            when(position){
                0-> fragment =
                    MovieFragment()
                1-> fragment =
                    TvShowFragment()
            }
            return fragment as Fragment
        }

        override fun getCount(): Int {
            return items
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when(position){
                0-> return getString(R.string.movie)
                1-> return getString(R.string.tv_show)
            }
            return null
        }
    }

}