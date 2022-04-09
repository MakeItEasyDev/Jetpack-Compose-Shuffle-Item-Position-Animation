package com.jetpack.itempositionanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jetpack.itempositionanimation.ui.theme.ItemPositionAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItemPositionAnimationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Shuffle Item Position",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }
                    ) {
                        ItemPositionAnimation()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemPositionAnimation() {
    var items by remember {
        mutableStateOf(
            listOf(
                "Jetpack Compose",
                "kotlin",
                "Java",
                "PHP",
                "Python",
                "C++"
            )
        )
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(15.dp))
        }

        items(
            items = items,
            key = { it }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 5.dp, horizontal = 15.dp)
                    .animateItemPlacement( //Shuffle animated item
                        animationSpec = tween(
                            durationMillis = 600
                        )
                    ),
                shape = RoundedCornerShape(10.dp),
                elevation = 10.dp
            ) {
                Text(
                    text = "I Love $it",
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray.copy(alpha = 0.2f))
                        .padding(20.dp)
                )
            }
        }

        item {
            Button(
                onClick = {
                    items = items.shuffled()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Shuffle",
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}























