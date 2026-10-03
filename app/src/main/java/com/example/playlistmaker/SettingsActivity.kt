package com.example.playlistmaker

import android.content.Intent
import android.os.Bundle
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
import androidx.core.net.toUri

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        val backButton = findViewById<MaterialButton>(R.id.backButton)

        backButton.setOnClickListener {
            finish()
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
        shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app_text))
            }
            val chooserIntent = Intent.createChooser(shareIntent, null)
            startActivity(chooserIntent)
        }

        val contactSupportButton = findViewById<MaterialTextView>(R.id.contactSupportButton)
        contactSupportButton.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = "mailto:".toUri()
                putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.support_email)))
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.support_subject))
                putExtra(Intent.EXTRA_TEXT, getString(R.string.support_body))
            }
            val chooserIntent = Intent.createChooser(emailIntent, null)
            startActivity(chooserIntent)
        }

        val userAgreementButton = findViewById<MaterialTextView>(R.id.userAgreementButton)
        userAgreementButton.setOnClickListener {
            val userAgreementIntent = Intent(
                Intent.ACTION_VIEW,
                getString(
                    R.string.user_agreement_url
                ).toUri())
            startActivity(userAgreementIntent)
        }

    }
}

