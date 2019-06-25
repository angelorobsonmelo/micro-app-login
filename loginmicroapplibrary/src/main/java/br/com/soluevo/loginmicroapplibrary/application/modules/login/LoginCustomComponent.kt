package br.com.soluevo.loginmicroapplibrary.application.modules.login

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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

class LoginCustomComponent(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs), LoginHandler {

    var loginCallbackHandler: LoginCallbackHandler? = null

    private var viewModel: LoginViewModel? = null
    private lateinit var binding: ActivityLoginBinding
    private lateinit var attrs: AttributeSet

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var validator: Validator

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.activity_login, this, true
        )

        binding.authRequest = AuthRequestDto()
        binding.handler = this

        validator = Validator(binding)
        validator.enableFormValidationMode()


        this.attrs = attrs
        setupDagger()
    }

    private fun setupDagger() {
        DaggerLoginComponent.builder()
            .contextModule(ContextModule(context))
            .build()
            .inject(this)
    }

    fun setUpViewModelFromActivitity(activity: AppCompatActivity) {
        binding.lifecycleOwner = activity
        viewModel = ViewModelProviders.of(activity, viewModelFactory)[LoginViewModel::class.java]

        viewModel?.errorObserver?.observe(activity, Observer {
            loginCallbackHandler?.onError(context.getString(it.toInt()))
            showAlert(it)
        })

        viewModel?.errorObserver?.observe(activity, Observer {
            loginCallbackHandler?.onError(context.getString(it.toInt()))
            showAlert(it)
        })

    }

    fun setUpViewModelFromFragment(fragment: Fragment) {
        binding.lifecycleOwner = fragment
        viewModel = ViewModelProviders.of(fragment, viewModelFactory)[LoginViewModel::class.java]

        viewModel = ViewModelProviders.of(fragment, viewModelFactory)[LoginViewModel::class.java]

        viewModel?.errorObserver?.observe(fragment, Observer {
            loginCallbackHandler?.onError(context.getString(it.toInt()))
            showAlert(it)
        })

        viewModel?.errorObserver?.observe(fragment, Observer {
            loginCallbackHandler?.onError(context.getString(it.toInt()))
            showAlert(it)
        })
    }

    override fun onPressLoginButton(authRequest: AuthRequestDto) {
        if (validator.validate()) {
            viewModel?.authenticate(authRequest)
        }
    }

    fun clearDisposable() {
        viewModel?.disposables?.clear()
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(context)

        builder
            .setMessage(message.toInt())
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ -> }

        val alert = builder.create()
        alert.show()
    }

}