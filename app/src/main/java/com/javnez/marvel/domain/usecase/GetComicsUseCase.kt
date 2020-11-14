package com.javnez.marvel.domain.usecase

import com.javnez.marvel.core.Result
import com.javnez.marvel.core.UseCase
import com.javnez.marvel.data.model.comic.Comic
import com.javnez.marvel.data.repositorie.comics.ComicsRepository
import com.javnez.marvel.domain.usecase.GetComicsUseCase.Params
import javax.inject.Inject

class GetComicsUseCase @Inject constructor(private val comicsRepository: ComicsRepository) :
    UseCase<List<Comic>, Params>() {

    override suspend fun run(params: Params): Result<List<Comic>> {
        return comicsRepository.getComics(params.characterId)
    }

    class Params(val characterId: Int)
}