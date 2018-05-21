package br.com.anestech.axatb_droid


import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


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

}
