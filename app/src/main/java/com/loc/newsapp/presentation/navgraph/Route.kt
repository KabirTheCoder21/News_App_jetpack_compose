package com.loc.newsapp.presentation.navgraph

sealed class Route(val route: String){
    object OnBoardingScreen : Route("onboarding")
    object HomeScreen : Route("homeScreen")
    object DetailScreen : Route("detailScreen")
    object SearchScreen : Route("searchScreen")
    object BookmarkScreen : Route("bookmarkScreen")
    object AppStartNavigation : Route("appStartNavigation")
    object NewsNavigation : Route("newsNavigation")
    object NewsNavigator : Route("newsNavigator")
}