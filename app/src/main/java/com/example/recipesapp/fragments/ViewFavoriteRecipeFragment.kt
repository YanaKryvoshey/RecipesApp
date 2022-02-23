package com.example.recipesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.adapters.FavoriteRecyclerAdapter
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentViewRecipesBinding
import kotlinx.android.synthetic.main.fragment_view_recipes.*


class ViewFavoriteRecipeFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    private var adapter: RecyclerView.Adapter<FavoriteRecyclerAdapter.FavoriteViewHolder>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewRecipesBinding.inflate(inflater,container,false)
        binding.viewBTBBack.setOnClickListener {
            Navigation.findNavController(binding!!.root).navigate(R.id.action_viewFavoriteRecipeFragment_to_mainFragment)
        }
        return  binding.root  }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        view_RCL_recipe.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = FavoriteRecyclerAdapter(this.context)
        }
    }
}