package evo.company.appcomponents.di

import android.app.Application
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import evo.company.appcomponents.App
import evo.company.appcomponents.api.ApiTokenInterceptor
import evo.company.appcomponents.api.DeveloperApi
import evo.company.appcomponents.api.DeveloperApiClient
import evo.company.appcomponents.api.RepoApi
import evo.company.appcomponents.api.RepoApiClient
import evo.company.appcomponents.api.SearchApi
import evo.company.appcomponents.api.SearchApiClient
import evo.company.appcomponents.repository.DeveloperDetailRepository
import evo.company.appcomponents.repository.FeedRepository
import evo.company.appcomponents.repository.SearchDevelopersRepository
import evo.company.appcomponents.ui.devDetail.DeveloperDetailViewModel
import evo.company.appcomponents.ui.devs.DevelopersViewModel
import evo.company.appcomponents.ui.feed.FeedViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val mainModule = module {
    single { androidApplication() as App }
    single { get<Application>().resources }
    single {
        OkHttpClient.Builder()
            .connectTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(ApiTokenInterceptor())
            .build()
    }
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
    single { RepoApiClient(get()) as RepoApi }
    single { SearchApiClient(get()) as SearchApi }
    single { DeveloperApiClient(get()) as DeveloperApi }
    single { FeedRepository(get()) }
    single { SearchDevelopersRepository(get()) }
    single { DeveloperDetailRepository(get()) }
    viewModel { FeedViewModel(get()) }
    viewModel { DevelopersViewModel(get()) }
    viewModel { DeveloperDetailViewModel(get()) }
}