package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplierDao {
    @Insert
    suspend fun insertSupplier(
        supplier: Supplier
    )
    @Query
        ("SELECT * FROM supplier ORDER BY namasupplier ASC")
    fun getAllSupplier(): Flow<List<Supplier>>

    @Query("SELECT * FROM supplier WHERE idsupplier = :id")
    fun getSupplier(id: Int): Flow<Supplier>
}