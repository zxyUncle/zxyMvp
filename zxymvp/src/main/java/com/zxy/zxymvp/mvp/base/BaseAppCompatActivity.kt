package com.zxy.zxymvp.mvp.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.trello.rxlifecycle2.components.support.RxFragmentActivity
import com.zxy.zxymultilingual.MultiLanguageUtil
import com.zxy.zxymvp.R
import kotlinx.android.synthetic.main.titlebar.*

/**
 * Created by zxy on 2020/6/9 0009 18:00
 * ******************************************
 * *
 * ******************************************
 */
open abstract class BaseAppCompatActivity : RxFragmentActivity() {
    abstract var layoutId: Int //初始化布局
    open protected fun initView(savedInstanceState: Bundle?) {}//初始化控件
    open protected fun initData() {}//初始化数据
    open protected fun initListener() {
    }//初始化数据

    val llTitleLeft: LinearLayout by lazy {
        //返回按钮
        findViewById<LinearLayout>(R.id.llTitleLeft)
    }
    val tvTitle: TextView by lazy {
        //标题栏
        findViewById<TextView>(R.id.tvTitle)
    }
    val llTitleRightIv1: LinearLayout by lazy {
        findViewById<LinearLayout>(R.id.llTitleRightIv1)
    }
    val llTitleRightIv2: LinearLayout by lazy {
        findViewById<LinearLayout>(R.id.llTitleRightIv2)
    }
    val llTitleRightTv: LinearLayout by lazy {
        findViewById<LinearLayout>(R.id.llTitleRightTv)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
//        EventBusUtil.register(this)
        initView(savedInstanceState)//初始化数据
        initListener()//初始化监听
        initData()//初始化数据
    }

    /**
     * 使用自己设置的语言--核心
     * @param newBase
     */
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase))
    }

    override fun onStart() {
        super.onStart()
        if (hasToolBar())
            tvTitle.isSelected = tvTitle.text.length > 9  //标题开启关闭走马灯
    }


    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        if (hasToolBar()) {
            super.setContentView(R.layout.titlebar)
            LayoutInflater.from(this).inflate(layoutResID, container, true)
            llTitleLeft.setOnClickListener {
                finish()
            }
        } else {
            super.setContentView(layoutResID)
        }
    }

    /**
     * 是否使用Base的toolbar,fase表示使用自己自定义，不使用自带的
     */
    protected open fun hasToolBar() = true

    override fun onDestroy() {
        super.onDestroy()
//        EventBusUtil.unregister(this)
    }
}