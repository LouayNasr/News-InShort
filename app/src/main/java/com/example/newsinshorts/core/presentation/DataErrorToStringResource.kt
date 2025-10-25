package com.example.newsinshorts.core.presentation

import com.example.newsinshorts.R
import com.example.newsinshorts.core.domain.DataError

// TODO: make sure if you need context or not as a parameter in the following function
fun DataError.toUiText(): UiText {
    val stringRes = when(this) {
        DataError.Remote.REQUEST_TIMEOUT -> R.string.error_request_timeout
        DataError.Remote.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        DataError.Remote.NO_INTERNET -> R.string.error_no_internet
        DataError.Remote.SERVER -> R.string.error_unknown
        DataError.Remote.SERIALIZATION -> R.string.error_serialization
        DataError.Remote.UNKNOWN -> R.string.error_unknown
    }

    return UiText.StringResourceId(stringRes)
}