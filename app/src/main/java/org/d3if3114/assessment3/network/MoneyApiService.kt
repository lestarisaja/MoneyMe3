package org.d3if3114.assessment3.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3114.assessment3.model.Money
import org.d3if3114.assessment3.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://lestarisaja.my.id/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface MoneyApiService {
    @GET("json.php")
    suspend fun getMoney(
        @Query("auth") userId: String
    ): List<Money>

    @Multipart
    @POST("json.php")
    suspend fun postMoney(
        @Part("auth") userId: String,
        @Part("tipe") tipe: RequestBody,
        @Part("tanggal") tanggal: RequestBody,
        @Part("deskripsi") deskripsi: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("json.php")
    suspend fun deleteMoney(
        @Query("auth") userId: String,
        @Query("id") id: String
    ): OpStatus
}
object MoneyApi {
    val service: MoneyApiService by lazy {
        retrofit.create(MoneyApiService::class.java)
    }
    fun getMoneyUrl(image: String): String {
        return "$BASE_URL$image"
    }
}
enum class ApiStatus { LOADING, SUCCESS, FAILED }