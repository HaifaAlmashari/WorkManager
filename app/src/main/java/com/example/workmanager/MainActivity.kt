package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val KEY_COUNT_VALUE = "key_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workManager = WorkManager.getInstance(applicationContext)

        val data: Data = Data.Builder()
            .putInt(KEY_COUNT_VALUE,125)
            .build()
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val uploadRequest = OneTimeWorkRequest.Builder(BackgroundPhotoUploader::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()
        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observe(this, Observer {
                if(it.state.isFinished){
                    val data = it.outputData
                    val message = data.getString(BackgroundPhotoUploader.KEY_WORKER)
                    Log.d("WorkerManager", "Status change to: ${it.state.isFinished}")
                    Log.d("WorkerManager", "Message: $message")
//                    Toast.makeText(applicationContext,message, Toast.LENGTH_LONG).show()
                }
            })

//        val task = OneTimeWorkRequest.Builder(BackgroundPhotoUploader::class.java).build()
//        val workManager = WorkManager.getInstance()
//        workManager.enqueue(task)
//
//        workManager.getWorkInfoByIdLiveData(task.id)
//            .observe(this, Observer {
//                if (it != null) {
//                    Log.d("WorkerManager", "Status change to: ${it.state.isFinished}")
//                }
//            })


    }
}


