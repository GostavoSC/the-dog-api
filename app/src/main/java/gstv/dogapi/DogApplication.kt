package gstv.dogapi

import android.app.Application
import android.content.Context
import gstv.dogapi.core.di.dogModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DogApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        startKoin {
            androidContext(this@DogApplication)
            modules(dogModule)
        }
    }
}