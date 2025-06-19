package io.mohammedalaamorsi.restaurants.presentation.restaurant_details

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.mohammedalaamorsi.restaurants.data.models.AttributesXX
import io.mohammedalaamorsi.restaurants.data.models.Included
import io.mohammedalaamorsi.restaurants.data.models.RestaurantDetails
import io.mohammedalaamorsi.restaurants.presentation.states.RestaurantDetailsUiState
import io.mohammedalaamorsi.restaurants.presentation.states.effects.Effect
import io.mohammedalaamorsi.restaurants.presentation.ui.ErrorMessage
import io.mohammedalaamorsi.restaurants.presentation.ui.LoadingIndicator
import io.mohammedalaamorsi.resturants.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantsDetailsScreen(viewModel: RestaurantDetailsViewModel, onNavigateBack: () -> Unit) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            Column(
                modifier = Modifier
                    .zIndex(0f)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .wrapContentHeight()
                ) {
                    AppBarHeader(
                        restaurant = when (state) {
                            is RestaurantDetailsUiState.Result -> (state as RestaurantDetailsUiState.Result).data.data
                            else -> null
                        }, modifier = Modifier.fillMaxWidth()
                    )
                    TopAppBar(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(16.dp),
                        navigationIcon = {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                                    .clickable(
                                        onClick = onNavigateBack,
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = LocalIndication.current
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = Color.Black,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        },
                        title = {},
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent,
                            scrolledContainerColor = Color.Transparent
                        ),
                        windowInsets = WindowInsets(0),
                    )
                }
            }
        }, contentWindowInsets = WindowInsets(0), containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        val cornerRadius = 16.dp
        Box(
            modifier = Modifier
                .zIndex(1f)
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    val shadowColor = Color.Black.copy(alpha = 0.25f)
                    val paint = Paint().asFrameworkPaint().apply {
                        color = android.graphics.Color.TRANSPARENT
                        setShadowLayer(
                            4.dp.toPx(), 0f, (-1).dp.toPx(), shadowColor.toArgb()
                        )
                    }

                    drawIntoCanvas {
                        it.nativeCanvas.drawRoundRect(
                            0f,
                            0f,
                            size.width,
                            size.height / 2,
                            cornerRadius.toPx(),
                            cornerRadius.toPx(),
                            paint
                        )
                    }
                }
                .background(
                    color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(
                        topStart = cornerRadius, topEnd = cornerRadius
                    )
                )) {
                when (state) {
                    is RestaurantDetailsUiState.Loading -> {
                        LoadingIndicator()
                    }

                    is RestaurantDetailsUiState.Error -> {
                        ErrorMessage((state as RestaurantDetailsUiState.Error).message)
                    }

                    is RestaurantDetailsUiState.Result -> {
                        (state as RestaurantDetailsUiState.Result).data.let { data ->
                                RestaurantDetailsLayout(
                                    includedList = data.included, restaurant = data.data
                                )
                        }
                    }
                }

            }
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
fun AppBarHeader(
    modifier: Modifier = Modifier, restaurant: RestaurantDetails? = null
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(restaurant?.attributes?.imageUrl)
            .error(R.drawable.placeholder_restaurant).placeholder(R.drawable.placeholder_restaurant)
            .crossfade(true).build(),
        contentDescription = restaurant?.attributes?.name,
        modifier = modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetailsLayout(
    includedList: List<Included>?,
    restaurant: RestaurantDetails?,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            RestaurantInfoSection(restaurant = restaurant)
        }

        item {
            RestaurantMenuSection(
                startIcon = Icons.Default.LocationOn,
                title = "Address: ",
                description = restaurant?.attributes?.addressLine1 ?: "Radisson Blue Hotel, Business Bay",
                Icons.AutoMirrored.Filled.ArrowForward
            )

        }

        item {
            NotesFromRestaurantSection(restaurant?.attributes?.customConfirmationComments)
        }


        item {
            RestaurantFeaturesSection(labels = restaurant?.attributes?.labels)
        }

        item {
            OtherVenuesSection(includedList = includedList, cuisine = restaurant?.attributes?.cuisine)

        }
    }
}


@Composable
fun RestaurantInfoSection(restaurant: RestaurantDetails?) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                fontWeight = FontWeight.Bold,
                text = restaurant?.attributes?.name ?: "Restaurant",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
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
                Text(
                    text = restaurant?.attributes?.ratingsAverage ?: "4.5",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )

            }
        }
    }

    Text(
        text = restaurant?.attributes?.description ?: "A fine dining experience with exquisite Italian cuisine.",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        lineHeight = 20.sp
    )

    Spacer(modifier = Modifier.height(12.dp))

    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
        ) {
            Text(
                text = "$",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            repeat(2) {
                Text(
                    text = "$",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
        ) {
            Text(
                text = restaurant?.attributes?.cuisine ?: "Italian Fine Dining",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = restaurant?.attributes?.addressLine1 ?: "Radisson Blue Hotel, Business Bay",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}


@Composable
fun RestaurantMenuSection(
    startIcon: ImageVector, title: String, description: String, endIcon: ImageVector
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = startIcon,
                contentDescription = "View menu",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Column(
                modifier = Modifier.wrapContentSize(), verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )

            }
        }
        Icon(
            imageVector = endIcon,
            contentDescription = "View menu",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )

    }
}

@Composable
fun NotesFromRestaurantSection(note: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .background(color = Color.Cyan.copy(alpha = 0.3f), shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
    ) {
        Text(
            text = "Notes from the restaurant",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = note ?: "Confirmation comments not available.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun RestaurantFeaturesSection(labels: List<String>?) {
    val currentLabels = labels ?: listOf(
        "label1 very long",
        "label2",
        "label3",
        "label4 very long",
        "label5",
        "label6",
        "label7",
        "label8",
        "label9",
        "label10"
    )
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        currentLabels.forEach { text ->
            Text(
                text = text, modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(12.dp), style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}

@Composable
fun OtherVenuesSection(includedList: List<Included>?, cuisine: String?) {
    val currentIncludedList = includedList ?: listOf(
        Included(
            attributes = AttributesXX(
                imageUrl = "https://example.com/image1.jpg",
            )
        ), Included(
            attributes = AttributesXX(
                imageUrl = "https://example.com/image1.jpg",
            )
        ), Included(
            attributes = AttributesXX(
                imageUrl = "https://example.com/image1.jpg",
            )
        ), Included(
            attributes = AttributesXX(
                imageUrl = "https://example.com/image1.jpg",
            )
        )
    )
    Column {
        Text(text = "Other venues${cuisine?.let { " with $it" } ?: ""}",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(currentIncludedList) { item ->
                VenueCard(
                    imageUrl = item.attributes?.imageUrl
                )

            }

        }

    }
}

@Composable
fun VenueCard(
    imageUrl: String?,
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(120.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
                .error(R.drawable.placeholder_restaurant)
                .placeholder(R.drawable.placeholder_restaurant).crossfade(true).build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
