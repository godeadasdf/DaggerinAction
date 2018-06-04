package com.example.kangning.daggerinaction

import android.content.Context
import dagger.*
import java.lang.annotation.RetentionPolicy
import javax.inject.*

/**
 * Created by kangning on 2018/6/4.
 */
//@Module
/*class AppModule {


    private var appContext: Context

    constructor(context: Context) {
        this.appContext = context
    }


    @Provides
    @AppScope
    @AppContext
    fun context(): Context = appContext
}*/

@Module
class ActivityModule {


    private var activity: Context

    constructor(context: Context) {
        this.activity = context
    }


    @Provides
    @ActivityScope
    @ActivityContext
    fun context(): Context = activity
}

class DataResposity {


    constructor( context: Context)

    fun getData() = "aaaaa"
}

@Module
class DataModule {

    @Provides
    fun provideDataResposity(@ActivityContext activity: Context): DataResposity {
        return DataResposity(activity)
    }
}


@Component(modules = [ActivityModule::class])
@ActivityScope
interface ActivityComponent {
    fun plusDataModule(dataModule: DataModule): DataComponent
}

@Subcomponent(modules = [DataModule::class])
interface DataComponent {
    fun inject(mainActivity: MainActivity)
}


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope {

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope {

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityContext {

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppContext {

}