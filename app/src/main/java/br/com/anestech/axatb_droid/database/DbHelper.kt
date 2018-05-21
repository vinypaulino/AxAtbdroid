package br.com.anestech.axatb_droid.database

import br.com.anestech.axatb_droid.domain.Ad
import io.realm.Realm
import io.realm.RealmObject

/**
 * Created by felipe on 06/09/17.
 */
class DbHelper {
    companion object {
        fun save(obj:RealmObject){
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.insertOrUpdate(obj)
            realm.commitTransaction()
            realm.close()
        }
        
        fun findAd(id: Long): Ad? {
            val realm = Realm.getDefaultInstance()
            val result = realm.copyFromRealm(realm.where(Ad::class.java).equalTo("id", id).findFirst())
            realm.close()
            return result
        }
    }
}