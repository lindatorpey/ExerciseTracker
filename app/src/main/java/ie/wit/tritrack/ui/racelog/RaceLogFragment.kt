package ie.wit.tritrack.ui.racelog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ie.wit.tritrack.databinding.FragmentRacelogBinding

class RaceLogFragment : Fragment() {

    private var _binding: FragmentRacelogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(RaceLogViewModel::class.java)

        _binding = FragmentRacelogBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.racelogTitle
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}