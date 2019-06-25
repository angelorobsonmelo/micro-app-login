package br.com.soluevo.loginmicroapplibrary.application.modules.login

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.com.ilhasoft.support.validation.Validator
import br.com.soluevo.loginmicroapplibrary.R
import br.com.soluevo.loginmicroapplibrary.application.commom.di.ContextModule
import br.com.soluevo.loginmicroapplibrary.application.dto.AuthRequestDto
import br.com.soluevo.loginmicroapplibrary.application.modules.login.di.component.DaggerLoginComponent
import br.com.soluevo.loginmicroapplibrary.databinding.ActivityLoginBinding
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginHandler {

    companion object {
        var loginCallbackHandler: LoginCallbackHandler? = null
    }

    private lateinit var binding: ActivityLoginBinding
    private lateinit var validator: Validator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.authRequest = AuthRequestDto()
        binding.handler = this

        validator = Validator(binding)
        validator.enableFormValidationMode()

        setupDagger()
        onObserverSuccess()
        onObserverError()

        binding.viewModel = viewModel
    }

    private fun setupDagger() {
        DaggerLoginComponent.builder()
            .contextModule(ContextModule(this))
            .build()
            .inject(this)
    }

    override fun onPressLoginButton(authRequest: AuthRequestDto) {
        if (validator.validate()) {
            viewModel.authenticate(authRequest)
        }
    }

    private fun onObserverSuccess() {
        viewModel.successObserver.observe(this, Observer {
            loginCallbackHandler?.onSuccess(it)
        })
    }

    private fun onObserverError() {
        viewModel.errorObserver.observe(this, Observer {
            loginCallbackHandler?.onError(getString(it.toInt()))
            showAlert(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposables.clear()
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)

        builder
            .setMessage(message.toInt())
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->  }

        val alert = builder.create()
        alert.show()
    }

}
