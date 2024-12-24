package com.example.ucp2.ui.view.Suplier

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.costumwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.PenyediaTokoViewModel
import com.example.ucp2.ui.viewmodel.Supplier.FormSplErrorState
import com.example.ucp2.ui.viewmodel.Supplier.SuplierViewModel
import com.example.ucp2.ui.viewmodel.Supplier.SupplierEvent
import com.example.ucp2.ui.viewmodel.Supplier.SupplierUiState
import kotlinx.coroutines.launch

@Composable
fun AddSplView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SuplierViewModel = viewModel(factory = PenyediaTokoViewModel.Factory)
) {
    val uiState = viewModel.SplUiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackbarMessage()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Suplier"
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            InsertBodySpl(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateUiState(updateEvent)
                },
                onClick = {
                    viewModel.saveData()
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun InsertBodySpl(
    modifier: Modifier = Modifier,
    onValueChange: (SupplierEvent) -> Unit,
    uiState: SupplierUiState,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormSuplier(
            supplierEvent = uiState.supplierEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@Composable
fun FormSuplier(
    supplierEvent: SupplierEvent,
    onValueChange: (SupplierEvent) -> Unit = {},
    errorState: FormSplErrorState = FormSplErrorState(),
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = supplierEvent.idsupplier,
            onValueChange = { onValueChange(supplierEvent.copy(idsupplier = it)) },
            label = { Text(text = "Id Suplier") },
            isError = errorState.idsupplier != null,
            placeholder = { Text(text = "Masukkan Id Suplier") },
            modifier = Modifier.fillMaxWidth()
        )
        if (errorState.idsupplier != null) {
            Text(text = errorState.idsupplier, color = Color.Red, modifier = Modifier.padding(start = 8.dp))
        }

        OutlinedTextField(
            value = supplierEvent.namasupplier,
            onValueChange = { onValueChange(supplierEvent.copy(namasupplier = it)) },
            label = { Text(text = "Nama Suplier") },
            isError = errorState.namasupplier != null,
            placeholder = { Text(text = "Masukkan Nama Suplier") },
            modifier = Modifier.fillMaxWidth()
        )
        if (errorState.namasupplier != null) {
            Text(text = errorState.namasupplier, color = Color.Red, modifier = Modifier.padding(start = 8.dp))
        }

        OutlinedTextField(
            value = supplierEvent.kontak,
            onValueChange = { onValueChange(supplierEvent.copy(kontak = it)) },
            label = { Text(text = "Kontak") },
            isError = errorState.kontak != null,
            placeholder = { Text(text = "Masukkan Kontak") },
            modifier = Modifier.fillMaxWidth()
        )
        if (errorState.kontak != null) {
            Text(text = errorState.kontak, color = Color.Red, modifier = Modifier.padding(start = 8.dp))
        }

        OutlinedTextField(
            value = supplierEvent.alamat,
            onValueChange = { onValueChange(supplierEvent.copy(alamat = it)) },
            label = { Text(text = "Alamat") },
            isError = errorState.alamat != null,
            placeholder = { Text(text = "Masukkan Alamat") },
            modifier = Modifier.fillMaxWidth()
        )
        if (errorState.alamat != null) {
            Text(text = errorState.alamat, color = Color.Red, modifier = Modifier.padding(start = 8.dp))
        }
    }
}
