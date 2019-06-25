package br.com.soluevo.loginmicroapplibrary.application.modules.login

import br.com.soluevo.loginmicroapplibrary.application.dto.AuthResponseDto

interface LoginCallbackHandler {

    fun onSuccess(authResponseDto: AuthResponseDto)
    fun onError(errorDescription: String)

}