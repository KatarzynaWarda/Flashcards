package com.example.myapplicationcompose.flashcards.screen.flashardsScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationcompose.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPager() {
    var pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {10}
    val scope = rememberCoroutineScope()

    HorizontalPager(state = pagerState) {
        page ->
        Column{
            Box(
                Modifier
                    .defaultMinSize(minHeight = 400.dp)
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.box), RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = page.toString(),
                    fontSize = 60.sp,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                IconButton(
                    onClick = { scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }},
                    modifier = Modifier
                        .size(80.dp)
                        .background(DarkGray,RoundedCornerShape(100.dp))
                        .border(BorderStroke(2.dp, colorResource(id = R.color.red)),RoundedCornerShape(100.dp)),
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
                    onClick = { scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }},
                    modifier = Modifier
                        .size(80.dp)
                        .background(DarkGray,RoundedCornerShape(100.dp))
                        .border(BorderStroke(2.dp, colorResource(id = R.color.green)),RoundedCornerShape(100.dp)),
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

@Preview
@Composable
fun Test() {
    HorizontalPager()
}
