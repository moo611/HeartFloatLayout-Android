package atech.com.heartfloat;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.widget.ImageView;

/**
 * 属性动画监听器的实现类
 */

public class BezierAnimatorListener implements ValueAnimator.AnimatorUpdateListener {


    private ImageView target;

    public BezierAnimatorListener(ImageView target){

        this.target = target;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {

        PointF pointF = (PointF) animation.getAnimatedValue();
        target.setX(pointF.x);
        target.setY(pointF.y);

        target.setAlpha(1-animation.getAnimatedFraction());

    }
}
