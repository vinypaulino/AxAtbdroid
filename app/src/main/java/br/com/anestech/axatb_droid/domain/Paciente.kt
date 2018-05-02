package br.com.anestech.axatb_droid.domain

import java.io.Serializable

/**
 * Created by Vinícius on 02/05/18.
 */
class Paciente  {
    var idade: Long = 0
    var peso: Float = 0.0f
    var tipoCirurgia = TipoCirurgia.APENDICITE_NAO_PERFURADA
    var alergias = null

    public fun eCrianca(): Boolean {
        if (this.idade <= 12) {
            return true
        }
        return false
    }
}