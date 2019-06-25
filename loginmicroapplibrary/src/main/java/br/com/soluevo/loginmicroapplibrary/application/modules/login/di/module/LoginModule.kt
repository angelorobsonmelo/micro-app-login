package br.com.soluevo.loginmicroapplibrary.application.modules.login.di.module

import br.com.soluevo.loginmicroapplibrary.application.commom.di.NetWorkModule
import br.com.soluevo.loginmicroapplibrary.service.login.LoginApiDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetWorkModule::class, LoginViewModelModule::class])
class LoginModule {

    @Provides
    @Singleton
    fun provideLoginApiDataSource(retrofit: Retrofit): LoginApiDataSource =
        retrofit.create(LoginApiDataSource::class.java)
}