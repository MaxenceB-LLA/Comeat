package com.example.comeat

import Repas
import android.icu.text.Transliterator.Position
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnCreateContextMenuListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.WindowInsetsCompat.Type
import androidx.recyclerview.widget.RecyclerView
import java.time.format.DateTimeFormatter

class RepasAdapter( private val lesRepas: List<Repas> ) : RecyclerView.Adapter< RepasAdapter.RepasViewHolder >(){

    class RepasViewHolder( view: View ) : RecyclerView.ViewHolder( view ){

        val tvDate: TextView = view.findViewById( R.id.tv_date )
        val tvSpecialite: TextView = view.findViewById( R.id.tv_specialite )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate( R.layout.activity_repas , parent , false)
        return RepasViewHolder( view )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RepasViewHolder, position: Int) {
        val repas = lesRepas.get( position )
        val formateur = DateTimeFormatter.ofPattern( "dd/MM/yyyy")

        holder.tvDate.text = repas.date.format(formateur)
        holder.tvSpecialite.text = repas.specialite.libelle
    }

    override fun getItemCount(): Int {
        return lesRepas.size
    }
}
