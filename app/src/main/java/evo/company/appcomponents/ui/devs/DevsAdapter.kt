package evo.company.appcomponents.ui.devs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import evo.company.appcomponents.R
import evo.company.appcomponents.model.SimpleDeveloper
import kotlinx.android.synthetic.main.item_developer.view.*

class DevsAdapter(
    private val items: List<SimpleDeveloper>
) : RecyclerView.Adapter<DevsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_developer, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(dev: SimpleDeveloper) {
            with(itemView) {
                Glide.with(this)
                    .load(dev.avatarUrl)
                    .into(icon)
                name.text = dev.login
                setOnClickListener {
                    val action = DevelopersFragmentDirections.actionDevelopersFragment2ToDevDetailFragment(dev.login)
                    Navigation.findNavController(itemView).navigate(action)
                }
            }
        }
    }
}
