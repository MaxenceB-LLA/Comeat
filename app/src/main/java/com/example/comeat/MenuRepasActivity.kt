package com.example.comeat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuRepasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_repas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn_participation : Button = findViewById(R.id.btn_participation)
        btn_participation.setOnClickListener {
            val intent = Intent(this, RepasActivity::class.java)
            startActivity(intent)
        }
        val btn_repas : Button = findViewById(R.id.btn_repas)
        btn_repas.setOnClickListener {
            val intent = Intent(this, RechercheRepasActivity::class.java)
            startActivity(intent)
        }
    }
}