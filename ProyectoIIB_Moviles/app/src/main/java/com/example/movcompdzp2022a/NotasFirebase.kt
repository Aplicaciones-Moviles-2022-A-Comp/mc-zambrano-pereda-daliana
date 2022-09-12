package com.example.movcompdzp2022a

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class NotasFirebase {

    private val DB = FirebaseFirestore.getInstance()
    val notaCollection = DB.collection("Notas")
    private val auth = Firebase.auth

    fun agregarNota (titulo: String, descripcion: String){
        val currentUserId = auth.currentUser!!.uid
        val notaTitulo = Nota(titulo, currentUserId)
        val notaDescripcion = Nota(descripcion, currentUserId)
        notaCollection.document().set(notaTitulo)
        notaCollection.document().set(notaDescripcion)

    }
}