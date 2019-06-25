package br.com.soluevo.loginmicroapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.soluevo.loginmicroapp.databinding.ActivityMainBinding
import br.com.soluevo.loginmicroapplibrary.application.dto.AuthResponseDto
import br.com.soluevo.loginmicroapplibrary.application.modules.login.LoginCallbackHandler
import br.com.soluevo.loginmicroapplibrary.application.modules.login.LoginCustomComponent

class MainComponentActivity : AppCompatActivity(),
    LoginCallbackHandler {

    private lateinit var binding: ActivityMainBinding
    private lateinit var loginComponentView: LoginCustomComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        loginComponentView = binding.loginComponent
        loginComponentView.loginCallbackHandler = this
        loginComponentView.setUpViewModelFromActivitity(this)
    }

    override fun onSuccess(authResponseDto: AuthResponseDto) {

    }

    override fun onError(errorDescription: String) {
    }

    override fun onDestroy() {
        super.onDestroy()
        loginComponentView.clearDisposable()
    }
}
