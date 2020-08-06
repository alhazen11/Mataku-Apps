package ccom.apps.mataku.api

import com.apps.mataku.BuildConfig
import com.apps.mataku.api.DataDbApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by root on 11/15/17.
 */
@Module
class ApiModule{
    @Provides @Singleton @Named("baseUrl")
    fun provideBaseUrl(): String{
        return BuildConfig.BASE_URL
    }

    @Provides @Singleton
    fun provideDataDbApi(retrofit: Retrofit): DataDbApi {
        return retrofit.create(DataDbApi::class.java)
    }
}