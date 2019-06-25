# Micro App Login
Login module

# Usage

In your build.gradle(Project: android).
```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}

```

Add in your build.gradle(Module: app) 

```
dependencies {
 
    // Login Micro app
    implementation 'com.github.angelorobsonmelo:micro-app-login:1.0.0'
}

```

# Usage login as micro APP


```Java

   // Implement LoginCallbackHandler "LoginCallbackHandler"
    class MainActivity : AppCompatActivity(), LoginCallbackHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_micro_app)

        LoginActivity.loginCallbackHandler = this // Instance LoginCallbackHandler
        startActivity(Intent(this, LoginActivity::class.java)) // Start micro app Activitiy
    }

   // Block when login is successful
    override fun onSuccess(authResponseDto: AuthResponseDto) {

    }

    // Block when login is error
    override fun onError(errorDescription: String) {

    }

}

```


# Usage login as component

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        tools:context=".MainActivity">

      <br.com.soluevo.loginmicroapplibrary.application.modules.login.LoginCustomComponent
                android:id="@+id/loginComponent"
                android:layout_width="match_parent"
                android:layout_height="match_parent"/>

</LinearLayout>



```Java

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.soluevo.loginmicroapp.databinding.ActivityMainBinding
import br.com.soluevo.loginmicroapplibrary.application.dto.AuthResponseDto
import br.com.soluevo.loginmicroapplibrary.application.modules.login.LoginCallbackHandler
import br.com.soluevo.loginmicroapplibrary.application.modules.login.LoginCustomComponent

// Implement LoginCallbackHandler "LoginCallbackHandler"
class MainComponentActivity : AppCompatActivity(),
    LoginCallbackHandler {

    private lateinit var binding: ActivityMainBinding
    private lateinit var loginComponentView: LoginCustomComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        loginComponentView = binding.loginComponent // Layout id reference
        loginComponentView.loginCallbackHandler = this // Instance loginCallbackHandler
        loginComponentView.setUpViewModelFromActivitity(this) // Set up from an activity. 
        // If using a fragment use the method: setUpViewModelFromFragment(this)
    }

  // Block when login is successful
    override fun onSuccess(authResponseDto: AuthResponseDto) {

    }

    // Block when login is error
    override fun onError(errorDescription: String) {

    }

   // clear disposable 
    override fun onDestroy() {
        super.onDestroy()
        loginComponentView.clearDisposable()
    }
}

```
