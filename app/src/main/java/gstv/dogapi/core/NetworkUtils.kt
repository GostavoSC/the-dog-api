package gstv.dogapi.core

import gstv.dogapi.service.DogService
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.thedogapi.com/v1/"
const val HEADER = "x-api-key"
const val API_KEY = "15637779-4734-476c-94b0-819e489b74ec"

class NetworkUtils {

    companion object {
        private fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                    OkHttpClient.Builder()
                        .authenticator(DogApiAuthenticator())
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiService: DogService = getRetrofitInstance().create(DogService::class.java)
    }
}

class DogApiAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return response.request().newBuilder()
            .header(HEADER, API_KEY)
            .build()
    }
}