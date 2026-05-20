package fr.sdv.b32526.spacenasa_app.data

import android.net.http.HttpResponseCache.install
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object SpaceApiClient {

    // Initialisation du client réseau Ktor avec le moteur OkHttp
    val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchAstronauts(): AstrosResponse {
        return client.get("http://api.open-notify.org/astros.json").body()
    }
}