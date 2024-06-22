package com.zzekak.domain.user

import java.time.Instant

data class Agreement(
    val marketingConsent: Instant?,
    val locationConsent: Instant?,
    val pushNotificationConsent: Instant?
) {
    companion object {
        val EMPTY = Agreement(null, null, null)
    }
}
