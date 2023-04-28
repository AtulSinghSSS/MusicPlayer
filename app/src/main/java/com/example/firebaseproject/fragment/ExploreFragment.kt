package com.example.firebaseproject.fragment

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.firebaseproject.R
import com.example.firebaseproject.activity.HomeActivity
import com.example.firebaseproject.appRepository.MyApplication
import com.example.firebaseproject.databinding.FragmentExploreBinding


class ExploreFragment : Fragment() {
    lateinit var mBinding: FragmentExploreBinding
    lateinit var activity: HomeActivity
    lateinit var appCtx: MyApplication
    lateinit var mediaPlayer: MediaPlayer
    lateinit var runnable: Runnable
    lateinit var handler: Handler
    var pause: Boolean = false
    val myArrayList = ArrayList<Int>()
    val onlineMusicList = ArrayList<String>()
    var nowPlaying = 1
    lateinit var audioUrl:String


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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false)
        handler = Handler()
        CallFun()
        return mBinding.root
    }

    private fun CallFun() {
        // Start the media player
        myArrayList.add(R.raw.file_example_audio)
        myArrayList.add(R.raw.sample_music)
        myArrayList.add(R.raw.sample_song)
     //   mediaPlayer = MediaPlayer.create(activity.applicationContext, myArrayList[nowPlaying])
        mediaPlayer = MediaPlayer()
      //  audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        onlineMusicList.add("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3")
        onlineMusicList.add("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3")
      //  onlineMusicList.add("http://commondatastorage.googleapis.com/codeskulptor-assets/sounddogs/soundtrack.ogg")



        // on below line we are displaying a toast message as audio player.




        mBinding.llplay.setOnClickListener { play() }
        mBinding.pauseBtn.setOnClickListener { puse() }

        mBinding.llNext.setOnClickListener {
            play()
            Log.e("TAG", "onlineMusicList:${onlineMusicList.size} " )
            if (mediaPlayer.isPlaying) {
                if (onlineMusicList.size > 0) {
                    nowPlaying += 1
                    if (nowPlaying < onlineMusicList.size) {
                        playMusic()
                        //  Toast.makeText(activity, "media Next", Toast.LENGTH_SHORT).show()
                    } else {
                        nowPlaying = 0
                        playMusic()
                    }
                }
            }
        }

        mBinding.llBack.setOnClickListener {
            play()
            if (mediaPlayer.isPlaying) {
                if (onlineMusicList.size > 0) {
                    if (nowPlaying > 0) {
                        nowPlaying -= 1
                        if (nowPlaying < onlineMusicList.size) {
                            playMusic()
                            //   Toast.makeText(activity, "media Back", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        nowPlaying = 0
                        playMusic()
                    }

                }

            }
        }

        // Stop the media player
        mBinding.stopBtn.setOnClickListener {
            if (mediaPlayer.isPlaying || pause.equals(true)) {
                mBinding.pauseBtn.visibility = View.GONE
                mBinding.llplay.visibility = View.VISIBLE
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
                //  Toast.makeText(activity, "media stop", Toast.LENGTH_SHORT).show()
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
            mBinding.tvDue.text = "$diff sec"

            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    fun playMusic() {
       // mediaPlayer = MediaPlayer.create(activity.applicationContext, myArrayList[nowPlaying]
        Log.e("TAG", "playMusic: $nowPlaying", )
        try {
            mediaPlayer.setDataSource(onlineMusicList[nowPlaying])
            Log.e("TAG", "dsfksdf:${onlineMusicList[nowPlaying]} ", )
            mediaPlayer.prepare()
            mediaPlayer.seekTo(mediaPlayer.currentPosition)
            mediaPlayer.start()

        } catch (e: Exception) {
            // on below line we are handling our exception.
            e.printStackTrace()
        }


    }

    fun play() {
        if (pause) {
            mBinding.pauseBtn.visibility = View.VISIBLE
            mBinding.llplay.visibility = View.GONE
            mediaPlayer.seekTo(mediaPlayer.currentPosition)
            mediaPlayer.start()
            pause = false
            //  Toast.makeText(activity, "media playing", Toast.LENGTH_SHORT).show()
        } else {
            mBinding.pauseBtn.visibility = View.VISIBLE
            mBinding.llplay.visibility = View.GONE
            playMusic()
            //   Toast.makeText(activity, "media playing", Toast.LENGTH_SHORT).show()

        }
        initializeSeekBar()
        mBinding.llplay.isEnabled = false
        mBinding.pauseBtn.isEnabled = true
        mBinding.stopBtn.isEnabled = true

        mediaPlayer.setOnCompletionListener {
            mBinding.llplay.isEnabled = true
            mBinding.pauseBtn.isEnabled = false
            mBinding.stopBtn.isEnabled = false
            // Toast.makeText(activity, "end", Toast.LENGTH_SHORT).show()
        }
    }

    fun puse() {
        if (mediaPlayer.isPlaying) {
            mBinding.pauseBtn.visibility = View.GONE
            mBinding.llplay.visibility = View.VISIBLE
            mediaPlayer.pause()
            pause = true
            mBinding.llplay.isEnabled = true
            mBinding.pauseBtn.isEnabled = false
            mBinding.stopBtn.isEnabled = true
            //  Toast.makeText(activity, "media pause", Toast.LENGTH_SHORT).show()
        }
    }


    // Creating an extension property to get the media player time duration in seconds
    val MediaPlayer.seconds: Int
        get() {
            return this.duration / 1000
        }

    // Creating an extension property to get media player current position in seconds
    val MediaPlayer.currentSeconds: Int
        get() {
            return this.currentPosition / 1000
        }
}