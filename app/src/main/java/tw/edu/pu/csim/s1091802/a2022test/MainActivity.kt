package tw.edu.pu.csim.s1091802.a2022test

import android.content.pm.ActivityInfo
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import kotlinx.coroutines.*
import kotlinx.android.synthetic.main.activity_main.*
import tw.edu.pu.csim.s1091802.a2022test.databinding.ActivityMainBinding

@GlideModule public final class MyAppGlideModule : AppGlideModule()

class MainActivity : AppCompatActivity(){

    lateinit var binding: ActivityMainBinding

    var check:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var job: Job
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlideApp.with(this)
            .load(R.drawable.p1)
            .circleCrop()
            .override(800, 600)
            .into(img2)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.img.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                check = !check


                if(check==false){
                    img.setImageResource(R.drawable.start)

                }else{
                    img.setImageResource(R.drawable.stop)

                }


            }

        }
        )

        job = GlobalScope.launch(Dispatchers.Main) {
            while (true) {
                if(check) {
                    val canvas: Canvas = binding.mysv.holder.lockCanvas()
                    binding.mysv.drawSomething(canvas)
                    binding.mysv.holder.unlockCanvasAndPost(canvas)
                }
                delay(25)
            }
        }

        //設定螢幕水平顯示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

    }


}