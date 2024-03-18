package com.ifs21025.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21025.dinopedia.databinding.ItemRowDinoBinding

class ListDinoAdapter(private val listDino: ArrayList<Dino>) :
    RecyclerView.Adapter<ListDinoAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {  val binding =
        ItemRowDinoBinding.inflate(LayoutInflater.from(viewGroup.context),
            viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position:
    Int) {
        val family = listDino[position]
        holder.binding.ivItemDino.setImageResource(family.pict)
        holder.binding.tvItemDino.text = family.name
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClicked(listDino[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listDino.size

    class ListViewHolder(var binding: ItemRowDinoBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Dino)
    }
} 
