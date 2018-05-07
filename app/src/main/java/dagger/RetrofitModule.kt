package dagger

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import util.Constants
import javax.inject.Singleton

@Module
class RetrofitModule {


    @Provides
    @Singleton
    fun providesRetrofitForParkingService():Retrofit{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL_OPEN_DATA)
                .build()
    }


}