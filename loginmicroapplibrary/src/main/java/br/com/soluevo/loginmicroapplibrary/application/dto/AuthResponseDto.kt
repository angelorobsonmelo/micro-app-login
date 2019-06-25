package br.com.soluevo.loginmicroapplibrary.application.dto

import br.com.soluevo.loginmicroapplibrary.application.domain.User

data class AuthResponseDto(
    val token: String,
    val user: User
)