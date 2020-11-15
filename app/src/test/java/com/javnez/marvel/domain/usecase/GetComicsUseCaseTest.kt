package com.javnez.marvel.domain.usecase

import com.google.common.truth.Truth
import com.javnez.marvel.core.Result
import com.javnez.marvel.data.model.comic.Comic
import com.javnez.marvel.data.repository.comics.ComicsRepository
import com.javnez.marvel.domain.usecase.GetComicsUseCase.Params
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetComicsUseCaseTest {

    companion object {
        private const val MOCK_CHARACTER_ID = 1
    }

    private val mockComicRepository: ComicsRepository = mockk()

    private lateinit var useCaseUnderTest: GetComicsUseCase

    @Before
    fun setUp() {
        useCaseUnderTest = GetComicsUseCase(mockComicRepository)
    }

    @Test
    fun `WHEN invoke use case THEN return repository data`() = runBlockingTest {

        val mockRepositoryResult: Result<List<Comic>> = mockk()
        coEvery { mockComicRepository.getComics(any()) } answers { mockRepositoryResult }

        val actualResult = useCaseUnderTest.run(Params(MOCK_CHARACTER_ID))

        coVerify(exactly = 1) { mockComicRepository.getComics(eq(MOCK_CHARACTER_ID)) }
        Truth.assertThat(actualResult).isEqualTo(mockRepositoryResult)
    }
}


