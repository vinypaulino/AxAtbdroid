package br.com.anestech.axatb_droid.fragments

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter.createFromResource
import android.widget.Toast
import br.com.anestech.axatb_droid.R
import br.com.anestech.axatb_droid.activity.ResultActivity
import br.com.anestech.axatb_droid.domain.Antibiotico
import br.com.anestech.axatb_droid.domain.Paciente
import br.com.anestech.axatb_droid.domain.TipoCirurgia
import br.com.anestech.axatb_droid.helper.CalculosHelper
import br.com.anestech.axatb_droid.helper.PacienteHelper
import kotlinx.android.synthetic.main.fragment_lancamento.*
import kotlinx.android.synthetic.main.fragment_lancamento.view.*


class LancamentoFragment : BaseFragment() {

    private var paciente : Paciente? = null
    private var tipoCirurgia = null
    private var antibiotico = Antibiotico()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_lancamento, container, false)

        carregaSpinnerTipoCirurgia(view)


        return view
    }

    private var calculosHelper: CalculosHelper? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_profilaxia_recomendada.setOnClickListener {

            paciente = PacienteHelper(context, this.view!!).carregaPaciente()

            if (paciente?.idade!!.equals(0) || paciente?.peso!!.equals(0.0f)){
                return@setOnClickListener
            }

             calculosHelper = CalculosHelper(
                    context,
                    paciente = paciente!!,
                    antibiotico = antibiotico
            )

            if (paciente?.tipoCirurgia.equals(getString(TipoCirurgia.VASCULAR.string)) ||
                    paciente?.tipoCirurgia.equals(getString(TipoCirurgia.CARDIACA.string)) ||
                    paciente?.tipoCirurgia.equals(getString(TipoCirurgia.NEUROLOGICA.string)) ||
                    paciente?.tipoCirurgia.equals(getString(TipoCirurgia.ORTOPEDICA.string))
                    )  {
                mostraDialogMRSA()
            } else {
                vaiParaATelaResultado()
            }
        }
    }

    private fun mostraDialogMRSA() {
        val dialog = AlertDialog.Builder(context)
                .setTitle(R.string.mrsa)
                .setMessage(R.string.msg_mrsa)
                .setPositiveButton(R.string.sim, { _, _ ->
                    paciente?.riscoMRSA = true
                    vaiParaATelaResultado()
                })
                .setNegativeButton(R.string.nao, { _, _ ->
                    vaiParaATelaResultado()
                })
                .create()
        dialog.show()
    }

    private fun vaiParaATelaResultado() {

        calculosHelper?.escolheCalculoPorCirurgia()
        val intent = Intent(context, ResultActivity::class.java)
        intent.putExtra("paciente", paciente)
        intent.putExtra("antibiotico", antibiotico)
        startActivity(intent)
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


