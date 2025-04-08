package com.example.comeat

import android.util.Log
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.TextView
import Modele
import Utilisateur
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saisieEmail : TextView = findViewById( R.id.et_Email)
        val saisieMdp : TextView = findViewById( R.id.et_mdp)

        val btnValider : Button = findViewById(R.id.btnValider)
        btnValider.setOnClickListener {
            val email : String = saisieEmail.text.toString()
            val mdp : String = saisieMdp.text.toString()

            var user : Utilisateur? = Modele.findUtilisateur(email, mdp)

            //Verif des données
            if(user != null){
                val intent = Intent( this , MenuRepasActivity::class.java )
                startActivity( intent )
            }
            else {
                Toast.makeText(this, "Identifiants Incorrect", Toast.LENGTH_SHORT).show()
            }

            Log.d( "ACT_CONN" , "Tentative de connexion : $email/$mdp" )
        }
        val boutonAnnuler : Button = findViewById( R.id.btn_annuler)
        boutonAnnuler.setOnClickListener {
            saisieEmail.setText("")
            saisieMdp.setText("")

            Log.d( "ACT_CONN" , "Annulation" )
        }

    }
}