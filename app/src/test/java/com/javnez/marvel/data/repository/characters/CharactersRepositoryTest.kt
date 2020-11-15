package com.javnez.marvel.data.repository.characters

import com.google.common.truth.Truth.assertThat
import com.javnez.marvel.core.Failure
import com.javnez.marvel.core.Result.Error
import com.javnez.marvel.core.Result.Success
import com.javnez.marvel.data.model.character.Character
import com.javnez.marvel.data.repository.characters.datasource.LocalDataSource
import com.javnez.marvel.data.repository.characters.datasource.NetworkDataSource
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersRepositoryTest {

    private val mockNetworkDataSource: NetworkDataSource = mockk()
    private val mockLocalDataSource: LocalDataSource = mockk(relaxed = true)

    private lateinit var repositoryUnderTest: CharactersRepository

    @Before
    fun setUp() {
        repositoryUnderTest = CharactersRepository(mockNetworkDataSource, mockLocalDataSource)
    }

    @Test
    fun `GIVEN network repository success WHEN get characters invoked THEN store in local and return success`() =
        runBlockingTest {

            val mockCharacters: List<Character> = mockk()
            coEvery { mockNetworkDataSource.getCharacters() } answers { Success(mockCharacters) }

            val actualResult = repositoryUnderTest.getCharacters()

            verify { mockLocalDataSource.storeCharacters(eq(mockCharacters)) }
            assertThat(actualResult is Success).isTrue()
            assertThat((actualResult as Success).data).isEqualTo(mockCharacters)
        }

    @Test
    fun `GIVEN network repository fails AND + local repository fails WHEN get characters invoked THEN return error`() {
        runBlockingTest {

            val mockNetworkFailure: Failure = mockk()
            coEvery { mockNetworkDataSource.getCharacters() } answers { Error(mockNetworkFailure) }
            coEvery { mockLocalDataSource.getCharacters() } answers { Error(mockk()) }

            val actualResult = repositoryUnderTest.getCharacters()

            verify { mockLocalDataSource.getCharacters() }
            assertThat(actualResult is Error).isTrue()
            assertThat((actualResult as Error).failure).isEqualTo(mockNetworkFailure)
        }
    }

    @Test
    fun `GIVEN network repository fails AND local repository success WHEN get characters invoked THEN return success`() =
        runBlockingTest {

            val mockNetworkFailure: Failure = mockk()
            val mockCharacter: List<Character> = mockk()

            coEvery { mockNetworkDataSource.getCharacters() } answers { Error(mockNetworkFailure) }
            coEvery { mockLocalDataSource.getCharacters() } answers { Success(mockCharacter) }

            val actualResult = repositoryUnderTest.getCharacters()

            verify { mockLocalDataSource.getCharacters() }
            assertThat(actualResult is Success).isTrue()
            assertThat((actualResult as Success).data).isEqualTo(mockCharacter)
        }
}