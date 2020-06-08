package com.example.nikeexc.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeexc.R
import com.example.nikeexc.domain.Definition
import kotlinx.android.synthetic.main.listview.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class UrbanAdapter() : RecyclerView.Adapter<UrbanAdapter.DefinitionViewHolder>() {

    var definitionList: MutableList<Definition> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listview, parent, false)
        return DefinitionViewHolder(view)
    }

    override fun getItemCount(): Int = definitionList.size

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        var date: Date? = null
        var DATE_FORMAT_PATTERN: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        try {
            date = SimpleDateFormat(DATE_FORMAT_PATTERN).parse(definitionList[position].written_on)
        } catch (e: ParseException) {

        }

        holder.word.text = definitionList[position].word
        holder.definitionView.text = definitionList[position].definition
        holder.author.text = "by " + definitionList[position].author + " " + date

        holder.itemView.thumbsUpIMG.text = definitionList[position].thumbs_up.toString()
        holder.itemView.thumbsDownIMG.text = definitionList[position].thumbs_down.toString()
    }

    class DefinitionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var definitionView: TextView = itemView.findViewById(R.id.definition)
        public var word: TextView = itemView.findViewById(R.id.word)
        public var author: TextView = itemView.findViewById(R.id.author)
    }
}