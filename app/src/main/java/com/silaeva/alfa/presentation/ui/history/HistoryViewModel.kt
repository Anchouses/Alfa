package com.silaeva.alfa.presentation.ui.history

import androidx.lifecycle.ViewModel
import com.silaeva.alfa.domain.data_model.BinCheckedModel
import com.silaeva.alfa.domain.interactor.HistoryInteractor
import kotlinx.coroutines.flow.Flow

class HistoryViewModel(private val historyInteractor: HistoryInteractor): ViewModel() {

    val binListFlow: Flow<List<BinCheckedModel>> = historyInteractor.getBinList()
}