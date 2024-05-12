package id.buaja.kmm_meal.di

import id.buaja.kmm_meal.data.repository.MealRepositoryImpl
import id.buaja.kmm_meal.domain.repository.MealRepository
import org.koin.core.module.dsl.*
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::MealRepositoryImpl) {
        bind<MealRepository>()
    }
}