package com.example.applivestream.di

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import pub.devrel.easypermissions.EasyPermissions

object Resource {
    const val KEYLIVE="KEYLIVE"
    const val linkRTMP="rtmp://a.rtmp.youtube.com/live2/"
    const val KEYCHANNEL="KEYCHANNEL"
    const val Key="AIzaSyDZI2iQobEUlXauR9IUgExsIOOWDKBT_SY"
    const val  Request_CODE=0
    private val permissions = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
     fun hasPermissions(context: Context)=
            EasyPermissions.hasPermissions(context, Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)


}