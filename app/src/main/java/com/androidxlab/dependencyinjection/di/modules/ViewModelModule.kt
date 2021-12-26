package com.androidxlab.dependencyinjection.di.modules

import androidx.lifecycle.ViewModel
import com.androidxlab.dependencyinjection.data.network.DefaultRepository
import com.androidxlab.dependencyinjection.data.Repository
import com.androidxlab.dependencyinjection.movieslist.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun provideViewModel(viewModel: MovieViewModel): ViewModel

    @Binds
    abstract fun provideRepository(repository: DefaultRepository): Repository
}