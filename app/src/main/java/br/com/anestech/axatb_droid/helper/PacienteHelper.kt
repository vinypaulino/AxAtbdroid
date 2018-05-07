package br.com.anestech.axatb_droid.helper

import android.view.View
import br.com.anestech.axatb_droid.R.id.ckb_allergy_cefalosporinas
import br.com.anestech.axatb_droid.R.id.ckb_allergy_penicillin
import br.com.anestech.axatb_droid.domain.Paciente
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main.view.*

/**
 * Created by vinicius on 07/05/18.
 */
class PacienteHelper (){
    val paciente = Paciente()

    fun carregaPaciente(view: View) : Paciente{
        val idade = view.edt_idade_paciente.text.toString().toLong()
        val peso = (if (view.edt_peso_paciente != null) view.edt_peso_paciente.text else null).toString().toFloat()

        paciente?.idade = idade
        paciente?.peso = peso
        paciente?.tipoCirurgia = view.spinner_type_surgery.selectedItem.toString()
        paciente?.alergia_cefalosporinas = view.ckb_allergy_cefalosporinas.isChecked
        paciente?.alergia_penicilina = view.ckb_allergy_penicillin.isChecked
        paciente?.alergia_sulfonamidas = view.ckb_allergy_sulfonamides.isChecked

        return paciente
    }
}