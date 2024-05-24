package id.buaja.kmm_meal.di

import id.buaja.kmm_meal.domain.usecase.*
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetRemoteMealByAreaUseCase)
    factoryOf(::GetRemoteDetailMealByIdUseCase)
}