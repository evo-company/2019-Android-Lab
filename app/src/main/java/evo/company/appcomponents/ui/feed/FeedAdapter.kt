package evo.company.appcomponents.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import evo.company.appcomponents.R
import evo.company.appcomponents.model.Repository
import kotlinx.android.synthetic.main.item_repository.view.*

class FeedAdapter(
    private val items: List<Repository>
) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(repository: Repository) {
            with(itemView) {
                title.text = repository.name
                description.text = repository.description
                description.visibility = if (repository.description.isBlank()) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
                languageName.text = repository.language.title
                languageIcon.setImageResource(repository.language.icon)
                starCount.text = repository.starsCount.toString()
                forkCount.text = repository.forksCount.toString()
            }
        }
    }
}
