package com.example.kangning.daggerinaction

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var activityComponent: ActivityComponent
    lateinit var dataComponent: DataComponent

    @Inject
    lateinit var dataResposity: MyDataResposity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        activityComponent = MainApplication.appComponent.plusActivityModule(ActivityModule(this))
//        dataComponent = activityComponent.plusDataModule(DataModule())
        activityComponent = MainApplication.appComponent.activityComponentBuilder().activityModule(ActivityModule(this)).build()
        dataComponent = activityComponent.dataComponentBuilder().build()
        dataComponent.inject(this)
        textview.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        if (dataResposity != null) {
            Log.d("context", dataResposity.getData())
        }
    }
}
