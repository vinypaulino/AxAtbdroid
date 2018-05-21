package br.com.anestech.axatb_droid


import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


/**
 * Created by felipe on 07/08/17.
 */

class AppCache : Application() {

    override fun onCreate() {
        super.onCreate()
        configAndInitRealm()
    }

    /**
     * Responsible for initialize and configure Realm database with initial data
     */
    private fun configAndInitRealm() {
        Realm.init(this)

        val config = RealmConfiguration.Builder()
             //   .initialData(DbInitialData())
                .name("axatb.realm")
                .build()

        Realm.setDefaultConfiguration(config)
    }

    companion object {
//        var currentUser:User? = null
//        private var tracker: Tracker? = null
//        @Synchronized
//        fun getDefaultTracker(ctx: Context): Tracker {
//            if (tracker == null) {
//                val analytics = GoogleAnalytics.getInstance(ctx)
//                tracker = analytics.newTracker("UA-85326028-4")
//            }
//            return tracker!!
//        }



        //Singleton da classe Application
        private  var appInstance: AtbApplication? = null
        fun getInstance(): AtbApplication {
            if(appInstance == null) {
                throw IllegalStateException("Configure a classe de Application no AndroidManifest.xml")
            }
            return appInstance!!
        }
    }
}
