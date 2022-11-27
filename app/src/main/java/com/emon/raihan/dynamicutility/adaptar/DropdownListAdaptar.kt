package com.emon.raihan.dynamicutility.adaptar

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.model.CodeDesOptions

class DropdownListAdaptar(
    private var movieList: ArrayList<CodeDesOptions>,
    dialog1: AlertDialog.Builder,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var requestFilterList = ArrayList<CodeDesOptions>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener
    var dialog: AlertDialog.Builder


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = movieList
        listener = listenerInit
        dialog = dialog1

    }

    interface OnItemClickListener {
        fun onItemClick(item: CodeDesOptions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_dropdown_item, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tv_bill_type_name: TextView = holder.itemView.findViewById(R.id.tv_bill_type_name)
       // val iv_bill_type: ImageView = holder.itemView.findViewById(R.id.iv_bill_type)
        val view_layout: LinearLayout = holder.itemView.findViewById(R.id.view_layout)

        tv_bill_type_name.text = currentItem.desc

        view_layout.setOnClickListener {
            val selectedList: CodeDesOptions = currentItem
            listener.onItemClick(selectedList)

        }

    }

    override fun getItemCount(): Int {
        return requestFilterList.size
        // notifyDataSetChanged()
        // Log.e("requestFilterList--->", requestFilterList.size.toString())
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = movieList
                } else {

                    val resultList = ArrayList<CodeDesOptions>()
                    for (row in movieList) {
                        if (
                            row.code.toString().lowercase()
                                .contains(constraint.toString().lowercase())

                            || row.desc.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                )
                        ) {
                            resultList.add(row)
                        }
                    }
                    requestFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = requestFilterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                requestFilterList = results?.values as ArrayList<CodeDesOptions>
                notifyDataSetChanged()
            }

        }
    }
}