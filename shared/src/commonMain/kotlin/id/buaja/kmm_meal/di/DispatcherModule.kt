package id.buaja.kmm_meal.di

import kotlinx.coroutines.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DISPATCHER_MAIN = "dispatcher-main"
const val DISPATCHER_IO = "dispatcher-io"
const val DISPATCHER_DEFAULT = "dispatcher-default"
const val DISPATCHER_UNCONFINED = "dispatcher-unconfined"

val dispatcherModule = module {
    single(named(DISPATCHER_MAIN)) {
        Dispatchers.Main
    }
    single(named(DISPATCHER_IO)) {
        Dispatchers.IO
    }
    single(named(DISPATCHER_DEFAULT)) {
        Dispatchers.Default
    }
    single(named(DISPATCHER_UNCONFINED)) {
        Dispatchers.Unconfined
    }
}