package com.example.androiddigitalclock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {

    val hourRightDisplayManager = DigitDisplayManager()
    val hourLeftDisplayManager = DigitDisplayManager()

    val secondsRightDisplayManager = DigitDisplayManager()
    val secondsLeftDisplayManager = DigitDisplayManager()

    //region LiveData
    private val _segmentTopLiveData = MutableLiveData(R.color.unselected)
    val segmentTopLiveData: LiveData<Int> = _segmentTopLiveData

    private val _segmentTopLeftLiveData = MutableLiveData(R.color.unselected)
    val segmentTopLeftLiveData: LiveData<Int> = _segmentTopLeftLiveData

    private val _segmentTopRightLiveData = MutableLiveData(R.color.unselected)
    val segmentTopRightLiveData: LiveData<Int> = _segmentTopRightLiveData

    private val _segmentMiddleLiveData = MutableLiveData(R.color.unselected)
    val segmentMiddleLiveData: LiveData<Int> = _segmentMiddleLiveData

    private val _segmentBottomLeftLiveData = MutableLiveData(R.color.unselected)
    val segmentBottomLeftLiveData: LiveData<Int> = _segmentBottomLeftLiveData

    private val _segmentBottomRightLiveData = MutableLiveData(R.color.unselected)
    val segmentBottomRightLiveData: LiveData<Int> = _segmentBottomRightLiveData

    private val _segmentBottomLiveData = MutableLiveData(R.color.unselected)
    val segmentBottomLiveData: LiveData<Int> = _segmentBottomLiveData

    //endregion LiveData

    fun startCounting() {
        viewModelScope.launch {
            var index = 0
            while (true) {
                val hours = index / 60
                val seconds = index % 60

                hourRightDisplayManager.onNewDigit(hours % 10)
                hourLeftDisplayManager.onNewDigit(hours / 10)

                secondsRightDisplayManager.onNewDigit(seconds % 10)
                secondsLeftDisplayManager.onNewDigit(seconds / 10)
                index++
                delay(250)
            }
        }
    }
}