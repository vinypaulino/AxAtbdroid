package br.com.anestech.axatb_droid

class AppConstants {
    companion object {

        const val APP_TOKEN = "751dfbdf0d94473048818380"
        private const val URL_BASE = "https://axserver.anestech.com.br/"

        const val URL_ADD_DEVICE = URL_BASE + "api/addDevice/"
        const val URL_IS_REGISTERED = URL_BASE + "api/is_registered/"
        const val URL_NEW_REGISTRATION = URL_BASE + "api/new_pre_registration/"

        const val URL_PUBLICITY = URL_BASE + "pt-BR/api/publicities/ipad"
        const val URL_TERMOS= URL_BASE + "api/terms"

    }
}