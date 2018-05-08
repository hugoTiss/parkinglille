package application

import android.app.Application
import dagger.AppModule
import dagger.DaggerServiceComponent
import dagger.ServiceComponent

class ParkingApplication:Application() {


    companion object {
        lateinit var instance:Application
            private set
        val serviceComponent:ServiceComponent by lazy {
            DaggerServiceComponent.builder().build()
        }

    }


    override fun onCreate() {
        super.onCreate()
        instance=this
        serviceComponent.inject(this)

    }
}