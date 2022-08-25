package ie.wit.tritrack.models

import timber.log.Timber

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class LogMemStore : LogStore {

    val logs = ArrayList<LogModel>()

    override fun findAll(): List<LogModel> {
        return logs
    }

    override fun findById(id:Long) : LogModel? {
        val foundLog: LogModel? = logs.find { it.id == id }
        return foundLog
    }

    override fun create(log: LogModel) {
        log.id = getId()
        logs.add(log)
        logAll()
    }

    fun logAll() {
        Timber.v("** Donations List **")
        logs.forEach { Timber.v("Donate ${it}") }
    }
}