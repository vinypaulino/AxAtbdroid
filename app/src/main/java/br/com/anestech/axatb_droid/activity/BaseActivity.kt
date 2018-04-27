package br.com.anestech.axatb_droid.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity

/**
 * Created by vinicius on 27/04/18.
 */
open class BaseActivity : AppCompatActivity(){
    //Propriedade para acessar o contexto de qualquer lugar
    protected val context: Context get() = this
    //Métodos comuns para todas activities

}