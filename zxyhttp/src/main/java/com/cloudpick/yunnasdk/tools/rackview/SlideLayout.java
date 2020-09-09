package com.cloudpick.yunnasdk.tools.rackview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Scroller;

import com.cloudpick.yunnasdk.R;


public class SlideLayout extends FrameLayout {
 
    private View contentView;
    private View menuView;
 
    private int viewHeight; //高是相同的
    private int contentWidth;
    private int menuWidth;

    private boolean isScroll = false;

    private int screenWidth = getWidth();
 
    //滑动器
    private Scroller scroller;
 
    public SlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }
 
    /**
     * 布局文件加载完成时被调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = findViewById(R.id.content);
        menuView = findViewById(R.id.menu);
        Log.e("onFinishInflate","onFinishInflate");
    }
 
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
 
        viewHeight = getMeasuredHeight();
 
        contentWidth = contentView.getMeasuredWidth();
        menuWidth = menuView.getMeasuredWidth();
    }
 
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        menuView.layout(contentWidth, 0, contentWidth+menuWidth, viewHeight);
    }
 
 
    private float startX;
    private float startY;
 
    private float downX;
    private float downY;
 
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                downX = startX = event.getX();
                downY = startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(!isScroll)break;
                float endX = event.getX();
                float endY = event.getY();
 
                //计算偏移量
                float distanceX = endX - startX;
 
                int toScrollX = (int) (getScrollX()-distanceX);
                //屏蔽非法值
                if (toScrollX < 0 )
                {
                    toScrollX = 0;
                }
                if (toScrollX > menuWidth)
                {
                    toScrollX = menuWidth;
                }
                System.out.println("toScroll-->"+toScrollX+"-->"+getScrollX());
                scrollTo(toScrollX,getScrollY());
 
                startX = event.getX();
 
                float dx = Math.abs(event.getX()-downX);
                float dy = Math.abs(event.getY()-downY);
                if (dx > dy && dx > 6)
                {
                    //事件反拦截，使父ListView的事件传递到自身SlideLayout
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
 
                break;
            case MotionEvent.ACTION_UP:
                if(!isScroll)break;
                float distance = event.getX()-downX;

                if(distance>100){
                    closeMenu();
                }else if(distance>0){
                    openMenu();
                }
                else if(distance<-100){
                    openMenu();
                }else{
                    closeMenu();
                }
 
//                if (getScrollX() > menuWidth/2)
//                {
//                    //打开menu
//                    openMenu();
//                }else {
//                    closeMenu();
//                }
//
                break;
        }
        return true;
    }
 
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                downX = startX = event.getX();
                downY = startY = event.getY();
                if (onStateChangeListener != null)
                {
                    onStateChangeListener.onMove(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(!isScroll)break;
                float dx = Math.abs(event.getX()-downX);
                float dy = Math.abs(event.getY()-downY);
                if (dx > dy && dx > 6)
                {
                    //拦截事件
                    return true;
                }
 
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    public void openScroll(boolean isScroll){
        this.isScroll = isScroll;
    }
 
    /**
     * 打开menu菜单
     */
    public void openMenu() {
        int dx = menuWidth-getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(),dx, getScrollY());
        invalidate();
        if (onStateChangeListener != null)
        {
            onStateChangeListener.onOpen(this);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        //0表示menu移动到的目标距离
        int dx = 0-getScrollX()+20;
        scroller.startScroll(getScrollX(), getScrollY(),dx, getScrollY());
        invalidate();
        if (onStateChangeListener != null)
        {
            onStateChangeListener.onClose(this);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        scroller.startScroll(getScrollX(), getScrollY(),getScreenWidth(), getScrollY());
        invalidate();
    }

    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset())
        {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }
 
    public interface OnStateChangeListener
    {
        void onOpen(SlideLayout slideLayout);
        void onMove(SlideLayout slideLayout);
        void onClose(SlideLayout slideLayout);
    }
 
    public OnStateChangeListener onStateChangeListener;
 
    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }
}