package com.serasa.trabalho_001.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.serasa.trabalho_001.R
import com.serasa.trabalho_001.fragments.ListOfArticlesFragment
import com.serasa.trabalho_001.fragments.UserDetailFragment
import com.serasa.trabalho_001.modelArticle.Article

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFrag(ListOfArticlesFragment())


        findViewById<BottomNavigationView>(R.id.bottom_navigation).apply {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.page_1 -> {
                        // Respond to navigation item 1 click
                        replaceFrag(ListOfArticlesFragment())
                        true
                    }
                    R.id.page_2 -> {
                        Intent(context, HomeActivity::class.java).apply {
                            startActivity(this)
                        }
                        true
                    }
                    R.id.page_3 -> {
                        replaceFrag(UserDetailFragment())
                        true
                    }
                    else -> false
                }
            }
        }
    }

    fun replaceFrag(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .commit()
    }

    fun gotToDetailActovity(article : Article){
        Intent(this, ItemDetailActivity::class.java).apply {
            putExtra("key_detail_article", article.id)
            startActivity(this)
        }
    }
}