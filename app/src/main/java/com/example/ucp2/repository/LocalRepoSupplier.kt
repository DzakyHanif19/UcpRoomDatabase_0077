package com.example.ucp2.repository

import com.example.ucp2.data.dao.SupplierDao
import com.example.ucp2.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

class LocalRepoSupplier(
    private val SupplierDao : SupplierDao
): RepoSupplier {
    override suspend fun insertSupplier(supplier: Supplier) {
        SupplierDao.insertSupplier(supplier)
    }

    override fun getAllSupplier(): Flow<List<Supplier>> =
        SupplierDao.getAllSupplier()
}