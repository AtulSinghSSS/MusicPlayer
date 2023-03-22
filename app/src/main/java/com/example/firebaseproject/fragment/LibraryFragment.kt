package com.example.firebaseproject.fragment

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.firebaseproject.R
import com.example.firebaseproject.activity.HomeActivity
import com.example.firebaseproject.appRepository.MyApplication
import com.example.firebaseproject.databinding.FragmentLibraryBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class LibraryFragment : Fragment(),Runnable  {
    private lateinit var mBinding: FragmentLibraryBinding
    private lateinit var activity: HomeActivity
    private lateinit var appCtx: MyApplication
   private lateinit var mediaPlayer:MediaPlayer
   lateinit var seekBar: SeekBar
   var wasPlaying:Boolean = false
    lateinit var btnStart:Button



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
        mBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_library,
                container,
                false
            )
        initFun()
        return mBinding.root
    }

    private fun initFun() {
        btnStart=mBinding.btnStart
        seekBar=mBinding.seekBar
        mediaPlayer= MediaPlayer()
        btnStart.setOnClickListener(View.OnClickListener { playSong() })

      /*  mBinding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // here, you react to the value being set in seekBar
                Toast.makeText((activity),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
                Toast.makeText(activity,"seekbar touch started!", Toast.LENGTH_SHORT).show();
                var x = Math.ceil(1000.00)
                if (x < 0 && mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    clearMediaPlayer()
                  //  mBinding.btnStart.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_media_play));
                    mBinding.seekBar.setProgress(0);
                }
            }


            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
                Toast.makeText(activity,"seekbar touch stopped!", Toast.LENGTH_SHORT).show();
            }
        })*/

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // here, you react to the value being set in seekBar
                var x = Math.ceil(1000.00)

                if (x < 0 && mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    clearMediaPlayer()
                    //  mBinding.btnStart.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_media_play));
                    mBinding.seekBar.setProgress(0);
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                }
            }
        })
    }

    fun playSong() {
        try {
            if (mediaPlayer != null && mediaPlayer.isPlaying) {
                clearMediaPlayer()
                seekBar.setProgress(0)
                wasPlaying = true
               /* fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@MainActivity,
                        android.R.drawable.ic_media_play
                    )
                )*/
            }
            if (!wasPlaying) {
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer()
                }
               /* fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@MainActivity,
                        android.R.drawable.ic_media_pause
                    )
                )*/


                val descriptor: AssetFileDescriptor = activity.getAssets().openFd("file_example_audio.mp3")
                mediaPlayer.setDataSource(
                    descriptor.fileDescriptor,
                    descriptor.startOffset,
                    descriptor.length
                )
                descriptor.close()
                mediaPlayer.prepare()
                mediaPlayer.setVolume(0.5f, 0.5f)
                mediaPlayer.isLooping = false
                seekBar.setMax(mediaPlayer.duration)
                mediaPlayer.start()
                Thread(this).start()
            }
            wasPlaying = false
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun run() {
        /*var currentPosition = mediaPlayer.currentPosition
        var total = mediaPlayer.duration


        while (mediaPlayer != null && mediaPlayer.isPlaying && currentPosition < total) {
            currentPosition = try {
                Thread.sleep(100)
                mediaPlayer.currentPosition
            } catch (e: InterruptedException) {
                Log.e("TAG", "run111111: $e")
                return
            } catch (e: java.lang.Exception) {
                Log.e("TAG", "run2121212: $e")
                return
            }
            seekBar.progress = currentPosition
        }*/
    }

     override fun onDestroy() {
        super.onDestroy()
        clearMediaPlayer()
    }
    fun clearMediaPlayer() {
        mediaPlayer.stop()
        mediaPlayer.release()
      // mediaPlayer= null
    }
}
