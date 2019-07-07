package evo.company.appcomponents.ui.devDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import evo.company.appcomponents.R
import kotlinx.android.synthetic.main.fragment_developer_detail.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DevDetailFragment : Fragment() {

    private val devDetailViewModel: DeveloperDetailViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_developer_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        devDetailViewModel.developer.observe(this, Observer { developer ->
            Glide.with(this).load(developer.avatarUrl).into(icon)
            name.text = developer.login
            description.text = developer.bio
            toolbarTitle.text = developer.login
            repositoriesCount.text = requireContext().getString(R.string.followers_count, developer.publicRepositoriesCount)
            gistsCount.text = requireContext().getString(R.string.gists_count, developer.publicGistsCount)
            followersCount.text = requireContext().getString(R.string.followers_count, developer.followersCount)
            followingsCount.text = requireContext().getString(R.string.followings_count, developer.followingCount)
        })

        backArrow.setOnClickListener {
            view.findNavController().popBackStack()
        }
    }
}