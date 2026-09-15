package com.example.playlistmaker

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left + 16, systemBars.top + 16, systemBars.right + 16, systemBars.bottom + 16)
            insets
        }

        val searchButton = findViewById<MaterialButton>(R.id.searchButton)
        val mediaButton = findViewById<MaterialButton>(R.id.mediaButton)
        val settingsButton = findViewById<MaterialButton>(R.id.settingsButton)

        val imageClickListener: View.OnClickListener =
            View.OnClickListener { Toast.makeText(this@MainActivity, "Нажали на кнопку!", Toast.LENGTH_SHORT).show() }

        searchButton.setOnClickListener(imageClickListener)
        mediaButton.setOnClickListener(imageClickListener)
        settingsButton.setOnClickListener(imageClickListener)
    }


}

