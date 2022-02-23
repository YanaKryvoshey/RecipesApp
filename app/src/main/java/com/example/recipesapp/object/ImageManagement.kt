package com.example.recipesapp.`object`

import android.content.Context
import com.example.recipesapp.Recipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ImageManagement {
    companion object {

         fun saveRecipeToPreferences(allRecipe: ArrayList<Recipe>,context: Context) {
            val sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
            val jsonString = Gson().toJson(allRecipe)
            with(sharedPref.edit()) {
                putString("newRecipe", jsonString)
                commit()
            }

        }

         fun getRecipeFromPreferences(context: Context): ArrayList<Recipe>{
            val sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
            val jsonString = sharedPref.getString("newRecipe", "DEFAULT")

            try {
                val getlist:ArrayList<Recipe>  = Gson().fromJson(jsonString, object: TypeToken<ArrayList<Recipe>>(){}.type)
                return getlist
            }catch (exception: RuntimeException){
                return arrayListOf()
            }


        }

         fun findFavoriteRecipe(context: Context): ArrayList<Recipe> {
            val allRecipe = getRecipeFromPreferences(context)
            var favorite: ArrayList<Recipe> = ArrayList()
            for (i in allRecipe.indices){
                if (allRecipe.get(i).favorite == true)
                    if (favorite != null) {
                        favorite.add(allRecipe.get(i))
                    }
            }

            return favorite
        }

        fun saveSearchRecipeToPreferences(allRecipe: ArrayList<Recipe>, context: Context?) {
            val sharedPref = context?.getSharedPreferences("SearchsharedPref", Context.MODE_PRIVATE)
            val jsonString = Gson().toJson(allRecipe)
            if (sharedPref != null) {
                with(sharedPref.edit()) {
                    putString("newSearchRecipe", jsonString)
                    commit()
                }
            }

        }

        fun getSearchRecipeFromPreferences(context: Context): ArrayList<Recipe>{
            val sharedPref = context.getSharedPreferences("SearchsharedPref", Context.MODE_PRIVATE)
            val jsonString = sharedPref.getString("newSearchRecipe", "DEFAULT")

            try {
                val getlist:ArrayList<Recipe>  = Gson().fromJson(jsonString, object: TypeToken<ArrayList<Recipe>>(){}.type)
                return getlist
            }catch (exception: RuntimeException){
                return arrayListOf()
            }


        }

    }
}
