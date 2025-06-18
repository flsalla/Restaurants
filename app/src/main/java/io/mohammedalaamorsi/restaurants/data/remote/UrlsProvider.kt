package io.mohammedalaamorsi.restaurants.data.remote


interface UrlsProvider {

    //("https://api.eat-sandbox.co/consumer/v2/restaurants?region_id=3906535a-d96c-47cf-99b0-009fc9e038e0")
    fun fetchRestaurants(regionId:String): String
    //("https://api.eat-sandbox.co/consumer/v2/restaurants/:id")
    fun fetchRestaurantDetails(id: String): String

}
