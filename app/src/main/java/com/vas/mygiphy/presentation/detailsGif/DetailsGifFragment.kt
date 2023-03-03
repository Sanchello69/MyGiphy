package com.vas.mygiphy.presentation.detailsGif

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.vas.mygiphy.R
import com.vas.mygiphy.appComponent
import com.vas.mygiphy.data.model.GifData
import com.vas.mygiphy.databinding.FragmentDetailsGifBinding
import com.vas.mygiphy.databinding.FragmentGifsBinding
import com.vas.mygiphy.presentation.listGif.GifsViewModel
import com.vas.mygiphy.presentation.listGif.GifsViewModelFactory
import kotlinx.android.synthetic.main.card_text_view.view.*
import javax.inject.Inject

class DetailsGifFragment : Fragment() {

    private var binding: FragmentDetailsGifBinding? = null

    private val viewModel: DetailsGifViewModel by lazy {
        ViewModelProvider(this)[DetailsGifViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsGifBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setupObservers() {
        viewModel.detailsGif.observe(viewLifecycleOwner){
            binding?.apply {
                gifView.load(it.images.gifUrl.url){
                    crossfade(true)
                    listener(
                        onStart = {
                            binding.apply {
                                progressBar.isVisible = true
                                buttonError.isVisible = false
                            }
                        },
                        onSuccess = { _, _ ->
                            binding.apply {
                                progressBar.isVisible = false
                                buttonError.isVisible = false
                            }
                        },
                        onError = { _, _ ->
                            binding.apply {
                                progressBar.isVisible = false
                                buttonError.isVisible = true
                            }
                        }
                    )
                }
                titleView.root.textView.text = "Title: ${it.title}"
                urlView.root.textView.text = "Url: ${it.url}"
                ratingView.root.textView.text = "Rating: ${it.rating}"
                dateView.root.textView.text = "Date: ${it.datetime}"
            }
        }
    }

}