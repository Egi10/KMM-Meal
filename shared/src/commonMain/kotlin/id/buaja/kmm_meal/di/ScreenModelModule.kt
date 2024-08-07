package id.buaja.kmm_meal.di

import id.buaja.kmm_meal.screens.detailsmeal.DetailsMealViewModel
import id.buaja.kmm_meal.screens.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val screenModelModule = module {
    factory {
        DetailsMealViewModel(get())
    }
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailsMealViewModel)
}