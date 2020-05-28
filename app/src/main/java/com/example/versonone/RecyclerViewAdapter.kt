package com.example.versonone

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import kotlinx.android.synthetic.main.activity_recycler.view.*
class RecyclerViewAdapter(val context: Context
                          , val index : Int
                          , val whatTypeOfJson : String
                          , val jsonFileRes : JsonFileRes.Jtype) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        private val imageItem : ImageView = itemView.ivProductInRecyclerView
        fun Bind(pos : Int) {
            when(whatTypeOfJson) {
                "act" -> {
                    Glide
                        .with(context)
                        .load(
                            if (jsonFileRes.act[pos].actimage == null || jsonFileRes.act[pos].actimage == "") {
                                (jsonFileRes.act[pos].actimageurl)
                            } else {
                                (jsonFileRes.act[pos].actimage)
                            }
                        )
                        .transform(CircleCrop())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageItem)
                }
                "product" -> {
                    Glide
                        .with(context)
                        .load(
                            if (jsonFileRes.act[index].product[pos].image == null || jsonFileRes.act[index].product[pos].image == "") {
                                (jsonFileRes.act[index].product[pos].imageurl)
                            } else {
                                (jsonFileRes.act[index].product[pos].image)
                            }
                        )
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageItem)
                }
                else -> 0
            }
            imageItem.setOnClickListener {
                        val intent = when(whatTypeOfJson){
                            "product" -> {
                                Intent(context, Product::class.java)
                            }
                            "act" -> {
                                Intent(context, Actor::class.java)
                            }
                            else -> Intent(context, MainActivity::class.java)
                        }
                        intent.putExtra("index", pos)
                        intent.putExtra("image", index)
                        startActivity(context, intent, null)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (whatTypeOfJson) {
            "act" -> {
                val view = LayoutInflater.from(parent.context) // 負責把Layout再變成可用的View
                    .inflate(R.layout.activity_recyclerforact, parent, false) // 是否在RecyclerView生成之前自動把圖檔放入View當中
                 ViewHolder(view)
            }
            "product" -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.activity_recycler, parent, false)
                 ViewHolder(view)
            }
            else ->{
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.activity_recycler, parent, false)
                 ViewHolder(view)
            }
        }
    }
    override fun getItemCount(): Int {
        return when (whatTypeOfJson) {
            "act" -> {
                 jsonFileRes.act.count()
            }
            "product" -> {
                 jsonFileRes.act[index].product.count()
            }
            else -> 0
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Bind(position)
    }
}