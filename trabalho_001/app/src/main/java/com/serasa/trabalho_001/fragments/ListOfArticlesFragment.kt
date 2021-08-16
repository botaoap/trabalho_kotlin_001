package com.serasa.trabalho_001.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.serasa.trabalho_001.R
import com.serasa.trabalho_001.adapter.AdapterListApi
import com.serasa.trabalho_001.adapter.ClickListArticle
import com.serasa.trabalho_001.enum.LinkApi
import com.serasa.trabalho_001.modelArticle.Article
import com.serasa.trabalho_001.serviceRetrofit.Endpoint
import com.serasa.trabalho_001.serviceRetrofit.RetrofitBuilder
import com.serasa.trabalho_001.ui.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListOfArticlesFragment : Fragment(R.layout.fragment_list_of_articles), Callback<List<Article>>, ClickListArticle {

    private lateinit var showArticleRecyclerView : RecyclerView
    private lateinit var adapter : AdapterListApi

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        connectionToApiArticle()

        adapter = AdapterListApi(mutableListOf(), this)

        showArticleRecyclerView = view.findViewById(R.id.recyclerViewListArticle)
        showArticleRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        showArticleRecyclerView.adapter = adapter

    }

    fun connectionToApiArticle(){
        RetrofitBuilder
            .getConnectioRetrofit(LinkApi.ARTICLE.url)
            .create(Endpoint::class.java)
            .getArtcicle()
            .clone()
            .enqueue(this)
    }

    override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
        println("Response On")
        response.body()?.apply {
            adapter.refresh(this)
        }
    }

    override fun onFailure(call: Call<List<Article>>, t: Throwable) {
        println("Failure")
    }

    override fun onClickableArticle(article: Article) {
        (requireActivity() as? MainActivity)?.gotToDetailActovity(article)

    }
}