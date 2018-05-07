package dagger

import com.parkinglille.android.parkinglille.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import util.Constants
import javax.inject.Singleton

@Module
class RetrofitModule {



    @Provides
    fun providesLogginInterceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor):OkHttpClient.Builder{
        var okHttpClient:OkHttpClient.Builder=OkHttpClient.Builder()
        okHttpClient.addInterceptor(loggingInterceptor)
        return okHttpClient
    }

    @Provides
    @Singleton
    fun providesRetrofitForParkingService(okHttpClient: OkHttpClient.Builder):Retrofit{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .baseUrl(Constants.BASE_URL_OPEN_DATA)
                .build()
    }


}