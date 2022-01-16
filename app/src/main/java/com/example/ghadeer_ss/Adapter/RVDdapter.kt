package com.example.ghadeer_ss.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ghadeer_ss.DBFragment
import com.example.ghadeer_ss.Database.UN
import com.example.ghadeer_ss.databinding.DbRowBinding


class RVDdapter(private val activity: DBFragment) :
    RecyclerView.Adapter<RVDdapter.ItemViewHolder>() {
    private var university = emptyList<UN>()

    class ItemViewHolder(val binding: DbRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(
            DbRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val uni = university[position]

        holder.binding.apply {
            tvName.text = uni.universityName
            tvCountry.text = uni.country



            btDelete.setOnClickListener {
                activity.viewModel.deleteUniversityDB(uni.id)
                Toast.makeText(activity.activity, "University deleted", Toast.LENGTH_LONG).show()

            }
            layout.setOnClickListener {
                Toast.makeText(activity.activity, "${uni.note}", Toast.LENGTH_LONG).show()
            }
            btUpdate.setOnClickListener {
                activity.updateNote(uni.note, uni.id)

            }
        }
    }

    override fun getItemCount() = university.size

    fun update(university: List<UN>) {
        println("UPDATING DATA")
        this.university = university
        notifyDataSetChanged()
    }
}