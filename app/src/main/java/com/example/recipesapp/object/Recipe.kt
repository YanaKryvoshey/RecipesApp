package com.example.recipesapp



data class Recipe(val profilePic: String, val imageUriList: ArrayList<String>,val name: String,val ingredients: String,val foodCategory: FoodCategory) {

    override fun toString() = "$name" + "$imageUriList"

    var favorite :Boolean = false


}
/*
enum class Ingredients {
    OLIVE_OIL, FLOUR,BUTTER,CHICKEN,SUGAR,SALT,EGG,RICE,VEGETABLE_OIL,PORK,BEEF,CHEESE,GARLIC,ORANGE,TURKEY,ONION,CORN,
    MILK,MAYONNAISE,CHILS,ALMOND,BACON,MUSHROOMS,COCONUT,BEETS,STRAWBERRIES,FENNEL,LAMB,APPLE,AHRIMP

}
*/
enum class FoodCategory {
    SALAD, MEAT,VEGETARIAN,VEGAN,SOUP,PIES, DESSERT
}