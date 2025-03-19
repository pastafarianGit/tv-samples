/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.jetstream.presentation.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.Text
import com.google.jetstream.data.entities.Movie
import com.google.jetstream.data.entities.MovieList
import com.google.jetstream.data.util.StringConstants
import com.google.jetstream.presentation.common.Error
import com.google.jetstream.presentation.common.Loading
import com.google.jetstream.presentation.common.MoviesRow
import com.google.jetstream.presentation.screens.dashboard.rememberChildPadding
import com.voyo.common.domain.models.Payload
import com.voyo.common.domain.models.frontdata.FrontDataType
import com.voyo.common.ui.utils.UIState
import com.voyo.common.ui.viewmodels.SectionViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

@Composable
fun HomeScreen(
    onMovieClick: (movie: Movie) -> Unit,
    goToVideoPlayer: (movie: Movie) -> Unit,
    onScroll: (isTopBarVisible: Boolean) -> Unit,
    isTopBarVisible: Boolean,
    sectionViewModel: SectionViewModel = koinViewModel(qualifier = named("BrowseSection")),
    homeScreeViewModel: HomeScreeViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = sectionViewModel) {
        sectionViewModel.fetchBrowseFront("/")
    }
    val homeUiState by sectionViewModel.sectionUiState.collectAsStateWithLifecycle()

    println("hello homescreen")
    val uiState by homeScreeViewModel.uiState.collectAsStateWithLifecycle()

    /*when (val s = uiState) {
        is HomeScreenUiState.Ready -> {
            Catalog(
                featuredMovies = s.featuredMovieList,
                trendingMovies = s.trendingMovieList,
                top10Movies = s.top10MovieList,
                nowPlayingMovies = s.nowPlayingMovieList,
                onMovieClick = onMovieClick,
                onScroll = onScroll,
                goToVideoPlayer = goToVideoPlayer,
                isTopBarVisible = isTopBarVisible,
                modifier = Modifier.fillMaxSize(),
            )
        }

        is HomeScreenUiState.Loading -> Loading(modifier = Modifier.fillMaxSize())
        is HomeScreenUiState.Error -> Error(modifier = Modifier.fillMaxSize())
    }*/

    when (val s = homeUiState) {
        is UIState.Success -> {
            FrontContent(s.data, uiState, onMovieClick, goToVideoPlayer, onScroll, isTopBarVisible)
            print("home sucess state $homeUiState")
        }

        is UIState.Error -> {
            print("home Error state $homeUiState")
            Text("fail" + homeUiState)
        }
        is UIState.Loading -> {
            print("home Loading state $homeUiState")
            Text("load" + homeUiState)
        }
    }
}

@Composable
fun FrontContent(data: List<FrontDataType>, uiState: HomeScreenUiState,
                 onMovieClick: (movie: Movie) -> Unit,
                 goToVideoPlayer: (movie: Movie) -> Unit,
                 onScroll: (isTopBarVisible: Boolean) -> Unit,
                 isTopBarVisible: Boolean,) {
    val convertedMovieList = getConvertedMovieList(data)
    when (val s = uiState) {
        is HomeScreenUiState.Ready -> {
            val jetStreamRows = listOf(s.featuredMovieList, s.trendingMovieList, s.top10MovieList, s.nowPlayingMovieList, s.featuredMovieList, s.trendingMovieList, s.top10MovieList, s.nowPlayingMovieList, s.featuredMovieList, s.trendingMovieList, s.top10MovieList, s.nowPlayingMovieList, s.featuredMovieList, s.trendingMovieList, s.top10MovieList, s.nowPlayingMovieList,s.featuredMovieList, s.trendingMovieList, s.top10MovieList, s.nowPlayingMovieList
            ,s.featuredMovieList, s.trendingMovieList, s.top10MovieList, s.nowPlayingMovieList, s.featuredMovieList, s.trendingMovieList, s.top10MovieList, s.nowPlayingMovieList, s.featuredMovieList, s.trendingMovieList, s.top10MovieList, s.nowPlayingMovieList, s.featuredMovieList, s.trendingMovieList, s.top10MovieList, s.nowPlayingMovieList,s.featuredMovieList, s.trendingMovieList, s.top10MovieList, s.nowPlayingMovieList,
                s.featuredMovieList, s.trendingMovieList, s.top10MovieList, s.nowPlayingMovieList, s.featuredMovieList, s.trendingMovieList, s.top10MovieList, s.nowPlayingMovieList, s.featuredMovieList, s.trendingMovieList, s.top10MovieList, s.nowPlayingMovieList, s.featuredMovieList, s.trendingMovieList, s.top10MovieList, s.nowPlayingMovieList,s.featuredMovieList, s.trendingMovieList, s.top10MovieList, s.nowPlayingMovieList
            )
            Catalog(
                featuredMovies = s.featuredMovieList,
                trendingMovies = s.trendingMovieList,
                top10Movies = s.top10MovieList,
                nowPlayingMovies = s.nowPlayingMovieList,
                onMovieClick = onMovieClick,
                onScroll = onScroll,
                goToVideoPlayer = goToVideoPlayer,
                isTopBarVisible = isTopBarVisible,
                modifier = Modifier.fillMaxSize(),
                data = jetStreamRows,
                convertedMovieList = convertedMovieList
            )
        }

        is HomeScreenUiState.Loading -> Loading(modifier = Modifier.fillMaxSize())
        is HomeScreenUiState.Error -> Error(modifier = Modifier.fillMaxSize())
    }
}

