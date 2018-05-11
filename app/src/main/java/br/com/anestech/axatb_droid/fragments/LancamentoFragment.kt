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
import br.com.anestech.axatb_droid.domain.Paciente
import br.com.anestech.axatb_droid.helper.PacienteHelper
import kotlinx.android.synthetic.main.fragment_lancamento.*
import kotlinx.android.synthetic.main.fragment_lancamento.view.*


class LancamentoFragment : BaseFragment() {

    private var paciente : Paciente? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_lancamento, container, false)

        carregaSpinnerTipoCirurgia(view)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_profilaxia_recomendada.setOnClickListener {

            paciente = PacienteHelper(context, this.view!!).carregaPaciente()


            if (paciente?.idade!!.equals(0) || paciente?.peso!!.equals(0.0f)){
                return@setOnClickListener
            }

            Toast.makeText(context, "idade ${paciente?.idade} tipo ${paciente?.tipoCirurgia} sulfo ${paciente?.alergia_sulfonamidas} ", Toast.LENGTH_LONG).show()

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


