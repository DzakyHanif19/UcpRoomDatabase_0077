package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.TokoApp
import com.example.ucp2.ui.viewmodel.Barang.BarangViewModel
import com.example.ucp2.ui.viewmodel.Barang.DetailBrgViewModel
import com.example.ucp2.ui.viewmodel.Barang.HomeBrgViewModel
import com.example.ucp2.ui.viewmodel.Barang.UpdateBrgViewModel
import com.example.ucp2.ui.viewmodel.Supplier.HomeSplViewModel
import com.example.ucp2.ui.viewmodel.Supplier.SuplierViewModel

object PenyediaTokoViewModel{

    val Factory = viewModelFactory {
        initializer {
            HomeTokoViewModel(
                TokoApp().containerApp.repoBarang,
                TokoApp().containerApp.repoSupplier
            )
        }
        initializer {
            HomeBrgViewModel(
                TokoApp().containerApp.repoBarang
            )
        }
        initializer {
            HomeSplViewModel(
                TokoApp().containerApp.repoSupplier
            )
        }
        initializer {
            BarangViewModel(
                TokoApp().containerApp.repoBarang,
            )
        }
        initializer {
            DetailBrgViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repoBarang,
            )
        }
        initializer {
            UpdateBrgViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repoBarang,
            )
        }
        initializer {
            SuplierViewModel(
                TokoApp().containerApp.repoSupplier
            )
        }
    }
}

fun CreationExtras.TokoApp() : TokoApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TokoApp)