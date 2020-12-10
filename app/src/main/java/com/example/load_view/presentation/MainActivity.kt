package com.example.load_view.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.load_view.R
import com.example.load_view.presentation.images_list.ImagesListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, ImagesListFragment(), ImagesListFragment::class.java.name)
            .commit()
    }
}