package com.vas.mygiphy.presentation.listGif

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vas.mygiphy.R
import com.vas.mygiphy.data.model.GifData
import com.vas.mygiphy.databinding.GifItemBinding

class GifsAdapter(private val onClick: (item: GifData) -> Unit) :
    PagingDataAdapter<GifData, GifsAdapter.ViewHolder>(DiffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(
            R.layout.gif_item, parent, false
        )
        val binding = GifItemBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: GifItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GifData) {

            fun loadGif(){
                binding.gifView.load(item.images.gifUrl.url){
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
            }

            loadGif()

            binding.cardView.setOnClickListener {
                onClick.invoke(item)
            }
            binding.buttonError.setOnClickListener {
                loadGif()
            }
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<GifData>() {
        override fun areItemsTheSame(oldItem: GifData, newItem: GifData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GifData, newItem: GifData): Boolean {
            return oldItem == newItem
        }
    }
}