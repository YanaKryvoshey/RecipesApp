package com.example.recipesapp.activitys

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.graphics.drawable.toBitmap
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.recipesapp.FoodCategory
import com.example.recipesapp.R
import com.example.recipesapp.Recipe
import com.example.recipesapp.`object`.ImageManagement
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_add_recipe.*
import java.io.ByteArrayOutputStream


class AddRecipeActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener{
    // variables
    private lateinit var images: ArrayList<Uri>
    private lateinit var allRecipe:ArrayList<Recipe>
    private lateinit var bitmapString:ArrayList<String>
     private val allfoodCategory = FoodCategory.values()
     private lateinit var foodCategory: FoodCategory
    private lateinit var profileBitmap: String
     // private lateinit var categoryName: FoodCategory

    // constants
    private val PICK_IMAGE_CODE = 123
    private val PICK_DISH_IMAGE_CODE = 789
    private var position = 0
    private var numimg = -1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)
        images = ArrayList<Uri>()
        allRecipe= ArrayList<Recipe>()
        bitmapString = ArrayList<String>()
        profileBitmap = ""


        pickDishImages_BTN_add.setOnClickListener {
            pickImagesDishIntent()
        }

        spinner!!.setOnItemSelectedListener(this)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, allfoodCategory
            )
            spinner.adapter = adapter

        }

        ImageSwitcher_add.setFactory { ImageView(application) }

        pickImagesBtn.setOnClickListener {
            pickImagesIntent()
        }

        nextBtn.setOnClickListener{
            if (position < images!!.size-1){
                position++
                ImageSwitcher_add.setImageURI(images!![position])
            } else{
                Toast.makeText(this,"No more images....",Toast.LENGTH_SHORT).show()
            }
        }

        previousBtn.setOnClickListener {
            if (position > 0){
                position--
                ImageSwitcher_add.setImageURI(images!![position])
            }else{
                Toast.makeText(this,"No more images....",Toast.LENGTH_SHORT).show()
            }
        }
        add_BTN_save.setOnClickListener {
            saveNewRecipe()
        }

    }

    private fun saveNewRecipe() {
        val name = add_TXT_Name.text.toString()
        val Ingredients = add_TXT_Ingredients.text.toString()
        //  for (i in images.indices){
        //     bitmapString.add(images.get(i).toString())
        //}
        // makeBitmapToImg()
        // val uriString: String = images.get(0).toString()
        val newRecipe = Recipe(profileBitmap,bitmapString,name,Ingredients,foodCategory)
        allRecipe = ImageManagement.getRecipeFromPreferences(this)
        allRecipe.add(newRecipe)
        ImageManagement.saveRecipeToPreferences(allRecipe,this)
        Toast.makeText(this,"Recipe saved",Toast.LENGTH_SHORT).show()

    }

    private fun pickImagesDishIntent() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_DISH_IMAGE_CODE)
    }

    private fun makeBitmapToImg(addImgProfile: ImageView?):String {
        val baos = ByteArrayOutputStream()
        val bitmap = addImgProfile?.drawable?.toBitmap()

        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val encodedImage = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
        return encodedImage
    }

    private fun pickImagesIntent(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE_CODE){
             if(data!!.clipData != null){
                 numimg++
                 val count = data.clipData!!.itemCount
                 for (i in 0 until count){
                     val imageUri = data.clipData!!.getItemAt(i).uri
                     images!!.add(imageUri)
                     ImageSwitcher_add.setImageURI(images!![numimg])
                     bitmapString.add(makeBitmapToImg(ImageSwitcher_add))
                 }
                 position = 0;
             }
             else{
                 val imageUri = data.data
                 ImageSwitcher_add.setImageURI(imageUri)
                 position = 0;
             }

        }else if(resultCode == Activity.RESULT_OK && requestCode == PICK_DISH_IMAGE_CODE){
            val imageUri = data?.data
            add_IMG_profile.setImageURI(imageUri)
            profileBitmap = makeBitmapToImg(add_IMG_profile)

        }

    }






    override fun onStart() {
        Log.d("ptt","AddRecipeActivity on Start")
        super.onStart()
    }

    override fun onStop() {
        Log.d("ptt","AddRecipeActivity on Stop")
        super.onStop()
    }

    override fun onRestart() {
        Log.d("ptt","AddRecipeActivity on Restart")
        super.onRestart()
    }

    override fun onResume() {
        Log.d("ptt","AddRecipeActivity on Resume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("ptt","AddRecipeActivity on Pause")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("ptt","AddRecipeActivity on Destroy")
        super.onDestroy()
    }

     override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
         txtView!!.text = "Selected : "+allfoodCategory[position]
         foodCategory = allfoodCategory.get(position)
     }

     override fun onNothingSelected(parent: AdapterView<*>?) {
         TODO("Not yet implemented")
     }


 }

private fun ImageView.setFactory(function: () -> ImageView) {

}





