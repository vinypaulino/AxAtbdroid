package br.com.anestech.axatb_droid.domain

import br.com.anestech.axatb_droid.R

/**
 * Created by Vinícius on 02/05/18.
 */
enum class TipoCirurgia (val string: Int) {

    APENDICITE_NAO_PERFURADA(R.string.APENDICITE_NAO_PERFURADA),
    CABECA_PESCOCO(R.string.CABECA_PESCOCO),
    CARDIACA(R.string.CARDIACA),
    CESARIA(R.string.CESARIA),
    CISTOSCOPIA(R.string.CISTOCOPIA),
    COLORRETAL(R.string.COLORRETAL),
    GASTROINTESTINAL_ALTO(R.string.GASTROINTESTINAL_ALTO),
    GENITO_URINARIO_ABERTO(R.string.GENITO_URINARIO_ABERTO),
    GINICOLOGICA(R.string.GINECOLOGICA),
    NEUROLOGICA(R.string.NEUROLOGICA),
    ORTOPEDICA(R.string.ORTOPEDICA),
    PLASTICA(R.string.PLASTICA),
    TORACICA(R.string.TORACICA),
    TRAUMA_ABERTO(R.string.TRAUMA_ABERTO),
    VASCULAR(R.string.VASCULAR)

}