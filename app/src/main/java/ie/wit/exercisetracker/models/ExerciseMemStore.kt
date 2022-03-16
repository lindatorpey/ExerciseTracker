package ie.wit.exercisetracker.models

import timber.log.Timber

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ExerciseMemStore : ExerciseStore {

    val exercises = ArrayList<ExerciseModel>()

    override fun findAll(): List<ExerciseModel> {
        return exercises
    }

    override fun findById(id:Long) : ExerciseModel? {
        val foundExercise: ExerciseModel? = exercises.find { it.id == id }
        return foundExercise
    }

    override fun create(exercise: ExerciseModel) {
        exercise.id = getId()
        exercises.add(exercise)
        logAll()
    }

    fun logAll() {
        Timber.v("** Exercises List **")
        exercises.forEach { Timber.v("Exercise ${it}") }
    }
}