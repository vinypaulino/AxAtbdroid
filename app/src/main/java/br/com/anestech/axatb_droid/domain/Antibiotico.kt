package br.com.anestech.axatb_droid.domain

import java.io.Serializable

/**
 * Created by vinicius on 14/05/18.
 */
class Antibiotico : Serializable{
    var nome : String? = null
    var dosePreconizada : String? = null
    var tempoDose : String? = null
    var repique: String?= null
    var prescricao: String?= null
    var dose : Float? = 0.0f
    var dosePorPeso: Float? = 0.0f

}