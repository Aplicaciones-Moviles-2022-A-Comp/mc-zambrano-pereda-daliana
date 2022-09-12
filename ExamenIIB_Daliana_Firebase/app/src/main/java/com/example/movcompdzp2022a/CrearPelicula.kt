package com.example.movcompdzp2022a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearPelicula : AppCompatActivity() {

    val DB = Firebase.firestore
    val peliculas = DB.collection("Peliculas")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_pelicula)
    }

    override fun onStart() {
        super.onStart()

        var nombreP = findViewById<TextView>(R.id.tpn_nombreInputC)
        var anioCreacionP = findViewById<EditText>(R.id.eTN_anioCreacionInputC)
        var numSagasP = findViewById<EditText>(R.id.sp_numSagasC)
        var presupuestoP = findViewById<TextView>(R.id.etND_presupuestoInputC)
        var tuvoExitoP = findViewById<TextView>(R.id.sp_exitoMercadoC)

        var btnCrearPelicula = findViewById<Button>(R.id.btn_GuardadPeliculaC)
        btnCrearPelicula.setOnClickListener {
            var pelicula = hashMapOf(
                "nombrePelicula" to nombreP.text.toString(),
                "anioCreacion" to anioCreacionP.text.toString().toInt(),
                "numSagas" to numSagasP.text.toString().toInt(),
                "presupuesto" to presupuestoP.text.toString().toInt(),
                "tuvoExito" to tuvoExitoP.toString())

            /*peliculas.add(pelicula).addOnSuccessListener {
                nombreP.text!!.clear()

            }*/

            val intentAddSucces = Intent(this, ListaPeliculas_Main::class.java)
            startActivity(intentAddSucces)
        }


        val btnCancelar = findViewById<Button>(R.id.btn_cancelarC)
        btnCancelar.setOnClickListener {
            val intent = Intent (this, ListaPeliculas_Main::class.java)
            startActivity(intent)
        }
    }

}