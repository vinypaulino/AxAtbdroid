package br.com.anestech.axatb_droid.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter.createFromResource
import android.widget.Toast
import br.com.anestech.axatb_droid.R
import br.com.anestech.axatb_droid.activity.ResultActivity
import kotlinx.android.synthetic.main.fragment_lancamento.*
import kotlinx.android.synthetic.main.fragment_lancamento.view.*


class LancamentoFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_lancamento, container, false)

        carregaSpinnerTipoCirurgia(view)

      //  btn_profilaxia_recomendada.setText("teste")

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_profilaxia_recomendada.setOnClickListener {
            val intent = Intent(context, ResultActivity::class.java)
            startActivity(intent)
        }
    }



    private fun carregaSpinnerTipoCirurgia(view: View) {
        val adapter = createFromResource(
                context,
                R.array.type_surgery,
                android.R.layout.simple_spinner_dropdown_item
        )
        view.spinner_type_surgery.adapter = adapter
    }


}


