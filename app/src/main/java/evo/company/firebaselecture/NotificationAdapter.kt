package evo.company.firebaselecture

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import evo.company.firebaselecture.model.Notification
import kotlinx.android.synthetic.main.item_notification.view.*

class NotificationAdapter(
    var notifications: List<Notification>
) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_notification,
                parent,
                false
            )
        ).apply { implementOnClickListeners() }
    }

    override fun getItemCount() = notifications.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notifications[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Notification) {
            with(itemView) {
                titleTv.text = item.title
                bodyTv.text = item.body
            }
        }
    }

    private fun ViewHolder.implementOnClickListeners() {
        with(itemView) {
            setOnClickListener {

            }
        }
    }
}
