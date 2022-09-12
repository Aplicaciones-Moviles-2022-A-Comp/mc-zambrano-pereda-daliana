package com.example.movcompdzp2022a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class Editar_Personaje : AppCompatActivity() {

    var personajePos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_personaje)
    }

    override fun onStart() {
        super.onStart()

        val idPelicula_Personaje = intent.getIntExtra("personaje", 1)
        personajePos = intent.getIntExtra("poscicionPersonajeeditar", 1)

        var nombrePersonajeI = findViewById<EditText>(R.id.tpn_nombreInputPer)
        var edadPersonajeI = findViewById<EditText>(R.id.eTN_edadInput)
        var generoPersonajeI = findViewById<EditText>(R.id.sp_genero)
        var nombrePeliculaI = findViewById<EditText>(R.id.etNP_nombrePeliInput)
        var esPrincesaI = findViewById<EditText>(R.id.sp_princesa)

        var idPersonje: Int = 0

        BaseDeDatoLocal.arrayRelacion_Pel_Per.forEachIndexed { index: Int, peliculaPersonaje: Pelicula_Personaje ->
            if(idPelicula_Personaje == peliculaPersonaje.idRelacion_Pelicula_Personaje){
                nombrePersonajeI.setText(peliculaPersonaje.nombreRelacion)
                idPersonje = peliculaPersonaje.idPersonaje
            }
        }

        BaseDeDatoLocal.arrayPersonajes.forEachIndexed { index: Int, personaje: Personaje ->
            if (idPersonje == personaje.idPersonajes){
                nombrePersonajeI.setText(personaje.nombrePersonaje)
                edadPersonajeI.setText(personaje.edadPersonaje)
                generoPersonajeI.setText(personaje.generoPersonaje)
                nombrePeliculaI.setText(personaje.nombrePelicula)
                esPrincesaI.setText(personaje.esPrincesa)
            }
        }

        val btnGuardar = findViewById<Button>(R.id.btn_guardarPer)
        btnGuardar.setOnClickListener {
            BaseDeDatoLocal.arrayRelacion_Pel_Per.forEachIndexed { index: Int, peliculaPersonaje: Pelicula_Personaje ->
                if (idPelicula_Personaje == peliculaPersonaje.idRelacion_Pelicula_Personaje){
                    Log.i("editar","${nombrePersonajeI.text.toString()}")
                    peliculaPersonaje.nombreRelacion = (nombrePersonajeI.text.toString())
                }
            }

            BaseDeDatoLocal.arrayPersonajes.forEachIndexed { index: Int, personaje: Personaje ->
                if(idPersonje == personaje.idPersonajes){
                    personaje.nombrePersonaje = nombrePersonajeI.text.toString()
                    personaje.edadPersonaje = edadPersonajeI.text.toString().toInt()
                    personaje.generoPersonaje = generoPersonajeI.text.toString()
                    personaje.nombrePelicula = nombrePeliculaI.toString()
                    personaje.esPrincesa = esPrincesaI.toString()
                }
            }
            respuesta ()
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelarPer)
        btnCancelar.setOnClickListener {
            respuesta()
        }
    }

    fun respuesta () {
        val devolverParametro = Intent()
        devolverParametro.putExtra("posicionPersonajeeditar", personajePos)
        setResult(
            RESULT_OK,
            devolverParametro
        )
        finish()
    }

}