package dev.alfianh.firsttestsuitmedia.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.alfianh.firsttestsuitmedia.R
import dev.alfianh.firsttestsuitmedia.models.Event
import kotlinx.android.synthetic.main.row_item_event.view.*

/**
 * Created by alfianh on 8/9/17.
 */
class EventAdapter(
        var context: Context,
        var data: ArrayList<Event>,
        var listener: Listener
) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindView(data[position])
        holder?.itemView?.setOnClickListener { listener.onClick(data[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item_event, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bindView(data: Event) {
            itemView.ivPhoto.setImageResource(data.image)
            itemView.tvName.text = data.name
            itemView.tvDesc.text = data.desc
        }
    }

    interface Listener {
        fun onClick(event: Event);
    }
}