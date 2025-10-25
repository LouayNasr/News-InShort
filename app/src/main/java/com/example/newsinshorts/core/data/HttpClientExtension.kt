package com.example.newsinshorts.core.data

import com.google.gson.JsonParseException
import kotlinx.coroutines.CancellationException
import retrofit2.Response
import java.net.SocketTimeoutException

// Generic Result Wrapper
sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val error: ApiError) : ApiResult<Nothing>()
}

// Enhanced Error Hierarchy
sealed class ApiError {
    data object NetworkError : ApiError()
    data object Timeout : ApiError()
    data object ServerError : ApiError()
    data object Unauthorized : ApiError()
    data object NotFound : ApiError()
    data object SerializationError : ApiError()
    data object TooManyRequests : ApiError()
    data class Unknown(val message: String?) : ApiError()
}

// Main Safe Call Wrapper
suspend inline fun <T> safeApiCall(
    crossinline apiCall: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = apiCall()
        handleResponse(response)
    } catch (e: Exception) {
        handleException(e)
    }
}

// Response Handler
inline fun <T> handleResponse(response: Response<T>): ApiResult<T> {
    return when {
        response.isSuccessful -> {
            try {
                val body = response.body()
                if (body != null) {
                    ApiResult.Success(body)
                } else {
                    ApiResult.Error(ApiError.SerializationError)
                }
            } catch (e: Exception) {
                ApiResult.Error(ApiError.SerializationError)
            }
        }
        else -> {
            when (response.code()) {
                401 -> ApiResult.Error(ApiError.Unauthorized)
                404 -> ApiResult.Error(ApiError.NotFound)
                408 -> ApiResult.Error(ApiError.Timeout)
                409 -> ApiResult.Error(ApiError.TooManyRequests)
                in 500..599 -> ApiResult.Error(ApiError.ServerError)
                else -> ApiResult.Error(ApiError.Unknown(response.message()))
            }
        }
    }
}

// Exception Handler
fun handleException(e: Exception): ApiResult.Error {
    return when (e) {
        is SocketTimeoutException -> ApiResult.Error(ApiError.Timeout)
        is JsonParseException -> ApiResult.Error(ApiError.SerializationError)
        is CancellationException -> throw e // Propagate cancellation
        else -> ApiResult.Error(ApiError.Unknown(e.message))
    }
}