package ie.wit.tritrack.ui.sessionlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SessionListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is a session list Fragment"
    }
    val text: LiveData<String> = _text
}