package com.example.movcompdzp2022a

import android.os.Parcel
import android.os.Parcelable

class Pelicula (
    val idPelicula: Int,
    var nombrePelicula: String?,
    var anioCreacion: Int?,
    var numSagas: Int?,
    var presupuesto: Int?,
    var tuvoExito: String?

    ) : Parcelable {

    override fun toString(): String {
        return "${nombrePelicula}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idPelicula)
        parcel.writeString(nombrePelicula)
        parcel.writeValue(anioCreacion)
        parcel.writeValue(numSagas)
        parcel.writeValue(presupuesto)
        parcel.writeString(tuvoExito)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pelicula> {
        override fun createFromParcel(parcel: Parcel): Pelicula {
            return Pelicula(parcel)
        }

        override fun newArray(size: Int): Array<Pelicula?> {
            return arrayOfNulls(size)
        }
    }
}