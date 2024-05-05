package id.buaja.kmm_meal.di

import id.buaja.kmm_meal.data.remote.*
import id.buaja.kmm_meal.data.source.remote.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {
    single<MealRemoteDataSource> {
        MealRemoteDataSourceImpl(
            httpClient = get(),
            dispatcher = get(named(DISPATCHER_IO))
        )
    }
}