package com.apps.mataku.api


import com.apps.mataku.BuildConfig.API_KEY
import com.apps.mataku.models.LogPterigium
import retrofit2.http.*
import rx.Observable

/**
 * Created by root on 11/15/17.
 */
interface DataDbApi {
    @GET("api/member/login")
    @Headers("X-API-KEY: "+API_KEY)
    fun getLogins(@QueryMap map : Map<String, String>): Observable<LoginResponse>
    //member
    @GET("api/member/getone")
    @Headers("X-API-KEY: "+API_KEY)
    fun getMember(@QueryMap map : Map<String, String>): Observable<MemberResponse>
    @PUT("api/member/update")
    @Headers("X-API-KEY: "+API_KEY)
    fun updateMember(@Body map : Map<String, String>): Observable<SuccesResponse>
    @DELETE("api/member/delete")
    @Headers("X-API-KEY: "+API_KEY)
    fun deleteMember(@Body map : Map<String, String>): Observable<SuccesResponse>
    @POST("api/member/add")
    @Headers("X-API-KEY: "+API_KEY)
    fun addMember(@Body map : Map<String, String>): Observable<SuccesResponse>
    @GET("api/member/dokter")
    @Headers("X-API-KEY: "+API_KEY)
    fun getDokter(@QueryMap map : Map<String, String>): Observable<MemberResponse>


    //Favorit
    @GET("api/favorit/getall")
    @Headers("X-API-KEY: "+API_KEY)
    fun getFavorit(@QueryMap map : Map<String, String>): Observable<FavoritResponse>
    @GET("api/favorit/getalldokter")
    @Headers("X-API-KEY: "+API_KEY)
    fun getFavoritDokter(@QueryMap map : Map<String, String>): Observable<FavoritResponse>
    @PUT("api/favorit/update")
    @Headers("X-API-KEY: "+API_KEY)
    fun updateFavorit(@Body map : Map<String, String>): Observable<SuccesResponse>
    @DELETE("api/favorit/delete")
    @Headers("X-API-KEY: "+API_KEY)
    fun deleteFavorit(@Body map : Map<String, String>): Observable<SuccesResponse>
    @POST("api/favorit/add")
    @Headers("X-API-KEY: "+API_KEY)
    fun addFavorit(@Body map : Map<String, String>): Observable<SuccesResponse>

    //Konsultasi
    @GET("api/konsultasi/getall")
    @Headers("X-API-KEY: "+API_KEY)
    fun getKonsultasi(@QueryMap map : Map<String, String>): Observable<KonsultasiResponse>
    @GET("api/konsultasi/getalldokter")
    @Headers("X-API-KEY: "+API_KEY)
    fun getKonsultasiDokter(@QueryMap map : Map<String, String>): Observable<KonsultasiResponse>
    @PUT("api/konsultasi/update")
    @Headers("X-API-KEY: "+API_KEY)
    fun updateKonsultasi(@Body map : Map<String, String>): Observable<SuccesResponse>
    @DELETE("api/konsultasi/delete")
    @Headers("X-API-KEY: "+API_KEY)
    fun deleteKonsultasi(@Body map : Map<String, String>): Observable<SuccesResponse>
    @POST("api/konsultasi/add")
    @Headers("X-API-KEY: "+API_KEY)
    fun addKonsultasi(@Body map : Map<String, String>): Observable<SuccesResponse>

    //LogButaWarna
    @GET("api/logbutawarna/getall")
    @Headers("X-API-KEY: "+API_KEY)
    fun getLogButaWarna(@QueryMap map : Map<String, String>): Observable<LogButaWarnaResponse>
    @PUT("api/logbutawarna/update")
    @Headers("X-API-KEY: "+API_KEY)
    fun updateLogButaWarna(@Body map : Map<String, String>): Observable<SuccesResponse>
    @DELETE("api/logbutawarna/delete")
    @Headers("X-API-KEY: "+API_KEY)
    fun deleteLogButaWarna(@Body map : Map<String, String>): Observable<SuccesResponse>
    @POST("api/logbutawarna/add")
    @Headers("X-API-KEY: "+API_KEY)
    fun addLogButaWarna(@Body map : Map<String, String>): Observable<SuccesResponse>

