package com.example.movcompdzp2022a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListaPeliculas_Main : AppCompatActivity() {

    val DB = Firebase.firestore
    val peliculas = DB.collection("Peliculas")
    var idItemSeleccionado = 0
    var adaptador: ArrayAdapter<Pelicula>?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_peliculas_main)
        Log.i("ciclo-vida", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        listarPelicula()
        val btnAnadirPelicula = findViewById<Button>(R.id.bt_CrearPelicula)
        btnAnadirPelicula.setOnClickListener {
            val intentAddEquipo = Intent(this, CrearPelicula::class.java)
            startActivity(intentAddEquipo)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("context-menu", "ID ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var peliculaSeleccionada : Pelicula = adaptador!!.getItem(idItemSeleccionado)!!
        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${peliculaSeleccionada.idPelicula}")
                val abrirEditarPelicula = Intent (this, Editar_Pelicula::class.java)
                abrirEditarPelicula.putExtra("PosPeliculas", peliculaSeleccionada)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                peliculas.document("${peliculaSeleccionada.idPelicula}").delete()
                    .addOnSuccessListener {
                        Log.i ("Pelicula eliminada", "con éxito")
                    }
                    .addOnFailureListener{
                        Log.i ("Pelicula eliminada", "sin éxito")
                    }

                listarPelicula()
                return true
            }
            R.id.mi_personaje -> {
                Log.i("context-menu", "Personajes: ${idItemSeleccionado}")
                val abrirInicioPersonajes = Intent (this, ListaPersonajes::class.java)
                abrirInicioPersonajes.putExtra("PosPeliculas", peliculaSeleccionada)
                startActivity(abrirInicioPersonajes)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun listarPelicula() {
        val lv_peliculas = findViewById<ListView>(R.id.lv_peliculas_lista)
        peliculas.get().addOnSuccessListener { result ->
            var peliculaLista = arrayListOf<Pelicula>()
            for (document in result){
                peliculaLista.add(
                    Pelicula(
                        document.id.toString().toInt(),
                        document.get("nombrePelicula").toString(),
                        document.get("anioCreacion").toString().toInt(),
                        document.get("numSagas").toString().toInt(),
                        document.get("presupuesto").toString().toInt(),
                        document.get("tuvoExito").toString(),
                        )
                )
            }
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                peliculaLista
            )
            lv_peliculas.adapter = adaptador
            adaptador!!.notifyDataSetChanged()
            registerForContextMenu(lv_peliculas)
        }.addOnFailureListener{
            Log.i("Error", "no se pudo crear la lista")
        }
    }


    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentEditarPelicula = Intent(this, clase)
        intentEditarPelicula.putExtra("posicionEditar", idItemSeleccionado)
        startActivity(intentEditarPelicula)
    }
}