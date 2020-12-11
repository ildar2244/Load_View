package com.example.load_view.di

import com.example.load_view.data.PicsumImagesRepositoryImpl
import com.example.load_view.domain.IPicsumImagesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providePicsumImagesRepository(repositoryImpl: PicsumImagesRepositoryImpl): IPicsumImagesRepository
}