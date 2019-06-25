package br.com.soluevo.loginmicroapplibrary.application.modules.login

import br.com.soluevo.loginmicroapplibrary.application.dto.AuthRequestDto

interface LoginHandler {

    fun onPressLoginButton(authRequest: AuthRequestDto)
}