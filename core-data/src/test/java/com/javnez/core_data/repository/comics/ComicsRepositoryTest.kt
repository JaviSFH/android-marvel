package com.javnez.core_data.repository.comics

import com.google.common.truth.Truth.assertThat
import com.javnez.core_data.core.Failure
import com.javnez.core_data.core.Result.Error
import com.javnez.core_data.core.Result.Success
import com.javnez.core_data.model.comic.Comic
import com.javnez.core_data.repository.comics.ComicsRepository
import com.javnez.core_data.repository.comics.datasources.LocalDataSource
import com.javnez.core_data.repository.comics.datasources.NetworkDataSource
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ComicsRepositoryTest {

    companion object {
        private const val MOCK_CHARACTER_ID = 1
    }

    private val mockNetworkDataSource: NetworkDataSource = mockk()
    private val mockLocalDataSource: LocalDataSource = mockk(relaxed = true)

    private lateinit var repositoryUnderTest: ComicsRepository

    @Before
    fun setUp() {
        repositoryUnderTest = ComicsRepository(mockNetworkDataSource, mockLocalDataSource)
    }

    @Test
    fun `GIVEN network repository success WHEN get comics invoked THEN store in local and return success`() =
        runBlockingTest {

            val mockComics: List<Comic> = mockk()
            coEvery { mockNetworkDataSource.getComics(any()) } answers { Success(mockComics) }

            val actualResult = repositoryUnderTest.getComics(MOCK_CHARACTER_ID)

            verify { mockLocalDataSource.storeComics(eq(mockComics)) }
            assertThat(actualResult is Success).isTrue()
            assertThat((actualResult as Success).data).isEqualTo(mockComics)
        }

    @Test
    fun `GIVEN network repository fails AND + local repository fails WHEN get comics invoked THEN return error`() {
        runBlockingTest {

            val mockNetworkFailure: Failure = mockk()
            coEvery { mockNetworkDataSource.getComics(any()) } answers { Error(mockNetworkFailure) }
            coEvery { mockLocalDataSource.getComics(any()) } answers { Error(mockk()) }

            val actualResult = repositoryUnderTest.getComics(MOCK_CHARACTER_ID)

            verify { mockLocalDataSource.getComics(eq(MOCK_CHARACTER_ID)) }
            assertThat(actualResult is Error).isTrue()
            assertThat((actualResult as Error).failure).isEqualTo(mockNetworkFailure)
        }
    }

    @Test
    fun `GIVEN network repository fails AND local repository success WHEN get comics invoked THEN return success`() =
        runBlockingTest {

            val mockNetworkFailure: Failure = mockk()
            val mockComics: List<Comic> = mockk()

            coEvery { mockNetworkDataSource.getComics(any()) } answers { Error(mockNetworkFailure) }
            coEvery { mockLocalDataSource.getComics(any()) } answers { Success(mockComics) }

            val actualResult = repositoryUnderTest.getComics(MOCK_CHARACTER_ID)

            verify { mockLocalDataSource.getComics(eq(MOCK_CHARACTER_ID)) }
            assertThat(actualResult is Success).isTrue()
            assertThat((actualResult as Success).data).isEqualTo(mockComics)
        }
}