package com.yagmurceliksoy.bookapp.data.source.remote

import com.yagmurceliksoy.bookapp.common.Constants.Endpoint.GET_BOOKS
import com.yagmurceliksoy.bookapp.common.Constants.Endpoint.GET_BOOK_DETAIL
import com.yagmurceliksoy.bookapp.data.model.GetBookDetailResponse
import com.yagmurceliksoy.bookapp.data.model.GetBooksResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET(GET_BOOKS)
    fun getProducts(): Call<GetBooksResponse>

    @GET(GET_BOOK_DETAIL)
    fun getBookDetail(
        @Query("id") id: Int
    ): Call<GetBookDetailResponse>
}