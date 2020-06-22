package com.zxy.zxymvp.activity

import android.os.Bundle
import android.widget.Toast
import com.zxy.zxyhttp.entity.PersonTravelNodes
import com.zxy.zxymvp.R
import com.zxy.zxymvp.mvp.impl.BaseMvpActivity
import com.zxy.zxymvp.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by zxy on 2020/6/9 0009 17:59
 * ******************************************
 * * MVP入口
 * ******************************************
 */
class MainActivity : BaseMvpActivity<MainPresenter>() {
    override var layoutId: Int = R.layout.activity_main
    override fun initView(savedInstanceState: Bundle?) {
        //获取网路的点击事件
        tvMainGet.setOnClickListener {
            presenter.login()
        }
        //返回按钮点击事件
        llTitleLeft.setOnClickListener {
            Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show()
        }
        llTitleRightIv1.setOnClickListener {
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show()
        }
        llTitleRightTv.setOnClickListener {
            Toast.makeText(this, "保存", Toast.LENGTH_SHORT).show()
        }

    }
    /**
     * 获取到数据的绑定方法
     */
    fun bindContent(personTravelNodes: PersonTravelNodes) {
        tvContent.text = personTravelNodes.toString()
    }
}