package com.example.ucp2.repository

import com.example.ucp2.data.dao.SupplierDao
import com.example.ucp2.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

class LocalRepoSupplier(
    private val supplierDao: SupplierDao
) : RepoSupplier {
    override suspend fun insertSuplier(supplier: Supplier) {
        supplierDao.insertSupplier(supplier)
    }
    override fun getAllSuplier(): Flow<List<Supplier>> =
        supplierDao.getAllSupplier()

    override fun getSupplier(id: Int): Flow<Supplier> =
        supplierDao.getSupplier(id)
}