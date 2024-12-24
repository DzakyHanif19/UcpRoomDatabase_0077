package com.example.ucp2.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.viewmodel.HomeTokoViewModel
import com.example.ucp2.ui.viewmodel.PenyediaTokoViewModel

object NamaSpl {
    @Composable
    fun options(
        supplierHomeViewModel: HomeTokoViewModel = viewModel(factory = PenyediaTokoViewModel.Factory)
    ): List<String> {
        val dataNama by supplierHomeViewModel.homeSplUiState.collectAsState()
        val namaSupplier = dataNama.listSupplier.map { it.namasupplier }
        return namaSupplier
    }
}