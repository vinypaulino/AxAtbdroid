package br.com.anestech.axatb_droid.domain

import android.text.Editable

/**
 * Created by Vinícius on 02/05/18.
 */
class Paciente()  {

    var idade : Long = 0
    var peso : Float = 0.0f
    var tipoCirurgia = String()
    var alergia_cefalosporinas:Boolean = false
    var alergia_penicilina: Boolean = false
    var alergia_sulfonamidas: Boolean = false



    fun eCrianca(): Boolean {
        if (idade <= 12) {
            return true
        }
        return false
    }

}