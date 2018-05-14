package br.com.anestech.axatb_droid.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.anestech.axatb_droid.R
import br.com.anestech.axatb_droid.domain.Antibiotico
import br.com.anestech.axatb_droid.domain.Paciente
import kotlinx.android.synthetic.main.fragment_antibiotico_result.view.*


class AntibioticoResultFragment : Fragment() {
    private var paciente : Paciente? = null
    private var antibiotico : Antibiotico?= null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        paciente = activity.intent.getSerializableExtra("paciente") as Paciente?
        antibiotico = activity.intent.getSerializableExtra("antibiotico") as Antibiotico?

        var view = inflater!!.inflate(R.layout.fragment_antibiotico_result, container, false)

        carregaAntibioticoNaTela(view)

        return view
    }

    private fun carregaAntibioticoNaTela(view: View) {
        view.txt_tipo_cirurgia_result.text = paciente?.tipoCirurgia
        view.txt_antibiotico_result.text = antibiotico?.nome
        view.txt_dose_preconizada_resultado.text = antibiotico?.dosePreconizada
        view.txt_tempo_dose_resultado.text = antibiotico?.tempoDose
        view.txt_repique_resultado.text = antibiotico?.repique
        view.txt_prescricao_resultado.text = antibiotico?.prescricao
    }

}