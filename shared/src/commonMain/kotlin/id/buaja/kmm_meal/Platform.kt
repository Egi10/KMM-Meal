package id.buaja.kmm_meal

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform