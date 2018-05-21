package br.com.anestech.axcalc.activities.main.ads


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.anestech.axatb_droid.AppCache
import br.com.anestech.axatb_droid.R
import br.com.anestech.axatb_droid.database.DbHelper
import br.com.anestech.axatb_droid.domain.Ad
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_ads.view.*

class AdsFragment : Fragment(), Callback {
    private lateinit var ad: Ad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = false

        val id = arguments!!.getLong("id_ad",0)
        ad = DbHelper.findAd(id)!!

    //    sendAnalytics()
    }

//    fun sendAnalytics(){
//        val tracker = AppCache.getDefaultTracker(activity!!.applicationContext)
//        tracker.setScreenName("Ad Visualizado " + ad.link_url)
//        tracker.send(HitBuilders.ScreenViewBuilder().build())
//    }

    private lateinit var fragment:View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragment = inflater.inflate(R.layout.fragment_ads, container, false)
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            Picasso.with(context)
                    .load(ad.image_url)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(fragment.imgViewAd, this)

            //(activity as AppCompatActivity).supportActionBar?.setTitle(R.string.action_news)
        }catch (ex:Exception){
            ex.printStackTrace()
        }
    }

    //callback from picasso image loading
    override fun onSuccess() {
        fragment.txtViewAdText.text = ad.text

        val link = "<a href='"+ad.link_url+"'>"+ad.link_text+"</a>"
        fragment.txtViewAdLink.movementMethod = LinkMovementMethod.getInstance()
        fragment.txtViewAdLink.text = Html.fromHtml(link)
    }

    //callback from picasso image loading
    var attempts = 0
    override fun onError() {
        if(attempts < 3) {
            Picasso.with(context)
                    .load(ad.image_url)
                    .into(fragment.imgViewAd, this)
            attempts++
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragment.clearFindViewByIdCache()
    }

    companion object {
        fun newInstance(id:Long): AdsFragment {
            val fragment = AdsFragment()
            val args = Bundle()
            args.putLong("id_ad", id)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
