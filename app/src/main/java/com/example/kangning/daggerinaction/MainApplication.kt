package com.example.kangning.daggerinaction

import android.app.Application

/**
 * Created by kangning on 2018/6/4.
 */
class MainApplication : Application() {


    companion object {
        //        lateinit var activityComponent: ActivityComponent
//        lateinit var dataModuleComponent: DataComponent
        lateinit var appComponent: AppComponent
        lateinit var secondComponent: SecondComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
        secondComponent = DaggerSecondComponent.builder().build()
    }


}