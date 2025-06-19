package io.mohammedalaamorsi.restaurants.presentation.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.mohammedalaamorsi.resturants.R

@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    onFocusChanged: (Boolean) -> Unit = {},
    onSearch: (String) -> Unit,
    text: String,
    isEnabled: Boolean=false
) {
    var searchText by remember { mutableStateOf(text) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }


    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = if (isFocused) 6.dp else 2.dp,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)
                )
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(24.dp))
                .border(
                    width = if (isFocused) 1.dp else 0.dp,
                    color = if (isFocused) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                    else Color.Transparent,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)
                ),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            ) {
                CustomTextField(
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .focusTarget()
                        .height(50.dp)
                        .weight(1f)
                        .animateContentSize()
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                            onFocusChanged(focusState.isFocused)
                        },
                    keyboardController = keyboardController,
                    initialValue = searchText,
                    onSearch = { query -> onSearch(query) },
                    isEnabled = isEnabled,
                    placeholder = stringResource(R.string.search_restaurants),
                    onValueChange = {
                        searchText = it.text
                        onSearch(searchText)
                    },
                )
            }
        }
    }
}
