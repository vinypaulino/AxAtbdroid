package br.com.anestech.axatb_droid.services.api

import android.content.Context
import br.com.anestech.axatb_droid.AppConstants
import br.com.anestech.axatb_droid.domain.Ad
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.koushikdutta.ion.Ion
import io.realm.Realm
import java.lang.Exception

class AxServerApi {
    companion object {

        fun registerForNotifications(context: Context, token:String): String?{
            val response = Ion.with(context)
                    .load("POST", AppConstants.URL_ADD_DEVICE)
                    .addHeader("app_token", AppConstants.APP_TOKEN)
                    .setBodyParameter("token", token)
                    .setBodyParameter("sns_env", "Production")
                    .setBodyParameter("app_token", AppConstants.APP_TOKEN)
                    .asString()
                    .get()

            return response
        }

//        fun isRegistered(context: Context?, localUser: User): RegistrationResponse? {
//            val response = Ion.with(context)
//                    .load("GET", AppConstants.URL_IS_REGISTERED + localUser.uuid)
//                    .addHeader("app_token", AppConstants.APP_TOKEN)
//                    .`as`(TypeToken.get(RegistrationResponse::class.java))
//                    .get()
//
//            return response
//        }

//        fun newRegistration(context:Context?, user:User): RegistrationResponse? {
//            val response = Ion.with(context)
//                    .load("POST", AppConstants.URL_NEW_REGISTRATION)
//                    .addHeader("app_token", AppConstants.APP_TOKEN)
//                    .setBodyParameter("name", user.name)
//                    .setBodyParameter("email", user.email)
//                    .setBodyParameter("udid", user.uuid)
//                    .`as`(TypeToken.get(RegistrationResponse::class.java))
//                    .get()
//            return response
//        }

//        fun getTermosDeUso(context:Context?): TermsResponse? {
//            val response = Ion.with(context)
//                    .load("GET", AppConstants.URL_TERMOS)
//                    .`as`(TypeToken.get(TermsResponse::class.java))
//                    .get()
//            return response
//        }

        fun syncAds(context: Context) {
            try {
                Ion.with(context)
                    .load(AppConstants.URL_PUBLICITY)
                    .asJsonObject()
                    .setCallback({ e, result ->
                        if (e == null) {
                            val listType = object : TypeToken<List<Ad>>() {}.type
                            val publicities = Gson().fromJson(result, JsonObject::class.java).get("publicities")
                            val ads: List<Ad> = Gson().fromJson(publicities, listType)

                            val realm = Realm.getDefaultInstance()
                            realm.executeTransaction {
                                realm.insertOrUpdate(ads)
                            }
                            realm.close()
                        } else {
                            e.printStackTrace()
                        }
                    })

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}