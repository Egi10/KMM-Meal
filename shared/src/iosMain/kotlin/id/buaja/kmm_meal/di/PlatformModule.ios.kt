package id.buaja.kmm_meal.di

import id.buaja.kmm_meal.core.common.IOSPlatform
import id.buaja.kmm_meal.core.common.Platform
import id.buaja.kmm_meal.core.network.*
import org.koin.core.module.Module
import org.koin.core.module.dsl.*
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        singleOf(::IOSMealHttpClientEngine) {
            bind<MealHttpClientEngine>()
        }

        factoryOf(::IOSPlatform) {
            bind<Platform>()
        }
    }