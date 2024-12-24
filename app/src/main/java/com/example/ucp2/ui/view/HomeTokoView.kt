package com.example.ucp2.ui.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.ui.costumwidget.TopHomeAppBar

@Composable
fun HomeTokoView(
    onBarangClick: () -> Unit,
    onSuplierClick: () -> Unit,
    onAddBrgClick: () -> Unit,
    onAddSplClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopHomeAppBar(
                showBackButton = false,
                onBack = { },
            )
        },
        content = { paddingValues ->
            BodyHomeTokoView(
                onBarangClick = { onBarangClick() },
                onSuplierClick = { onSuplierClick() },
                onAddBrgClick = { onAddBrgClick() },
                onAddSplClick = { onAddSplClick() },
                paddingValues = paddingValues,
            )
        }
    )
}

@Composable
fun BodyHomeTokoView(
    onBarangClick: () -> Unit = { },
    onSuplierClick: () -> Unit = { },
    onAddBrgClick: () -> Unit = { },
    onAddSplClick: () -> Unit = { },
    paddingValues: PaddingValues,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        item {
            GradientCard(
                title = "Add Product",
                icon = Icons.Filled.Add,
                gradient = Brush.horizontalGradient(colors = listOf(Color(0xFF4CAF50), Color(0xFF8BC34A))),
                onClick = onAddBrgClick
            )
        }
        item {
            GradientCard(
                title = "Product List",
                icon = Icons.Filled.List,
                gradient = Brush.horizontalGradient(colors = listOf(Color(0xFF2196F3), Color(0xFF64B5F6))),
                onClick = onBarangClick
            )
        }
        item {
            GradientCard(
                title = "Add Supplier",
                icon = Icons.Filled.Add,
                gradient = Brush.horizontalGradient(colors = listOf(Color(0xFFFF9800), Color(0xFFFFB74D))),
                onClick = onAddSplClick
            )
        }
        item {
            GradientCard(
                title = "Supplier List",
                icon = Icons.Filled.List,
                gradient = Brush.horizontalGradient(colors = listOf(Color(0xFFF44336), Color(0xFFE57373))),
                onClick = onSuplierClick
            )
        }
    }
}

@Composable
fun GradientCard(
    title: String,
    icon: ImageVector,
    gradient: Brush,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var clicked by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (clicked) 0.98f else 1f,
        animationSpec = tween(durationMillis = 150)
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                clicked = !clicked
                onClick()
            }
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .height(150.dp),
        shape = MaterialTheme.shapes.large, // Larger rounded corners
        elevation = CardDefaults.elevatedCardElevation(8.dp) // Slightly reduced elevation for a softer shadow
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    modifier = Modifier.size(50.dp), // Adjusted size for the icon
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontSize = 16.sp, // Adjusted font size
                        fontWeight = FontWeight.Bold // Bold text for emphasis
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}
