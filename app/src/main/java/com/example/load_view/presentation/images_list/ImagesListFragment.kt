package com.example.load_view.presentation.images_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.load_view.R
import kotlinx.android.synthetic.main.fragment_images_list.*

class ImagesListFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(ImagesListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_images_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        viewModel.apply {

            loading.observe(viewLifecycleOwner) { isShow ->
                pb_images.visibility = if (isShow) View.VISIBLE else View.INVISIBLE
            }

            images.observe(viewLifecycleOwner) {
                Log.d("9999", "init: IMAGES: ${it[1].url}")
            }

            error.observe(viewLifecycleOwner) { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}