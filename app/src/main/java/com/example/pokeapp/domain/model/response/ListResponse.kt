package com.example.pokeapp.domain.model.response

import java.math.BigInteger

data class ListResponse<D>(
        val results: List<D>,
        val next: String,
        val previous: String,
        val count: BigInteger
    )