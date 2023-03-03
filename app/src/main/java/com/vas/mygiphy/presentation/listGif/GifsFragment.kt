package com.vas.mygiphy.presentation.listGif

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.vas.mygiphy.R
import com.vas.mygiphy.appComponent
import com.vas.mygiphy.databinding.FragmentGifsBinding
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class GifsFragment : Fragment() {

    private val viewModel: GifsViewModel by viewModels{
        viewModelFactory
    }

    @Inject
    lateinit var viewModelFactory: GifsViewModelFactory

    private var binding: FragmentGifsBinding? = null
    private var listAdapter: GifsAdapter? = null

    private val pagingAdapter by lazy {
        GifsAdapter{
            val action = GifsFragmentDirections.actionGifsFragmentToDetailsGifFragment(it)
            view?.let { view ->
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGifsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupListeners()
        setupUI()
    }

    override fun onDestroyView() {
        binding = null
        listAdapter = null

        super.onDestroyView()
    }

    private fun setupListeners() {
        binding?.apply {

            retryButton.setOnClickListener {
                pagingAdapter.retry()
            }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    viewModel.updateQuerySearch(p0 ?: "")
                    return true
                }

            })

            pagingAdapter.addLoadStateListener {
                progressBar.isVisible = it.refresh is LoadState.Loading
                retryButton.isVisible = it.refresh is LoadState.Error
                errorTextView.isVisible = it.refresh is LoadState.Error
            }

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    searchView.clearFocus()
                    super.onScrollStateChanged(recyclerView, newState)
                }
            })
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch{
            viewModel.gifListData.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

    private fun setupUI() {
        binding?.recyclerView?.adapter = pagingAdapter
    }

}