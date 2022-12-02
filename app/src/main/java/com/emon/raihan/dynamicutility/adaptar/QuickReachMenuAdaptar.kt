package com.emon.raihan.dynamicutility.adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.emon.raihan.dynamicutility.R
import com.emon.raihan.dynamicutility.model.Loan

import java.util.ArrayList

class QuickReachMenuAdaptar(
    private var movieList: ArrayList<Loan>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var requestFilterList = ArrayList<Loan>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = movieList
        listener = listenerInit


    }

    interface OnItemClickListener {
        fun onItemClick(item: Loan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_loan_list, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tv_amount: TextView? = holder.itemView.findViewById(R.id.tv_amount)
        val tv_status: TextView? = holder.itemView.findViewById(R.id.tv_status)
        val tv_display_amount: TextView? = holder.itemView.findViewById(R.id.tv_display_amount)
        val tv_date: TextView? = holder.itemView.findViewById(R.id.tv_date)

        tv_amount!!.text = currentItem.amount
        tv_display_amount!!.text = currentItem.amount
        tv_date!!.text = currentItem.date




        tv_amount.setOnClickListener {
              val selectedList: Loan = currentItem
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

                    val resultList = ArrayList<Loan>()
                    for (row in movieList) {
                        if (
                            row.amount.toString().lowercase()
                                .contains(constraint.toString().lowercase())

                            || row.date.toString().lowercase()
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

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                requestFilterList = results?.values as ArrayList<Loan>
                notifyDataSetChanged()
            }

        }
    }
}