package io.mohammedalaamorsi.restaurants.presentation.restaurants_list

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.mohammedalaamorsi.restaurants.data.models.Attributes
import io.mohammedalaamorsi.restaurants.data.models.Data
import io.mohammedalaamorsi.restaurants.data.models.DataType
import io.mohammedalaamorsi.restaurants.data.models.Experiences
import io.mohammedalaamorsi.restaurants.data.models.OnlineSeatingShifts
import io.mohammedalaamorsi.restaurants.data.models.PromotionalGroups
import io.mohammedalaamorsi.restaurants.data.models.Rating
import io.mohammedalaamorsi.restaurants.data.models.Region
import io.mohammedalaamorsi.restaurants.data.models.Relationships
import io.mohammedalaamorsi.restaurants.presentation.states.RestaurantsUiState
import io.mohammedalaamorsi.restaurants.presentation.states.effects.Effect
import io.mohammedalaamorsi.restaurants.presentation.ui.ErrorMessage
import io.mohammedalaamorsi.restaurants.presentation.ui.LoadingIndicator
import io.mohammedalaamorsi.resturants.R
import org.koin.androidx.compose.koinViewModel


@Composable
fun RestaurantsListScreen(
    viewModel: RestaurantsListViewModel = koinViewModel(),
    onRestaurantClicked: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            Column {
                // App title
                Text(
                    text = "Restaurants",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    ) { paddingValues ->
        RestaurantsList(state, paddingValues) { restaurantId ->
            onRestaurantClicked(restaurantId)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.effects.collect {
            when (it) {
                is Effect.ShowSnackbarResource -> {
                    snackbarHostState.showSnackbar(
                        message = it.messageRes,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }
}

@Composable
fun RestaurantsList(
    state: RestaurantsUiState,
    paddingValues: PaddingValues,
    onRestaurantClicked: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        when (state) {
            is RestaurantsUiState.Error -> {
                ErrorMessage(state.errorMessage)
            }

            is RestaurantsUiState.Loading -> {
                LoadingIndicator()
            }

            is RestaurantsUiState.Result -> {
                RestaurantsList(
                    restaurants = state.data,
                    onRestaurantClicked = onRestaurantClicked
                )
            }

            RestaurantsUiState.Empty -> {
                Text(
                    text = stringResource(R.string.no_restaurants_found),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(Alignment.CenterVertically)
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun RestaurantsList(
    restaurants: List<Data>,
    onRestaurantClicked: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(restaurants) { restaurant ->
            RestaurantCard(
                restaurant = restaurant,
                onRestaurantClicked = {  onRestaurantClicked(it)  }
            )
        }
    }
}

@Composable
fun RestaurantCard(
    restaurant: Data,
    onRestaurantClicked: (id: String) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        restaurant.attributes?.name?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // Restaurant Name and Rating
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {

            // Address
            restaurant.attributes?.addressLine1?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFA726),
                    modifier = Modifier.size(16.dp)
                )
                restaurant.attributes?.ratingsAverage?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable(
                    indication = LocalIndication.current ,
                    interactionSource = remember { MutableInteractionSource() }
                ) { restaurant.id?.let { onRestaurantClicked(it) } },
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Box(modifier = Modifier.wrapContentSize().background(Color.Transparent, shape = RoundedCornerShape(12.dp))) {
                // Restaurant Image
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(restaurant.attributes?.imageUrl)
                        .error(R.drawable.placeholder_restaurant)
                        .placeholder(R.drawable.placeholder_restaurant )
                        .crossfade(true)
                        .build(),
                    contentDescription = restaurant.attributes?.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                // Restaurant Details
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {

                    // Price Level and Cuisine Type
                    Column (
                        modifier = Modifier.wrapContentHeight(),
                    ) {
                        // Price Level Indicators
                        Row(
                            modifier = Modifier
                                .wrapContentSize()
                                .background(color = White, shape = RoundedCornerShape(16.dp))
                                .padding(4.dp),
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            repeat(3) { index ->
                                Text(
                                    text = "$",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Gray,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        restaurant.attributes?.cuisine?.let {
                            Text(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .background(color = White, shape = RoundedCornerShape(16.dp))
                                    .padding(4.dp),
                                text = it,
                                style = MaterialTheme.typography.bodySmall,
                                color = Gray,
                                fontWeight = FontWeight.Medium,
                            )
                        }

                    }
                }
            }
        }
    }
}


@Composable
@PreviewLightDark
fun RestaurantCardPreview() {
    RestaurantsList(
        restaurants = listOf(
            Data(
                id = "1",
                type = "restaurant",
                relationships = Relationships(
                    rating = Rating(
                        data = DataType(
                            id = "1",
                            type = "rating"
                        )
                    ),
                    region = Region(data = DataType(id = "1", type = "region")),

                    experiences = Experiences(
                        data = listOf("experience", "experience")
                    ),
                    onlineSeatingShifts = OnlineSeatingShifts(
                        data = listOf(
                            DataType(id = "1", type = "online_seating_shift"),
                            DataType(id = "2", type = "online_seating_shift")
                        )
                    ),
                    promotionalGroups = PromotionalGroups(
                        data = listOf(
                            DataType(id = "1", type = "promotional_group"),
                            DataType(id = "2", type = "promotional_group")
                        )
                    )
                ),
                attributes = Attributes(
                    name = "Gordon Ramsay's Bread Street Kitchen & Bar",
                    priceLevel = 2,
                    phone = "+97144262626",
                    menuUrl = "https://www.atlantis.com/dubai/restaurants/gordon-ramsay-bread-street-kitchen-and-bar",
                    requireBookingPreferenceEnabled = false,
                    difficult = false,
                    cuisine = "European",
                    imageUrl = "https://ucarecdn.com/ce9354d8-fa87-4c09-b850-955b4244dfc4/",
                    latitude = 25.131603,
                    longitude = 55.118518,
                    addressLine1 = "Atlantis The Palm, Dubai, Crescent Road ",
                    ratingsAverage = "4.0",
                    ratingsCount = 1499,
                    labels = listOf(
                        "Smart Casual",
                        "Good for Groups",
                        "Good for Families",
                        "Live Music",
                        "Good for Lunch",
                        "Good for Brunch",
                        "Good for Dinner",
                        "Accepts Credit Cards",
                        "Accepts Cash"
                    )
                )
            ),
            Data(
                id = "1",
                type = "restaurant",
                relationships = Relationships(
                    rating = Rating(
                        data = DataType(
                            id = "1",
                            type = "rating"
                        )
                    ),
                    region = Region(data = DataType(id = "1", type = "region")),

                    experiences = Experiences(
                        data = listOf("experience", "experience")
                    ),
                    onlineSeatingShifts = OnlineSeatingShifts(
                        data = listOf(
                            DataType(id = "1", type = "online_seating_shift"),
                            DataType(id = "2", type = "online_seating_shift")
                        )
                    ),
                    promotionalGroups = PromotionalGroups(
                        data = listOf(
                            DataType(id = "1", type = "promotional_group"),
                            DataType(id = "2", type = "promotional_group")
                        )
                    )
                ),
                attributes = Attributes(
                    name = "Gordon Ramsay's Bread Street Kitchen & Bar",
                    priceLevel = 2,
                    phone = "+97144262626",
                    menuUrl = "https://www.atlantis.com/dubai/restaurants/gordon-ramsay-bread-street-kitchen-and-bar",
                    requireBookingPreferenceEnabled = false,
                    difficult = false,
                    cuisine = "European",
                    imageUrl = "https://ucarecdn.com/ce9354d8-fa87-4c09-b850-955b4244dfc4/",
                    latitude = 25.131603,
                    longitude = 55.118518,
                    addressLine1 = "Atlantis The Palm, Dubai, Crescent Road ",
                    ratingsAverage = "4.0",
                    ratingsCount = 1499,
                    labels = listOf(
                        "Smart Casual",
                        "Good for Groups",
                        "Good for Families",
                        "Live Music",
                        "Good for Lunch",
                        "Good for Brunch",
                        "Good for Dinner",
                        "Accepts Credit Cards",
                        "Accepts Cash"
                    )
                )
            )
        ),
    ) { }


}
