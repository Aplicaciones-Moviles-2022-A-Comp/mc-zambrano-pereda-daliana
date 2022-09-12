package com.example.movcompdzp2022a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class CrearPersonaje : AppCompatActivity() {

    var nextId = 0
    var lastId = 0
    var nombre = ""
    var idPersonajeSeleccionado = 0
    var PersonajePosicion = 0
    var idPersonajeS = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo-vida","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_personaje)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")

        PersonajePosicion = intent.getIntExtra("posicionPersonaje", -1)
        Log.i("posPersonaje","${PersonajePosicion}")

        BaseDeDatoLocal.arrayPersonajes.forEachIndexed { indice: Int, personaje: Personaje ->
            if (indice==PersonajePosicion){
                idPersonajeS = personaje.idPersonajes
            }
        }

        var longListaPersonajes = BaseDeDatoLocal.arrayRelacion_Pel_Per.lastIndex
        BaseDeDatoLocal.arrayRelacion_Pel_Per.forEachIndexed { index: Int, peliculaPersonaje : Pelicula_Personaje ->
            if (index == longListaPersonajes){
                lastId = peliculaPersonaje.idRelacion_Pelicula_Personaje
            }
        }

        nextId = lastId + 1

        var nombrePersonajeI = findViewById<EditText>(R.id.tpn_nombreInputPerC)
        var edadI = findViewById<EditText>(R.id.eTN_edadInputC)
        var generoI = findViewById<EditText>(R.id.sp_generoC)
        var nombrePeliculaI = findViewById<EditText>(R.id.etNP_nombrePeliInputC)
        var esPrincesaI = findViewById<EditText>(R.id.sp_princesaC)

        var btnGuardar = findViewById<Button>(R.id.btn_guardarPerC)

        btnGuardar.setOnClickListener {
            var nombrePersonaje = nombrePersonajeI.text.toString()
            var edad = edadI.text.toString().toInt()
            var genero = generoI.text.toString()
            var nombrePelicula = nombrePeliculaI.text.toString()
            var esPrincesa = esPrincesaI.text.toString()

            BaseDeDatoLocal.arrayRelacion_Pel_Per.add(
                Pelicula_Personaje(nextId, nombrePersonaje, idPersonajeS, idPersonajeSeleccionado)
            )
            BaseDeDatoLocal.arrayPersonajes.add(
                Personaje(idPersonajeSeleccionado, nombrePersonaje, edad, genero, nombrePelicula, esPrincesa)
            )
            respuesta()
        }

        var btnCancelar = findViewById<Button>(R.id.btn_cancelarPerC)
        btnCancelar.setOnClickListener {
            respuesta()
        }
    }

    fun respuesta () {
        val devolverParametros = Intent()
        devolverParametros.putExtra("posicionPersonaje", PersonajePosicion)
        setResult(
            RESULT_OK,
            devolverParametros
        )
        finish()
    }
}