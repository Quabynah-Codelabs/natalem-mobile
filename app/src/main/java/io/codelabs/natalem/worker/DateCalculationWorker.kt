package io.codelabs.natalem.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * [Worker] implementation: Calculates the day of any week in the year
 */
class DateCalculationWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {

        return Result.success()
    }

}