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

class CrearPersonaje : AppCompatActivity() {

    var peliculaSeleccionada = Pelicula(0, "", 0, 0, 0, "")
    val DB = Firebase.firestore
    val peliculas = DB.collection("Peliculas")
    val personajes = DB.collection("Personajes")
    var idJugadorSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo-vida","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_personaje)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        peliculaSeleccionada = intent.getParcelableExtra<Pelicula>("posicionPeliculas")!!
        val peliculaSubColeccion = peliculas.document("${peliculaSeleccionada.idPelicula}")
            .collection("Personaje")

        var nombrePersonajeI = findViewById<EditText>(R.id.tpn_nombreInputPerC)
        var edadI = findViewById<EditText>(R.id.eTN_edadInputC)
        var generoI = findViewById<EditText>(R.id.sp_generoC)
        var nombrePeliculaI = findViewById<EditText>(R.id.etNP_nombrePeliInputC)
        var esPrincesaI = findViewById<EditText>(R.id.sp_princesaC)

        Log.i("posPelicula", "${peliculaSeleccionada.idPelicula}")

        var btnGuardar = findViewById<Button>(R.id.btn_guardarPerC)
        btnGuardar.setOnClickListener {
            var personaje = hashMapOf(
                "nombrePersonaje" to nombrePersonajeI.text.toString(),
                "edad" to edadI.text.toString().toInt(),
                "genero" to generoI.text.toString(),
                "nombrePelicula" to nombrePeliculaI.text.toString(),
                "esPrincesa" to esPrincesaI.text.toString()
            )
            peliculaSubColeccion.add(personaje).addOnSuccessListener {
                Toast.makeText(this, "Personaje registrado exitosamente", Toast.LENGTH_SHORT)
                    .show();
                Log.i("Personaje creado", " con éxito")
            }.addOnFailureListener {
                Log.i("Personaje creado", " sin éxito")
            }

        val intentAddSuccess = Intent(this, ListaPersonajes::class.java)
        startActivity(intentAddSuccess)
    }
        var btnCancelar = findViewById<Button>(R.id.btn_cancelarPerC)
        btnCancelar.setOnClickListener {
            respuesta()
        }
    }

    fun respuesta () {
        val devolverParametros = Intent()
        devolverParametros.putExtra("posicionPersonaje", peliculaSeleccionada)
        setResult(
            RESULT_OK,
            devolverParametros
        )
        finish()
    }
}