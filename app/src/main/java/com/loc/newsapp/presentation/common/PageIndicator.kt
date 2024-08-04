package com.loc.newsapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loc.newsapp.ui.theme.BlueGray

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    SelectedPage : Int,
    selectedColor : Color = MaterialTheme.colorScheme.primary,
    unSelectedColor : Color = BlueGray
    ) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        repeat(pageSize) { page ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .let {
                        if (page == SelectedPage) {
                            it.wrapContentSize()
                        } else {
                            it.size(size = 16.dp)
                        }
                    }
                    .clip(CircleShape)
                    .background(color = if (page == SelectedPage) selectedColor else unSelectedColor)
            )
            {
                if (page == SelectedPage) {
                    Text(text = "${page + 1}/${pageSize}",
                        modifier = Modifier.padding(horizontal = 6.dp),
                        color = Color.White,
                        fontSize = 12.sp)
                }
            }
        }
    }
}
