package br.com.soluevo.loginmicroapplibrary.application.dto

data class AuthRequestDto(
    var username: String,
    var password: String
) {
    constructor(): this ("", "")
}