package br.com.anestech.axatb_droid.activity

import android.os.Bundle
import br.com.anestech.axatb_droid.R
import br.com.anestech.axatb_droid.extensions.addFragment
import br.com.anestech.axatb_droid.fragments.AntibioticoResultFragment
import br.com.anestech.axatb_droid.fragments.DadosPacienteResultFragment

class ResultActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        addFragment(R.id.frame_result, DadosPacienteResultFragment(), R.id.frame_antibiotico_result, AntibioticoResultFragment())

    }
}
