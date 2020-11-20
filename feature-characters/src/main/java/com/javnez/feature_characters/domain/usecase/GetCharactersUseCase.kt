package com.javnez.feature_characters.domain.usecase

import com.javnez.core_data.core.Result
import com.javnez.feature_characters.core.UseCase
import com.javnez.feature_characters.core.UseCase.None
import com.javnez.core_data.model.character.Character
import com.javnez.core_data.repository.characters.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) :
    UseCase<List<Character>, None>() {

    override suspend fun run(params: None): Result<List<Character>> {
        return charactersRepository.getCharacters()
    }
}