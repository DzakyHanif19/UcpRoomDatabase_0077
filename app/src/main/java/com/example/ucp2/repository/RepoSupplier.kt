package com.example.ucp2.repository

import com.example.ucp2.data.entity.Barang
import com.example.ucp2.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

interface RepoSupplier {
    suspend fun insertSuplier(supplier: Supplier)
    fun getAllSuplier(): Flow<List<Supplier>>
    fun getSupplier(id: Int): Flow<Supplier>
}