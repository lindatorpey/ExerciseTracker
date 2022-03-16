package ie.wit.tritrack.ui.racelog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RaceLogViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is where you will log your race time!"
    }
    val text: LiveData<String> = _text
}