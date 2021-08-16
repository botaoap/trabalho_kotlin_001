package com.serasa.trabalho_001.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.serasa.trabalho_001.R
import com.serasa.trabalho_001.enum.LinkApi
import com.serasa.trabalho_001.modelArticle.Article
import com.serasa.trabalho_001.serviceRetrofit.Endpoint
import com.serasa.trabalho_001.serviceRetrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemDetailActivity : AppCompatActivity(), Callback<Article> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_article)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val getDetail = intent.getIntExtra("key_detail_article", 0) as Int

        connectionToApiForId(getDetail)
    }

    fun connectionToApiForId(id : Int) {
        RetrofitBuilder
            .getConnectioRetrofit(LinkApi.ARTICLE.url)
            .create(Endpoint::class.java)
            .getAritcleById(id)
            .clone()
            .enqueue(this)
    }

    fun bind(article: Article) {
        findViewById<TextView>(R.id.textViewTitle).apply {
            text = article.title
        }
        findViewById<TextView>(R.id.textViewNewsSite).apply {
            text = article.news
        }
        findViewById<TextView>(R.id.textViewSummary).apply {
            text = article.summary
        }
        findViewById<ImageView>(R.id.imageViewArticle).apply {
            Glide.with(context)
                .load(article.image)
                .placeholder(R.drawable.ic_error)
                .into(this)
        }
    }

    override fun onResponse(call: Call<Article>, response: Response<Article>) {
        println("Nice Connection")
        response.body()?.apply {
            bind(this)
        }
    }

    override fun onFailure(call: Call<Article>, t: Throwable) {
        println("Bad connection")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

}