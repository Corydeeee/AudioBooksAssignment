package com.example.audiobookscomapplication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.audiobookscomapplication.model.Podcast
import com.example.audiobookscomapplication.viewmodel.HomeViewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.navigation.NavController
import com.example.audiobookscomapplication.Screen


@Composable
fun HomepageView(modifier: Modifier, viewModel: HomeViewModel, navController: NavController) {
    val funTest = fun(id: String) {
        navController.navigate(route = Screen.Detail.route + "?id=${id}")
    }

    HomePageViewInternal(
        modifier = modifier,
        podCastData = viewModel.shownData,
        isLoading = viewModel.isLoading,
        totalCount = viewModel.totalCount,
        onNavClick = funTest,
        onNextPage = viewModel::nextPage,
    )
}

@Composable
fun HomePageViewInternal(
    modifier: Modifier = Modifier,
    podCastData: List<Podcast>?,
    isLoading: Boolean,
    totalCount: Int,
    onNavClick: (id: String) -> Unit,
    onNextPage: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp),
    ) {
        Text(
            text = "Podcasts",
            style = typography.titleLarge,
            fontWeight = FontWeight.ExtraBold
        )
        if (isLoading) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }

        } else {
            if (!podCastData.isNullOrEmpty()) {
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    podCastData.forEach { podcast ->
                        PodcastRow(podcast, onNavClick)
                        HorizontalDivider()
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Viewing ${podCastData.size} of $totalCount")
                            if (podCastData.size != totalCount) {
                                Button(onClick = onNextPage) {
                                    Text("Load More")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PodcastRow(podcast: Podcast, onNavClick: (id: String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .clickable {
                podcast.id?.let { onNavClick(it) }
            },
        verticalAlignment = Alignment.CenterVertically,

        ) {
        AsyncImage(
            modifier = Modifier
                .requiredSize(80.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primaryContainer),
            model = podcast.thumbnail,
            contentDescription = null
        )
        Column(
            modifier = Modifier.padding(start = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            podcast.title?.let {
                Text(text = it, style = typography.titleMedium)
            }
            podcast.publisher?.let {
                Text(text = it, color = Color.Gray, fontStyle = FontStyle.Italic)
            }
            Column(Modifier.requiredSize(16.dp)) { }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePageViewInternal(
        podCastData = listOf(Podcast.stub(), Podcast.stub(), Podcast.stub()),
        isLoading = false,
        totalCount = 20,
        onNavClick = {},
        onNextPage = {}
    )
}