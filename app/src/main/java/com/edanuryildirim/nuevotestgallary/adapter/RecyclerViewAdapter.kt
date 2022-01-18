package com.edanuryildirim.nuevotestgallary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edanuryildirim.nuevotestgallary.R
import com.edanuryildirim.nuevotestgallary.model.GallaryModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter (private var gallaryList : ArrayList<GallaryModel>, private val listener : Listener) :
    RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>(){

    interface Listener{
        fun onItemClick(nutrition : GallaryModel)
    }

    class RowHolder (view: View) : RecyclerView.ViewHolder(view){

        fun bind(nutrition: GallaryModel, position: Int, listener : Listener){
            itemView.setOnClickListener{listener.onItemClick(nutrition)}
            itemView.text_name.text = nutrition.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(gallaryList[position],position,listener)
        val imgUrl = "${gallaryList[position].url}"
        Picasso.get().load(imgUrl).into(holder.itemView.imageNews)
    }

    override fun getItemCount(): Int {
        return gallaryList.count()
    }

}