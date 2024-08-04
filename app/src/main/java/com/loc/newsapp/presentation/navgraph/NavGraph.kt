package com.loc.newsapp.presentation.navgraph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.loc.newsapp.presentation.common.ArticlecardShimmerEffect
import com.loc.newsapp.presentation.common.home.HomeScreen
import com.loc.newsapp.presentation.common.home.HomeViewModel
import com.loc.newsapp.presentation.onboarding.OnBoardingScreen
import com.loc.newsapp.presentation.onboarding.OnBoardingViewModel
import com.loc.newsapp.presentation.search.SearchScreen
import com.loc.newsapp.presentation.search.SearchViewModel

@Composable
fun NavGraph(
   startDestination: String
)
{
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination)
    {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(route = Route.OnBoardingScreen.route){
                val viewModels : OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModels::onEvent
                )
            }
        }
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigator.route
        ){
            composable(route = Route.NewsNavigator.route){
                val viewModel = hiltViewModel<SearchViewModel>()
                SearchScreen(state = viewModel.state.value, event = viewModel::onEvent, navigate = {})
            }
        }
    }
}