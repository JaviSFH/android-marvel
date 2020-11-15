package com.javnez.marvel.domain.usecase

import com.javnez.marvel.core.Result
import com.javnez.marvel.core.UseCase
import com.javnez.marvel.core.UseCase.None
import com.javnez.marvel.data.model.character.Character
import com.javnez.marvel.data.repository.characters.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) :
    UseCase<List<Character>, None>() {

    override suspend fun run(params: None): Result<List<Character>> {
        return charactersRepository.getCharacters()
    }
}