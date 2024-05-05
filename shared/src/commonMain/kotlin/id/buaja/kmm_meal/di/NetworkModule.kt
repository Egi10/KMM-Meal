package id.buaja.kmm_meal.di

import id.buaja.MealConfig
import id.buaja.kmm_meal.core.common.Platform
import id.buaja.kmm_meal.core.network.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val BASE_URL = "base_url"

val networkModule = module {
    single {
        setupJson()
    }

    single(named(BASE_URL)) {
        MealConfig.BASE_URL
    }

    single {
        setupHttpClient(
            json = get(),
            baseUrl = get(named(BASE_URL)),
            isDebugMode = get<Platform>().isDebugMode(),
            httpClientEngine = get()
        )
    }
}