package com.key.magicbook.index

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.appbar.AppBarLayout
import com.key.keylibrary.base.BaseFragment
import com.key.keylibrary.base.ConstantValues
import com.key.keylibrary.base.ShowImageActivity
import com.key.keylibrary.bean.BusMessage
import com.key.keylibrary.utils.AppBarStateChangeListener
import com.key.keylibrary.utils.UiUtils
import com.key.magicbook.R
import com.key.magicbook.set.SetActivity
import com.key.magicbook.util.BitmapUtil
import com.key.magicbook.util.GlideUtils
import kotlinx.android.synthetic.main.fragment_index_mine.*

/**
 * created by key  on 2020/3/2
 */
class MineFragment :BaseFragment(){
    private var finalColor = 0
    private var appCompatActivity :AppCompatActivity ?= null
    var functions = arrayOf("阅读历史", "我的收藏","书单","我的评论",
        "阅读数据","关于我们", "相关法律","设置")
    var icons = arrayOf(R.mipmap.history,R.mipmap.collection,R.mipmap.book_list,R.mipmap.chat,
        R.mipmap.data,R.mipmap.us,R.mipmap.law,R.mipmap.setting)
    override fun setLayoutId(): Int {
        return R.layout.fragment_index_mine
    }



    override fun initView() {
        initFunction();
        setTitle(toolbar)
        appCompatActivity = activity as AppCompatActivity
        appCompatActivity!!.setSupportActionBar(toolbar)
        setIconBackground("")
        icon.setOnClickListener {
            val busMessage = BusMessage<Bitmap>()
            busMessage.tag = 0
            startActivity(Intent(activity,ShowImageActivity::class.java))
            activity!!.overridePendingTransition(0, 0)
            busMessage.target = ShowImageActivity::class.java.simpleName
            busMessage.data = icon.drawable.toBitmap()
            val mainActivity = activity as IndexActivity
            mainActivity.sendBusMessage(busMessage)
        }
        app_bar.addOnOffsetChangedListener(
            object : AppBarStateChangeListener(){
                override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                     when(state){
                         State.EXPANDED->{
                            toolbar_right_icon.visibility = View.INVISIBLE

                         }
                         State.COLLAPSED->{
                             val colorRevert = ColorUtils.blendARGB(
                                 finalColor,
                                 resources.getColor(R.color.dart_gray_color),
                                 0.6f
                             )
                             collapsing_toolbar.contentScrim = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                                 intArrayOf(colorRevert,colorRevert))
                             toolbar_right_icon.visibility = View.VISIBLE
                         }
                     }
                }
            }
        )
    }


    private fun initFunction(){
        val inflater = LayoutInflater.from(activity)
        for(value in functions){
            inflater.inflate(R.layout.merge_mine_fragment_function, fun_root, true)
        }

        for(value in 0 until fun_root.childCount){
             fun_root.getChildAt(value).findViewById<TextView>(R.id.name).text = functions[value]

             Glide.with(activity!!).
                 load(activity!!.getDrawable(icons[value])).
                 into(fun_root.getChildAt(value).findViewById<ImageView>(R.id.icon))


            when(value){
                7->{
                    fun_root.getChildAt(value).setOnClickListener {
                        startActivity( Intent(activity,SetActivity::class.java))
                        activity!!.overridePendingTransition(0,0)
                    }

                }
            }

        }

    }
    @Suppress("DEPRECATION")
    private fun setIconBackground(url :String){
        if(url.isNotEmpty()){
            GlideUtils.load(activity,url,icon)
        }
        var decodeResource = BitmapFactory.decodeResource(activity!!.resources, R.mipmap.test)
        if(url.isNotEmpty()){
            if(!url.contains("http")){
                decodeResource = BitmapUtil.GetLocalOrNetBitmap(url)
                executeBitmap(decodeResource)
            }else{
                Glide.with(activity!!).asBitmap().load(url).into(object :SimpleTarget<Bitmap>(){
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        executeBitmap(resource)
                    }

                })
            }
        }else{
            executeBitmap(decodeResource)
        }

    }

    fun executeBitmap(bitmap: Bitmap){
        val picturePixel = BitmapUtil.getPicturePixel(bitmap)
        var tempColor = 0
        var tempCount = 0
        var sameCount = 0
        for(value in picturePixel){
            if(value != tempColor){
                tempColor = value
                if(sameCount > tempCount){
                    tempCount = sameCount
                    finalColor = value
                }
                sameCount = 0
            }else{
                sameCount++;
            }
        }

        val ints = intArrayOf(
            finalColor,
            activity!!.resources.getColor(R.color.dart_gray_color)
        )
        val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, ints)
        icon_root.setBackgroundDrawable(gradientDrawable)
        val margin = UiUtils.getScreenHeight(activity) / 10
        val layoutParams = icon.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.width = (margin * 1.5).toInt()
        layoutParams.height = (margin * 1.5).toInt()
        layoutParams.topMargin = margin
        layoutParams.bottomMargin = margin
        icon.layoutParams = layoutParams
    }

    override fun receiveAllMessage(busMessage: BusMessage<Any>): Boolean {
        super.receiveAllMessage(busMessage)
        busMessage as BusMessage<String>
        if(busMessage.message == ConstantValues.TAKE_PHOTO){
            setIconBackground(busMessage.data)
        }
        return true
    }


    override fun onResume() {
        super.onResume()
        appCompatActivity!!.supportActionBar!!.title = "谢泽凯"
    }
}