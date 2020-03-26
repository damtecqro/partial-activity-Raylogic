package com.test.pokedex.Activities

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import com.koushikdutta.ion.Ion
import com.test.pokedex.R
import java.util.*

class ActivityDetail : AppCompatActivity() {

    private var context: Context = this
    private var entry: String = "0"
    private lateinit var data: JsonObject
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var imagePokemon: ImageView
    private lateinit var namePokemon: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        manageIntent()
        initializeComponents()
        initializeData()
    }

    private fun manageIntent() {
        if (intent != null) {
            entry = intent.getStringExtra("#")
        }
    }

    private fun initializeComponents(){
        imagePokemon = findViewById(R.id.pokemon_detail_image)
        namePokemon = findViewById(R.id.pokemon_detail_name)
    }

    private fun addTextView(innerText: String, parentID: Int) {
        val linearLayout: LinearLayout = findViewById(parentID)
        var type = TextView(this)
        type.gravity = Gravity.CENTER
        type.textSize = 16f
        type.setTextColor(Color.parseColor("#353535"))
        type.text = innerText
        linearLayout.addView(type)
    }

    private fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")

    private fun initializeData(){
        Ion.with(context)
            .load("https://pokeapi.co/api/v2/pokemon/" + entry + "/")
            .asJsonObject()
            .done { e, result ->
                data = result
                if(e == null){
                    if(!data.get("sprites").isJsonNull){
                        if(data.get("sprites").asJsonObject.get("front_default") != null){
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
                        imagePokemon.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.pokemon_logo_min
                            )
                        )
                        Log.e("JSON", "No sprite")
                    }

                    if(!data.get("name").isJsonNull){
                        val name = entry + ") " + result.get("name").toString().replace("\"", "").capitalize()
                        namePokemon.text = name
                    } else {
                        namePokemon.text = "Doesn't exist"
                    }

                    if(!data.get("types").isJsonNull){
                        val types = result.get("types").asJsonArray
                        for(i in 0.until(types.size())){
                            val typeClass = types.get(i).asJsonObject.get("type").asJsonObject
                            val typeName = typeClass.get("name").toString().replace("\"", "").capitalize()
                            addTextView(typeName, R.id.types)
                        }
                    } else {
                        Log.e("JSON","No types")
                    }

                    if(!data.get("stats").isJsonNull){
                        val stats = result.get("stats").asJsonArray
                        for(i in 0.until(stats.size())){
                            val statValue = stats.get(i).asJsonObject.get("base_stat")
                            val statName = stats.get(i).asJsonObject.get("stat").asJsonObject.get("name")
                            val statInfo = statName.toString().replace("\"", "").replace("-"," ").capitalizeWords() + " = " + statValue.toString()
                            addTextView(statInfo, R.id.stats)
                        }
                    } else {
                        Log.e("JSON","No stats")
                    }

                    if(!data.get("moves").isJsonNull){
                        val moves = result.get("moves").asJsonArray
                        for(i in 0.until(moves.size())){
                            val moveName = moves.get(i).asJsonObject.get("move").asJsonObject.get("name")
                            val moveInfo = moveName.toString().replace("\"", "").replace("-"," ").capitalizeWords()
                            addTextView(moveInfo, R.id.moves)
                        }
                    } else {
                        Log.e("JSON","No moves")
                    }
                }
                initializeList()
            }
    }

    fun initializeList(){
        linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        linearLayoutManager.scrollToPosition(0)
    }
}
