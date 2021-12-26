package com.androidxlab.dependencyinjection.di

import android.content.Context
import com.androidxlab.dependencyinjection.MainActivity
import com.androidxlab.dependencyinjection.di.modules.AppModule
import com.androidxlab.dependencyinjection.di.modules.ViewModelFactoryModule
import com.androidxlab.dependencyinjection.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, ViewModelFactoryModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(activity: MainActivity)
}