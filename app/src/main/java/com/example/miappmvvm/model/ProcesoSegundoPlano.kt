package com.example.miappmvvm.model

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class ProcesoSegundoPlano(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        Thread.sleep(3000)
        return Result.success()
    }
}