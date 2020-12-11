package com.example.load_view.presentation.pictures_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.load_view.R
import com.example.load_view.presentation.model.Picture
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pictures_list.*
import javax.inject.Inject

class PicturesListFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel by viewModels<PicturesListViewModel> { viewModelFactory }
    private lateinit var mAdapter: PicturesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
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
        return inflater.inflate(R.layout.fragment_pictures_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        mAdapter = PicturesAdapter(clickEventByItem())
        rv_images.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mAdapter
        }

        viewModel.apply {

            loading.observe(viewLifecycleOwner) { isShow ->
                pb_images.visibility = if (isShow) View.VISIBLE else View.GONE
            }

            images.observe(viewLifecycleOwner) {
                mAdapter.submitList(it)
            }

            error.observe(viewLifecycleOwner) { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clickEventByItem(): (Picture) -> Unit {
        return { p ->
            findNavController().navigate(
                PicturesListFragmentDirections.actionPicturesListFragmentToPictureFragment(p)
            )
        }
    }
}