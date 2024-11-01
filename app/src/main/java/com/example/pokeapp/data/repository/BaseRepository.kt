package com.example.pokeapp.data.repository

import com.example.pokeapp.domain.resource.Resource
import retrofit2.Response

abstract class BaseRepository {
    suspend fun <T> getResponse(
        request: suspend () -> Response<T>,
        defaultErrorMessage: String
    ): Resource<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Resource.Success(result.body()!!)
            } else {
                Resource.Failure(
                    false,
                    500,
                    defaultErrorMessage
                )
            }
        } catch (e: Throwable) {
            Resource.Failure(false, 100, defaultErrorMessage)
        }
    }
}