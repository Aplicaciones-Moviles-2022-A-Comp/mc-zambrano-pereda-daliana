package com.example.movcompdzp2022a

class BEntrenador (
    val nombre: String?, //? Significa que puede ser nulo
    val descripcion: String?,
){
    override fun toString(): String {
        return "${nombre} - ${descripcion}"
    }
}