package br.com.anestech.axatb_droid.activity


import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import br.com.anestech.axatb_droid.R
import br.com.anestech.axatb_droid.domain.Paciente
import br.com.anestech.axatb_droid.extensions.addFragment
import br.com.anestech.axatb_droid.extensions.setupToolbar
import br.com.anestech.axatb_droid.fragments.LancamentoFragment
import br.com.anestech.axatb_droid.services.api.AxServerApi
import br.com.anestech.axcalc.activities.main.ads.AdsGalleryFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.doAsync
import java.lang.Exception


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    var paciente : Paciente? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(R.id.toolbar, "Antibioticoprofilaxia Cirúrgica")


//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

  //      configuraNavDrawer()
        addFragment(R.id.frame_lancamento, LancamentoFragment())
    }

    override fun onResume() {
        super.onResume()
        doAsync {
            AxServerApi.syncAds(applicationContext)
        }

        try {

            val action = intent.action
            if (action == "open_ads") {
                loadFragmentAds()
                intent.action = null
            }
        }catch (ex: Exception){
            ex.printStackTrace()
        }
    }


    private fun configuraNavDrawer() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.itemId == R.id.action_notifications) {
            loadFragmentAds()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun loadFragmentAds() {
        supportFragmentManager.beginTransaction()
                .add(R.id.frame_lancamento, AdsGalleryFragment.newInstance())
                .addToBackStack(null)
                .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {
                loadFragmentAds()
            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}
