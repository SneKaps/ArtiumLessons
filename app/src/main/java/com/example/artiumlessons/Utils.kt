package com.example.artiumlessons

import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder

private const val UTF_8 = "UTF-8"

fun <T> String?.deserializeToClass(
    clazz: Class<T>,
): T? {
    val decodedLessonJson = try {
        URLDecoder.decode(this ?: return null, UTF_8)
    } catch (_: Exception) {
        null
    }

    return try {
        Gson().fromJson(decodedLessonJson, clazz)
    } catch (e: Exception) {
        null
    }
}

fun Any?.serialize(): String? {
    val obj = this ?: return null

    val serializedString = Gson().toJson(obj)
    return URLEncoder.encode(serializedString, UTF_8)
}

inline fun <T> safeCall(action: () -> T) = try {
    Result.success(action())
} catch (e: Exception) {
    Result.failure(e)
}