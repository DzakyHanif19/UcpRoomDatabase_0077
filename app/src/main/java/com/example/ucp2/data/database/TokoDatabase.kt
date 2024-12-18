package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.BarangDao
import com.example.ucp2.data.dao.SupplierDao
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.data.entity.Supplier

@Database(entities = [Barang::class, Supplier::class], version = 1, exportSchema = false)
abstract class  TokoDatabase : RoomDatabase(){

    abstract fun barangDao(): BarangDao
    abstract fun supplierDao(): SupplierDao

    companion object{
        @Volatile
        private var instance: TokoDatabase? = null

        fun getDatabase(context: Context):TokoDatabase{
            return (instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    TokoDatabase:: class.java,
                    "TokoDatabase"
                )
                    .build().also { instance = it }
            })
        }
    }
}