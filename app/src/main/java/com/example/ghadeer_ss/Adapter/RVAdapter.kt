package com.example.ghadeer_ss.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ghadeer_ss.ApiFragment
import com.example.ghadeer_ss.API.UniversityData
import com.example.ghadeer_ss.databinding.ItemRowBinding


class RVAdapter(
    private val entries: ArrayList<UniversityData.University>,
    private val activity: ApiFragment

) : RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val entry = entries[position]

        holder.binding.apply {
            btItem.text = entry.name

            //if(position%2==0){btItem.setBackgroundColor(Color.parseColor("#BBB0EE"))}

            btItem.setOnClickListener {
                activity.addToDatabase(entry.name, entry.country, "")
            }
        }
    }

    override fun getItemCount() = entries.size

}