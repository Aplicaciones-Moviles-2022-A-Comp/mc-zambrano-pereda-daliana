package com.example.movcompdzp2022a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class Editar_Pelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_pelicula)
    }

    override fun onStart() {
        super.onStart()

        val posicionPelicula = intent.getIntExtra("posicionEditar",1)

        val editarnombrePelicula = findViewById<TextView>(R.id.tpn_nombreInput)
        val editaranioCreacion = findViewById<TextView>(R.id.eTN_anioCreacionInput)
        val editarnumSagas = findViewById<TextView>(R.id.sp_numSagas)
        val editarpresupuesto = findViewById<TextView>(R.id.etND_presupuestoInput)
        val editartuvoExito = findViewById<TextView>(R.id.sp_exitoMercado)

        CompanionObjecto.arrayPeliculas.forEachIndexed{ indice: Int, pelicula : Pelicula ->

            if (indice == posicionPelicula){
                editarnombrePelicula.setText(pelicula.nombrePelicula)
                editaranioCreacion.setText(pelicula.anioCreacion.toString())
                editarnumSagas.setText(pelicula.numSagas)
                editarpresupuesto.setText(pelicula.presupuesto.toString())
                editartuvoExito.setText(pelicula.tuvoExito)
            }
        }

        val btnGuardarCambios = findViewById<Button>(R.id.btn_GuardadPelicula)
        btnGuardarCambios.setOnClickListener {
            CompanionObjecto.arrayPeliculas.forEachIndexed{ indice: Int, pelicula: Pelicula ->
                if (indice == posicionPelicula){

                    pelicula.nombrePelicula = editarnombrePelicula.text.toString()
                    pelicula.anioCreacion = editaranioCreacion.text.toString().toInt()
                    pelicula.numSagas = editarnumSagas.text.toString()
                    pelicula.presupuesto = editarpresupuesto.text.toString().toInt()
                    pelicula.tuvoExito = editartuvoExito.text.toString()
                }
            }
            val intentEditSucces = Intent(this, ListaPeliculas_Main::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelarEditar = findViewById<Button>(R.id.btn_cancelar)
        btnCancelarEditar.setOnClickListener{
            val intentEditCancel = Intent(this, ListaPeliculas_Main::class.java)
            startActivity(intentEditCancel)
        }

    }
}