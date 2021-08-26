package forza.home.assignment.misc.di

import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import com.squareup.moshi.Moshi
import forza.home.assignment.data.teams.network.TeamsApi
import forza.home.assignment.data.teams.network.TeamsNetworkDataSource
import forza.home.assignment.data.teams.network.TeamsNetworkDataSourceImpl

val networkModule = module {
    single { provideDefaultOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideLpApi(get()) }
    single { provideNetworkSource(get()) }
}

fun provideDefaultOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .followRedirects(true)
        .followSslRedirects(true)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder().build()
        return Retrofit.Builder()
            .baseUrl("https://s3-eu-west-1.amazonaws.com")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    fun provideLpApi(retrofit: Retrofit): TeamsApi {
        return retrofit.create(TeamsApi::class.java)
    }

    fun provideNetworkSource(api: TeamsApi): TeamsNetworkDataSource = TeamsNetworkDataSourceImpl(api)
