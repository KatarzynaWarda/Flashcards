package com.example.myapplicationcompose.flashcards.screen.flashardsScreen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationcompose.R
import com.example.myapplicationcompose.flashcards.viewModel.FlashcardsViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FlashCardsScreen(
    viewModel: FlashcardsViewModel,
    navigateToMainScreen: () -> Unit,
    glossaryId: Int,
) {
    var buttonEnabled by remember { mutableStateOf(true) }
    var isTermDisplayed by remember { mutableStateOf(true) }

    LaunchedEffect(glossaryId) {
        viewModel.loadEntriesForGlossary(glossaryId)
        Log.d("FlashcardsIdl",glossaryId.toString())

    }

    val entriesForGlossary by viewModel.entriesForGlossary.collectAsState()

    LaunchedEffect(entriesForGlossary) {
        entriesForGlossary.forEach { entry ->
            Log.d("FlashcardsEntries", "Term: ${entry.term}, Definition: ${entry.definition}, Glossary ID: ${entry.glossaryId}")
        }
    }

    val glossary = viewModel.getGlossaryById(glossaryId)

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { entriesForGlossary.size }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(top = 20.dp, start = 30.dp, end = 30.dp, bottom = 10.dp)
            .testTag("container")
    ) {
        Column {
            IconButton(
                onClick = {
                    navigateToMainScreen()
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icons8_close_60),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Black),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            glossary?.let {
                Text(
                    text = it.name,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = (viewModel.bad()).toString(),
                    fontSize = 40.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = (viewModel.summary()).toString() + " / " + entriesForGlossary.size.toString(),
                    fontSize = 40.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = (viewModel.good()).toString(),
                    fontSize = 40.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            val scope = rememberCoroutineScope()
            HorizontalPager(
                state = pagerState,
            ) { page ->
                SwipeCard(
                    onSwipe = { swipeLeft ->
                        scope.launch {
                            if (page < entriesForGlossary.size - 1) {
                                pagerState.animateScrollToPage(page + 1)
                                if (swipeLeft) {
                                    viewModel.incrementBad()
                                } else {
                                    viewModel.incrementGood()
                                }
                            }
                        }
                    },
                ) {
                    Column {
                        Box(
                            Modifier
                                .defaultMinSize(minHeight = 400.dp)
                                .fillMaxWidth()
                                .background(
                                    colorResource(id = R.color.box),
                                    RoundedCornerShape(20.dp)
                                )
                                .clickable {
                                    isTermDisplayed = !isTermDisplayed
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = if (isTermDisplayed) {
                                    entriesForGlossary.getOrNull(page)?.term ?: ""
                                } else {
                                    entriesForGlossary.getOrNull(page)?.definition ?: ""
                                },
                                fontSize = 60.sp,
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                IconButton(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            viewModel.incrementBad()
                        }
                        isTermDisplayed = true
                        if (pagerState.currentPage == entriesForGlossary.size - 1) {
                            buttonEnabled = false
                        }
                    },
                    enabled = buttonEnabled,
                    modifier = Modifier
                        .size(80.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icons8_close_60),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp),
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.red))
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            viewModel.incrementGood()
                        }
                        isTermDisplayed = true
                        if (pagerState.currentPage == entriesForGlossary.size - 1) {
                            buttonEnabled = false
                        }
                    },
                    enabled = buttonEnabled,
                    modifier = Modifier
                        .size(80.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icons8_done_52),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.green))
                    )
                }
            }
        }
    }
}
