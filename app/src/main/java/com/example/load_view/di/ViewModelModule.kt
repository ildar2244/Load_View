package com.example.load_view.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.load_view.presentation.picture.PictureViewModel
import com.example.load_view.presentation.pictures_list.PicturesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: InjectingViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PicturesListViewModel::class)
    abstract fun providePicturesListViewModel(viewmodel: PicturesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PictureViewModel::class)
    abstract fun providePictureViewModel(viewmodel: PictureViewModel): ViewModel
}