package org.yash10019coder.suspectdetectionxml

import android.app.Application
import timber.log.Timber

class SuspectDetectionApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
