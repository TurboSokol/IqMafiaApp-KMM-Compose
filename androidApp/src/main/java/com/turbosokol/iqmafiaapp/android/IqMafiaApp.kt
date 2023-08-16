package com.turbosokol.iqmafiaapp.android

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.turbosokol.iqmafiaapp.android.utils.viewModelsModule
import com.turbosokol.iqmafiaapp.di.uiModule
import initSharedKoin
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.InternalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import kotlin.time.ExperimentalTime

@OptIn(InternalSerializationApi::class)
@ExperimentalCoroutinesApi
@ExperimentalTime
class IqMafiaApp: Application(), Application.ActivityLifecycleCallbacks, KoinComponent {

    override fun onCreate() {
        super.onCreate()
        instance = this
        registerActivityLifecycleCallbacks(this)

        initKoin()
    }
    override fun onActivityCreated(p0: Activity, p1: Bundle?) {}

    override fun onActivityStarted(p0: Activity) {}

    override fun onActivityResumed(p0: Activity) {}

    override fun onActivityPaused(p0: Activity) {}

    override fun onActivityStopped(p0: Activity) {}

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}

    override fun onActivityDestroyed(p0: Activity) {}

    private fun initKoin() {
        initSharedKoin {
            androidContext(this@IqMafiaApp)
            modules(viewModelsModule)
            modules(uiModule)
        }
    }

    companion object {
        lateinit var instance: IqMafiaApp
            private set
    }
}