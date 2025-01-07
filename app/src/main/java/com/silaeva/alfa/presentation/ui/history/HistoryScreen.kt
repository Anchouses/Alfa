package com.silaeva.alfa.presentation.ui.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.silaeva.alfa.presentation.ui.common_ui.HistoryItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen(
    paddingValues: PaddingValues,
    viewModel: HistoryViewModel = koinViewModel()
) {
    val list = viewModel.binListFlow.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(list.value.size) {
                HistoryItem(
                    binCheckedModel = list.value[it]
                )
            }
        }
    }
}