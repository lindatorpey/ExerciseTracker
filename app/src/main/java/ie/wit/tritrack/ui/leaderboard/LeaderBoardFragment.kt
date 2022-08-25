package ie.wit.tritrack.ui.leaderboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ie.wit.tritrack.R
import androidx.lifecycle.Observer


class LeaderBoard : Fragment() {
    private lateinit var leaderBoardViewModel: LeaderBoardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        leaderBoardViewModel =
            ViewModelProvider(this).get(LeaderBoardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_leader_board, container, false)
        //val textView: TextView = root.findViewById(R.id.text_slideshow)
        leaderBoardViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })
        return root
    }
}