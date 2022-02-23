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

class FavoriteRecyclerAdapter (val context: Context) : RecyclerView.Adapter<FavoriteRecyclerAdapter.FavoriteViewHolder>() {

    //private val allRecipe = ImageManagement.getRecipeFromPreferences(context)
    private  var allFavoriteRecipe:ArrayList<Recipe> = ImageManagement.findFavoriteRecipe(context)

    override fun getItemCount(): Int {
        return allFavoriteRecipe.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_recipe_card, parent, false)
        return FavoriteViewHolder(v)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {

        val imgstring = allFavoriteRecipe.get(position).profilePic
        val imageBytes = Base64.decode(imgstring, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        holder.card_IMG.setImageBitmap(decodedImage)
        holder.card_TXT_Ingredients.text = allFavoriteRecipe.get(position).foodCategory.toString()
        holder.card_TXT_name.text = allFavoriteRecipe.get(position).name
        holder.card_IMG_favorite.setImageResource(R.drawable.red_heart)

    }

    /*private fun findFavoriteRecipe(): ArrayList<Recipe> {
        var favorite: ArrayList<Recipe> = ArrayList()
        for (i in allRecipe.indices){
            if (allRecipe.get(i).favorite == true)
                if (favorite != null) {
                    favorite.add(allRecipe.get(i))
                }
        }

        return favorite
    }*/

    inner class FavoriteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var card_IMG: ImageView
        var card_TXT_name: TextView
        var card_TXT_Ingredients: TextView
        var card_IMG_favorite:ImageView

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
                itemView.findNavController().navigate(R.id.action_viewFavoriteRecipeFragment_to_favorite_recipe_profile_Fragment, bundle)


            }


        }
    }


}

