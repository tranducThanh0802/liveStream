package com.example.applivestream.di

import android.Manifest
import android.content.Context
import pub.devrel.easypermissions.EasyPermissions

object Resource {
    const val KEYLIVE="KEYLIVE"
    const val linkRTMP="rtmp://a.rtmp.youtube.com/live2/"
    const val KEYCHANNEL="KEYCHANNEL"
    const val Key="AIzaSyDZI2iQobEUlXauR9IUgExsIOOWDKBT_SY"
    const val  Request_CODE=0
     fun hasPermissions(context: Context)=
            EasyPermissions.hasPermissions(context, Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)


}