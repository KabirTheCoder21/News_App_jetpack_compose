package com.loc.newsapp.presentation.onboarding.components

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.loc.newsapp.R
import com.loc.newsapp.presentation.Dimens.mediumPadding1
import com.loc.newsapp.presentation.Dimens.mediumPadding2
import com.loc.newsapp.presentation.onboarding.Page
import com.loc.newsapp.presentation.onboarding.pages
import com.loc.newsapp.ui.theme.NewsAppTheme


@Composable
fun OnBoardingPage(
    modifier : Modifier = Modifier,
    page : Page
)
{
    Column(modifier = modifier){
        Image(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f),
            painter = painterResource(id = page.image), contentDescription = null,
            contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.height(mediumPadding1))
        Text(text = page.title,
            modifier = Modifier.padding(mediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground)
        Text(text = page.content,
            modifier = Modifier.padding(mediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground)
    }
}
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun OnBoardingPagePreview()
{
    NewsAppTheme {
        OnBoardingPage(page = pages[0])
    }
}