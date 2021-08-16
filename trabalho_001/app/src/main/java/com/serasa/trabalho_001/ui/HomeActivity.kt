package com.serasa.trabalho_001.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.serasa.trabalho_001.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<Button>(R.id.buttonNext).setOnClickListener {
            nextActivity()
        }
    }

    fun nextActivity() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
        }
    }
}