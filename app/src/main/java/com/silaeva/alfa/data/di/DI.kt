package com.silaeva.alfa.data.di

import com.silaeva.alfa.data.repository_impl.HistoryRepositoryImpl
import com.silaeva.alfa.data.repository_impl.InfoRepositoryImpl
import com.silaeva.alfa.domain.interactor.HistoryInteractor
import com.silaeva.alfa.domain.interactor.InfoInteractor
import com.silaeva.alfa.domain.repository.HistoryRepository
import com.silaeva.alfa.domain.repository.InfoRepository
import com.silaeva.alfa.presentation.ui.history.HistoryViewModel
import com.silaeva.alfa.presentation.ui.info.InfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val interactorModule = module {

    single <InfoRepository> {
        InfoRepositoryImpl.get()
    }

    factory {
        InfoInteractor(infoRepository = get())
    }

    single <HistoryRepository> {
        HistoryRepositoryImpl.get()
    }

    factory {
        HistoryInteractor(historyRepository = get())
    }
}

val presentationModule = module {

    viewModel {
        InfoViewModel(infoInteractor = get(), historyInteractor = get())
    }

    viewModel {
        HistoryViewModel(historyInteractor = get())
    }
}