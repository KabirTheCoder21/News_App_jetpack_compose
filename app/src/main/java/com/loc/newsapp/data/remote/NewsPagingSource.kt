package com.loc.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.newsapp.domain.model.Article

class NewsPagingSource(
    private val newsAPI : NewsAPI,
    private val sources : String
): PagingSource<Int, Article>() {
    private var totalCounts = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsAPI.getNews(sources = sources, page = page)
            totalCounts += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (totalCounts >= newsResponse.totalResults) null else page + 1 ,
                prevKey = null
            )
        }catch (e : Exception)
        {
          e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
      return state.anchorPosition?.let { anchorPosition->
          val anchorPage = state.closestPageToPosition(anchorPosition)
          anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
      }
    }

}