package com.example.recipesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipesapp.R
import com.example.recipesapp.Recipe
import com.example.recipesapp.`object`.ImageManagement
import com.example.recipesapp.adapters.SearchRecyclerAdapter
import com.example.recipesapp.databinding.FragmentSearchRecipeBinding
import kotlinx.android.synthetic.main.fragment_search_recipe.*
import java.util.ArrayList


class SearchRecipeFragment : Fragment() {

    private lateinit var recipeToShow: ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchRecipeBinding.inflate(inflater, container, false)
        recipeToShow = ArrayList<Recipe>()
        val allRecipe = ImageManagement.getRecipeFromPreferences(inflater.context)


        binding.searchBTMStartSearch.setOnClickListener {
            val ingredientsToSearch = binding.searchTXTIngredients.text.toString()
            searchRecipe(allRecipe, ingredientsToSearch, inflater)
        }

        binding.searchBTBBack.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_searchRecipeFragment2_to_mainFragment)
        }

        return binding.root
    }

    private fun searchRecipe(allRecipe: ArrayList<Recipe>, ingredientsToSearch: String,
        inflater: LayoutInflater
    ) {
        val ingredientsList = ingredientsToSearch.split("\\s+".toRegex()).map { word ->
            word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
        }
        for (i in allRecipe.indices) {
            val recipeIngredients =
                allRecipe.get(i).ingredients.split("\\s+".toRegex()).map { word ->
                    word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
                }
            if (compareWords(ingredientsList, recipeIngredients)) {
                recipeToShow.add(allRecipe.get(i))
            }

        }
        if (recipeToShow.isEmpty()) {
            Toast.makeText(
                inflater.context,
                "there is no recipe with your ingredients",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            ImageManagement.saveSearchRecipeToPreferences(recipeToShow,this.context)
            search_RCL_recipe.apply {
                // set a LinearLayoutManager to handle Android
                // RecyclerView behavior
                layoutManager = LinearLayoutManager(activity)
                // set the custom adapter to the RecyclerView
                adapter = SearchRecyclerAdapter(this.context, recipeToShow)
            }
        }
    }

    private fun compareWords(
        ingredientsList: List<String>,
        recipeIngredients: List<String>
    ): Boolean {
        for (x in ingredientsList.indices) {
            for (y in recipeIngredients.indices) {
                if (ingredientsList[x].equals(recipeIngredients[y])) {
                    return true
                }
            }
        }
        return false
    }


}


