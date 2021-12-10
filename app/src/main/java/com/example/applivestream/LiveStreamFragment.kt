package com.example.applivestream

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.applivestream.databinding.FragmentListLiveBinding
import com.example.applivestream.databinding.FragmentLiveStreamBinding
import com.pedro.encoder.input.video.CameraOpenException
import com.pedro.rtmp.utils.ConnectCheckerRtmp
import com.pedro.rtplibrary.rtmp.RtmpCamera1
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


private const val ARG_PARAM1 = "key"
private const val  RETRY_COUNT = 10
private const val URL_LIVE_STREAM = "rtmp://a.rtmp.youtube.com/live2/"

class LiveStreamFragment constructor() : Fragment(), ConnectCheckerRtmp, SurfaceHolder.Callback {
    lateinit var binding:FragmentLiveStreamBinding
    private var param1: String? = null
    private var time=0L
    private lateinit var rtmpCamera: RtmpCamera1
    private val permissions = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentLiveStreamBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!hasPermissions()) {
            ActivityCompat.requestPermissions(requireActivity(), permissions, 1)
        }
        rtmpCamera = RtmpCamera1(binding.surfaceView, this)
        rtmpCamera.setReTries(RETRY_COUNT)
        binding.surfaceView.holder.addCallback(this)
        binding.btnCall.setOnClickListener{
            btncall()
        }
        binding.btnMute.setOnClickListener{
            btnMute()
        }
        binding.btnSwitchCamera.setOnClickListener{
            btnCamera()
        }

    }

    private fun btnCamera() {
        try {
            rtmpCamera.switchCamera()
        } catch (e: CameraOpenException) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun btnMute() {
        if(rtmpCamera.isAudioMuted){
            binding.btnMute.setImageResource(R.drawable.btn_unmute)
            rtmpCamera.enableAudio()
        }else{
            binding.btnMute.setImageResource(R.drawable.btn_mute)
            rtmpCamera.disableAudio()
        }
    }

    private fun btncall(){
        if (rtmpCamera.isStreaming) {
           binding.iconLive.visibility=View.GONE
            binding.time.visibility=View.GONE
            binding.btnCall.setImageResource(R.drawable.btn_startcall)
            rtmpCamera.stopStream()
        } else {
            binding.iconLive.visibility=View.VISIBLE
            if (rtmpCamera.isRecording || rtmpCamera.prepareAudio() && rtmpCamera.prepareVideo()) {
                binding.btnCall.setImageResource(R.drawable.btn_endcall)
                binding.time.visibility=View.VISIBLE
                binding.iconLive.visibility=View.VISIBLE
                rtmpCamera.startStream(URL_LIVE_STREAM.plus(param1.toString().trim()))
            } else {
                Toast.makeText(
                    requireContext(), "Error preparing stream, This device cant do it",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun hasPermissions(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(requireContext(), permission)) {
                    return false
                }
            }
        }
        return true
    }

    override fun onAuthErrorRtmp() {
        requireActivity().runOnUiThread {
             Toast.makeText(requireContext(), "onAuthErrorRtmp", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAuthSuccessRtmp() {
        requireActivity().runOnUiThread {
            Toast.makeText(requireContext(), "onAuthSuccessRtmp", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onConnectionFailedRtmp(reason: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(requireContext(), "onConnectionSuccessRtmp", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onConnectionStartedRtmp(rtmpUrl: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(requireContext(), "onConnectionStartedRtmp", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onConnectionSuccessRtmp() {
        requireActivity().runOnUiThread {
            Toast.makeText(requireContext(), "onConnectionSuccessRtmp", Toast.LENGTH_SHORT).show()
        }
        GlobalScope.launch(Dispatchers.Default) {
            time= System.currentTimeMillis()
            while (time>0L){
                binding.time.text=getFormattedStopWatchTime(System.currentTimeMillis()-time)
                delay(1000)

            }
        }
    }

    override fun onDisconnectRtmp() {
        requireActivity().runOnUiThread {
            Toast.makeText(requireContext(), "onDisconnectRtmp", Toast.LENGTH_SHORT).show()
        }
        time= 0
        binding.time.text=""
    }

    override fun onNewBitrateRtmp(bitrate: Long) {

    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        Toast.makeText(requireContext(), "surfaceCreated", Toast.LENGTH_SHORT).show()

    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
      //  Toast.makeText(requireContext(), "surfaceChanged", Toast.LENGTH_SHORT).show()
        rtmpCamera.startPreview()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Toast.makeText(requireContext(), "surfaceDestroyed", Toast.LENGTH_SHORT).show()
    }
    fun getFormattedStopWatchTime(ms: Long, includeMillis: Boolean = false): String {
        var milliseconds = ms
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        milliseconds -= TimeUnit.HOURS.toMillis(hours)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
        if(!includeMillis) {
            return "${if(hours < 10) "0" else ""}$hours:" +
                    "${if(minutes < 10) "0" else ""}$minutes:" +
                    "${if(seconds < 10) "0" else ""}$seconds"
        }
        milliseconds -= TimeUnit.SECONDS.toMillis(seconds)
        milliseconds /= 10
        return "${if(hours < 10) "0" else ""}$hours:" +
                "${if(minutes < 10) "0" else ""}$minutes:" +
                "${if(seconds < 10) "0" else ""}$seconds:" +
                "${if(milliseconds < 10) "0" else ""}$milliseconds"
    }


}