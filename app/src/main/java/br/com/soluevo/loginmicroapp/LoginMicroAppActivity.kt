package br.com.soluevo.loginmicroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.soluevo.loginmicroapplibrary.application.dto.AuthResponseDto
import br.com.soluevo.loginmicroapplibrary.application.modules.login.LoginActivity
import br.com.soluevo.loginmicroapplibrary.application.modules.login.LoginCallbackHandler

class LoginMicroAppActivity : AppCompatActivity(), LoginCallbackHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_micro_app)

        LoginActivity.loginCallbackHandler = this
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun onSuccess(authResponseDto: AuthResponseDto) {

    }

    override fun onError(errorDescription: String) {

    }

}
