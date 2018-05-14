package br.com.anestech.axatb_droid.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import br.com.anestech.axatb_droid.R
import br.com.anestech.axatb_droid.domain.Paciente
import br.com.anestech.axatb_droid.extensions.addFragment
import br.com.anestech.axatb_droid.extensions.setupToolbar
import br.com.anestech.axatb_droid.fragments.AntibioticoResultFragment
import br.com.anestech.axatb_droid.fragments.DadosPacienteResultFragment

class ResultActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        setupToolbar(R.id.toolbar, "Recomendação", true)

        addFragment(R.id.frame_result, DadosPacienteResultFragment(), R.id.frame_antibiotico_result, AntibioticoResultFragment())

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
