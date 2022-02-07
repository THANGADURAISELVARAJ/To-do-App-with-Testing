package com.alvin.to_doappwithtesting.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddAndEditViewModel @Inject constructor() : ViewModel() {

    private var _navigateToMainActivity = MutableLiveData<Boolean>(false)
    var navigateToMainActivity: LiveData<Boolean> = _navigateToMainActivity


    fun validation(quoteText: String?, author: String?): String {
        return if (quoteText.isNullOrEmpty() || quoteText.length > 30) {
            "Pls Enter quote text"
        } else if (author.isNullOrEmpty() || author.length > 30) {
            "Pls Enter author name"
        } else {
            _navigateToMainActivity.postValue(true)
            ""
        }
    }
}