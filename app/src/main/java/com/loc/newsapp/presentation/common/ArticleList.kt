package com.loc.newsapp.presentation.common
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.Dimens

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val listState = rememberLazyListState()
    val handlePagingResult = handlePagingResult(articles = articles)


    // Calculate the scroll offset and animate items accordingly
    val scrollOffset by remember {
        derivedStateOf {
            listState.firstVisibleItemScrollOffset
        }
    }

    if (handlePagingResult) {
        Box(modifier = modifier.fillMaxSize()) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 24.dp)
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(Dimens.mediumPadding1),
                contentPadding = PaddingValues(all = 6.dp)
            ) {
                Log.d("size", "ArticleList: ${articles.itemCount}")
                items(count = articles.itemCount) { index ->
                    articles[index]?.let {
                        // Animate alpha based on scroll offset
                        val offsetRatio = scrollOffset / 1000f
                        val alpha by animateFloatAsState(
                            targetValue = 1f - offsetRatio,
                            animationSpec = tween(durationMillis = 300)
                        )
                        ArticleCard(
                            modifier = Modifier.graphicsLayer(alpha = alpha),
                            article = it,
                            onClick = { onClick(it) }
                        )
                    }
                }
            }


        }
    }
}

@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>
): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            if (error.error.message == "No more data") {
                true // End of list, no need to show error
            } else {
                EmptyScreen(error)
                false
            }
        }

        else -> {
            true
        }
    }
}


@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.mediumPadding1)) {
        repeat(10) {
            ArticlecardShimmerEffect(
                modifier = Modifier.padding(horizontal = Dimens.mediumPadding1)
            )
        }
    }
}
