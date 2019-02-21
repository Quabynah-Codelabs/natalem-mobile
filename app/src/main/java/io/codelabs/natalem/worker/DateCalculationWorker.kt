package io.codelabs.natalem.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import io.codelabs.natalem.util.debugLog

/**
 * [Worker] implementation: Calculates the day of any week in the year
 */
class DateCalculationWorker(
    context: Context,
    private val params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
       debugLog(params.inputData.keyValueMap)
        return Result.success()
    }

}