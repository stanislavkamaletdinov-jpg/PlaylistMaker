package com.example.playlistmaker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

class SearchActivity : AppCompatActivity() {
    var searchText: String = ""

    val tracks = arrayListOf<Track>()

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

        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // ВЫПОЛНЯЙТЕ ПОИСКОВЫЙ ЗАПРОС ЗДЕСЬ
                true
            } else { false }
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

        tracks.addAll(
            listOf(
                Track(
                    "Smells Like Teen Spirit",
                "Nirvana",
                "5:01",
                "https://is5-ssl.mzstatic.com/image/thumb/Music115/v4/7b/58/c2/7b58c21a-2b51-2bb2-e59a-9bb9b96ad8c3/00602567924166.rgb.jpg/100x100bb.jpg"
                ),
                        Track(
                        "Billie Jean",
                "Michael Jackson",
                "4:35",
                "https://is5-ssl.mzstatic.com/image/thumb/Music125/v4/3d/9d/38/3d9d3811-71f0-3a0e-1ada-3004e56ff852/827969428726.jpg/100x100bb.jpg"
            ),
                Track(
                    "Stayin' Alive",
                    "Bee Gees",
                    "4:10",
                    "https://is4-ssl.mzstatic.com/image/thumb/Music115/v4/1f/80/1f/1f801fc1-8c0f-ea3e-d3e5-387c6619619e/16UMGIM86640.rgb.jpg/100x100bb.jpg"
                ),
                Track(
                    "Whole Lotta Love",
                    "Led Zeppelin",
                    "5:33",
                    "https://is2-ssl.mzstatic.com/image/thumb/Music62/v4/7e/17/e3/7e17e33f-2efa-2a36-e916-7f808576cf6b/mzm.fyigqcbs.jpg/100x100bb.jpg"
                ),
                Track(
                    "Sweet Child O'Mine",
                    "Guns N' Roses",
                    "5:03",
                    "https://is5-ssl.mzstatic.com/image/thumb/Music125/v4/a0/4d/c4/a04dc484-03cc-02aa-fa82-5334fcb4bc16/18UMGIM24878.rgb.jpg/100x100bb.jpg"
                )
            )
        )

        val tracksRecyclerView = findViewById<RecyclerView>(R.id.tracksRecyclerView)
        tracksRecyclerView.layoutManager = LinearLayoutManager(this)
        tracksRecyclerView.adapter = TrackAdapter(tracks)
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
