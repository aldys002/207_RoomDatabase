package com.example.roomsatu.view.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myroomsatu.view.viewmodel.HomeViewModel
import com.example.roomsatu.repositori.AplikasiSiswa
import com.example.roomsatu.view.viewmodel.DetailViewModel
import com.example.roomsatu.view.viewmodel.EditViewModel
import com.example.roomsatu.view.viewmodel.EntryViewModel


object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiSiswa().container.repositoriSiswa)
        }

        initializer { EntryViewModel (repositoriSiswa = aplikasiSiswa().container.repositoriSiswa)
        }

        initializer {
            DetailViewModel(savedStateHandle = this.createSavedStateHandle(), aplikasiSiswa().container.repositoriSiswa )
        }
        initializer {
            EditViewModel(this.createSavedStateHandle(),aplikasiSiswa().container.repositoriSiswa)
        }
    }
}

fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)