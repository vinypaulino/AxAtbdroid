package br.com.anestech.axatb_droid.helper

import android.content.Context
import android.widget.Toast
import br.com.anestech.axatb_droid.domain.Antibiotico
import br.com.anestech.axatb_droid.domain.Paciente
import br.com.anestech.axatb_droid.domain.TipoCirurgia

/**
 * Created by vinicius on 14/05/18.
 */
class CalculosHelper (context: Context, paciente: Paciente, antibiotico: Antibiotico){
    private val paciente = paciente
    private val context = context
    private val antibiotico = antibiotico
    private var dosePorPeso: Float = 0.0f


    fun escolheCalculoPorCirurgia() {
        when (paciente?.tipoCirurgia) {

            context.getString(TipoCirurgia.APENDICITE_NAO_PERFURADA.string) -> calculoApendicite()

            context.getString(TipoCirurgia.CABECA_PESCOCO.string) -> calculoCabecaPescoco()


            context.getString(TipoCirurgia.CARDIACA.string) ->
                Toast.makeText(context, "tipo ${paciente?.tipoCirurgia} ", Toast.LENGTH_LONG).show()

            context.getString(TipoCirurgia.CESARIA.string) ->
                Toast.makeText(context, "tipo ${paciente?.tipoCirurgia} ", Toast.LENGTH_LONG).show()

            context.getString(TipoCirurgia.CISTOSCOPIA.string) -> calculoCistoscopia()

            context.getString(TipoCirurgia.COLORRETAL.string) -> calculoApendicite()

            context.getString(TipoCirurgia.GASTROINTESTINAL_ALTO.string) -> calculoApendicite()

            context.getString(TipoCirurgia.GENITO_URINARIO_ABERTO.string) ->
                Toast.makeText(context, "tipo ${paciente?.tipoCirurgia} ", Toast.LENGTH_LONG).show()

            context.getString(TipoCirurgia.GINICOLOGICA.string) -> calculoApendicite()

            context.getString(TipoCirurgia.NEUROLOGICA.string) ->
                Toast.makeText(context, "tipo ${paciente?.tipoCirurgia} ", Toast.LENGTH_LONG).show()

            context.getString(TipoCirurgia.ORTOPEDICA.string) ->
                Toast.makeText(context, "tipo ${paciente?.tipoCirurgia} ", Toast.LENGTH_LONG).show()

            context.getString(TipoCirurgia.PLASTICA.string) ->
                Toast.makeText(context, "tipo ${paciente?.tipoCirurgia} ", Toast.LENGTH_LONG).show()

            context.getString(TipoCirurgia.TORACICA.string) ->
                Toast.makeText(context, "tipo ${paciente?.tipoCirurgia} ", Toast.LENGTH_LONG).show()

            context.getString(TipoCirurgia.TRAUMA_ABERTO.string) -> calculoTraumaAberto()

            context.getString(TipoCirurgia.VASCULAR.string) ->
                Toast.makeText(context, "tipo ${paciente?.tipoCirurgia} ", Toast.LENGTH_LONG).show()

        }
    }

    private fun calculoApendicite() {

        antibiotico.tempoDose = "Administrar 30-60 minutos antes da incisão"
        antibiotico.prescricao = "Suspender após o procedimento"
        //Alergia à Cefalosporinas e/ou Penicilinas
            if (paciente.alergia_cefalosporinas || paciente.alergia_penicilina){

                antibiotico.nome = "Clindamicina + Gentamicina"
                antibiotico.repique = "Repique de 6/6 horas durante o procedimento e Gentamicina em dose única"

                //Tem Alergia
                if (paciente.eAdulto()){
                    //é Adulto
                    antibiotico.dose = 5.toFloat()
                    calculaDosePorPeso()
                    antibiotico.dosePreconizada = "Clindamicina 900mg + Gentamicina $dosePorPeso mg (5mg/Kg)"

                } else {
                    // é Criança
                    antibiotico.dose = 10.toFloat()
                    calculaDosePorPeso()
                    var dosePreconizada = "Clindamicina $dosePorPeso (10mg/Kg)"
                    antibiotico.dose = 5.toFloat()
                    calculaDosePorPeso()
                    dosePreconizada += " e Gentamicina $dosePorPeso mg (5mg/kg)"
                    antibiotico.dosePreconizada = dosePreconizada
                }
            } else {
                //não tem Alergia
                antibiotico.repique = "Repique de 2/2 horas durante o procedimento"
                antibiotico.nome = "Cefoxitina"
                if (paciente.eAdulto()){
                    antibiotico.dosePreconizada = "2g"
                } else {
                    antibiotico.dose = 40.toFloat()
                    calculaDosePorPeso()
                    antibiotico.dosePreconizada = "$dosePorPeso (40 mg/Kg)"
                }
            }
    }

