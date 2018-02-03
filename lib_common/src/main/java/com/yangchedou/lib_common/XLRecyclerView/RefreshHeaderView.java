package com.yangchedou.lib_common.XLRecyclerView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yangchedou.lib_common.R;

/**
 * Created by ADMIN on 2017/12/13.
 */

public class RefreshHeaderView extends View {

    private Paint paint;

    public RefreshHeaderView(Context context) {
        this(context,null);
    }

    public RefreshHeaderView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public RefreshHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.ClearnEditText);

        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.bottom_icon));
        paint.setAntiAlias(true);  //消除锯齿

        Path path = new Path();
        //path.arcTo(400, 200, 600, 400, -180, 225, false);
        //以下三行绘制金拱门
        //canvas.drawArc();
        //path.arcTo(230,-500,370,-100,-190,190,true);
        //path.arcTo(370,-500,510,-100,-180,190,false);
        RectF rectF1 = new RectF(750,600,850,800);
        path.addOval(rectF1, Path.Direction.CW);
        //canvas.drawOval(750,600,850,800,paint);//以上两行等同于此行  此行要求api>=21
        path.moveTo(0,0);
        path.lineTo(1000,0);
        path.moveTo(0,0);
        path.lineTo(-1000,0);
        path.moveTo(0,0);
        path.lineTo(0,1000);
        path.moveTo(0,0);
        path.lineTo(0,-1000);
        path.moveTo(0,0);
        path.cubicTo(-100,-100,-500,-290,300,300);
        path.lineTo(-100,-100);
        path.addCircle(300,300,200, Path.Direction.CCW);
        path.addArc(rectF1,90,180);
        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path,paint);

    }
}
