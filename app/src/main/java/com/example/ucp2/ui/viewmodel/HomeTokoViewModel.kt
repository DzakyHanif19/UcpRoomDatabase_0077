package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.data.entity.Supplier
import com.example.ucp2.repository.RepoBarang
import com.example.ucp2.repository.RepoSupplier
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class HomeTokoViewModel(
    private val repoBarang: RepoBarang,
    private val repoSupplier: RepoSupplier
): ViewModel() {

    val homeBrgUiState: StateFlow<HomeBrgUiState> = repoBarang.getAllBarang()
        .filterNotNull()
        .map {
            HomeBrgUiState(
                barangList = it.toList(),
                isLoading = false,
            )
        }
        .catch { throwable ->
            emit(
                HomeBrgUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = throwable.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeBrgUiState(
                isLoading = true
            )
        )
    val homeSplUiState: StateFlow<HomeSplUiState> = repoSupplier.getAllSuplier()
        .filterNotNull()
        .map {
            HomeSplUiState(
                listSupplier = it.toList(),
                isLoading = false,
            )
        }
        .catch { throwable ->
            emit(
                HomeSplUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = throwable.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeSplUiState(
                isLoading = true
            )
        )
}

data class HomeBrgUiState(
    val barangList: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError : Boolean = false,
    val errorMessage: String = ""
)

data class HomeSplUiState(
    val listSupplier: List<Supplier> = listOf(),
    val isLoading: Boolean = false,
    val isError : Boolean = false,
    val errorMessage: String = ""
)