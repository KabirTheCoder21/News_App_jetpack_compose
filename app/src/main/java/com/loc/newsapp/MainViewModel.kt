package com.loc.newsapp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.loc.newsapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel(){
     var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set
    init {
        viewModelScope.launch{
            appEntryUseCases.readAppEntry().onEach{ shouldStartFromHS ->
                if (shouldStartFromHS) {
                    startDestination = Route.NewsNavigation.route
                } else {
                    startDestination = Route.AppStartNavigation.route
                }
                delay(300)
                splashCondition = false

            }.launchIn(viewModelScope)
        }
    }
}