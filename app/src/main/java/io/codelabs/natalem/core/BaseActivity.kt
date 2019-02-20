package io.codelabs.natalem.core

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * Custom Root class for all [AppCompatActivity] in this project
 */
abstract class BaseActivity : AppCompatActivity() {
    // Job dispatchers for Coroutines
    private val job = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + job)
    val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}