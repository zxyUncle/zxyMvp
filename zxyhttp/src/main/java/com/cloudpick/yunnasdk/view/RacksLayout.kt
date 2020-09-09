package com.cloudpick.yunnasdk.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.alexvasilkov.gestures.Settings
import com.alexvasilkov.gestures.views.GestureFrameLayout
import com.cloudpick.yunnasdk.R
import com.cloudpick.yunnasdk.entity.GatesBean
import com.cloudpick.yunnasdk.entity.LandBeanRequest
import com.cloudpick.yunnasdk.entity.RacksBean
import com.cloudpick.yunnasdk.tools.racksview.RacksView
import com.cloudpick.yunnasdk.utils.clickWithTrigger

/**
 * Created by zxy on 2020/8/6 15:32
 * ******************************************
 * *    RacksView+GestureFrameLayout
 * ******************************************
 */
class RacksLayout : LinearLayout {
    lateinit var mGestureFrameLayout: GestureFrameLayout
    lateinit var mRacksView: RacksView
    lateinit var ivAdd: ImageView
    lateinit var ivSub: ImageView

    constructor(mContext: Context?, attrs: AttributeSet?) : super(mContext, attrs) {
        init(mContext)
        initListener()
    }


    fun setData(bean: RacksBean, gatesBean: GatesBean) {
        mRacksView.updateCanvas(bean)
        mRacksView.setGatesBean(gatesBean)
    }

    private fun init(mContext: Context?) {
        val inflate = LayoutInflater.from(mContext).inflate(R.layout.include_racksview, null)
        this.addView(inflate)
        mGestureFrameLayout = inflate.findViewById(R.id.mGestureFrameLayout)
        mRacksView = inflate.findViewById(R.id.mRacksView)
        ivAdd = inflate.findViewById(R.id.ivAdd)
        ivSub = inflate.findViewById(R.id.ivSub)

        setMultiple()
    }

    private fun initListener() {
        ivAdd.clickWithTrigger {
            val controller = mGestureFrameLayout.controller
            val state = controller.state
            state.set(state.x / 2, state.y / 2, state.zoom + 0.05f, state.rotation)
            controller.animateStateTo(state)
            controller.updateState()
        }
        ivSub.clickWithTrigger {
            val controller = mGestureFrameLayout.getController()
            val state = controller.state
            state.set(state.x / 2, state.y / 2, state.zoom - 0.05f, state.rotation)
            controller.animateStateTo(state)
            controller.updateState()
        }

        mRacksView.setOnItemClickListener { view, rackId ->
            if (onItemClickListener != null) {
                onItemClickListener!!.onItemClick(view, rackId)
            }
        }
    }

    fun setMultiple(max: Float = 10f, min: Float = 0.5f) {
        mGestureFrameLayout.controller.settings
                .setBoundsType(Settings.Bounds.INSIDE)
                .setMaxZoom(max).minZoom = min
    }

    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View, rackId: String)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}