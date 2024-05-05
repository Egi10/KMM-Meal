package id.buaja.kmm_meal.core.common

import kotlin.experimental.ExperimentalNativeApi

class IOSPlatform : Platform {
    @OptIn(ExperimentalNativeApi::class)
    override fun isDebugMode(): Boolean {
        return kotlin.native.Platform.isDebugBinary
    }
}