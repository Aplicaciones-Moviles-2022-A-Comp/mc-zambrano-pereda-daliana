package com.example.movcompdzp2022a

import android.os.Parcel
import android.os.Parcelable

class Pelicula_Personaje (

    val idRelacion_Pelicula_Personaje: Int,
    var nombreRelacion: String?,
    var idPelicula: Int,
    var idPersonaje: Int

    ) : Parcelable {

    override fun toString(): String {
        return "${nombreRelacion}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idRelacion_Pelicula_Personaje)
        parcel.writeString(nombreRelacion)
        parcel.writeInt(idPelicula)
        parcel.writeInt(idPersonaje)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pelicula_Personaje> {
        override fun createFromParcel(parcel: Parcel): Pelicula_Personaje {
            return Pelicula_Personaje(parcel)
        }

        override fun newArray(size: Int): Array<Pelicula_Personaje?> {
            return arrayOfNulls(size)
        }
    }
}