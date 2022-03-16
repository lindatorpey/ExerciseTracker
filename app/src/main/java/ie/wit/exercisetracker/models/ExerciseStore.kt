package ie.wit.exercisetracker.models

interface ExerciseStore {
    fun findAll() : List<ExerciseModel>
    fun findById(id: Long) : ExerciseModel?
    fun create(exercise: ExerciseModel)
}