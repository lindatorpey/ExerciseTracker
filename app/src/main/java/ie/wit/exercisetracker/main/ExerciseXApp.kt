package ie.wit.exercisetracker.main

import android.app.Application
import ie.wit.exercisetracker.models.ExerciseMemStore
import ie.wit.exercisetracker.models.ExerciseStore
import timber.log.Timber

class ExerciseXApp : Application() {

    lateinit var exerciseStore: ExerciseStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        exerciseStore = ExerciseMemStore()
        Timber.i("ExerciseX Application Started")
    }
}