package com.yagmurceliksoy.bookapp.ui.home.adapter

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.yagmurceliksoy.bookapp.MainApplication
import com.yagmurceliksoy.bookapp.R
import com.yagmurceliksoy.bookapp.common.viewBinding
import com.yagmurceliksoy.bookapp.data.model.Book
import com.yagmurceliksoy.bookapp.data.model.GetBooksResponse
import com.yagmurceliksoy.bookapp.databinding.FragmentBooksBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksFragment : Fragment(R.layout.fragment_books), BooksAdapter.BookListener {

    private val binding by viewBinding(FragmentBooksBinding::bind)

    private val booksAdapter by lazy { BooksAdapter(this) }

    var bestSellerBook : Book? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvBooks.adapter = booksAdapter
            ivBestSellerBook.setOnClickListener {
                val action = bestSellerBook?.id?.let { it ->
                    BooksFragmentDirections.actionBooksFragmentToDetailFragment(it)
                }
                if (action != null) {
                    findNavController().navigate(action)
                }
            }
            getBooks()
        }
    }

    private fun getBooks() {
        MainApplication.bookService?.getProducts()?.enqueue(object :
            Callback<GetBooksResponse> {
            override fun onResponse(call: Call<GetBooksResponse>, response: Response<GetBooksResponse>) {
                val result = response.body()?.books

                bestSellerBook = result?.firstOrNull { it.bestSeller }

                bestSellerBook?.id
                bestSellerBook?.let {
                    Glide.with(requireContext())
                        .load(it.imageUrl)
                        .into(binding.ivBestSellerBook)
                }

                if (result.isNullOrEmpty().not()) {
                    booksAdapter.submitList(result)
                }
            }

            override fun onFailure(call: Call<GetBooksResponse>, t: Throwable) {
                Log.e("GetProducts", t.message.orEmpty())
            }
        })
    }

    override fun onBookClick(id: Int) {
        val action = BooksFragmentDirections.actionBooksFragmentToDetailFragment(id)
        findNavController().navigate(action)
    }
}