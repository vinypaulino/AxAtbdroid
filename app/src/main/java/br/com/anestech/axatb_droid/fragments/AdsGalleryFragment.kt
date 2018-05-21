package br.com.anestech.axcalc.activities.main.ads


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.anestech.axatb_droid.R
import br.com.anestech.axatb_droid.domain.Ad
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_ad_gallery.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * A simple [Fragment] subclass.
 * Use the [AdsGalleryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdsGalleryFragment : Fragment() {

    private lateinit var ads:MutableList<Ad>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = false

        val realm = Realm.getDefaultInstance()
        ads = realm.copyFromRealm(realm.where(Ad::class.java).findAll())
        realm.close()
        //sendAnalytics()
    }

    fun sendAnalytics(){
//        val tracker = AppCache.getDefaultTracker(activity!!.applicationContext)
//        tracker.setScreenName("AnnouncementsView - Galeria de ADS")
//        tracker.send(HitBuilders.ScreenViewBuilder().build())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ad_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerAds.adapter = AdsPagerAdapter(childFragmentManager,ads)
        indicator.setViewPager(viewPagerAds)

        btCloseAds.onClick {
            activity?.onBackPressed()
        }
    }


    override fun onResume() {
        super.onResume()
   //     activity?.app_bar_main?.visibility = View.GONE
    }

    override fun onDestroyView() {
 //       activity?.app_bar_main?.visibility = View.VISIBLE
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): AdsGalleryFragment {
            val fragment = AdsGalleryFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
