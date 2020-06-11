package com.zxy.zxyhttp.tools

import com.kennyc.view.MultiStateView

/**
 * Created by zxy on 2020/4/7 0007 16:48
 * ******************************************
 * *
 * ******************************************
 */


fun MultiStateView.load() {
    this.viewState = MultiStateView.ViewState.LOADING
}

fun MultiStateView.content() {
    this.viewState = MultiStateView.ViewState.CONTENT
}

fun MultiStateView.empty() {
    this.viewState = MultiStateView.ViewState.EMPTY
}

fun MultiStateView.error() {
    this.viewState = MultiStateView.ViewState.ERROR
}
