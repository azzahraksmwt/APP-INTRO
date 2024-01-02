package com.androidstudio.app_intro.ui.lecturerdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LecturerdataViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is lecturer data Fragment"
    }
    val text: LiveData<String> = _text
}