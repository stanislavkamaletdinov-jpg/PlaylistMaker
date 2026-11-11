package com.example.playlistmaker

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SearchHistory (
    private val sharedPreferences: SharedPreferences
) {
    private val searchHistory: MutableList<Track> = mutableListOf()

    fun readHistory() {
       val json = sharedPreferences.getString("search_history", null)
        searchHistory.clear()
        if (json.isNullOrBlank()) {
            return
        }

        val historyType = object : TypeToken<List<Track>>() {}.type

        val savedTracks = gson.fromJson<List<Track>?>(json, historyType)
        if (savedTracks.isNullOrEmpty()) {
            return
        }

        searchHistory.addAll(savedTracks)
    }

    fun getHistory(): List<Track> {
        readHistory()
        return searchHistory.toList()
    }

    private fun saveHistory() {
        val json = gson.toJson(searchHistory)

        sharedPreferences.edit {
            putString("search_history", json)
        }
    }

    fun clearHistory() {
        searchHistory.clear()
        saveHistory()
    }

    fun addTrack(track: Track) {
        readHistory()

        if (track.trackId != null) {
            searchHistory.removeAll {
                it.trackId == track.trackId
            }
        }

        searchHistory.add(0, track)
        while (searchHistory.size > 10) {
            searchHistory.removeAt(searchHistory.lastIndex)
        }

        saveHistory()
    }


    private val gson = Gson()
}