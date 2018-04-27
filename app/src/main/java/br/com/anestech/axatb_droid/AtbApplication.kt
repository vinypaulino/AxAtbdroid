package br.com.anestech.axatb_droid

import android.app.Application
import android.util.Log

/**
 * Created by vinicius on 27/04/18.
 */
class AtbApplication : Application(){
    private val TAG = "AtbApplication"
    override fun onCreate() {
        super.onCreate()
        //Salva a instância para termos acesso como Singleton
        appInstance = this
    }
        companion object {
            //Singleton da classe Application
            private  var appInstance: AtbApplication? = null
            fun getInstance(): AtbApplication {
                if(appInstance == null) {
                    throw IllegalStateException("Configure a classe de Application no AndroidManifest.xml")
                }
                return appInstance!!
            }
        }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "AtbApplication.onTerminate()")
    }
}