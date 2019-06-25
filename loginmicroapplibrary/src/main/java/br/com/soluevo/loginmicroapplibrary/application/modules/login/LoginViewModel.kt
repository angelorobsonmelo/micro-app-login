package br.com.soluevo.loginmicroapplibrary.application.modules.login

import br.com.soluevo.loginmicroapplibrary.application.commom.BaseViewModel
import br.com.soluevo.loginmicroapplibrary.application.dto.AuthRequestDto
import br.com.soluevo.loginmicroapplibrary.application.dto.AuthResponseDto
import br.com.soluevo.loginmicroapplibrary.service.login.LoginApiDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginApiDataSource: LoginApiDataSource
) : BaseViewModel<AuthResponseDto>() {

    val disposables = CompositeDisposable()

    fun authenticate(authRequestDto: AuthRequestDto) {
        val disposable = loginApiDataSource.authenticate(authRequestDto)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoadingObserver.value = true }
            .doAfterTerminate { isLoadingObserver.value = false }
            .subscribe(
                {
                    successObserver.value = it
                },
                {
                    errorObserver.value = it.localizedMessage
                }
            )

        disposables.add(disposable)
    }
}