package br.com.soluevo.loginmicroapplibrary.service.login

import br.com.soluevo.loginmicroapplibrary.application.dto.AuthRequestDto
import br.com.soluevo.loginmicroapplibrary.application.dto.AuthResponseDto
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiDataSource {

    @POST("auth/login")
    fun authenticate(@Body authRequestDto: AuthRequestDto): Single<AuthResponseDto>
}