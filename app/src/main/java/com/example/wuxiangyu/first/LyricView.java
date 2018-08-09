package com.example.wuxiangyu.first;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class LyricView extends View {
    TextPaint mPaintCurrent;
    TextPaint mPaintNext;
    TextPaint mPaintHide;
    TextPaint mPaintRefrence;

    private int refrenceLineTop = 600;

    private static final int textSize30 = 60;
    private static final int textSize20 = 60;
    private static float MAX_SCALE = 1.5f;
    private static float DEFALT_SCALE = 1.0f;
    /**
     * next和hide文字对应宽度占比
     */
    private static float CURRENT_WIDTH_PERCENT = 2.0f / 3;
    /**
     * curent和next之间的距离
     */
    private static float paddingBetweenCurrentToNext = 50;

    /**
     * 需要计算next文本的高度
     */
    private int mNextHeight = 0;
    /**
     * 当前动画进度
     */
    private float mPercent = 0;
    /**
     *next文字当前缩放比例
     */
    private float currentScale = DEFALT_SCALE;


    private String strCurrent = "1  1111";
    private String strNext = "2222222222222发大发的发生地粉丝发多少";
    private String strHide = "33吴向禹吴向禹吴33向禹吴向禹";
    public LyricView(Context context) {
        this(context, null);
    }

    public LyricView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LyricView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintRefrence = new TextPaint();
        mPaintRefrence.setColor(Color.WHITE);
        mPaintRefrence.setAntiAlias(true);
        mPaintRefrence.setStrokeWidth(10);

        mPaintCurrent = new TextPaint();
        mPaintCurrent.setColor(Color.WHITE);
        mPaintCurrent.setAntiAlias(true);
        mPaintCurrent.setTextSize(textSize30);

        mPaintNext = new TextPaint();
        mPaintNext.setColor(Color.WHITE);
        mPaintNext.setAntiAlias(true);
        mPaintNext.setTextSize(textSize20);


        mPaintHide = new TextPaint();
        mPaintHide.setColor(Color.WHITE);
        mPaintHide.setAntiAlias(true);
        mPaintHide.setTextSize(textSize20);
        mPaintHide.setAlpha(0);
    }

    public void startAnimation() {
        currentScale = DEFALT_SCALE;
        mPercent = 0;
        ValueAnimator animator = ValueAnimator.ofFloat(DEFALT_SCALE, MAX_SCALE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentScale = (float) animation.getAnimatedValue();
                mPercent = (currentScale - DEFALT_SCALE) / (MAX_SCALE - DEFALT_SCALE);
                Log.e("currentScale", "currentScale " + currentScale);
                invalidate();
                mPaintCurrent.setAlpha((int) (255 * (1 - mPercent)));
                mPaintHide.setAlpha((int) (255 * mPercent));
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //初始化
                mPaintCurrent.setAlpha(255);
                mPaintHide.setAlpha(0);
                currentScale = DEFALT_SCALE;
                mPercent = 0;
                String temp = strCurrent;
                strCurrent = strNext;
                strNext = strHide;
                strHide = temp;
                invalidate();
            }
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(400);
        animator.start();

    }

    /*
    current移动的距离：scale * nextheight
    next移动的距离：nextheight
    hide移动的距离：nextheight
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        refrenceLineTop = getHeight() / 2;
    }

    /**
     * 正常drawtext的参考点是左下角坐标（其实是左边，baseline，）
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(0, refrenceLineTop, getWidth(), refrenceLineTop, mPaintRefrence);
        StaticLayout currentStatic = new StaticLayout(strCurrent, mPaintCurrent, (int) (getWidth() * CURRENT_WIDTH_PERCENT), Layout.Alignment.ALIGN_NORMAL, 1.1f, 1.0f, false);
        canvas.save();
        canvas.translate(0, refrenceLineTop - currentStatic.getHeight() * MAX_SCALE - mPercent * mNextHeight);
        canvas.scale(MAX_SCALE, MAX_SCALE, 0, 0);
        currentStatic.draw(canvas);
        canvas.restore();


        StaticLayout nextStatic = new StaticLayout(strNext, mPaintNext, (int) (getWidth() * CURRENT_WIDTH_PERCENT), Layout.Alignment.ALIGN_NORMAL, 1.1f, 1.0f, false);
        canvas.save();
        mNextHeight = nextStatic.getHeight();
        canvas.translate(0, refrenceLineTop - mPercent * mNextHeight * MAX_SCALE + paddingBetweenCurrentToNext * (1 - mPercent));
        canvas.scale(currentScale, currentScale, 0, 0);
        nextStatic.draw(canvas);
        canvas.restore();

        StaticLayout hideStatic = new StaticLayout(strHide, mPaintHide, (int) (getWidth() * CURRENT_WIDTH_PERCENT), Layout.Alignment.ALIGN_NORMAL, 1.1f, 1.0f, false);
        canvas.save();
        canvas.translate(0, refrenceLineTop + mNextHeight * (1 - mPercent) + paddingBetweenCurrentToNext);
        hideStatic.draw(canvas);
        canvas.restore();
    }
}
