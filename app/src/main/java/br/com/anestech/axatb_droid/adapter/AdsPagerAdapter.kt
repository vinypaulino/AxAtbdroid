package br.com.anestech.axcalc.activities.main.ads

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import br.com.anestech.axatb_droid.domain.Ad

class AdsPagerAdapter(fm: FragmentManager, var adsList: List<Ad>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return AdsFragment.newInstance(adsList[position].id)
    }

    override fun getCount(): Int {
        return adsList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}