package com.ewam.myforexapp.api

sealed class UIState {
    object OnSuccess : UIState()
    object OnError : UIState()
    object InProgress : UIState()
    object OnWaiting : UIState()
}