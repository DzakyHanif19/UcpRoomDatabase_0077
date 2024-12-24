package com.example.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang")
data class Barang(
    @PrimaryKey
    val  idbarang: String,
    val  namabarang: String,
    val  deskripsi: String,
    val  harga: Int,
    val  stok: Int,
    val  namasupplier: String,
)