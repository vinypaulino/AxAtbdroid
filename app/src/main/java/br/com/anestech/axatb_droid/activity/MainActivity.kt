package br.com.anestech.axatb_droid.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ArrayAdapter.createFromResource
import android.widget.Toast
import br.com.anestech.axatb_droid.R
import br.com.anestech.axatb_droid.domain.Paciente
import br.com.anestech.axatb_droid.extensions.setupToolbar
import br.com.anestech.axatb_droid.extensions.toast
import br.com.anestech.axatb_droid.helper.PacienteHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*


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

        configuraNavDrawer()

        carregaSpinnerTipoCirurgia()

        btn_profilaxia_recomendada.setOnClickListener{
            val view = window.decorView

            val paciente = PacienteHelper().carregaPaciente(view)

            toast(" ${paciente?.idade} peso ${paciente?.peso} ${paciente?.tipoCirurgia} " +
                    " Alergia cefa ${paciente?.alergia_cefalosporinas}" +
                    " Alergia peni ${paciente?.alergia_penicilina}" +
                     " Alergia Sul ${paciente?.alergia_sulfonamidas}" +
                    " é crianca ? ${paciente?.eCrianca()}", Toast.LENGTH_LONG)
        }

    }



    private fun carregaSpinnerTipoCirurgia() {
        val adapter = createFromResource(
                context,
                R.array.type_surgery,
                android.R.layout.simple_spinner_dropdown_item
        )
        spinner_type_surgery.adapter = adapter
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
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

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
