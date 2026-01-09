package com.example.queueless.data.remote

import com.example.queueless.data.local.TokenDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AuthAuthenticator(
    private val tokenDataStore: TokenDataStore,
    private val onLogout: () -> Unit
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        // If already failed once, stop retrying
        if (responseCount(response) >= 1) {
            runBlocking {
                tokenDataStore.clear()
            }
            onLogout()
            return null
        }

        return null
    }

    private fun responseCount(response: Response): Int {
        var res = response
        var count = 1
        while (res.priorResponse != null) {
            count++
            res = res.priorResponse!!
        }
        return count
    }
}
