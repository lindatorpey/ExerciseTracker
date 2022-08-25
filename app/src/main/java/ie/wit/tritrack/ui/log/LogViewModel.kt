package ie.wit.tritrack.ui.log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply{
        value = "Blank for Now"
    }
    val text: LiveData<String> = _text
}