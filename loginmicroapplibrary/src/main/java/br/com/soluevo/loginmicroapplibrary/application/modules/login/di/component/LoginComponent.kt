package br.com.soluevo.loginmicroapplibrary.application.modules.login.di.component

import br.com.soluevo.loginmicroapplibrary.application.commom.di.ContextModule
import br.com.soluevo.loginmicroapplibrary.application.modules.login.LoginActivity
import br.com.soluevo.loginmicroapplibrary.application.modules.login.LoginCustomComponent
import br.com.soluevo.loginmicroapplibrary.application.modules.login.di.module.LoginModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LoginModule::class, ContextModule::class])
interface LoginComponent {

    fun inject(loginActivity: LoginActivity)
    fun inject(loginCustomComponent: LoginCustomComponent)
}