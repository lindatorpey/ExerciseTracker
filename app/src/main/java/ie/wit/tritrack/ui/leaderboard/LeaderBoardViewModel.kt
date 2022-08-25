package ie.wit.tritrack.ui.leaderboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LeaderBoardViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "LeaderBoard"
    }
    val text: LiveData<String> = _text
}