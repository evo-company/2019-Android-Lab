package evo.company.appcomponents.ui.devs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import evo.company.appcomponents.R
import kotlinx.android.synthetic.main.fragment_devs.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DevelopersFragment : Fragment() {

    companion object {
        fun newInstance() = DevelopersFragment()
    }

    private val devViewModel: DevelopersViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_devs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        devViewModel.searchedDevelopers.observe(this, Observer {
            recyclerView.adapter = DevsAdapter(it)
        })
    }
}