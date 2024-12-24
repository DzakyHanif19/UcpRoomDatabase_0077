package com.example.ucp2.ui.viewmodel.Barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.repository.RepoBarang
import kotlinx.coroutines.launch

class BarangViewModel(private val repoBarang: RepoBarang) : ViewModel() {
    var BrgUiState by mutableStateOf(BrgUiState())

    fun updateUiState(barangEvent: BarangEvent) {
        BrgUiState = BrgUiState.copy(
            barangEvent = barangEvent
        )
    }
    private fun validateFields(): Boolean {
        val event = BrgUiState.barangEvent
        val errorState = FormBrgErrorState(
            idbarang = if (event.idbarang.isNotEmpty()) null else "Id barang tidak boleh kosong",
            namabarang = if (event.namabarang.isNotEmpty()) null else "Nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi tidak boleh kosong",
            harga = if (event.harga > 0) null else "Stok tidak boleh negatif",
            stok = if (event.stok >= 0) null else "Stok tidak boleh kosong",
            namasupplier = if (event.namasupplier.isNotEmpty()) null else "Nama Suplier tidak boleh kosong"

        )
        BrgUiState = BrgUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
    fun saveData(){
        val currentEvent = BrgUiState.barangEvent

        if (validateFields()){
            viewModelScope.launch {
                try {
                    repoBarang.insertBarang(currentEvent.toBarangEntity())
                    BrgUiState = BrgUiState.copy(
                        snackbarMessage = "Data Berhasil Disimpan",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormBrgErrorState()
                    )
                }catch (e: Exception){
                    BrgUiState = BrgUiState.copy(
                        snackbarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        }else{
            BrgUiState = BrgUiState.copy(
                snackbarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }
    fun resetSnackbarMessage(){
        BrgUiState = BrgUiState.copy(
            snackbarMessage = null
        )
    }
}

data class BrgUiState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormBrgErrorState = FormBrgErrorState(),
    val snackbarMessage: String? = null
)

data class FormBrgErrorState(
    val idbarang: String? = null,
    val namabarang: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namasupplier: String? = null
){
    fun isValid(): Boolean {
        return idbarang == null && namabarang == null && deskripsi == null &&
                harga == null && stok == null && namasupplier == null
    }
}
data class BarangEvent(
    val idbarang: String = "",
    val namabarang: String = "",
    val deskripsi: String = "",
    val harga: Int = 0,
    val stok: Int = 0,
    val namasupplier: String = ""
)

fun BarangEvent.toBarangEntity(): Barang = Barang(
    idbarang = idbarang,
    namabarang = namabarang,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    namasupplier = namasupplier
)