package com.turbosokol.iqmafiaapp.service

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

/***
 *If this code runs it was created by Evgenii Sokol.
 *If it doesn’t work, I don’t know who created it.
 ***/

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient() {
    config(this)
//    engine {
//        configureRequest {
//            setAllowsCellularAccess(true)
//        }
//        val builder = CertificatePinning.Builder().validateTrust(true).add("api-dev.cns.electrolux.com", "sha256/gZjiFEqv+866iFota1vhNAkgYrxp1lL5YV2LoiRFLOo=")
//                handleChallenge(builder.build())
//    }
}