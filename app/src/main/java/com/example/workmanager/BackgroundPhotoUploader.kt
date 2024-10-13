package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class BackgroundPhotoUploader(context: Context, workerParams: WorkerParameters): Worker(context,
    workerParams) {

    companion object{
        const val KEY_WORKER = "key_worker"
    }

//    override fun doWork(): Result {
//        var inputData : Data = getInputData()
//        var number : Int = inputData.getInt("number", -1)
//        Log.d("BackgroundPhotoUploader", "doWork: number "+ number)
//        Log.d("WorkerManager", "Uploading Photos to Google Cloud Platform...")
//       return Result.success()
//    }

    override fun doWork(): Result {
        try {
            val count = inputData.getInt(MainActivity.KEY_COUNT_VALUE,0)
            for (i in 0 until count) {
                Log.i("WorkerManager", "Uploading $i")
            }

            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = time.format(Date())

            val outPutData = Data.Builder()
                .putString(KEY_WORKER,currentDate)
                .build()
            Log.d("WorkerManager", "Uploading Photos to Google Cloud Platform...")
            return Result.success(outPutData)
        } catch (e:Exception){
            return Result.failure()
        }
    }

}