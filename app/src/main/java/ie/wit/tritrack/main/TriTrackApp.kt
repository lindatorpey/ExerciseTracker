package ie.wit.tritrack.main

import android.app.Application
import ie.wit.tritrack.models.LogMemStore
import ie.wit.tritrack.models.LogStore
import timber.log.Timber

class TriTrackApp : Application() {

    lateinit var logsStore: LogStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        logsStore = LogMemStore()
        Timber.i("TriTrack Application Started")
    }
}