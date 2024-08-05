package com.example.starwarsapp.ui.fragments

import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapp.R
import com.example.starwarsapp.adapter.CharactersAdapter
import com.example.starwarsapp.databinding.FragmentCharactersBinding
import com.example.starwarsapp.ui.MainActivity
import com.example.starwarsapp.ui.viewmodels.CharactersViewModel
import com.example.starwarsapp.utils.Constants
import com.example.starwarsapp.utils.Resources
import com.example.starwarsapp.utils.checkInternetConnection
import kotlinx.coroutines.launch


class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private lateinit var binding: FragmentCharactersBinding
    lateinit var charactersViewModel: CharactersViewModel
    val myAdapter = CharactersAdapter()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharactersBinding.bind(view)

        charactersViewModel = (activity as MainActivity).charactersViewModel
        setUpRecycler()

        if (checkInternetConnection(requireContext())) {
            lifecycleScope.launch {
                charactersViewModel.charactersList.collect {
                    myAdapter.submitData(it)
                }
            }
        } else {
            binding.chItemError.visibility = View.VISIBLE
            binding.button.setOnClickListener {
                if (checkInternetConnection((activity as MainActivity).applicationContext)) {
                    binding.chItemError.visibility = View.INVISIBLE
                    lifecycleScope.launch {
                        charactersViewModel.charactersList.collect {
                            myAdapter.submitData(it)
                        }
                    }

                } else {
                    Toast.makeText(
                        (activity as MainActivity).applicationContext,
                        " Network Error ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
            // Setting Listener for Click On Item
            myAdapter.onItemClickListener {

                val bundle = Bundle().apply {
                    putParcelable("character", it)
                }
                findNavController().navigate(
                    R.id.action_charactersFragment_to_characterDetailsFragment,
                    bundle
                )
            }
        }
    fun setUpRecycler() {
        binding.charactersRV.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(activity)

        }

    }

    }














/*  if(checkInternetConnection((activity as MainActivity).applicationContext)){
// getting Characters From View Model
charactersViewModel.getCharacters(pageNumber)

// Observing On Live Data
charactersObserve()

// Setting Listener for Click On Item
myAdapter.onItemClickListener {

    val bundle = Bundle().apply {
        putParcelable("character",it)
    }
    findNavController().navigate(R.id.action_charactersFragment_to_characterDetailsFragment,bundle)
}
}else{

binding.chItemError.visibility = View.VISIBLE
binding.button.setOnClickListener {
    if (checkInternetConnection((activity as MainActivity).applicationContext)){
        charactersViewModel.getCharacters(pageNumber)
        charactersObserve()
    }else{
        Toast.makeText((activity as MainActivity).applicationContext," Network Error " ,Toast.LENGTH_SHORT).show()
    }

}
}*/


/*  private fun charactersObserve(){
     charactersViewModel._characters.observe(viewLifecycleOwner, Observer {
         when(it){
             is Resources.Fail -> {
                 binding.chItemError.visibility = View.VISIBLE
                 binding.chProgressBar.visibility = View.GONE
             }
             is Resources.Loading ->  {
                 binding.chItemError.visibility = View.GONE
                 binding.chProgressBar.visibility = View.VISIBLE

             }
             is Resources.Success ->{
                 binding.chProgressBar.visibility = View.GONE
                 binding.chItemError.visibility = View.GONE

                 myAdapter.differ.submitList(it.data?.results?.toList())

             }
         }
     })
 }*/


/*
var isError = false
var isLoading = false
var isLastPage = false
var isScrolling = false

val scrollListener = object : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount

        val isNoErrors = !isError
        val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
        val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
        val isNotAtBeginning = firstVisibleItemPosition >= 0
        val isTotalMoreThanvisible = totalItemCount >= Constants.QUERY_PAGE_SIZE
        val shouldPaginate = isNoErrors && isNotLoadingAndNotLastPage
                && isNotAtBeginning && isTotalMoreThanvisible && isScrolling
        if (shouldPaginate) {


            isScrolling = false
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
            pageNumber++
            charactersViewModel.getCharacters(pageNumber)
            myAdapter.notifyDataSetChanged()
            isScrolling = true
        }
    }
}
*/