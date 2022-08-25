package ie.wit.tritrack.models

interface LogStore {
    fun findAll(): List<LogModel>
    fun findById(id: Long) : LogModel?
    fun create (log: LogModel)
}