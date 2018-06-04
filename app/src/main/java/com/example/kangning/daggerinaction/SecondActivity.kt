package com.example.kangning.daggerinaction

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import javax.inject.Inject

class SecondActivity : AppCompatActivity() {

    @Inject
    lateinit var myDataResposity: MyDataResposity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        MainApplication.secondComponent.inject(this)
        Log.d("sss", myDataResposity.toString())
    }
}
