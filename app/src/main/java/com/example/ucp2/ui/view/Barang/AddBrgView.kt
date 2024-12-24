package com.example.ucp2.ui.view.Barang

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.NamaSpl
import com.example.ucp2.ui.costumwidget.DropDownSpl
import com.example.ucp2.ui.viewmodel.Barang.BarangEvent
import com.example.ucp2.ui.viewmodel.Barang.BarangViewModel
import com.example.ucp2.ui.viewmodel.Barang.BrgUiState
import com.example.ucp2.ui.viewmodel.Barang.FormBrgErrorState
import com.example.ucp2.ui.viewmodel.PenyediaTokoViewModel
import kotlinx.coroutines.launch

@Composable
fun InsertBrgView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BarangViewModel = viewModel(factory = PenyediaTokoViewModel.Factory)
) {
    val uiState = viewModel.BrgUiState
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
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            com.example.ucp2.ui.costumwidget.TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Barang",
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(bottom = 80.dp)
        ) {
            InsertBodyBrg(
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
fun InsertBodyBrg(
    modifier: Modifier = Modifier,
    onValueChange: (BarangEvent) -> Unit,
    uiState: BrgUiState,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Form Tambah Barang",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                BarangForm(
                    barangEvent = uiState.barangEvent,
                    onValueChange = onValueChange,
                    errorState = uiState.isEntryValid,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        ExtendedFloatingActionButton(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            icon = { Icon(Icons.Default.Send, contentDescription = "Simpan", tint = Color.White) },
            text = { Text("Simpan", color = Color.White, fontSize = 18.sp) },
            containerColor = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun BarangForm(
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = { },
    errorState: FormBrgErrorState = FormBrgErrorState(),
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        InputField(
            value = barangEvent.idbarang,
            label = "Id Barang",
            placeholder = "Masukkan Id Barang",
            isError = errorState.idbarang != null,
            errorMessage = errorState.idbarang,
            onValueChange = { onValueChange(barangEvent.copy(idbarang = it)) }
        )
        InputField(
            value = barangEvent.namabarang,
            label = "Nama Barang",
            placeholder = "Masukkan Nama Barang",
            isError = errorState.namabarang != null,
            errorMessage = errorState.namabarang,
            onValueChange = { onValueChange(barangEvent.copy(namabarang = it)) }
        )
        InputField(
            value = barangEvent.deskripsi,
            label = "Deskripsi",
            placeholder = "Masukkan Deskripsi",
            isError = errorState.deskripsi != null,
            errorMessage = errorState.deskripsi,
            onValueChange = { onValueChange(barangEvent.copy(deskripsi = it)) }
        )
        InputField(
            value = barangEvent.harga.toString(),
            label = "Harga",
            placeholder = "Masukkan Harga",
            isError = errorState.harga != null,
            errorMessage = errorState.harga,
            onValueChange = { onValueChange(barangEvent.copy(harga = it.toIntOrNull() ?: 0)) }
        )
        InputField(
            value = barangEvent.stok.toString(),
            label = "Stok",
            placeholder = "Masukkan Stok",
            isError = errorState.stok != null,
            errorMessage = errorState.stok,
            onValueChange = { onValueChange(barangEvent.copy(stok = it.toIntOrNull() ?: 0)) }
        )
        DropDownSpl(
            selectedValue = barangEvent.namasupplier,
            options = NamaSpl.options(),
            label = "Nama Suplier",
            onValueChangedEvent = { selectedSupplier ->
                onValueChange(barangEvent.copy(namasupplier = selectedSupplier))
            }
        )
        if (errorState.namasupplier != null) {
            Text(
                text = errorState.namasupplier,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
            )
        }
    }
}

@Composable
fun InputField(
    value: String,
    label: String,
    placeholder: String,
    isError: Boolean,
    errorMessage: String?,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            isError = isError,
            placeholder = { Text(text = placeholder) },
            shape = RoundedCornerShape(8.dp)
        )
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
            )
        }
    }
}
