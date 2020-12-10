package com.example.load_view.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.load_view.R
import com.example.load_view.presentation.pictures_list.PicturesListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, PicturesListFragment(), PicturesListFragment::class.java.name)
            .commit()*/

        findNavController(R.id.nav_host_fragment)
    }
}