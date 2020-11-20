package com.javnez.marvel.domain.usecase

import com.google.common.truth.Truth
import com.javnez.core_data.core.Result
import com.javnez.marvel.core.UseCase.None
import com.javnez.core_data.model.character.Character
import com.javnez.core_data.repository.characters.CharactersRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCharactersUseCaseTest {

    private val mockCharacterRepository: CharactersRepository = mockk()

    private lateinit var useCaseUnderTest: GetCharactersUseCase

    @Before
    fun setUp() {
        useCaseUnderTest = GetCharactersUseCase(mockCharacterRepository)
    }

    @Test
    fun `WHEN invoke use case THEN return repository data`() = runBlockingTest {

        val mockRepositoryResult: Result<List<Character>> = mockk()
        coEvery { mockCharacterRepository.getCharacters() } answers { mockRepositoryResult }

        val actualResult = useCaseUnderTest.run(None())

        coVerify(exactly = 1) { mockCharacterRepository.getCharacters() }
        Truth.assertThat(actualResult).isEqualTo(mockRepositoryResult)
    }
}