package com.javnez.marvel.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.javnez.marvel.core.Result.Success
import com.javnez.marvel.domain.usecase.GetComicsUseCase
import com.javnez.marvel.domain.usecase.GetComicsUseCase.Params
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    //private val testScope = TestCoroutineScope()

    companion object {
        private const val MOCK_CHARACTER_ID = 1
    }

    private val mockGetComicsUseCase: GetComicsUseCase = mockk()
    private val mockObserver: Observer<DetailsState> = spyk(Observer { })

    private lateinit var viewModelUnderTest: DetailsViewModel
    private val slotState = slot<DetailsState>()

    @Before
    fun setup() {
        viewModelUnderTest = DetailsViewModel(mockGetComicsUseCase)
        viewModelUnderTest.state.observeForever(mockObserver)
    }

    @Test
    fun `GIVEN empty characterId WHEN invoke loadComics THEN set error state`() {

        viewModelUnderTest.loadComics(null)

        verify { mockObserver.onChanged(capture(slotState)) }
        assertThat(slotState.captured is DetailsState.Error).isTrue()
    }

    @Test
    fun `GIVEN empty state WHEN invoke loadComics THEN invoke use case`() {

        coEvery { mockGetComicsUseCase.run(any()) } returns Success(mockk())

        //every { mockGetComicsUseCase(any(), any()) } returns
        //        Observable.just(Resource.Loading)

        runBlocking {viewModelUnderTest.loadComics(MOCK_CHARACTER_ID)}

        val slotUseCaseParam = slot<Params>()

        //verify { mockGetComicsUseCase(any(), capture(slotUseCaseParam)) }
        verify { mockObserver.onChanged(capture(slotState)) }
        assertThat(slotState.captured is DetailsState.Loading).isTrue()
        //assertThat(slotUseCaseParam.captured.characterId).isEqualTo(MOCK_CHARACTER_ID)

        //mockGetComicsUseCase(testScope, Params(1))
        //verify { fetchPopularTvShowUseCase.fetchMovies(any()) } //TODO Verify use case not invoked

    }

    @Test
    fun `GIVEN existing state WHEN invoke loadComics THEN invoke use case`() {

    }

    @Test
    fun `GIVEN use case error WHEN use case invoked THEN set error state`() {

    }

    @Test
    fun `GIVEN use case success WHEN use case invoked THEN set success state`() {

    }
}