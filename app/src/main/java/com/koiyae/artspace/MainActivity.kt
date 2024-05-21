package com.koiyae.artspace

import android.os.Bundle
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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.koiyae.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFD5C4A1)) {
                    Column {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            CenterAlignedTopAppBar(
                                modifier = Modifier.clip(shape = RoundedCornerShape(40.dp)),
                                title = {
                                    Text(
                                        text = "Gótico",
                                        style = TextStyle(
                                            color = Color.Black, fontSize = 30.sp
                                        ),
                                        fontWeight = FontWeight.Bold,
                                    )
                                },
                                colors = TopAppBarDefaults.largeTopAppBarColors(
                                    containerColor = Color(0xFFF9F5D7).copy(alpha = 0.5f)
                                )
                            )
                        }
                        MainScreen(modifier = Modifier.fillMaxSize())
                    }
                }
            }
        }
    }

    @Composable
    fun MainScreen(modifier: Modifier = Modifier) {
        val firstArtwork = R.drawable.capture
        val secondArtwork = R.drawable.crucifixion
        val thirdArtwork = R.drawable.calling

        var title by remember { mutableIntStateOf(R.string.capture) }
        var year by remember { mutableIntStateOf(R.string.capture_year) }
        var author by remember { mutableIntStateOf(R.string.capture_author) }
        var currentArtwork by remember { mutableIntStateOf(firstArtwork) }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color(0XFFD5C4A1)),
            verticalArrangement = Arrangement.Center,
        ) {
            ArtworkDisplay(currentArtwork = currentArtwork)
            ArtworkTitle(title = title, year = year, author = author)
            Spacer(modifier = Modifier.weight(1f))
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
                                author = R.string.calling_author
                            }

                            secondArtwork -> {
                                currentArtwork = firstArtwork
                                title = R.string.capture
                                year = R.string.capture_year
                                author = R.string.capture_author
                            }

                            thirdArtwork -> {
                                currentArtwork = secondArtwork
                                title = R.string.crucifixion
                                year = R.string.crucifixion_year
                                author = R.string.crucifixion_author
                            }
                        }
                    },
                    modifier = modifier
                        .weight(1f)
                        .wrapContentSize()
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(start = 10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD79921)),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 15.dp, pressedElevation = 1.dp, focusedElevation = 0.dp
                    )
                ) {
                    Text(
                        text = "Anterior", style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
                Button(
                    onClick = {
                        when (currentArtwork) {
                            firstArtwork -> {
                                currentArtwork = secondArtwork
                                title = R.string.crucifixion
                                year = R.string.crucifixion_year
                                author = R.string.crucifixion_author
                            }

                            secondArtwork -> {
                                currentArtwork = thirdArtwork
                                title = R.string.calling
                                year = R.string.calling_year
                                author = R.string.calling_author
                            }

                            thirdArtwork -> {
                                currentArtwork = firstArtwork
                                title = R.string.capture
                                year = R.string.capture_year
                                author = R.string.capture_author
                            }
                        }
                    },
                    modifier = modifier
                        .weight(1f)
                        .wrapContentSize()
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(end = 10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD79921)),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 15.dp, pressedElevation = 1.dp, focusedElevation = 0.dp
                    )
                ) {
                    Text(
                        text = "Próximo", style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }


    @Composable
    fun ArtworkDisplay(
        modifier: Modifier = Modifier, @DrawableRes currentArtwork: Int
    ) {
        Box(
            modifier = modifier
                .aspectRatio(0.8f)
                .width(600.dp)
                .padding(15.dp)
                .shadow(shape = RoundedCornerShape(30.dp), elevation = 20.dp)
        ) {
            Box(
                modifier = modifier.background(Color(0xFFEBDBB2)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color(0xFFEBDBB2)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(currentArtwork),
                        contentDescription = null,
                        modifier = modifier
                            .fillMaxWidth()
                            .aspectRatio(0.8f)
                            .width(600.dp)
                            .padding(20.dp)
                            .clip(RoundedCornerShape(15.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }

    @Composable
    fun ArtworkTitle(
        modifier: Modifier = Modifier,
        @StringRes title: Int,
        @StringRes year: Int,
        @StringRes author: Int
    ) {
        Box(
            modifier = modifier
                .padding(10.dp)
                .shadow(shape = RoundedCornerShape(15.dp), elevation = 20.dp)
        ) {
            Box(
                modifier = modifier
                    .background(color = Color(0xFFEBDBB2))
                    .padding(10.dp)
            ) {
                Column(
                    modifier = modifier.align(Alignment.CenterStart),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = stringResource(title),
                        style = TextStyle(fontSize = 25.sp),
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        modifier = modifier
                            .fillMaxWidth()
                            .align(alignment = Alignment.Start)
                    )
                    Row {
                        Text(
                            text = stringResource(author),
                            style = TextStyle(fontSize = 20.sp),
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = modifier
                        )
                        Text(
                            text = stringResource(year),
                            style = TextStyle(fontSize = 20.sp),
                            color = Color.Black,
                            modifier = modifier.padding(start = 5.dp)
                        )
                    }
                }
            }
        }


    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        ArtSpaceTheme {
            MainScreen()
        }
    }
}