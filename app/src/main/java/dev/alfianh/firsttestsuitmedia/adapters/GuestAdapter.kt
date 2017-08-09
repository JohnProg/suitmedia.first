package dev.alfianh.firsttestsuitmedia.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.alfianh.firsttestsuitmedia.R
import dev.alfianh.firsttestsuitmedia.models.Event
import dev.alfianh.firsttestsuitmedia.models.Guest
import kotlinx.android.synthetic.main.row_item_guest.view.*
import org.w3c.dom.Text

/**
 * Created by alfianh on 8/9/17.
 */
class GuestAdapter(
        var context: Context,
        var data: ArrayList<Guest>,
        var listener: Listener
) : RecyclerView.Adapter<GuestAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        (holder as ViewHolder).bindView(data[position].name, R.drawable.suitmedia)
        holder?.itemView?.setOnClickListener { listener.onClick(data[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item_guest, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bindView(name: String, image: Int) {
            itemView.ivPhoto.setImageResource(image)
            itemView.tvName.text = name
        }
    }

    interface Listener {
        fun onClick(guest: Guest);
    }
}