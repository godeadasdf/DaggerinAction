package com.example.kangning.daggerinaction

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent
    lateinit var dataComponent: DataComponent

    @Inject
    lateinit var dataResposity: DataResposity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent = DaggerActivityComponent.builder().activityModule(ActivityModule(this)).build()
        dataComponent = activityComponent.plusDataModule(DataModule())
        dataComponent.inject(this)

        if (dataResposity != null) {
            Log.d("context", dataResposity.toString())
        }
    }
}
