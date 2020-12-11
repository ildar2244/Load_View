package com.example.load_view.core

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class UseCase<Type, in Params> {

    var backgroundContext: CoroutineContext = Dispatchers.IO
    var foregroundContext: CoroutineContext = Dispatchers.Main
    private var parentJob: Job = Job()

    abstract suspend fun run(params: Params): Response<Type>

    operator fun invoke(params: Params, onResult: (Response<Type>) -> Unit = {}) {
        unsubscribe()
        parentJob = Job()
        CoroutineScope(foregroundContext + parentJob).launch {
            val result = withContext(backgroundContext) {
                run(params)
            }
            onResult(result)
        }
    }

    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }
}