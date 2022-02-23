package com.example.recipesapp.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.R
import com.example.recipesapp.Recipe
import com.example.recipesapp.`object`.ImageManagement

class SearchRecyclerAdapter(val context: Context,val allRecipes:ArrayList<Recipe>): RecyclerView.Adapter<SearchRecyclerAdapter.SearchViewHolder>() {



    override fun getItemCount(): Int {
        return allRecipes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_recipe_card, parent, false)
        return SearchViewHolder(v)
    }

    override fun onBindViewHolder(holder: SearchRecyclerAdapter.SearchViewHolder, position: Int) {

        val imgstring = allRecipes.get(position).profilePic
        val imageBytes = Base64.decode(imgstring, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        holder.card_IMG.setImageBitmap(decodedImage)
        holder.card_TXT_Ingredients.text = allRecipes.get(position).foodCategory.toString()
        holder.card_TXT_name.text = allRecipes.get(position).name

        if(allRecipes.get(position).favorite == false){
            holder.card_IMG_favorite.setImageResource(R.drawable.heart)
        }else{
            holder.card_IMG_favorite.setImageResource(R.drawable.red_heart)
        }

    }


    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var card_IMG: ImageView
        var card_TXT_name: TextView
        var card_TXT_Ingredients: TextView
        var card_IMG_favorite: ImageView

        init {
            card_IMG = itemView.findViewById(R.id.card_IMG)
            card_TXT_name = itemView.findViewById(R.id.card_TXT_name)
            card_TXT_Ingredients = itemView.findViewById(R.id.card_TXT_Ingredients)
            card_IMG_favorite = itemView.findViewById(R.id.card_IMG_favorite)

            itemView.setOnClickListener {

                var position = getAdapterPosition()

                val context = itemView.context
                Toast.makeText(context,""+position, Toast.LENGTH_SHORT).show()
                val bundle = bundleOf("position" to position)
                itemView.findNavController().navigate(R.id.action_searchRecipeFragment2_to_searchRecipeProfileFragment, bundle)


            }
        }
    }


}