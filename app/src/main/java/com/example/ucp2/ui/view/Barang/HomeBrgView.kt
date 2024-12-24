package com.example.ucp2.ui.view.Barang

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.ui.costumwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.Barang.HomeBrgUiState
import com.example.ucp2.ui.viewmodel.Barang.HomeBrgViewModel
import com.example.ucp2.ui.viewmodel.PenyediaTokoViewModel

@Composable
fun HomeBrgView(
    modifier: Modifier = Modifier,
    viewModel: HomeBrgViewModel = viewModel(factory = PenyediaTokoViewModel.Factory),
    onAddBrgClick: () -> Unit = {},
    onDetailBrgClick: (String) -> Unit = {},
    onBack: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "List Barang",
            )
        }
    ) { innerPadding ->
        val detailBrgUiState by viewModel.HomeBrgUiState.collectAsState()

        BodyHomeBrgView(
            homeUiState = detailBrgUiState,
            onClick = { onDetailBrgClick(it) },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeBrgView(
    homeUiState: HomeBrgUiState,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    when {
        homeUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeUiState.isError -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = homeUiState.errorMessage ?: "Terjadi kesalahan",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        homeUiState.listBrg.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data Barang.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        else -> {
            ListBarang(
                listBrg = homeUiState.listBrg,
                onClick = { onClick(it) },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListBarang(
    listBrg: List<Barang>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items = listBrg) { brg ->
            CardBarang(
                brg = brg,
                onClick = { onClick(brg.idbarang) }
            )
        }
    }
}

@Composable
fun CardBarang(
    brg: Barang,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = when {
                brg.stok == 0 -> Color.Gray.copy(alpha = 0.1f)
                brg.stok in 1..10 -> Color.Red.copy(alpha = 0.2f)
                else -> Color.Green.copy(alpha = 0.2f)
            }
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = brg.namabarang,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Deskripsi: ${brg.deskripsi}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Harga: Rp.${brg.harga}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Stok: ${brg.stok}",
                fontWeight = FontWeight.Bold,
                color = when {
                    brg.stok == 0 -> Color.Gray
                    brg.stok in 1..10 -> Color.Red
                    else -> Color.Green
                }
            )
        }
    }
}
