package com.christopher_elias.network.models.base

import com.squareup.moshi.Json

/**
 * This structure represent a List of items.
 * This structure can be used across multiple feature modules or any other modules.
 */
data class ResponseItems<T>(
    @field:Json(name = "results") val results: List<T>
)