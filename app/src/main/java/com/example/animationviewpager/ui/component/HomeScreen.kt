package com.example.animationviewpager.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animationviewpager.utils.CirclePath
import com.example.animationviewpager.utils.Instance
import com.example.animationviewpager.ui.theme.GrayDescription
import kotlin.math.absoluteValue

@Preview(showSystemUi = true)
@Composable
@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
fun HomeScreen() {
    val state = rememberPagerState()
    val (offsetY, setOffSetY) = remember { mutableStateOf(1f) }
    HorizontalPager(
        pageCount = Instance.locationModels.count(),
        modifier = Modifier
            .pointerInteropFilter {
                setOffSetY(it.y)
                false
            }
            .padding(16.dp)
            .clip(
                RoundedCornerShape(18.dp)
            ),
        state = state
    ) { page ->
        Box(modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                val pageOffset = state.offsetForPage(page)
                translationX = size.width * pageOffset

                val endOffset = state.endOffsetForPage(page)
                shadowElevation = 20f
                shape = CirclePath(
                    progress = 1f - endOffset.absoluteValue,
                    origin = Offset(
                        size.width,
                        offsetY
                    )
                )
                clip = true

                val abslouteOffset = state.offsetForPage(page).absoluteValue
                val scale = 1f + (abslouteOffset.absoluteValue * .3f)

                scaleX = scale
                scaleY = scale

                val startOffset = state.startOffsetForPage(page)
                alpha = (2f - startOffset) / 2
            }) {
            val locationModel = Instance.locationModels[page]
            Image(
                painter = painterResource(id = locationModel.image),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(26.dp)
            ) {
                Text(
                    text = locationModel.title,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold, color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = locationModel.description,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium, color = GrayDescription
                )
            }
        }
    }

}