package com.example.movcompdzp2022a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Editar_Pelicula : AppCompatActivity() {

    var peliculaSeleccionada = Pelicula(0, "", 0, 0, 0, "")
    val DB = Firebase.firestore
    val peliculas = DB.collection("Peliculas")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_pelicula)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        peliculaSeleccionada = intent.getParcelableExtra<Pelicula>("PosPeliculas")!!

        val editarnombrePelicula = findViewById<TextView>(R.id.tpn_nombreInput)
        val editaranioCreacion = findViewById<TextView>(R.id.eTN_anioCreacionInput)
        val editarnumSagas = findViewById<EditText>(R.id.sp_numSagas)
        val editarpresupuesto = findViewById<TextView>(R.id.etND_presupuestoInput)
        val editartuvoExito = findViewById<EditText>(R.id.sp_exitoMercado)

        editarnombrePelicula.setText(peliculaSeleccionada.nombrePelicula)
        editaranioCreacion.setText(peliculaSeleccionada.anioCreacion.toString())
        editarnumSagas.setText(peliculaSeleccionada.numSagas.toString())
        editarpresupuesto.setText(peliculaSeleccionada.presupuesto.toString())
        editartuvoExito.setText(peliculaSeleccionada.tuvoExito)

        val btnGuardarCambios = findViewById<Button>(R.id.btn_GuardadPelicula)
        btnGuardarCambios.setOnClickListener {
                peliculas.document("${peliculaSeleccionada.idPelicula}").update(
                    "nombrePelicula", editarnombrePelicula.text.toString() ,
                    "anioCreacion", editaranioCreacion.text.toString().toInt() ,
                    "numSagas", editarnumSagas.text.toString().toInt() ,
                    "presupuesto", editarpresupuesto.text.toString().toInt() ,
                    "tuvoExito", editartuvoExito.text.toString()
                )


            Toast.makeText(this,"Equipo actualizado exitosamente", Toast.LENGTH_SHORT).show()

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
