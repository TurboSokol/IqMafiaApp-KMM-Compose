package com.turbosokol.iqmafiaapp.android

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.turbosokol.iqmafiaapp.android.utils.viewModelsModule
import com.turbosokol.iqmafiaapp.common.app.AppAction
import com.turbosokol.iqmafiaapp.common.app.AppPlatform
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.time.ExperimentalTime

@ExperimentalTime
open class IqMafia: Application(), Application.ActivityLifecycleCallbacks, KoinComponent {
    protected val viewModel: ReduxViewModel by inject()

    override fun onCreate() {
        super.onCreate()
        instance = this
        registerActivityLifecycleCallbacks(this)

        initKoin()
        initApp()
    }
    override fun onActivityCreated(p0: Activity, p1: Bundle?) {}

    override fun onActivityStarted(p0: Activity) {}

    override fun onActivityResumed(p0: Activity) {}

    override fun onActivityPaused(p0: Activity) {}

    override fun onActivityStopped(p0: Activity) {}

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}

    override fun onActivityDestroyed(p0: Activity) {}

    private fun initKoin() {
        com.turbosokol.iqmafiaapp.core.di.initKoin {
            androidContext(this@IqMafia)
            modules(viewModelsModule)
        }
    }

    fun initApp() {
        viewModel.execute(AppAction.SetPlatform(AppPlatform.ANDROID))
    }

    companion object {
        lateinit var instance: IqMafia
            private set
    }
}