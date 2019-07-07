package evo.company.appcomponents.api

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to add API token to a Authorization header.
 */
class ApiTokenInterceptor : Interceptor {

    companion object {

        private const val AUTHORIZATION_HEADER = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        //API_KEY will be `null` if nothing is set in local.properties

        //builder.addHeader(AUTHORIZATION_HEADER, "token 297d51672d5c84bd8df01d2fdba507e58a027a55")

        return chain.proceed(builder.build())
    }
}
