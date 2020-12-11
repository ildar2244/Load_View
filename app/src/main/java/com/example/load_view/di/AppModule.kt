package com.example.load_view.di

import android.content.Context
import com.example.load_view.App
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(app: App): Context {
        return app.applicationContext
    }
}