package io.codelabs.natalem.util

import android.os.Handler
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import io.codelabs.natalem.core.BaseActivity
import io.codelabs.natalem.model.SavedDate
import io.codelabs.natalem.worker.DateCalculationWorker
import kotlinx.coroutines.launch

fun Any?.debugLog(msg: Any?) = println("Natalem ->  ${msg.toString()}")

fun BaseActivity.calculateDateOfBirth(year: Int, month: Int, day: Int, listener: OnDayRetrievedListener) {
    this.ioScope.launch {
        database.dao().saveDate(SavedDate(year, month, day))
        val workRequest = OneTimeWorkRequestBuilder<DateCalculationWorker>().build()
        val operation = WorkManager.getInstance().enqueue(workRequest)

        uiScope.launch {
            Handler().postDelayed({
                operation.state.observe(this@calculateDateOfBirth, Observer {
                    debugLog(it.toString())
                    listener.onDayRetrieved(it.toString())
                })
            },3000)
        }

    }
}

interface OnDayRetrievedListener {

    fun onDayRetrieved(result: String)

}