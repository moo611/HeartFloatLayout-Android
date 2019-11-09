package atech.com.heartfloat;

import android.animation.TypeEvaluator;
import android.graphics.PointF;


/**
 * 贝塞尔估值器
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {


    private PointF mFlagPoint;
   // private PointF mFlagPoint2;

    public BezierEvaluator(PointF flagPoint) {

        mFlagPoint = flagPoint;
       // mFlagPoint2 = flagPoint2;

    }

    //这里设置了2阶贝塞尔曲线
    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        return BezierUtil.CalculateBezierPointForQuadratic(fraction, startValue, mFlagPoint, endValue);
    }



}
