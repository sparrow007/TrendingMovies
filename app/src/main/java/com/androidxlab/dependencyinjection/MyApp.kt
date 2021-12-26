package com.androidxlab.dependencyinjection

import android.app.Application
import com.androidxlab.dependencyinjection.di.AppComponent
import com.androidxlab.dependencyinjection.di.DaggerAppComponent

class MyApp : Application() {

    val daggerComponent: AppComponent by lazy {
        provideDaggerComponent()
    }

    private fun provideDaggerComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}