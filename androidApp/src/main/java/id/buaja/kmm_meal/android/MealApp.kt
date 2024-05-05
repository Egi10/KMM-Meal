package id.buaja.kmm_meal.android

import android.app.Application
import id.buaja.kmm_meal.base.CoreApplication
import org.koin.android.ext.koin.*
import org.koin.core.logger.Level

class MealApp: Application() {
    override fun onCreate() {
        super.onCreate()

        CoreApplication.initialize {
            androidLogger(Level.NONE)
            androidContext(this@MealApp)
        }
    }
}