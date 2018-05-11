package br.com.anestech.axatb_droid.helper

import android.content.Context
import android.view.View
import android.widget.Toast
import br.com.anestech.axatb_droid.domain.Paciente
import kotlinx.android.synthetic.main.fragment_lancamento.*
import kotlinx.android.synthetic.main.fragment_lancamento.view.*

/**
 * Created by vinicius on 07/05/18.
 */
class PacienteHelper(private val context: Context, private val view: View) {
   private val paciente = Paciente()
     fun carregaPaciente(): Paciente {
        var idadePaciente: Long = 0
        try {
            idadePaciente = view.edt_idade_paciente.text.toString().toLong()
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Por favor preencha o campo Idade do paciente", Toast.LENGTH_SHORT).show()

        }

        var pesoPaciente = 0.0f
        try {
            pesoPaciente = view.edt_peso_paciente.text.toString().toFloat()
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Por favor preencha o campo Peso do paciente", Toast.LENGTH_SHORT).show()

        }

        val tipoCirurgiaTexto = view.spinner_type_surgery.selectedItem.toString()
        val alergiaCefalosporinas = view.ckb_allergy_cefalosporinas.isChecked
        val alergiaPenicilina = view.ckb_allergy_penicillin.isChecked
        val alergiaSulfonamidas = view.ckb_allergy_sulfonamides.isChecked


        paciente?.idade = idadePaciente
        paciente?.peso = pesoPaciente
        paciente?.tipoCirurgia = tipoCirurgiaTexto
        paciente?.alergia_cefalosporinas = alergiaCefalosporinas
        paciente?.alergia_penicilina = alergiaPenicilina
        paciente?.alergia_sulfonamidas = alergiaSulfonamidas
        return paciente
    }

}