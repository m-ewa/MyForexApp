package com.ewam.myforexapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewam.myforexapp.api.ForexApiRepository
import com.ewam.myforexapp.api.UIState
import com.ewam.myforexapp.models.DataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val forexApiRepository: ForexApiRepository
) : ViewModel() {

//    private val _selectedComic = MutableLiveData<Comic>()
//    val selectedComic: LiveData<Comic>
//        get() = _selectedComic

    private val _forexData = MutableLiveData<DataModel>()
    val forexData: LiveData<DataModel>
        get() = _forexData

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState>
        get() = _uiState

    private val exceptionHandler = CoroutineExceptionHandler { r, rr ->
        Log.i("Mytag r", r.toString())
        Log.i("Mytag rr", rr.toString())
        _uiState.value = UIState.OnError
    }

    fun getAllForexData() {
        _uiState.value = UIState.InProgress
        viewModelScope.launch(exceptionHandler) {
            _forexData.value = forexApiRepository.getLatestForexCurrencyData("USD,AUD,CAD,PLN,MXN")
            Log.i("Mytag", _forexData.value.toString())
            _forexData.value = forexApiRepository.getForexCurrencyDataByDate("2013-03-16","USD,AUD,CAD,PLN,MXN")
            Log.i("Mytag", _forexData.value.toString())

            _uiState.value = UIState.OnSuccess
        }
    }
}