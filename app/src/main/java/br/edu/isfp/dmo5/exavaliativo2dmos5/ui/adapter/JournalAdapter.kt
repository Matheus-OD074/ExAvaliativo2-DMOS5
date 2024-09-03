package br.edu.isfp.dmo5.exavaliativo2dmos5.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.isfp.dmo5.exavaliativo2dmos5.R
import br.edu.isfp.dmo5.exavaliativo2dmos5.data.model.Journal
import br.edu.isfp.dmo5.exavaliativo2dmos5.databinding.ItemJournalBinding
import br.edu.isfp.dmo5.exavaliativo2dmos5.ui.listener.JournalClickListener

class JournalAdapter(val clickListener: JournalClickListener): RecyclerView.Adapter<JournalAdapter.ViewHolder>() {

    private var dataset: List<Journal> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_journal, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val  journal = dataset[position]

        holder.binding.textTitle.text = journal.title
        holder.binding.textDescription.text = journal.description
        holder.binding.textDateTime.text = journal.dateTime
        holder.binding.imgDelete.setOnClickListener{
            clickListener.clickDelete(position)
        }
        holder.binding.viewJournal.setOnLongClickListener {
            clickListener.clickOpen(position)
            true
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun submitDataset(dataset: List<Journal>){
        this.dataset = dataset
        this.notifyDataSetChanged()
    }

    fun  getDatasetItem(position: Int): Journal{
        return dataset[position]
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val binding: ItemJournalBinding = ItemJournalBinding.bind(view)

    }
}