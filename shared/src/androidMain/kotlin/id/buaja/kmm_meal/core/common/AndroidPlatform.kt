package id.buaja.kmm_meal.core.common

import id.buaja.kmm_meal.BuildConfig

class AndroidPlatform : Platform {
    override fun isDebugMode(): Boolean {
        return BuildConfig.DEBUG
    }
}