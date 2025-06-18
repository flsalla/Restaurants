package io.mohammedalaamorsi.restaurants.data.remote


class RestaurantsUrlProvider : UrlsProvider {

    override fun fetchRestaurants(regionId: String): String {
        return "https://api.eat-sandbox.co/consumer/v2/restaurants?region_id=$regionId"
    }

    override fun fetchRestaurantDetails(id: String): String {
        return "https://api.eat-sandbox.co/consumer/v2/restaurants/$id"
    }

}
