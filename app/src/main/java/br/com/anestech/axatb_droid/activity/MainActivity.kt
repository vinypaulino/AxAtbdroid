package br.com.anestech.axatb_droid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.anestech.axatb_droid.R
import br.com.anestech.axatb_droid.extensions.setupToolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(R.id.toolbar, "Antibioticoprofilaxia Cirúrgica")
    }
}
