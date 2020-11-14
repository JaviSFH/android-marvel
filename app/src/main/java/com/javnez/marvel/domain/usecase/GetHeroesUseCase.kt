package com.javnez.marvel.domain.usecase

import com.javnez.marvel.core.Result
import com.javnez.marvel.core.UseCase
import com.javnez.marvel.core.UseCase.None
import com.javnez.marvel.data.model.character.Characters
import com.javnez.marvel.data.repositories.CharactersRepository
import javax.inject.Inject

class GetHeroesUseCase @Inject constructor(private val charactersRepository: CharactersRepository) : UseCase<List<Characters>, None>() {

    override suspend fun run(params: None): Result<List<Characters>> {
        return charactersRepository.getCharacters()
    }

}