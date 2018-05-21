package br.com.anestech.axatb_droid.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import br.com.anestech.axatb_droid.R
import br.com.anestech.axatb_droid.domain.Antibiotico
import br.com.anestech.axatb_droid.domain.Paciente
import br.com.anestech.axatb_droid.domain.TipoCirurgia
import kotlinx.android.synthetic.main.fragment_antibiotico_result.view.*


class AntibioticoResultFragment : Fragment() {
    private var paciente : Paciente? = null
    private var antibiotico : Antibiotico?= null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        paciente = activity.intent.getSerializableExtra("paciente") as Paciente?
        antibiotico = activity.intent.getSerializableExtra("antibiotico") as Antibiotico?

        setHasOptionsMenu(true)

        var view = inflater!!.inflate(R.layout.fragment_antibiotico_result, container, false)

        carregaAntibioticoNaTela(view)

        if (paciente?.tipoCirurgia.equals(getString(TipoCirurgia.CARDIACA.string))){
            mostraDialogAviso( R.string.msg_cardiaca_result)
        } else if (paciente?.tipoCirurgia.equals(getString(TipoCirurgia.CESARIA.string))){
            mostraDialogAviso(R.string.msg_cesaria_result)
        }

        return view

    }

    private fun mostraDialogAviso(msg : Int) {
        val dialog = AlertDialog.Builder(context)
                .setTitle(R.string.aviso)
                .setMessage(msg)
                .setPositiveButton(R.string.ok, null)
                .create()
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.fragment_result_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_share){
            compartilharResultado()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun compartilharResultado() {

        var alergias : String = "Sem Alergias"

        if (paciente?.alergia_cefalosporinas!!){
            alergias = "Cefalosporinas"
        }

        if (paciente?.alergia_penicilina!!){
            if (alergias == "Sem Alergias") {
               alergias = "Penicilina"
            } else {
               alergias +=  ", Penicilina"
            }
        }

        if (paciente?.alergia_sulfonamidas!!){
            if (alergias == "Sem Alergias") {
                alergias = "Sulfonamidas"
            } else alergias += ", Sulfonamidas"
        }

        var conteudo : String
        conteudo = "Recomendação \n \n" +
                "Dados do paciente  \n" +
                "Idade: ${paciente?.idade} anos  " +
                "Peso: ${paciente?.peso} Kg \n\n" +
                "Antibioticoprofilaxia: \n\n" +
                "Tipo de Cirurgia: ${paciente?.tipoCirurgia} \n\n" +
                "Alergias: $alergias \n\n" +
                "Antibiótico: ${antibiotico?.nome} \n\n" +
                "Dose: ${antibiotico?.dosePreconizada} \n\n " +
                "Tempo da dose antes da incisão: ${antibiotico?.tempoDose} \n\n " +
                "Repique: ${antibiotico?.repique} \n\n" +
                "Prescrição: ${antibiotico?.prescricao} \n\n" +
                "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n\n" +
                "AxATB by Anestech - T.I. em Anestesiologia - www.anestech.com.br"


        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, conteudo)
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.enviar_para)))
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