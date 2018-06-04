package com.example.kangning.daggerinaction

import android.content.Context
import dagger.*
import java.lang.annotation.RetentionPolicy
import javax.inject.*

/**
 * Created by kangning on 2018/6/4.
 */
@Module
class AppModule {


    private var appContext: Context

    constructor(context: Context) {
        this.appContext = context
    }


    @Provides
    @AppScope
    @AppContext
    fun context(): Context = appContext
}

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

    constructor(context: Context)
}

//@DataScope
class MyDataResposity {

    @Inject
    constructor()

    fun getData() = "aaaaa"
}

@Module
class DataModule {

    @Provides
    @DataScope
    fun provideDataResposity(@ActivityContext activity: Context): DataResposity {
        return DataResposity(activity)
    }
}

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {
    //    fun plusActivityModule(activityModule: ActivityModule): ActivityComponent
    fun activityComponentBuilder(): ActivityComponent.Builder
}

@Subcomponent(modules = [ActivityModule::class])
@ActivityScope
interface ActivityComponent {
    //    fun plusDataModule(dataModule: DataModule): DataComponent
    @Subcomponent.Builder
    interface Builder {
        fun activityModule(activityModule: ActivityModule): ActivityComponent.Builder
        fun build(): ActivityComponent
    }

    fun dataComponentBuilder(): DataComponent.Builder
}

@Subcomponent(modules = [DataModule::class])
@DataScope
interface DataComponent {

    @Subcomponent.Builder
    interface Builder {
        fun dataModule(dataModule: DataModule): DataComponent.Builder
        fun build(): DataComponent
    }

    fun inject(mainActivity: MainActivity)
}

@Component(modules = [AppModule::class])
@Singleton
interface SecondComponent{
    fun inject(secondActivity: SecondActivity )
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope {

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope {

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DataScope {

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityContext {

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppContext {

}

