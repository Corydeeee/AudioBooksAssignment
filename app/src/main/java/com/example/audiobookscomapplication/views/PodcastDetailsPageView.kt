package com.example.audiobookscomapplication.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.audiobookscomapplication.model.Podcast
import com.example.audiobookscomapplication.viewmodels.PodcastDetailsPageViewModel

@Composable
fun PodcastDetailsPageView(modifier: Modifier, viewModel: PodcastDetailsPageViewModel) {
    PodcastDetailsPageViewInternal(modifier, viewModel.podcast, viewModel::onFavorite)
}

@Composable
fun PodcastDetailsPageViewInternal(
    modifier: Modifier, podcastData: Podcast?, onFavourite: () -> Unit
) {
    podcastData?.let { podcast ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            podcast.title?.let {
                Text(
                    modifier = Modifier.padding(top = 32.dp),
                    text = podcast.title,
                    style = typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            }
            podcast.publisher?.let {

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = podcast.publisher,
                    color = Color.Gray,
                    fontStyle = FontStyle.Italic
                )
            }
            podcast.image?.let {
                AsyncImage(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .requiredSize(300.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    model = podcast.image,
                    contentDescription = null
                )
            }
            Button(modifier = Modifier.padding(top = 16.dp), onClick = onFavourite) {
                Text(if (podcast.favorite) "Favourited" else "Favourite")
            }
            podcast.description?.let {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = podcast.description,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PodcastDetailsPageViewInternalPreview() {
    PodcastDetailsPageViewInternal(modifier = Modifier, Podcast.stub(), {})
}