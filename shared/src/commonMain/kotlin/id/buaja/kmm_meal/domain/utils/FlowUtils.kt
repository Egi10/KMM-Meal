package id.buaja.kmm_meal.domain.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

fun <T> executeWithResultFlow(
    context: CoroutineContext = Dispatchers.Default,
    block: suspend () -> T
): Flow<Result<T>> {
    return flow {
        try {
            val out = block.invoke()
            emit(Result.Success(out))
        } catch (e: Throwable) {
            emit(Result.Error(e))
        }
    }.onStart {
        emit(Result.Loading)
    }.catch {
        emit(Result.Error(it))
    }.flowOn(context)
}

fun <T> executeWithFlow(
    context: CoroutineContext = Dispatchers.Default,
    block: suspend () -> T
): Flow<T> {
    return flow {
        try {
            val out = block.invoke()
            emit(out)
        } catch (e: Throwable) {
            throw e
        }
    }.flowOn(context)
}
