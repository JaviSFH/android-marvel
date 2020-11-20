package com.javnez.feature_characters.core

import com.javnez.core_data.core.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Base use case and centralized point of the architecture to change thread.
 */
abstract class UseCase<out Data, in Params> where Data : Any {

    abstract suspend fun run(params: Params): Result<Data>

    operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Result<Data>) -> Unit = {}
    ) {
        val job = scope.async(Dispatchers.IO) { run(params) }
        scope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

    class None
}
