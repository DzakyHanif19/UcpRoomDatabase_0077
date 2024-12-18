package com.example.ucp2.repository

import com.example.ucp2.data.entity.Barang
import com.example.ucp2.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

interface RepoSupplier {
    suspend fun insertSupplier(supplier: Supplier)
    fun getAllSupplier(): Flow<List<Supplier>>
}