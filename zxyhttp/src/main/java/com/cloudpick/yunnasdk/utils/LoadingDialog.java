package com.cloudpick.yunnasdk.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.cloudpick.yunnasdk.R;

/**
 * Created by ZXY on 2019/9/5 11:52.
 * Class functions
 * *********************************************************
 * * 加载中Dilaog
 * *********************************************************
 */
public class LoadingDialog implements DialogInterface.OnDismissListener {
    private Context mContext;
    private Dialog dialog;
    private String TAG = LoadingDialog.class.getSimpleName();

    public boolean isShowing() {
        if (dialog == null) {
            return false;
        }
        if (dialog != null && dialog.isShowing()) {
            return true;
        }
        return false;
    }

    public LoadingDialog(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 按照原始内容显示
     */
    public void show() {
        if (mContext == null || isShowing()) {
            return;
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.yn_dialog_loading, null);
        initView(view);
    }

    /**
     * 按照自定义内容显示
     */
    public void showMessage(String msg) {
        if (mContext == null || isShowing()) {
            return;
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.yn_dialog_loading, null);
        TextView tv_state = (TextView) view.findViewById(R.id.tv_state);
        tv_state.setText(msg);
        initView(view);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                if (dialog != null && mContext != null) {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void initView(View view) {
        dialog = new Dialog(mContext, R.style.loadingdialog);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        int windowWidth = ScreenUtils.dpToPxInt(mContext, 110);
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
        p.width = windowWidth; // 宽度设置为屏幕的0.65
        p.height = windowWidth;
        dialog.getWindow().setAttributes(p);
        dialog.setOnDismissListener(this);
        dialog.show();
        if (handler.hasMessages(0)) {
            handler.removeMessages(0);
        }
        handler.sendEmptyMessageDelayed(0, 12000);
    }

    public void setCancelable(boolean flog) {
        dialog.setCancelable(flog);
    }


    public void show(String msg) {
        dialogBuilder(msg, true);
    }


    public void show(String msg, boolean isCancelable) {
        dialogBuilder(msg, isCancelable);
    }

    private void dialogBuilder(String msg, boolean isCancelable) {
        if (mContext == null) {
            return;
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.yn_dialog_loading, null);
        dialog = new Dialog(mContext, R.style.loadingdialog);
        dialog.setContentView(view);
        dialog.setCancelable(isCancelable);
        dialog.setCanceledOnTouchOutside(true);


        TextView tv_state = (TextView) view.findViewById(R.id.tv_state);
        if (msg == null || "".equals(msg)) {
            tv_state.setVisibility(View.GONE);
        }
        if (tv_state != null) {
            tv_state.setText(msg == null ? "" : msg);
        }

        int windowWidth = ScreenUtils.dpToPxInt(mContext, 110);
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
        p.width = windowWidth; // 宽度设置为屏幕的0.65
        p.height = windowWidth;
        dialog.getWindow().setAttributes(p);
        dialog.setOnDismissListener(this);
        dialog.show();
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing() && mContext != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Log.e(TAG, "onDismiss");
    }
}