    //LogKatarak
    @GET("api/logkatarak/getall")
    @Headers("X-API-KEY: "+API_KEY)
    fun getLogKatarak(@QueryMap map : Map<String, String>): Observable<LogKatarakResponse>
    @PUT("api/logkatarak/update")
    @Headers("X-API-KEY: "+API_KEY)
    fun updateLogKatarak(@Body map : Map<String, String>): Observable<SuccesResponse>
    @DELETE("api/logkatarak/delete")
    @Headers("X-API-KEY: "+API_KEY)
    fun deleteLogKatarak(@Body map : Map<String, String>): Observable<SuccesResponse>
    @POST("api/logkatarak/add")
    @Headers("X-API-KEY: "+API_KEY)
    fun addLogKatarak(@Body map : Map<String, String>): Observable<SuccesResponse>

    //logminus
    @GET("api/logminus/getall")
    @Headers("X-API-KEY: "+API_KEY)
    fun getLogMinus(@QueryMap map : Map<String, String>): Observable<LogMinusResponse>
    @PUT("api/logminus/update")
    @Headers("X-API-KEY: "+API_KEY)
    fun updateLogMinus(@Body map : Map<String, String>): Observable<SuccesResponse>
    @DELETE("api/logminus/delete")
    @Headers("X-API-KEY: "+API_KEY)
    fun deleteLogMinus(@Body map : Map<String, String>): Observable<SuccesResponse>
    @POST("api/logminus/add")
    @Headers("X-API-KEY: "+API_KEY)
    fun addLogMinus(@Body map : Map<String, String>): Observable<SuccesResponse>

    //logpterigium
    @GET("api/logpterigium/getall")
    @Headers("X-API-KEY: "+API_KEY)
    fun getLogPterigium(@QueryMap map : Map<String, String>): Observable<LogPterigiumResponse>
    @PUT("api/logpterigium/update")
    @Headers("X-API-KEY: "+API_KEY)
    fun updateLogPterigium(@Body map : Map<String, String>): Observable<SuccesResponse>
    @DELETE("api/logpterigium/delete")
    @Headers("X-API-KEY: "+API_KEY)
    fun deleteLogPterigium(@Body map : Map<String, String>): Observable<SuccesResponse>
    @POST("api/logpterigium/add")
    @Headers("X-API-KEY: "+API_KEY)
    fun addLogPterigium(@Body map : Map<String, String>): Observable<SuccesResponse>

    //notifikasi
    @GET("api/notifikasi/getall")
    @Headers("X-API-KEY: "+API_KEY)
    fun getNotifikasi(@QueryMap map : Map<String, String>): Observable<NotifikasiResponse>
    @PUT("api/notifikasi/update")
    @Headers("X-API-KEY: "+API_KEY)
    fun updateNotifikasi(@Body map : Map<String, String>): Observable<SuccesResponse>
    @DELETE("api/notifikasi/delete")
    @Headers("X-API-KEY: "+API_KEY)
    fun deleteNotifikasi(@Body map : Map<String, String>): Observable<SuccesResponse>
    @POST("api/notifikasi/add")
    @Headers("X-API-KEY: "+API_KEY)
    fun addNotifikasi(@Body map : Map<String, String>): Observable<SuccesResponse>

    //review
    @GET("api/review/getall")
    @Headers("X-API-KEY: "+API_KEY)
    fun getReview(@QueryMap map : Map<String, String>): Observable<ReviewResponse>
    @GET("api/review/getalldokter")
    @Headers("X-API-KEY: "+API_KEY)
    fun getReviewDokter(@QueryMap map : Map<String, String>): Observable<ReviewResponse>
    @PUT("api/review/update")
    @Headers("X-API-KEY: "+API_KEY)
    fun updateReview(@Body map : Map<String, String>): Observable<SuccesResponse>
    @DELETE("api/review/delete")
    @Headers("X-API-KEY: "+API_KEY)
    fun deleteReview(@Body map : Map<String, String>): Observable<SuccesResponse>
    @POST("api/review/add")
    @Headers("X-API-KEY: "+API_KEY)
    fun addReview(@Body map : Map<String, String>): Observable<SuccesResponse>

    /*
    @GET("movie/now_playing")
    fun getNowPlayingMovie(@QueryMap map : Map<String, String>): Observable<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovie(@QueryMap map : Map<String, String>): Observable<MovieResponse>

    @GET("movie/popular")
    fun getPopularMovie(@QueryMap map : Map<String, String>): Observable<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: String, @QueryMap map: Map<String, String>): Observable<MovieDetail>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(@Path("movie_id") id: String): Observable<VideoResponse>
    */
}