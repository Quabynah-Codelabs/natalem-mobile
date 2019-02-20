package io.codelabs.natalem.util

import android.os.Handler
import io.codelabs.natalem.core.BaseActivity
import io.codelabs.natalem.model.SavedDate
import kotlinx.coroutines.launch

fun Any?.debugLog(msg: Any?) = println("Natalem ->  ${msg.toString()}")

fun BaseActivity.calculateDateOfBirth(year: Int, month: Int, day: Int, listener: OnDayRetrievedListener) {
    this.ioScope.launch {
        database.dao().saveDate(SavedDate(year, month, day))
        //todo: do computation here

        uiScope.launch {
            Handler().postDelayed({
                listener.onDayRetrieved("You have been hacked!")
            }, 2000)
        }

    }
}

interface OnDayRetrievedListener {

    fun onDayRetrieved(result: String)

}