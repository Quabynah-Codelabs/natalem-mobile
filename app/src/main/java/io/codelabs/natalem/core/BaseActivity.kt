package io.codelabs.natalem.core

import androidx.appcompat.app.AppCompatActivity
import io.codelabs.natalem.viewmodel.NatalemDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * Custom Root class for all [AppCompatActivity] in this project
 */
abstract class BaseActivity : AppCompatActivity() {
    // Job dispatchers for Coroutines
    private val job = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + job)
    val uiScope = CoroutineScope(Dispatchers.Main + job)

    val database: NatalemDatabase by inject { parametersOf(application as NatalemApplication) }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}