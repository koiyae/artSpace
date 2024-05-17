package com.koiyae.artspace

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.koiyae.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val firstArtwork = R.drawable.capture
    val secondArtwork = R.drawable.crucifixion
    val thirdArtwork = R.drawable.calling

    var title by remember { mutableStateOf(R.string.capture) }
    var year by remember { mutableStateOf(R.string.capture_year) }
    var currentArtwork by remember { mutableStateOf(firstArtwork) }
    var imageResource by remember { mutableStateOf(currentArtwork) }

    Column(
        modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(15.dp))
        ArtworkDisplay(currentArtwork = currentArtwork)
        ArtworkTitle(title = title, year = year)
        Row(
            modifier = modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = thirdArtwork
                            title = R.string.calling
                            year = R.string.calling_year
                        }

                        secondArtwork -> {
                            currentArtwork = firstArtwork
                            title = R.string.capture
                            year = R.string.capture_year
                        }

                        thirdArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.crucifixion
                            year = R.string.crucifixion_year
                        }
                    }
                },
                modifier = modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(text = "Previous")
            }
            Button(
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.crucifixion
                            year = R.string.crucifixion_year
                        }

                        secondArtwork -> {
                            currentArtwork = thirdArtwork
                            title = R.string.calling
                            year = R.string.calling_year
                        }

                        thirdArtwork -> {
                            currentArtwork = firstArtwork
                            title = R.string.capture
                            year = R.string.capture_year
                        }
                    }
                },
                modifier = modifier
                    .weight(1f)
                    .padding(end = 10.dp)
            ) {
                Text(text = "Next")
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun ArtworkDisplay(
    modifier: Modifier = Modifier, @DrawableRes currentArtwork: Int
) {
    Image(
        painter = painterResource(currentArtwork),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .padding(50.dp),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun ArtworkTitle(
    @StringRes title: Int, @StringRes year: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(title))
        Text(text = stringResource(year))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        Greeting()
    }
}