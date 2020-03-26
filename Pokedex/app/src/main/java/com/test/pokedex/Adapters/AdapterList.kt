package com.test.pokedex.Adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.koushikdutta.ion.Ion
import com.test.pokedex.Activities.ActivityDetail
import com.test.pokedex.R

class AdapterList:RecyclerView.Adapter<AdapterList.ViewHolder>() {

    private lateinit var data: JsonArray
    private lateinit var context: Context

    fun AdapterList(context:Context,data:JsonArray){
        this.context = context
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterList.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_list,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size()
    }

    override fun onBindViewHolder(holder: AdapterList.ViewHolder, position: Int) {
        var item:JsonObject = data.get(position).asJsonObject
        holder.bind(item,context)
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        private var imagePokemon: ImageView = view.findViewById(R.id.pokemon_image)
        private var namePokemon: TextView = view.findViewById(R.id.pokemon_name)
        private var myView = view

        fun bind(item: JsonObject, context: Context) {
            myView.tag = position

            imagePokemon.setOnClickListener {
                val clickedPosition = myView.tag as Int
                var intent = Intent(context, ActivityDetail::class.java)
                intent.putExtra("#", (clickedPosition + 1).toString())
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                context.startActivity(intent)
            }

            namePokemon.text = (item.get("name").asString).substring(0, 1).toUpperCase() + (item.get("name").asString).substring(1).toLowerCase()

            Ion.with(context)
                .load(item.get("url").asString)
                .asJsonObject()
                .done { e, result ->
                    if (e == null) {
                        if (!result.get("sprites").isJsonNull) {
                            if (result.get("sprites").asJsonObject.get("front_default") != null) {
                                //Pintar
                                Glide
                                    .with(context)
                                    .load(result.get("sprites").asJsonObject.get("front_default").asString)
                                    .placeholder(R.drawable.pokemon_logo_min)
                                    .error(R.drawable.pokemon_logo_min)
                                    .into(imagePokemon)
                            } else {
                                imagePokemon.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        context,
                                        R.drawable.pokemon_logo_min
                                    )
                                )
                            }
                        } else {
                            imagePokemon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.pokemon_logo_min))
                        }
                    }
                }
        }
    }
}
