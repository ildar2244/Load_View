package com.example.load_view.di

import com.example.load_view.presentation.picture.PictureFragment
import com.example.load_view.presentation.pictures_list.PicturesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun injectPicturesListFragment(): PicturesListFragment

    @ContributesAndroidInjector
    abstract fun injectPictureFragment(): PictureFragment
}