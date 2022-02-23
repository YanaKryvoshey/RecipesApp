package com.example.recipesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentMainBinding


class MainFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater,container,false)
        //val view = inflater.inflate(R.layout.fragment_main,container,false)

        binding.mainBTNADD.setOnClickListener{
            Navigation.findNavController(binding!!.root).navigate(R.id.action_mainFragment_to_addRecipeActivity)
        }
        binding.mainBTNSearch.setOnClickListener{
            Navigation.findNavController(binding!!.root).navigate(R.id.action_mainFragment_to_searchRecipeFragment2)
        }
        binding.mainBTNViewAll.setOnClickListener{
            Navigation.findNavController(binding!!.root).navigate(R.id.action_mainFragment_to_viewRecipesFragment2)
        }
        binding.mainBTNViewFavorite.setOnClickListener {
            Navigation.findNavController(binding!!.root).navigate(R.id.action_mainFragment_to_viewFavoriteRecipeFragment)
        }

        return binding.root
    }


}