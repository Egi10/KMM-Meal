package id.buaja.kmm_meal.di

import id.buaja.kmm_meal.screens.home.HomeScreenModel
import org.koin.dsl.module

val screenModelModule = module {
    factory {
        HomeScreenModel(get())
    }
}