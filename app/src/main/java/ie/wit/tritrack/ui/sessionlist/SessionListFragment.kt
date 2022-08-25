package ie.wit.tritrack.ui.sessionlist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.tritrack.R
import ie.wit.tritrack.adapters.LogAdapter
import ie.wit.tritrack.databinding.FragmentSessionlistBinding
import ie.wit.tritrack.main.TriTrackApp
import ie.wit.tritrack.ui.log.LogFragment

class SessionListFragment : Fragment() {

    lateinit var app: TriTrackApp
    private var _fragBinding: FragmentSessionlistBinding? = null
    private val fragBinding get() = _fragBinding!!

    private lateinit var sessionListViewModel: SessionListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as TriTrackApp
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragBinding = FragmentSessionlistBinding.inflate(inflater,container, false)
        val root = fragBinding.root
        activity?.title = "SessionList"

        sessionListViewModel =
            ViewModelProvider(this).get(SessionListViewModel::class.java)
        sessionListViewModel.text.observe(viewLifecycleOwner, Observer {

            //textView.text = it
        })

        fragBinding.recyclerView.setLayoutManager(LinearLayoutManager(activity))
        fragBinding.recyclerView.adapter = LogAdapter(app.logsStore.findAll())
        return root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_log, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            LogFragment().apply {
                arguments = Bundle().apply { }
            }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}