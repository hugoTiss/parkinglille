package dagger


import application.ParkingApplication
import com.parkinglille.android.parkinglille.ui.StartActivity
import dao.ParkingDataDAO
import network.RepositoryManager
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            RetrofitModule::class,
            ServiceModule::class,
            AppModule::class,
            RoomModule::class
        ]
)
interface ServiceComponent {

    fun inject(app: ParkingApplication)
    fun inject(startActivity: StartActivity)
    fun getRepositoryManager():RepositoryManager
//    fun getParkingDataDao():ParkingDataDAO

}