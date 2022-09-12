package com.example.movcompdzp2022a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Editar_Personaje : AppCompatActivity() {

    var peliculaSeleccionada = Pelicula(0, "", 0, 0, 0, "")
    var personajeSeleccionado : Personaje? = Personaje(0, "", 0, "", "", "")
    val DB = Firebase.firestore
    val peliculas = DB.collection("Peliculas")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_personaje)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        peliculaSeleccionada = intent.getParcelableExtra<Pelicula>("posicionPeliculaeditar")!!
        personajeSeleccionado = intent.getParcelableExtra<Personaje>("personaje")!!

        var nombrePersonajeI = findViewById<EditText>(R.id.tpn_nombreInputPer)
        var edadPersonajeI = findViewById<EditText>(R.id.eTN_edadInput)
        var generoPersonajeI = findViewById<EditText>(R.id.sp_genero)
        var nombrePeliculaI = findViewById<EditText>(R.id.etNP_nombrePeliInput)
        var esPrincesaI = findViewById<EditText>(R.id.sp_princesa)

        nombrePersonajeI.setText(personajeSeleccionado!!.nombrePersonaje)
        edadPersonajeI.setText(personajeSeleccionado!!.edadPersonaje).toString()
        generoPersonajeI.setText(personajeSeleccionado!!.generoPersonaje)
        nombrePeliculaI.setText(personajeSeleccionado!!.nombrePelicula)
        esPrincesaI.setText(personajeSeleccionado!!.esPrincesa)

        val btnGuardar = findViewById<Button>(R.id.btn_guardarPer)
        btnGuardar.setOnClickListener {
            peliculas.document("${peliculaSeleccionada.idPelicula}")
                .collection("Personajes")
                .document("${personajeSeleccionado!!.idPersonajes}")
                .update(
                    "nombrePersonaje", nombrePersonajeI.text.toString(),
                    "edadPersonaje", edadPersonajeI.text.toString().toInt(),
                    "generoPersonaje", generoPersonajeI.text.toString(),
                    "nombrePelicula", nombrePeliculaI.text.toString(),
                    "esPrincesa", esPrincesaI.text.toString()
                )
            Toast.makeText(this, "Los cambios han sido guardados exitosamente!", Toast.LENGTH_SHORT).show()
            val intentEditSuccess = Intent (this, ListaPersonajes::class.java)
            startActivity(intentEditSuccess)
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelarPer)
        btnCancelar.setOnClickListener {
            respuesta()
        }
    }

    fun respuesta () {
        val devolverParametro = Intent()
        devolverParametro.putExtra("posicionPelicula", peliculaSeleccionada)
        setResult(
            RESULT_OK,
            devolverParametro
        )
        finish()
    }

}