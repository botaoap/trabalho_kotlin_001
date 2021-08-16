package com.serasa.trabalho_001.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serasa.trabalho_001.R
import com.serasa.trabalho_001.model.ResultApi
import com.serasa.trabalho_001.modelArticle.Article

class AdapterListApi(
    var listOfArticle: MutableList<Article>,
    var clickableArticle : ClickListArticle
) : RecyclerView.Adapter<ItemMakeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMakeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_article,parent, false)
        return ItemMakeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemMakeViewHolder, position: Int) {
        listOfArticle[position].apply {
            holder.bind(this)
            holder.itemView.setOnClickListener {
                clickableArticle.onClickableArticle(this)
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfArticle.size
    }

    fun refresh(list : List<Article>){
        listOfArticle = mutableListOf()
        listOfArticle.addAll(list)
        notifyDataSetChanged()
    }
}

class ItemMakeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bind(article : Article) {
        itemView.findViewById<TextView>(R.id.textviewTitleListArticle).apply {
            text = article.title
        }
        itemView.findViewById<ImageView>(R.id.imageViewListArticle).apply {
            Glide.with(context)
                .load(article.image)
                .placeholder(R.drawable.ic_error)
                .into(this)
        }
    }
}