    private fun calculoCabecaPescoco(){
        antibiotico.nome = "Clindamicina"
        antibiotico.tempoDose = "Administrar 30-60 minutos antes da incisão"
        antibiotico.repique = "Repique de 6/6 horas durante o procedimento"
        antibiotico.prescricao = "Suspender após o procedimento"

        if (paciente.eAdulto()){
            antibiotico.dosePreconizada = "900 mg"
        } else {
            antibiotico.dose = 10.toFloat()
            calculaDosePorPeso()
            antibiotico.dosePreconizada = "$dosePorPeso mg (10mg/Kg)"
        }
    }

    private fun calculoTraumaAberto(){
        if (paciente.alergia_cefalosporinas || paciente.alergia_penicilina){
            if (paciente.eAdulto()){
                antibiotico.nome = "Ciprofloxacina"
                antibiotico.dosePreconizada = "400mg"
                antibiotico.tempoDose = "Administrar em até 6 horas após o trauma e 60-120 minutos antes da incisão"
                antibiotico.repique = "Dose única"
                antibiotico.prescricao = "Manter de 12/12 horas por 72 horas"
            }else{
                antibiotico.nome = "Clindamicina + Gentamicina"
                antibiotico.dose = 10.toFloat()
                calculaDosePorPeso()
                var dosePreconizada = "Clindamicina $dosePorPeso mg (10mg/Kg)"
                antibiotico.dose = 2.5.toFloat()
                calculaDosePorPeso()
                dosePreconizada += "Gentamicina $dosePorPeso mg (2.5mg/Kg)"
                antibiotico.dosePreconizada = dosePreconizada
                antibiotico.tempoDose = "Administrar em até 6 horas após o trauma e 30-60 minutos antes da incisão"
                antibiotico.repique = "Repique de 6/6 horas durante o procedimento e Gentamicina em dose única"
                antibiotico.prescricao = "Manter de 12/12 horas por 72 horas"
            }
        } else {
            antibiotico.nome = "Cefuroxima"
            antibiotico.tempoDose = "Administrar em até 6 horas após o trauma e 30-60 minutos antes da incisão"
            antibiotico.repique = "Repique a cada 4 horas enquanto durar o procedimento"
            antibiotico.prescricao = "Manter de 8/8 horas por 72 horas"
            if (paciente.eAdulto()){
                antibiotico.dosePreconizada = "1,5g"
            } else {
                antibiotico.dose = 50.toFloat()
                calculaDosePorPeso()
                antibiotico.dosePreconizada ="$dosePorPeso mg (50 mg/Kg)"
            }
        }
    }

    private fun calculoCistoscopia(){
        antibiotico.prescricao = "Suspender após o procedimento"
        if (paciente.alergia_sulfonamidas){
            if(paciente.alergia_cefalosporinas || paciente.alergia_penicilina){
                if (paciente.eAdulto()){
                    antibiotico.nome = "Ciprofloxacina"
                    antibiotico.dosePreconizada = "400mg"
                    antibiotico.tempoDose = "Administrar 60-120 minutos antes da incisão"
                    antibiotico.repique = "Sem repique"
                } else {
                    antibiotico.nome = "Gentamicina"
                    antibiotico.dose = 5.toFloat()
                    calculaDosePorPeso()
                    antibiotico.dosePreconizada = "$dosePorPeso mg (5mg/Kg)"
                    antibiotico.tempoDose = "Administrar 30-60 minutos antes da incisão"
                    antibiotico.repique = "Sem repique"
                }
            } else {
                if (paciente.eAdulto()){
                    antibiotico.nome = "Ciprofloxacina"
                    antibiotico.dosePreconizada = "400mg"
                    antibiotico.tempoDose = "Administrar 60-120 minutos antes da incisão"
                    antibiotico.repique = "Sem repique"
                } else {
                    antibiotico.nome = "Cefuroxima"
                    antibiotico.dose = 50.toFloat()
                    calculaDosePorPeso()
                    antibiotico.dosePreconizada = "$dosePorPeso mg (50mg/Kg)"
                    antibiotico.tempoDose = "Administrar 30-60 minutos antes da incisão"
                    antibiotico.repique = "Repique de 4/4 horas durante o procedimento"
                }
            }
        }else{
            antibiotico.nome = "Sulfametoxazol + Trimetropim"
            antibiotico.tempoDose = "Administrar 30-60 minutos antes da incisão"
            antibiotico.repique = "Repique em 12/12 horas"
            if (paciente.eAdulto()){
                antibiotico.dosePreconizada = "960mg"
            } else {
                antibiotico.dose = 6.toFloat()
                calculaDosePorPeso()
                antibiotico.dosePreconizada = "$dosePorPeso mg (6mg/Kg)"
            }
        }

    }

    fun calculaDosePorPeso(){
      dosePorPeso = 0.toFloat()
      dosePorPeso = paciente.peso * antibiotico.dose!!
    }
}