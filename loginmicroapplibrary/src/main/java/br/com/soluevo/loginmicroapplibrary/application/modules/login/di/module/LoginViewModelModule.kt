package br.com.soluevo.loginmicroapplibrary.application.modules.login.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.soluevo.loginmicroapplibrary.application.commom.di.ViewModelFactory
import br.com.soluevo.loginmicroapplibrary.application.commom.di.ViewModelKey
import br.com.soluevo.loginmicroapplibrary.application.modules.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(loginViewModel: LoginViewModel): ViewModel

}