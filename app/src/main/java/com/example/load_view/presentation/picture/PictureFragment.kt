package com.example.load_view.presentation.picture

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.load_view.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_picture.*
import javax.inject.Inject


class PictureFragment : Fragment() {

    private val args: PictureFragmentArgs by navArgs()
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel by viewModels<PictureViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val flags = (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        activity?.window?.decorView?.systemUiVisibility = flags
    }

    override fun onDetach() {
        super.onDetach()
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPictureBitmap(args.picture.id)
        init()
    }

    private fun init() {

        viewModel.apply {

            imageBitmap.observe(viewLifecycleOwner) {
                iv_picture.setImageBitmap(it)
            }

            imageUrl.observe(viewLifecycleOwner) {
                tv_picture_url.text = it
            }
        }
    }
}