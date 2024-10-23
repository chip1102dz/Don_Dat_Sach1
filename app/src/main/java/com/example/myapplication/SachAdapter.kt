package com.example.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBinding

class SachAdapter(val context: Context) : RecyclerView.Adapter<SachAdapter.SachViewHolder>() {

    var list = mutableListOf<Sach>()
    fun setData(list: MutableList<Sach>){
        this.list = list
        notifyDataSetChanged()
    }
    fun HienThi(context: Context){
        AlertDialog.Builder(context)
            .setTitle("HOP THOAI")
            .setMessage("BAN CO MUON CHON?")
            .setNegativeButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            .setPositiveButton("No", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            }).show()
    }
    class SachViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SachViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SachViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SachViewHolder, position: Int) {
        val user = list[position]
        holder.binding.name.text = user.name
        holder.binding.image.setImageResource(user.image)
        holder.itemView.setOnLongClickListener {
            HienThi(context)
            true
        }
    }
}