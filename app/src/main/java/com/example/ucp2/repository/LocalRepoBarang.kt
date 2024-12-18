package com.example.ucp2.repository

import com.example.ucp2.data.dao.BarangDao
import com.example.ucp2.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepoBarang(
    private val BarangDao : BarangDao
): RepoBarang{
    override suspend fun insertBarang(barang: Barang) {
        BarangDao.insertBarang(barang)
    }

    override suspend fun deleteBarang(barang: Barang) {
        BarangDao.deleteBarang(barang)
    }

    override suspend fun updateBarang(barang: Barang) {
        BarangDao.updateBarang(barang)
    }

    override fun getAllBarang(): Flow<List<Barang>> =
        BarangDao.getAllBarang()

    override fun getBarang(nim: String): Flow<Barang> =
        BarangDao.getBarang(nim)
}