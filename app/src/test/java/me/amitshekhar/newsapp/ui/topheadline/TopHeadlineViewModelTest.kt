package me.amitshekhar.newsapp.ui.topheadline

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import me.amitshekhar.newsapp.data.model.Article
import me.amitshekhar.newsapp.data.repository.TopHeadlineRepository
import me.amitshekhar.newsapp.ui.base.UiState
import me.amitshekhar.newsapp.utils.AppConstant
import me.amitshekhar.newsapp.utils.DispatcherProvider
import me.amitshekhar.newsapp.utils.TestDispatcherProvider
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlineViewModelTest {

    @Mock
    private lateinit var topHeadlineRepository: TopHeadlineRepository

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun fetchNews_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {
            Mockito.doReturn(flowOf(emptyList<Article>()))
                .`when`(topHeadlineRepository)
                .getTopHeadlines(AppConstant.COUNTRY)

            val viewModel = TopHeadlineViewModel(topHeadlineRepository, dispatcherProvider)
            viewModel.fetchNews(AppConstant.COUNTRY)

            viewModel.uiState.test {
                Assert.assertEquals(UiState.Success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            Mockito.verify(topHeadlineRepository, Mockito.times(1))
                .getTopHeadlines(AppConstant.COUNTRY)
        }
    }

    @Test
    fun fetchNews_whenRepositoryResponseError_shouldSetErrorUiState() {
        runTest {
            val errorMessage = "Error Message For You"
            Mockito.doReturn(flow<List<Article>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(topHeadlineRepository)
                .getTopHeadlines(AppConstant.COUNTRY)

            val viewModel = TopHeadlineViewModel(topHeadlineRepository, dispatcherProvider)
            viewModel.fetchNews(AppConstant.COUNTRY)

            viewModel.uiState.test {
                Assert.assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            Mockito.verify(topHeadlineRepository, Mockito.times(1))
                .getTopHeadlines(AppConstant.COUNTRY)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }
}
