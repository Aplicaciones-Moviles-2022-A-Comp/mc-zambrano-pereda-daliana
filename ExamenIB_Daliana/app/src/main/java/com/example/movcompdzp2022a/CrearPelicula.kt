package com.example.movcompdzp2022a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class CrearPelicula : AppCompatActivity() {

    var nextId = 0
    var lastId = 0
    var nombrePelicula = ""
    var anioCreacion = 0
    var numSagas = 0
    var presupuesto = 0
    var tuvoExito = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_pelicula)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")
        var longitudLista = BaseDeDatoLocal.arrayPeliculas.lastIndex
        BaseDeDatoLocal.arrayPeliculas.forEachIndexed{ indice: Int, pelicula : Pelicula ->
            if (indice == longitudLista){
                lastId = pelicula.idPelicula
            }
        }

        nextId = lastId+1


        var nombreP = findViewById<TextView>(R.id.tpn_nombreInputC)
        var anioCreacionP = findViewById<EditText>(R.id.eTN_anioCreacionInputC)
        var numSagasP = findViewById<EditText>(R.id.sp_numSagasC)
        var presupuestoP = findViewById<TextView>(R.id.etND_presupuestoInputC)
        var tuvoExitoP = findViewById<TextView>(R.id.sp_exitoMercadoC)

        var btnCrearPelicula = findViewById<Button>(R.id.btn_GuardadPeliculaC)
        btnCrearPelicula.setOnClickListener {
            nombrePelicula = nombreP.text.toString()
            anioCreacion = anioCreacionP.text.toString().toInt()
            numSagas = numSagasP.text.toString().toInt()
            presupuesto = presupuestoP.text.toString().toInt()
            tuvoExito = tuvoExitoP.toString()

            BaseDeDatoLocal.arrayPeliculas.add(
                Pelicula(nextId, nombrePelicula, anioCreacion,numSagas,presupuesto,tuvoExito)
            )
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