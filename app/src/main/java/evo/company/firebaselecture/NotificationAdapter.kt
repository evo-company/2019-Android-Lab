package evo.company.firebaselecture

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import evo.company.firebaselecture.model.Notification
import kotlinx.android.synthetic.main.item_notification.view.*

class NotificationAdapter(
    var notifications: MutableList<Notification>,
    val onLoadMore: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val NOTIFICATION_VIEW_TYPE = 522
        private const val LOAD_MORE_VIEW_TYPE = 523
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            NOTIFICATION_VIEW_TYPE -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_notification,
                    parent,
                    false
                )
            ).apply { implementOnClickListeners() }
            else -> {
                LoadMoreViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_load_more,
                        parent,
                        false
                    ).apply { setOnClickListener { onLoadMore.invoke() } }
                )
            }
        }
    }

    fun appendList(notifications: List<Notification>) {
        val startIndex = this.notifications.size
        this.notifications.addAll(notifications)
        notifyItemRangeInserted(startIndex, notifications.size)
        if (startIndex == 0) {
            notifyItemInserted(itemCount)
        }
    }

    override fun getItemCount(): Int {
        var res = notifications.size
        if (notifications.isNotEmpty()) {
            res++
        }
        return res
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            itemCount - 1 -> LOAD_MORE_VIEW_TYPE
            else -> NOTIFICATION_VIEW_TYPE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bind(notifications[position])
            else -> {
            }
        }
    }

    class LoadMoreViewHolder(view: View) : RecyclerView.ViewHolder(view)

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
