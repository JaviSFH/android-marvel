package com.javnez.feature_characters.domain.usecase

import com.javnez.core_data.core.Result
import com.javnez.feature_characters.core.UseCase
import com.javnez.core_data.model.comic.Comic
import com.javnez.core_data.repository.comics.ComicsRepository
import com.javnez.feature_characters.domain.usecase.GetComicsUseCase.Params
import javax.inject.Inject

class GetComicsUseCase @Inject constructor(private val comicsRepository: ComicsRepository) :
    UseCase<List<Comic>, Params>() {

    override suspend fun run(params: Params): Result<List<Comic>> {
        return comicsRepository.getComics(params.characterId)
    }

    class Params(val characterId: Int)
}