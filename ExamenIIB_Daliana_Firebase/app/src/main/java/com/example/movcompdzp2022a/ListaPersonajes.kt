package com.example.movcompdzp2022a

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.widget.AdapterView
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.widget.ArrayAdapter as ArrayAdapter1

class ListaPersonajes : AppCompatActivity() {

    var peliculaSeleccionada = Pelicula(0, "", 0, 0, 0, "")
    val DB = Firebase.firestore
    val peliculas = DB.collection("Peliculas")
    var idItemSeleccionado = 0
    var adapter : ArrayAdapter1<Personaje>?=null
    var personajeSeleccionado : Personaje? = Personaje(0, "", 0, "", "", "")

    var resultAnadirPersonaje = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                peliculaSeleccionada = intent.getParcelableExtra<Pelicula>("PosPeliculas")!!

            }
        }

    }

    var resultEditarPersonaje = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                peliculaSeleccionada = intent.getParcelableExtra<Pelicula>("PosPeliculas")!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_personajes)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        peliculaSeleccionada = intent.getParcelableExtra<Pelicula>("PosPeliculas")!!
        listViewPersonajes()

        val nombrePelicula = findViewById<TextView>(R.id.tv_Pelicula)
        nombrePelicula.setText("Peliculas" + peliculaSeleccionada.nombrePelicula)

        val btnCrearPersonaje = findViewById<Button>(R.id.bt_CrearPersonaje)
        btnCrearPersonaje.setOnClickListener {
            abrirActividadAddPersonaje(CrearPersonaje::class.java)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_peliculas, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        idItemSeleccionado = info.position
        Log.i("context-menu", "ID Personaje ${idItemSeleccionado}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        personajeSeleccionado = adapter!!.getItem(idItemSeleccionado)
        return when (item.itemId) {
            R.id.mi_editarPersonaje -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadEditarPersonaje(Editar_Personaje::class.java)
                return true
            }
            R.id.mi_eliminarPersonaje -> {
                Log.i("context-menu", "Delete position: ${personajeSeleccionado}")
                val peliculaSubColeccion= peliculas.document("${peliculaSeleccionada.idPelicula}")
                    .collection("Personajes")
                    .document("${personajeSeleccionado!!.idPersonajes}")
                    .delete()
                    .addOnSuccessListener {
                        Log.i("Personaje eliminado "," con exito")
                    }
                    .addOnFailureListener{
                        Log.i("Personaje eliminado "," fallido")
                    }
                listViewPersonajes()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun listViewPersonajes(){
        val peliculaSubColeccion = peliculas.document("${peliculaSeleccionada.idPelicula}")
            .collection("Personajes")
            .whereEqualTo("idPelicula", "${peliculaSeleccionada.idPelicula}")

        val personaje_lv = findViewById<ListView>(R.id.lv_personaje_lista)
        peliculaSubColeccion.get().addOnSuccessListener { result ->
            var listaPersonajes = arrayListOf<Personaje>()
            for (document in result){
                listaPersonajes.add(
                    Personaje(
                        document.id.toString().toInt(),
                        document.data.get("nombrePersonaje").toString(),
                        document.data.get("edadPersonaje").toString().toInt(),
                        document.data.get("generoPersonaje").toString(),
                        document.data.get("nombrePelicula").toString(),
                        document.data.get("esPrincesa").toString(),
                    )
                )
            }
            adapter = android.widget.ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                listaPersonajes
            )
            personaje_lv.adapter = adapter
            adapter!!.notifyDataSetChanged()
            registerForContextMenu(personaje_lv)
        }
    }

    fun abrirActividadEditarPersonaje(
        clase: Class<*>
    ) {
        val intentEditarPersonaje = Intent(this, clase)
        intentEditarPersonaje.putExtra("personaje", personajeSeleccionado)
        intentEditarPersonaje.putExtra("posicionPeliculaeditar",peliculaSeleccionada)
        resultEditarPersonaje.launch(intentEditarPersonaje)
    }

    fun abrirActividadAddPersonaje(
        clase: Class<*>
    ) {
        val intentAddNewPersonaje = Intent(this, clase)
        intentAddNewPersonaje.putExtra("posicionPelicula",peliculaSeleccionada)
        resultAnadirPersonaje.launch(intentAddNewPersonaje)
    }
}