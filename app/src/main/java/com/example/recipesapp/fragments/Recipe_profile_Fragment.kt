package com.example.recipesapp.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation

import com.example.recipesapp.R
import com.example.recipesapp.Recipe
import com.example.recipesapp.`object`.ImageManagement

import com.example.recipesapp.databinding.FragmentRecipeProfileBinding
import kotlinx.android.synthetic.main.activity_add_recipe.*
import kotlinx.android.synthetic.main.fragment_recipe_profile_.*


class Recipe_profile_Fragment : Fragment() {

    private lateinit var bitmapsimaged:ArrayList<Bitmap>
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecipeProfileBinding.inflate(inflater,container,false)
        val index = arguments?.getInt("position")
        bitmapsimaged = ArrayList<Bitmap>()
         val allRecipe = ImageManagement.getRecipeFromPreferences(inflater.context)
        setAllData(index,allRecipe,binding)

        binding.profileIMGFavorite.setOnClickListener{
            updateFavorite(index,allRecipe,binding,inflater)
        }

        binding.profileBTMNext.setOnClickListener{
            if (position < bitmapsimaged!!.size-1){
                position++
                binding.profileIMG.setImageBitmap(bitmapsimaged.get(position))
            } else{
                Toast.makeText(inflater.context,"No more images....", Toast.LENGTH_SHORT).show()
            }
        }

        binding.profileBTMBefore.setOnClickListener {
            if (position > 0){
                position--
                binding.profileIMG.setImageBitmap(bitmapsimaged.get(position))
            }else{
                Toast.makeText(inflater.context,"No more images....", Toast.LENGTH_SHORT).show()
            }
        }
        binding.profileBTBBack.setOnClickListener {
            Navigation.findNavController(binding!!.root).navigate(R.id.action_recipe_profile_Fragment_to_viewRecipesFragment2)
        }
        binding.profileBTMDelete.setOnClickListener {
            allRecipe.remove(allRecipe.get(index!!))
            ImageManagement.saveRecipeToPreferences(allRecipe,inflater.context)
            Toast.makeText(inflater.context,"the recipe delete",Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    private fun updateFavorite(
        index: Int?,
        allRecipe: java.util.ArrayList<Recipe>,
        binding: FragmentRecipeProfileBinding,
        inflater: LayoutInflater
    ) {
        val recipe = index?.let { allRecipe?.get(it) }
        allRecipe.remove(recipe)
        if (recipe != null) {
            if(recipe.favorite==false){
                recipe.favorite = true
                binding.profileIMGFavorite.setImageResource(R.drawable.red_heart)
            }else{
                recipe.favorite = false
                binding.profileIMGFavorite.setImageResource(R.drawable.heart)

            }
            allRecipe.add(index,recipe)
            //allRecipe.add(recipe)
            ImageManagement.saveRecipeToPreferences(allRecipe,inflater.context)
        }
    }

    private fun setAllData(
        index: Int?,
        allRecipe: ArrayList<Recipe>,
        binding: FragmentRecipeProfileBinding
    ) {
        val recipe = index?.let { allRecipe?.get(it) }
        if (recipe != null) {
            binding.profileTXTName.text = recipe.name.toString()
            binding.profileTXTCategory.text = recipe.foodCategory.toString()
            binding.profileIMGProfile.setImageBitmap(getbitmapFromString(recipe.profilePic))
            for(i in recipe.imageUriList.indices){
                var imageBitmao = getbitmapFromString(recipe.imageUriList.get(i))
                binding.profileIMG.setImageBitmap(imageBitmao)
                if (imageBitmao != null) {
                    bitmapsimaged.add(imageBitmao)
                }
            }
            //position = 0;
            binding.profileTXTINGRADIENT.text = recipe.ingredients
            if (recipe.favorite == false){
                binding.profileIMGFavorite.setImageResource(R.drawable.heart)
            }else{
                binding.profileIMGFavorite.setImageResource(R.drawable.red_heart)
            }
        }
    }

    private fun getbitmapFromString(imgstring: String): Bitmap? {
        var imageBytes = Base64.decode(imgstring, Base64.DEFAULT)
        var decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        return decodedImage
    }


}


