package com.example.playlistmaker

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial
import androidx.core.content.edit

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left + 16, systemBars.top + 16, systemBars.right + 16, systemBars.bottom + 16)
            insets
        }

        val themeSwitch = findViewById<SwitchMaterial>(R.id.themeSwitch)

        val sharedPrefs = getSharedPreferences(
            "playlist_maker_preferences",
            MODE_PRIVATE
        )

        val isDarkTheme =
            (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) ==
                    Configuration.UI_MODE_NIGHT_YES

        themeSwitch.isChecked = isDarkTheme

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->

            sharedPrefs.edit {
                putBoolean("dark_theme", isChecked)
            }

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        val shareButton = findViewById<MaterialTextView>(R.id.shareButton)
        val contactSupportButton = findViewById<MaterialTextView>(R.id.contactSupportButton)
        val userAgreementButton = findViewById<MaterialTextView>(R.id.userAgreementButton)
        val backButton = findViewById<MaterialButton>(R.id.backButton)



    }
}

