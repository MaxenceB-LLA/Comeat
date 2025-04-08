package com.example.comeat

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RechercheRepasActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recherche_repas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var specialiteRepas: String = ""
        var dateRepas: String = ""

        val btn_valider : Button = findViewById(R.id.btn_valider)
        btn_valider.isEnabled = false
        btn_valider.alpha = 0.5f

        val spnSpecialite: Spinner = findViewById( R.id.select_specialite)
        val adaptateur = ArrayAdapter( this , android.R.layout.simple_spinner_item , Modele.getSpecialites())

        val btn_aff : TextView = findViewById( R.id.date)
        val btn_date : Button = findViewById( R.id.btn_select_date)

        fun verifValider(){
            if (spnSpecialite.selectedItem !=null && btn_aff.text.isEmpty()){
                btn_valider.isEnabled = true
                btn_valider.alpha = 1.0f
            }
        }

        btn_date.setOnClickListener {
            val dateCourante = LocalDate.now()
            val annee = dateCourante.year
            val mois = dateCourante.monthValue - 1
            val jour = dateCourante.dayOfMonth

            val datePickerDialog = DatePickerDialog(
                this,
                {
                    view , anneeSelect , moisSelect , jourSelect ->

                    val dateSelectionnee = LocalDate.of(
                        anneeSelect ,
                        moisSelect + 1,
                        jourSelect)
                val formateur = DateTimeFormatter.ofPattern( "dd/MM/yyyy")
                val dateFormatee = dateSelectionnee.format( formateur )

                btn_aff.text = dateFormatee
                    dateRepas = dateFormatee
                } ,
                annee , mois , jour
            )

            datePickerDialog.show()
            verifValider()
        }

        adaptateur.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item)

        spnSpecialite.adapter = adaptateur

        spnSpecialite.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                specialiteRepas = spnSpecialite.selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Action à effectuer quand rien n'est sélectionné
                verifValider()
            }
        }

        btn_valider.setOnClickListener{
            val intent = Intent( this, ListeRepasActivity::class.java)

            //intent.putExtra( "specialite_repas" , specialiteRepas )
            //intent.putExtra( "date_repas" , dateRepas.toString() )

            startActivity( intent )
        }
    }
}