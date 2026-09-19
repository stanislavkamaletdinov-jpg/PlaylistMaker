package com.example.playlistmaker

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

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

        val shareButton = findViewById<MaterialButton>(R.id.shareButton)
        val contactSupportButton = findViewById<MaterialButton>(R.id.contactSupportButton)
        val userAgreementButton = findViewById<MaterialButton>(R.id.userAgreementButton)
        val backButton = findViewById<MaterialButton>(R.id.backButton)

//        shareButton.setOnClickListener()
//        contactSupportButton.setOnClickListener()
//        userAgreementButton.setOnClickListener()
//        backButton.setOnClickListener()
    }
}

