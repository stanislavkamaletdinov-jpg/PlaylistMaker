package com.example.playlistmaker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import androidx.core.widget.doOnTextChanged

class SearchActivity : AppCompatActivity() {
    var searchText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)

        val backButton = findViewById<MaterialButton>(R.id.backButton)

        backButton.setOnClickListener {
            finish()
        }

        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val clearButton = findViewById<ImageView>(R.id.clearButton)
        val navigationMedia = findViewById<LinearLayout>(R.id.navigationMedia)
        val navigationSettings = findViewById<LinearLayout>(R.id.navigationSettings)

        searchEditText.doOnTextChanged { s, start, before, count ->
            searchText = s?.toString() ?: ""
            if (s.isNullOrEmpty()) {
                clearButton.visibility = View.GONE
            } else {
                clearButton.visibility = View.VISIBLE
            }
            // TODO: Добавить поиск
        }

        clearButton.setOnClickListener {
            searchEditText.text.clear()
            WindowCompat.getInsetsController(
                window, searchEditText
            )
                .hide(WindowInsetsCompat.Type.ime())
        }

        navigationMedia.setOnClickListener {
            val intent = Intent(this, MediaLibraryActivity::class.java)
            startActivity(intent)
        }

        navigationSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("SEARCH_TEXT", searchText)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        searchText = savedInstanceState.getString("SEARCH_TEXT", "")
        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        searchEditText.setText(searchText)
    }
}
