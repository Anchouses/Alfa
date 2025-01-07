package com.silaeva.alfa.presentation.ui.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silaeva.alfa.domain.data_model.BinInfoModel
import com.silaeva.alfa.domain.interactor.HistoryInteractor
import com.silaeva.alfa.domain.interactor.InfoInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InfoViewModel(
    private val infoInteractor: InfoInteractor,
    private val historyInteractor: HistoryInteractor
): ViewModel() {

    var binNum: String = ""
    private val _info: MutableStateFlow<BinInfoModel?> = MutableStateFlow(null)
    val info: StateFlow<BinInfoModel?> = _info
    private var job: Job? = null

    fun getInfo(bin: String) {
        binNum = bin
        job = null
        job = viewModelScope.launch {
            _info.value = infoInteractor.getBinInfo(bin)
            _info.value?.let {
                historyInteractor.saveInfo(bin.toInt(), it)
            }
        }
    }
}