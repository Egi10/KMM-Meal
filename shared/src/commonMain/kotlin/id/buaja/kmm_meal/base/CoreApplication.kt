package id.buaja.kmm_meal.base

import id.buaja.kmm_meal.di.*
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

object CoreApplication {
    fun initialize(appDeclaration: KoinAppDeclaration = {}) = startKoin {
        appDeclaration()
        modules(
            listOf(
                platformModule,
                networkModule
            )
        )
    }
}