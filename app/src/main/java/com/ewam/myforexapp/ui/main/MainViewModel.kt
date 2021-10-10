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
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val forexApiRepository: ForexApiRepository
) : ViewModel() {

    var position = 0
    private val millisInDay = 86_400_000
    private val _forexData = MutableLiveData<DataModel>()
    val forexData: LiveData<DataModel>
        get() = _forexData

    private val _forexDataList = MutableLiveData<List<DataModel>>()
    val forexDataList: LiveData<List<DataModel>>
        get() = _forexDataList

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
//            _forexData.value = forexApiRepository.getLatestForexCurrencyData("USD,AUD,CAD,PLN,MXN")
//            Log.i("Mytag", _forexData.value.toString())
//            _forexData.value = forexApiRepository.getForexCurrencyDataByDate("2013-03-16","USD,AUD,CAD,PLN,MXN")
//            Log.i("Mytag", _forexData.value.toString())

            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN)
            val data = Date()

            var time = data.time
            val arraylist = arrayListOf<DataModel>()
            for (i in 0..1){
                val mDate = formatter.format(time)
                time -= millisInDay
//                _forexData.value = forexApiRepository.getLatestForexCurrencyData("USD,AUD,CAD,PLN,MXN")
                forexApiRepository.getForexCurrencyDataByDate(mDate,"USD,AUD,CAD,PLN,MXN")?.let {
                    arraylist.add(it)
                    _forexDataList.value = arraylist.toMutableList()
                }
            }

            _uiState.value = UIState.OnSuccess
        }
    }
}