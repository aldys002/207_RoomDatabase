package com.example.roomsatu.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsatu.repositori.RepositoriSiswa
import com.example.roomsatu.room.Siswa
import com.example.roomsatu.view.uicontroller.ListSiswa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val repositoriSiswa: RepositoriSiswa): ViewModel(){
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> = repositoriSiswa.getAllSiswaStream()
        .filterNotNull()
        .map {HomeUiState(ListSiswa(it.toList()))}
        .stateIn(scopr = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState())

    data class HomeUiState(
        val listSiswa: List<Siswa> = listOf()
    )
}
