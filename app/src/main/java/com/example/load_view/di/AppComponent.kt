package com.example.load_view.di

import com.example.load_view.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class, AppModule::class, RepositoryModule::class,
    NetworkModule::class, ViewModelModule::class, FragmentModule::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: App): AppComponent
    }
}