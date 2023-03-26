package com.example.firebaseproject.fragment

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.firebaseproject.R
import com.example.firebaseproject.activity.HomeActivity
import com.example.firebaseproject.appRepository.MyApplication
import com.example.firebaseproject.databinding.FragmentExploreBinding


class ExploreFragment : Fragment() {
    lateinit var mBinding:FragmentExploreBinding
    lateinit var activity: HomeActivity
    lateinit var appCtx: MyApplication
    lateinit var mediaPlayer: MediaPlayer
    lateinit var runnable:Runnable
    lateinit var handler: Handler
    var pause:Boolean = false
    var firstAttempt:Boolean = true
    val myArrayList = ArrayList<String>()
    var songs: MutableList<String> = ArrayList()
     var nowPlaying=0
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as HomeActivity
        appCtx = activity.application as MyApplication
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_explore, container, false)
        handler= Handler()
        CallFun()
        return mBinding.root
    }

    private fun CallFun() {
        // Start the media player
       mBinding.llplay.setOnClickListener{
            if(pause){
                mBinding.pauseBtn.visibility=View.VISIBLE
                mBinding.llplay .visibility=View.GONE
                mediaPlayer.seekTo(mediaPlayer.currentPosition)
                mediaPlayer.start()
                pause = false
                Toast.makeText(activity,"media playing",Toast.LENGTH_SHORT).show()
            }else{
                mBinding.pauseBtn.visibility=View.VISIBLE
                mBinding.llplay .visibility=View.GONE
                mediaPlayer = MediaPlayer.create(activity.applicationContext,R.raw.file_example_audio)
                mediaPlayer.start()
                Toast.makeText(activity,"media playing",Toast.LENGTH_SHORT).show()

            }
           initializeSeekBar()
           mBinding.llplay.isEnabled = false
           mBinding.pauseBtn.isEnabled = true
           mBinding.stopBtn.isEnabled = true

           mediaPlayer.setOnCompletionListener {
               mBinding.llplay.isEnabled = true
              mBinding.pauseBtn.isEnabled = false
               mBinding.stopBtn.isEnabled = false
               Toast.makeText(activity,"end",Toast.LENGTH_SHORT).show()
           }
       }

        // Pause the media player
        mBinding.pauseBtn.setOnClickListener {
            if(mediaPlayer.isPlaying){
                mBinding.pauseBtn.visibility=View.GONE
                mBinding.llplay .visibility=View.VISIBLE
                mediaPlayer.pause()
                pause = true
                mBinding.llplay.isEnabled = true
                mBinding.pauseBtn.isEnabled = false
                mBinding.stopBtn.isEnabled = true
                Toast.makeText(activity,"media pause",Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.llNext.setOnClickListener{

            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mediaPlayer.setOnCompletionListener {
                try {
                    if (firstAttempt) {
                        firstAttempt = false
                    } else {
                        nextSong()
                        mediaPlayer.start()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            if(firstAttempt){
                firstAttempt = false;
            }else{
             //   nextSong();
                mediaPlayer.start()
                Toast.makeText(activity,"media Next",Toast.LENGTH_SHORT).show()
            }
        }

        // Stop the media player
        mBinding.stopBtn.setOnClickListener{
            if(mediaPlayer.isPlaying || pause.equals(true)){
                mBinding.pauseBtn.visibility=View.GONE
                mBinding.llplay .visibility=View.VISIBLE
                pause = false
                mBinding.seekBar.setProgress(0)
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()
                handler.removeCallbacks(runnable)
                mBinding.llplay.isEnabled = true
                mBinding.pauseBtn.isEnabled = false
                mBinding.stopBtn.isEnabled = false
                mBinding.tvPass.text = ""
                mBinding.tvDue.text = ""
                Toast.makeText(activity,"media stop",Toast.LENGTH_SHORT).show()
            }
        }
        // Seek bar change listener
        mBinding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (b) {
                    mediaPlayer.seekTo(i * 1000)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
    }

    private fun initializeSeekBar() {
        mBinding.seekBar.max = mediaPlayer.seconds

        runnable = Runnable {
            mBinding.seekBar.progress = mediaPlayer.currentSeconds

            mBinding.tvPass.text = "${mediaPlayer.currentSeconds} sec"
            val diff = mediaPlayer.seconds - mediaPlayer.currentSeconds
            mBinding.tvDue .text = "$diff sec"

            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    fun nextSong() {
        nowPlaying = nowPlaying + 1
        if (nowPlaying === songs.size) {
            nowPlaying = 0
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.start()
        }
    }

    // Creating an extension property to get the media player time duration in seconds
    val MediaPlayer.seconds:Int
        get() {
            return this.duration / 1000
        }
    // Creating an extension property to get media player current position in seconds
    val MediaPlayer.currentSeconds:Int
        get() {
            return this.currentPosition/1000
        }
}