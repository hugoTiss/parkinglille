package application

import android.app.Application
import dagger.AppModule
import dagger.DaggerServiceComponent
import dagger.ServiceComponent

class ParkingApplication:Application() {


    val serviceComponent:ServiceComponent by lazy {
        DaggerServiceComponent.builder().appModule(AppModule(this)).build()
    }
    override fun onCreate() {
        super.onCreate()
        serviceComponent.inject(this)

    }
}