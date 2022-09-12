package com.example.movcompdzp2022a

import android.os.Parcel
import android.os.Parcelable

class Personaje (
    val idPersonajes: Int,
    var nombrePersonaje: String?,
    var edadPersonaje: Int,
    var generoPersonaje: String?,
    var nombrePelicula: String?,
    var esPrincesa: String?
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idPersonajes)
        parcel.writeString(nombrePersonaje)
        parcel.writeValue(edadPersonaje)
        parcel.writeString(generoPersonaje)
        parcel.writeString(nombrePelicula)
        parcel.writeString(esPrincesa)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "${nombrePersonaje}"
    }

    companion object CREATOR : Parcelable.Creator<Personaje> {
        override fun createFromParcel(parcel: Parcel): Personaje {
            return Personaje(parcel)
        }

        override fun newArray(size: Int): Array<Personaje?> {
            return arrayOfNulls(size)
        }
    }

}