fun getConvertedMovieList(data: List<FrontDataType>):  List<MovieList> {
    val newList =  ArrayList<MovieList>()

    for (row in data) {
        if(row.payload != null){
            newList.add(row.payload!!.map { toMovie(it) })
        }
    }
    return newList
}

fun toMovie(payload: Payload): Movie {
    return Movie(payload.UUID, "", "", payload.portraitImage()?:"", payload.title() ?: "title", "" )
}


@Composable
private fun Catalog(
    featuredMovies: MovieList,
    trendingMovies: MovieList,
    top10Movies: MovieList,
    nowPlayingMovies: MovieList,
    onMovieClick: (movie: Movie) -> Unit,
    onScroll: (isTopBarVisible: Boolean) -> Unit,
    goToVideoPlayer: (movie: Movie) -> Unit,
    modifier: Modifier = Modifier,
    isTopBarVisible: Boolean = true,
    data: List<MovieList>,
    convertedMovieList: List<MovieList>,
) {
    val frontRows = listOf(featuredMovies, trendingMovies, top10Movies, nowPlayingMovies)

    val lazyListState = rememberLazyListState()
    val childPadding = rememberChildPadding()
    var immersiveListHasFocus by remember { mutableStateOf(false) }

    val shouldShowTopBar by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex == 0 &&
                lazyListState.firstVisibleItemScrollOffset < 300
        }
    }

    LaunchedEffect(shouldShowTopBar) {
        onScroll(shouldShowTopBar)
    }
    LaunchedEffect(isTopBarVisible) {
        if (isTopBarVisible) lazyListState.animateScrollToItem(0)
    }

    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(bottom = 108.dp),
        // Setting overscan margin to bottom to ensure the last row's visibility
        modifier = modifier,
    ) {
        itemsIndexed(data) { index, movieList ->
            //Text(text = "Title $index ${frontDataType.dataId}")
            MoviesRow(
                modifier = Modifier.padding(top = 16.dp),
                movieList = movieList,
                title = "$index ${StringConstants.Composable.HomeScreenNowPlayingMoviesTitle}",
                onMovieSelected = onMovieClick,
                convertedMovieList = convertedMovieList
            )
        }

        item(contentType = "FeaturedMoviesCarousel") {
            FeaturedMoviesCarousel(
                movies = featuredMovies,
                padding = childPadding,
                goToVideoPlayer = goToVideoPlayer,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(324.dp)
                /*
                 Setting height for the FeaturedMovieCarousel to keep it rendered with same height,
                 regardless of the top bar's visibility
                 */
            )
        }
        item(contentType = "MoviesRow") {
            MoviesRow(
                modifier = Modifier.padding(top = 16.dp),
                movieList = trendingMovies,
                title = StringConstants.Composable.HomeScreenTrendingTitle,
                onMovieSelected = onMovieClick
            )
        }
        item(contentType = "Top10MoviesList") {
            Top10MoviesList(
                movieList = top10Movies,
                onMovieClick = onMovieClick,
                modifier = Modifier.onFocusChanged {
                    immersiveListHasFocus = it.hasFocus
                },
            )
        }
        item(contentType = "MoviesRow") {
            MoviesRow(
                modifier = Modifier.padding(top = 16.dp),
                movieList = nowPlayingMovies,
                title = StringConstants.Composable.HomeScreenNowPlayingMoviesTitle,
                onMovieSelected = onMovieClick
            )
        }
    }
}
