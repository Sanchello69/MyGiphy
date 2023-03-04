package com.vas.mygiphy.presentation.detailsGif

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vas.mygiphy.databinding.FragmentDetailsGifBinding
import com.vas.mygiphy.utils.loadGif
import kotlinx.android.synthetic.main.card_text_view.view.*

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

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setupObservers() {
        viewModel.detailsGif.observe(viewLifecycleOwner){
            binding?.apply {
                gifView.loadGif(
                    url = it.images.gifUrl.url,
                    progressBar = progressBar,
                    buttonError = null
                )
                titleView.root.textView.text = "Title: ${it.title}"
                urlView.root.textView.text = "Url: ${it.url}"
                ratingView.root.textView.text = "Rating: ${it.rating}"
                dateView.root.textView.text = "Date: ${it.datetime}"
            }
        }
    }

}