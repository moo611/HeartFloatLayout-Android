package atech.com.heartfloat;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;


/**
 * 飘心动画
 * created by desong 2019 11.09
 */

public class HeartFloatLayout extends RelativeLayout {

    float mWidth;  //父容器的宽度
    float mHeight; //父容器的高度
    float dWidth;  //心的宽度
    float dHeight; //心的高度
    Context c;
    int count = 0;
    Random random;
    int[]resources = new int[]{R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,R.drawable.h6,R.drawable.h7,R.drawable.h8};
    int duration1,duration2;
    boolean scaleable;
    TimeInterpolator timeInterpolator;

    public HeartFloatLayout(Context context) {
        super(context);

    }

    public HeartFloatLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context,attrs);

    }

    public HeartFloatLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context,attrs);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

       mWidth = w;
       mHeight = h;


    }

    private void init(Context c,AttributeSet attrs){

        this.c = c;

        random = new Random();

        TypedArray array=c.obtainStyledAttributes(attrs, R.styleable.heartfloat);
        dWidth=array.getDimension(R.styleable.heartfloat_dWidth,dip2px(c,20));
        dHeight=array.getDimension(R.styleable.heartfloat_dHeight,dip2px(c,20));
        scaleable = array.getBoolean(R.styleable.heartfloat_scaleable,true);
        duration1 = array.getInteger(R.styleable.heartfloat_alpha_duration,500);
        duration2 = array.getInteger(R.styleable.heartfloat_float_duration,3000);
        array.recycle();

    }


    public void launchHeart(){

            count++;

            ImageView imageView = new ImageView(getContext());
            imageView.setImageDrawable(ContextCompat.getDrawable(c,getDrawableNum()));
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int)dWidth,(int)dHeight);
            lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            addView(imageView,lp);

            if (scaleable){
                Animator set = getEnterAnimtor(imageView);
                set.start();
            }

            ValueAnimator floatAnim = getFloatAnimator(imageView);
            floatAnim.start();

            Log.v("aaaaa",count+"");

    }

    //缩放透明度动画
    private AnimatorSet getEnterAnimtor(final View target) {

        ObjectAnimator alpha = ObjectAnimator.ofFloat(target,View.ALPHA, 0.2f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target,View.SCALE_X, 0.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target,View.SCALE_Y, 0.2f, 1f);


        AnimatorSet enter = new AnimatorSet();
        enter.setDuration(duration1);
        enter.setInterpolator(new LinearInterpolator());
        enter.playTogether(alpha,scaleX, scaleY);
        enter.setTarget(target);
        return enter;

    }
  //漂浮动画
    private ValueAnimator getFloatAnimator(final View target){

       //这个点是按照imageview的左上顶点计算的，而不是imageview的中心点，因此起始点，终点还是控制点，我们要在中心点横坐标基础上减掉dWidth/2;
        float mControlX = getNum((int)(mWidth/2-dWidth-dWidth/2),(int)(mWidth/2+dWidth-dWidth/2));
        float mControlY = mHeight/2+dHeight/2;

        float mStartPointX = mWidth/2-dWidth/2;
        float mStartPointY = mHeight;

        Log.v("aaaaa",mStartPointX+"and"+mStartPointY);

        float mEndPointX = getNum((int)(mWidth/2-2*dWidth-dWidth/2),(int)(mWidth/2+2*dWidth-dWidth/2));
        float mEndPointY = 0;


        ValueAnimator valueAnimator = ValueAnimator.ofObject(new BezierEvaluator(new PointF(mControlX,mControlY)),new PointF(mStartPointX, mStartPointY),
                new PointF(mEndPointX, mEndPointY));
        valueAnimator.setTarget(target);
        valueAnimator.setDuration(duration2);
        valueAnimator.setInterpolator(timeInterpolator);
        valueAnimator.addUpdateListener(new BezierAnimatorListener((ImageView)target));
        //结束后删掉target
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {


            }

            @Override
            public void onAnimationEnd(Animator animation) {

                removeView(target);
                Log.v("bbbbb", "removeView后子view数:"+getChildCount());


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        return valueAnimator;

    }


    private float getNum(int startNum,int endNum){
        if(endNum > startNum){

            return (float)(random.nextInt(endNum - startNum) + startNum);
        }
        return 0;
    }


    private int getDrawableNum(){

        int rondomNum = random.nextInt(resources.length);
        return resources[rondomNum];

    }

    /**
     * 设置图片大小
     * @param width
     * @param height
     */

    public void setSize(float width,float height){

        this.dHeight = dip2px(c,height);
        this.dWidth = dip2px(c,width);

    }

    /**
     * 设置资源
     * @param resources
     */

    public void setResources(int[]resources){
        this.resources = resources;
    }
    /**
     是否需要缩放透明度
     */
    public void setScaleable(boolean scaleable){

        this.scaleable = scaleable;

    }
    /**
     * 缩放透明度动画时间
     */
    public void setScaleDuration(int duration1){

        this.duration1 = duration1;
    }
    /**
     * 漂浮动画时间
     */

    public void setFloatDuration(int duration2){

        this.duration2 = duration2;

    }

    /**
     * 设置飘动的加速，匀速，减速
     */

    public void setFloatSpeed(TimeInterpolator timeInterpolator){

        this.timeInterpolator = timeInterpolator;

    }

    /**
     * dp 转px
     * @param context
     * @param dpValue
     * @return
     */

    private float dip2px(Context context,float dpValue) {

        final float scale = context.getResources().getDisplayMetrics().density;

        return  dpValue * scale +0.5f;

    }


}
