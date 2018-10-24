package com.example.wuxiangyu.gank

import com.google.gson.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.lang.reflect.Type
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


object RetrofitManager {
    val baseUrl = "https://gank.io/"
    private var retrofit = buildRetrofit(baseUrl)
    private fun buildRetrofit(baseUrl: String): Retrofit {
        val gson = GsonBuilder().registerTypeAdapter(String.javaClass, StringConverter()).create()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    private fun createOkHttpClient(): OkHttpClient {
        try {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {

                }

                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }

            })
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            return OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory())
                .hostnameVerifier { hostname, session -> true }
                .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}
class StringConverter : JsonSerializer<String>, JsonDeserializer<String> {
    override fun serialize(
        src: String?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return if (src == null || src.equals("null") ) JsonPrimitive("") else JsonPrimitive(src.toString())
    }

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): String {
        return json.asJsonPrimitive.asString
    }

}

