package br.com.anestech.axatb_droid.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.anestech.axatb_droid.R
import br.com.anestech.axatb_droid.domain.Paciente
import kotlinx.android.synthetic.main.fragment_dados_paciente_result.view.*

class DadosPacienteResultFragment : Fragment() {

    private var paciente : Paciente? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paciente = activity.intent.getSerializableExtra("paciente") as Paciente
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater!!.inflate(R.layout.fragment_dados_paciente_result, container, false)

        carregaDadosPacienteNaTela(view)

        return view
    }

    private fun carregaDadosPacienteNaTela(view: View) {
        view.txt_paciente_idade.text = paciente?.idade.toString()
        view.txt_paciente_peso.text = paciente?.peso.toString()
    }

}
