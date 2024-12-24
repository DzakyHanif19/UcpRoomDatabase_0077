package com.example.ucp2.ui.viewmodel.Supplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Supplier
import com.example.ucp2.repository.RepoSupplier
import kotlinx.coroutines.launch


class SuplierViewModel(private val repoSupplier: RepoSupplier) : ViewModel() {
    var SplUiState by mutableStateOf(SupplierUiState())

    fun updateUiState(supplierEvent: SupplierEvent) {
        SplUiState = SplUiState.copy(
            supplierEvent = supplierEvent
        )
    }

    private fun validateFields(): Boolean {
        val event = SplUiState.supplierEvent
        val errorState = FormSplErrorState(
            idsupplier = if (event.idsupplier.isEmpty()) "Id suplier tidak boleh kosong" else null,
            namasupplier = if (event.namasupplier.isEmpty()) "Nama suplier tidak boleh kosong" else null,
            kontak = if (event.kontak.isEmpty()) "Kontak suplier tidak boleh kosong" else null,
            alamat = if (event.alamat.isEmpty()) "Alamat suplier tidak boleh kosong" else null
        )
        SplUiState = SplUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = SplUiState.supplierEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repoSupplier.insertSuplier(currentEvent.toSupplierEntity())
                    SplUiState = SplUiState.copy(
                        snackbarMessage = "Data Berhasil Disimpan",
                        supplierEvent = SupplierEvent(),
                        isEntryValid = FormSplErrorState()
                    )
                } catch (e: Exception) {
                    SplUiState = SplUiState.copy(
                        snackbarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        } else {
            SplUiState = SplUiState.copy(
                snackbarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }
    fun resetSnackbarMessage() {
        SplUiState = SplUiState.copy(
            snackbarMessage = null
        )
    }
}



data class SupplierUiState(
    val supplierEvent: SupplierEvent = SupplierEvent(),
    val isEntryValid: FormSplErrorState = FormSplErrorState(),
    val snackbarMessage: String? = null
)

data class FormSplErrorState(
    val idsupplier: String? = null,
    val namasupplier: String? = null,
    val kontak: String? = null,
    val alamat: String? = null
){
    fun isValid(): Boolean{
        return idsupplier == null && namasupplier == null && kontak == null && alamat == null
    }
}

data class SupplierEvent(
    val idsupplier: String = "",
    val namasupplier: String = "",
    val kontak: String = "",
    val alamat: String = ""
)

fun SupplierEvent.toSupplierEntity(): Supplier = Supplier(
    idsupplier = idsupplier,
    namasupplier = namasupplier,
    kontak = kontak,
    alamat = alamat
)